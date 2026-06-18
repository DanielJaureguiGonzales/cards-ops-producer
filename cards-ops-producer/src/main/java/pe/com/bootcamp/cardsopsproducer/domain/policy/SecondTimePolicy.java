package pe.com.bootcamp.cardsopsproducer.domain.policy;

import io.reactivex.rxjava3.core.Single;
import org.springframework.stereotype.Component;
import pe.com.bootcamp.cardsopsproducer.api.dto.CardReplacementRequestDto;

@Component
public class SecondTimePolicy implements AttemptPolicy{

    @Override
    public Single<Integer> resolveAttempt(CardReplacementRequestDto dto) {
        return Single.just(2);
    }

    @Override
    public String name() {
        return "secondTimePolicy";
    }
}
