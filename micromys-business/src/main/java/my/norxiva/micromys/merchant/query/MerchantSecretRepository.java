package my.norxiva.micromys.merchant.query;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MerchantSecretRepository extends MongoRepository<MerchantSecret, String> {

}
