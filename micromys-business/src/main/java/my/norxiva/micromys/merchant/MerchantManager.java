package my.norxiva.micromys.merchant;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.merchant.query.Merchant;
import my.norxiva.micromys.merchant.query.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MerchantManager {

    private MerchantRepository repository;

    @Autowired
    public MerchantManager(MerchantRepository repository) {
        this.repository = repository;
    }

    public Merchant get(String no) {
        return repository.findOne(no);
    }

    public Merchant save(Merchant merchant) {
        return repository.save(merchant);
    }

    public void delete(String no){
        repository.delete(no);
    }


}
