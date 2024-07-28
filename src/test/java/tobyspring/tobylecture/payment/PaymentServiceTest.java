package tobyspring.tobylecture.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {
    Clock clock;

    @BeforeEach
    void setUp() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 충족한다.")
    void prepare() {
        // given
        PaymentService paymentService = new PaymentService(new ExchangeRateProviderStub(BigDecimal.valueOf(500)), clock);

        // when
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.TEN);

        // then
        // 환율 정보를 가져온다
        assertThat(payment.getExchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(500));

        // 원화 환산 금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(5_000));

        // 원화 환산 금액의 유효 시간 계산
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    @Test
    void validUntil() {
        PaymentService paymentService = new PaymentService(new ExchangeRateProviderStub(BigDecimal.valueOf(500)), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // valid until이 prepare() 30분 뒤로 설정되었는가?
        LocalDateTime now = LocalDateTime.now(clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }
}