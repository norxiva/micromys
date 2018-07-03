package my.norxiva.micromys.channel;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.channel.query.ChannelSetting;
import my.norxiva.micromys.channel.query.ChannelSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChannelSettingManager {

    private ChannelSettingRepository repository;

    @Autowired
    public ChannelSettingManager(ChannelSettingRepository repository) {
        this.repository = repository;
    }

    public ChannelSetting get(String id) {
        return repository.findOne(id);
    }

    public ChannelSetting save(ChannelSetting setting) {
        return repository.save(setting);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
