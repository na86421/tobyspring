package tobyspring.tobylecture.order;

import java.math.BigDecimal;

public record OrderRequest(String no, BigDecimal total) {
}
