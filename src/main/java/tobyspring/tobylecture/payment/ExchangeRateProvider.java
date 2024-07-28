package tobyspring.tobylecture.payment;

import java.math.BigDecimal;

public interface ExchangeRateProvider {
    BigDecimal getExchangeRate(String currency);
}
