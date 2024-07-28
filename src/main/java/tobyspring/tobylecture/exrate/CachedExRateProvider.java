package tobyspring.tobylecture.exrate;

import tobyspring.tobylecture.payment.ExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExchangeRateProvider {
    private final ExchangeRateProvider target;

    private BigDecimal cachedExchangeRate;
    private LocalDateTime cacheExpiryTime;

    public CachedExRateProvider(ExchangeRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) {
        if (cachedExchangeRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cachedExchangeRate = this.target.getExchangeRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);

            System.out.println("cache updated");
        }

        return cachedExchangeRate;
    }
}
