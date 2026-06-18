package pe.com.bootcamp.cardsopsproducer.infrastructure.decorator;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import pe.com.bootcamp.cardsopsproducer.domain.port.EventPublisher;

@Component("resilientPublisher") @Primary
@RequiredArgsConstructor
public class ResilientPublisher implements EventPublisher<Object> {

    private final @Qualifier("kafkaEventPublisher") EventPublisher<Object> delegate;

    @Override
    @CircuitBreaker(name = "kafkaPublisher")
    @Retry(name = "kafkaPublisher")
    public Single<SendResult<String, Object>> publish(String topic, String key, Object value) {
        return delegate.publish(topic, key, value);
    }
}
