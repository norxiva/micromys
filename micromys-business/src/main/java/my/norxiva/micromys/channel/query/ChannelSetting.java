package my.norxiva.micromys.channel.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.micromys.BankAcronym;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * channel setting for execute request to channel system.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Document
public class ChannelSetting {

    @Id
    private String channelId;

    @Version
    private Long version;

    @NotBlank
    private String baseUrl;
    private String returnUrl;
    private String callbackUrl;

    private String encoding;

    private String base64PrivateKey;
    private String base64PublicKey;
    private String asymmetricAlgorithm;
    private String symmetricAlgorithm;
    private String signatureAlgorithm;

    private Map<BankAcronym, String> bankAliasMap = new HashMap<>();

}
