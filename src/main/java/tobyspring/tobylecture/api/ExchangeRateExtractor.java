package tobyspring.tobylecture.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

public interface ExchangeRateExtractor {
    BigDecimal extract(String response) throws JsonProcessingException;
}
