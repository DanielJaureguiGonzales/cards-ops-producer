package pe.com.bootcamp.cardsopsproducer.domain.policy;

import io.reactivex.rxjava3.core.Single;
import pe.com.bootcamp.cardsopsproducer.api.dto.CardReplacementRequestDto;

public interface AttemptPolicy {

    Single<Integer> resolveAttempt(CardReplacementRequestDto dto);
    String name();

}
