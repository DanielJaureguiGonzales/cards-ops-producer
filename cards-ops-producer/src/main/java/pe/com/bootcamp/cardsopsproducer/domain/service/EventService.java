package pe.com.bootcamp.cardsopsproducer.domain.service;

import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pe.com.bootcamp.cardsopsproducer.api.dto.CardReplacementRequestDto;
import pe.com.bootcamp.cardsopsproducer.domain.mapper.EventMapper;
import pe.com.bootcamp.cardsopsproducer.domain.policy.AttemptPolicy;
import pe.com.bootcamp.cardsopsproducer.domain.port.EventPublisher;

@Service
public class EventService {
    private final AttemptPolicy policy;

    private final EventMapper mapper;

    private final EventPublisher<Object> publisher;

    private final String topic;

    public EventService(AttemptPolicy policy, EventMapper mapper, EventPublisher<Object> publisher,
                        @Value("${kafka.topic}") String topicString ) {

        this.policy = policy;
        this.mapper = mapper;
        this.publisher = publisher;
        this.topic = topicString;
    }

    public Single<String> process(CardReplacementRequestDto dto) {

        return policy.resolveAttempt(dto)
                .map(attempt -> mapper.toEvent(dto, attempt))
                .flatMap(evt -> publisher.publish(topic, evt.getRequestId(), evt)
                        .map(sr -> evt.getEventId()));

    }

}
