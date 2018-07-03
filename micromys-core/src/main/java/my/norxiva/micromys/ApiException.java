package my.norxiva.micromys;

import lombok.Getter;

public class ApiException extends RuntimeException {
    @Getter
    private String code;

    public ApiException(ApiCode apiCode) {
        super(apiCode.message());
        this.code = apiCode.code();
    }

}
