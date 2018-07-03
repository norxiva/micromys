package my.norxiva.micromys.util;

import aj.org.objectweb.asm.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ParameterUtils {

    private static final String DOT = ".";
    private static final String CLAZZ_SUFFIX = ".class";

    public static String[] getParameterNamesByAsm5(Class<?> clazz,
                                                   final Method method) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes == null || parameterTypes.length == 0) {
            return null;
        }
        final Type[] types = new Type[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            types[i] = Type.getType(parameterTypes[i]);
        }
        final List<String> parameterNames = new ArrayList<>();

        String className = clazz.getName();
        int lastDotIndex = className.lastIndexOf(DOT);
        className = className.substring(lastDotIndex + 1) + CLAZZ_SUFFIX;
        try (InputStream is = clazz.getResourceAsStream(className)) {
            ClassReader classReader = new ClassReader(is);
            classReader.accept(new ClassVisitor(Opcodes.ASM5) {
                @Override
                public MethodVisitor visitMethod(int access, String name,
                                                 String desc, String signature, String[] exceptions) {
                    // handle specified method only
                    Type[] argumentTypes = Type.getArgumentTypes(desc);
                    if (!method.getName().equals(name)
                            || !Arrays.equals(argumentTypes, types)) {
                        return super.visitMethod(access, name, desc, signature,
                                exceptions);
                    }
                    return new MethodVisitor(Opcodes.ASM5) {

                        @Override
                        public void visitParameter(String s, int i) {
                            super.visitParameter(s, i);
                        }

                        @Override
                        public void visitLocalVariable(String name, String desc,
                                                       String signature, Label start,
                                                       Label end, int index) {
                            // first parameter of non-static method is 'this'
                            // limit parameter size, otherwise it will get another local variables in function body.
                            if ((Modifier.isStatic(method.getModifiers()) || index > 0)
                                    && parameterNames.size() < types.length ) {
                                parameterNames.add(name);
                            }
                        }
                    };
                }
            }, 0);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        return parameterNames.toArray(new String[]{});
    }

}
