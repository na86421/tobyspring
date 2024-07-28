package tobyspring.tobylecture.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tobyspring.tobylecture.exrate.ExchangeRateData;

import java.math.BigDecimal;

public class ErApiExchangeRateExtractor implements ExchangeRateExtractor{
    @Override
    public BigDecimal extract(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExchangeRateData data = mapper.readValue(response, ExchangeRateData.class);

        return data.rates().get("KRW");
    }
}
