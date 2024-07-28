package tobyspring.tobylecture.exrate;

import tobyspring.tobylecture.payment.ExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExchageRateProvider implements ExchangeRateProvider {
    @Override
    public BigDecimal getExchangeRate(String currency) {
        if (currency.equals("USD")) {
            return BigDecimal.valueOf(1000);
        }

        throw new IllegalArgumentException("지원되지 않는 통화입니다.");
    }
}
