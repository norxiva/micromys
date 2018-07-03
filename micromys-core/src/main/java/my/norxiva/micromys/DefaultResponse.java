package my.norxiva.micromys;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DefaultResponse {
    public static final String SUCCEED = "SUCCEED";
    public static final String FAILED = "FAILED";

    private String code;
    private String message;
}
