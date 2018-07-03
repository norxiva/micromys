package my.norxiva.micromys.channel;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.channel.query.ChannelClearing;
import my.norxiva.micromys.channel.query.ChannelClearingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChannelClearingManager {

    private ChannelClearingRepository repository;

    @Autowired
    public ChannelClearingManager(ChannelClearingRepository repository) {
        this.repository = repository;
    }

    public ChannelClearing save(ChannelClearing clearing) {
        return repository.save(clearing);
    }

    public ChannelClearing get(String id) {
        return repository.findOne(id);
    }

    public void delete(String id) {
        repository.delete(id);
    }

}
