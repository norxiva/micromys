package my.norxiva.micromys.merchant.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document
public class MerchantSecret {

    @Id
    private String merchantNo;

    @Version
    private Long version;
    private String base64PrivateKey;
    private String base64PublicKey;
    private String asymmetricAlgorithm;
    private String symmetricAlgorithm;
    private String signatureAlgorithm;

}
