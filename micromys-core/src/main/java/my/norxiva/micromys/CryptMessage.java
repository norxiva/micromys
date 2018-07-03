package my.norxiva.micromys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CryptMessage {

    @NotBlank
    private String merchantNo;

    @NotBlank
    private String message;

    @NotBlank
    private String key;

    @NotBlank
    private String signature;
}
