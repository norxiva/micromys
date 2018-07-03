package my.norxiva.micromys.merchant;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.merchant.query.MerchantSecret;
import my.norxiva.micromys.merchant.query.MerchantSecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MerchantSecretManager {

    private MerchantSecretRepository repository;

    @Autowired
    public MerchantSecretManager(MerchantSecretRepository repository) {
        this.repository = repository;
    }

    public MerchantSecret get(String no) {
        return repository.findOne(no);
    }

    public MerchantSecret save(MerchantSecret secret) {
        return repository.save(secret);
    }

    public void delete(String no) {
        repository.delete(no);
    }
}
