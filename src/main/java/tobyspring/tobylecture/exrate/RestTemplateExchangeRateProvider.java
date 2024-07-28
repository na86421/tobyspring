package tobyspring.tobylecture.exrate;

import org.springframework.web.client.RestTemplate;
import tobyspring.tobylecture.payment.ExchangeRateProvider;

import java.math.BigDecimal;

public class RestTemplateExchangeRateProvider implements ExchangeRateProvider {
    private final RestTemplate restTemplate;

    public RestTemplateExchangeRateProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return restTemplate.getForObject(url, ExchangeRateData.class).rates().get("KRW");
    }
}
