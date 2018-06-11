package my.norxiva.micromys.endpoint;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionType;
import my.norxiva.micromys.order.api.CreateOrderCommand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateOrder() {
        CreateOrderCommand command = new CreateOrderCommand();
        command.setNo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMhhHHmmss")));
        command.setAmount(BigDecimal.valueOf(7.2));
        command.setChannelType(ChannelType.FUYOU);
        command.setTransactionCategory(TransactionCategory.DIRECT);
        command.setTransactionType(TransactionType.WITHHOLD);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/micromys/api/order", command, String.class);

        Assert.assertEquals(responseEntity.getBody(), "SUCCESS");
    }
}
