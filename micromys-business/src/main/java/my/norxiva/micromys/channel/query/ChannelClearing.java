package my.norxiva.micromys.channel.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document
public class ChannelClearing {

    @Id
    private String channelId;

    @Version
    private Long version;
    private Integer clearingCircle = 1;
    private LocalTime reconciliationTime;

}
