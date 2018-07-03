package my.norxiva.micromys.channel;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.channel.query.ChannelConfig;
import my.norxiva.micromys.channel.query.ChannelConfigRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ChannelConfigManager {

    private ChannelConfigRepository repository;

    public ChannelConfigManager(ChannelConfigRepository repository) {
        this.repository = repository;
    }

    public ChannelConfig get(String id) {
        return repository.findOne(id);
    }

    public ChannelConfig save(ChannelConfig channelConfig) {
        return repository.save(channelConfig);
    }

    public List<ChannelConfig> findAll() {
        return repository.findAll();
    }

    public void delete(String id) {
        repository.delete(id);
    }

}
