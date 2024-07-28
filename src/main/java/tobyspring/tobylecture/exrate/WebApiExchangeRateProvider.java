package tobyspring.tobylecture.exrate;

import tobyspring.tobylecture.api.ApiTemplate;
import tobyspring.tobylecture.payment.ExchangeRateProvider;

import java.math.BigDecimal;

public class WebApiExchangeRateProvider implements ExchangeRateProvider {
    private final ApiTemplate apiTemplate;

    public WebApiExchangeRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExchangeRate(url);
    }
}
