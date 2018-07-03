package my.norxiva.micromys.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

@Slf4j
public class ParameterUtilsTest {

    public static class TestClass {

        void test(String name, String desc) {
            String local = "ss";
            String local2 = "ss";
            System.out.println(String.format("%s-%s-%s-%s", name, desc, local, local2));
        }
    }

    @Test
    public void testGetParameterNamesByAsm5() throws NoSuchMethodException {
        Method method = TestClass.class.getDeclaredMethod("test", String.class, String.class);
        String[] parameters = ParameterUtils.getParameterNamesByAsm5(TestClass.class, method);
        Assert.assertNotNull(parameters);
        Assert.assertEquals(parameters.length, 2);
        Assert.assertEquals(parameters[0], "name");
        Assert.assertEquals(parameters[1], "desc");
    }
}
