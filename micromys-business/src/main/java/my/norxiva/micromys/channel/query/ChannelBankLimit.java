package my.norxiva.micromys.channel.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.micromys.BankAcronym;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionType;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * channel bank card limit configuration.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Document
@CompoundIndexes({
        @CompoundIndex(name = "channelBankLimitIdx",
                def = "{'transactionType': 1, 'transactionCategory': 1, 'bankAcronym': 1}")
})
public class ChannelBankLimit {

    @Id
    private String id;

    @Version
    private Long version;

    @Indexed
    @NotBlank
    private String channelId;

    @NotNull
    private ChannelType channelType;
    private TransactionType transactionType;
    private TransactionCategory transactionCategory;
    private BankAcronym bankAcronym;

    private BigDecimal maxLimit = BigDecimal.ZERO;
    private BigDecimal minLimit = BigDecimal.ZERO;

}
