package my.norxiva.micromys.channel.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.micromys.BankAcronym;
import my.norxiva.micromys.ChannelStatus;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.TransactionType;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * channel basic configuration
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Document
public class ChannelConfig {
    @Id
    private String id;

    @Version
    private Long version;

    @Indexed
    @NotNull
    private ChannelType type;
    private Integer priority = 5;

    private List<TransactionType> supportedTransactionTypes = new ArrayList<>();
    private List<BankAcronym> supportedBanks = new ArrayList<>();

    @NotBlank
    private String name;
    private String desc;

    @NotNull
    private ChannelStatus status;


}
