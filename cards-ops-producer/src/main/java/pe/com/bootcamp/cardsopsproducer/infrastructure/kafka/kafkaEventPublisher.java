package pe.com.bootcamp.cardsopsproducer.infrastructure.kafka;

import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import pe.com.bootcamp.cardsopsproducer.domain.port.EventPublisher;

@Component("kafkaEventPublisher") @RequiredArgsConstructor
public class kafkaEventPublisher implements EventPublisher<Object> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public Single<SendResult<String, Object>> publish(String topic, String key, Object value) {
        return Single.create(emitter ->
                kafkaTemplate.send(topic, key, value).whenComplete((result, ex)->{
                    if (ex!=null) emitter.onError(ex);
                    else emitter.onSuccess(result);
                }));

    }
}
