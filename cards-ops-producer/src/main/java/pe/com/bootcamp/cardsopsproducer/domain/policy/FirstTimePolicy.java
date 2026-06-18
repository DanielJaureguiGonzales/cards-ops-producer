package pe.com.bootcamp.cardsopsproducer.domain.policy;

import io.reactivex.rxjava3.core.Single;
import org.springframework.stereotype.Component;
import pe.com.bootcamp.cardsopsproducer.api.dto.CardReplacementRequestDto;

@Component
public class FirstTimePolicy implements AttemptPolicy{


    @Override
    public Single<Integer> resolveAttempt(CardReplacementRequestDto dto) {
        return Single.just(1);
    }

    @Override
    public String name() {
        return "firstTimePolicy";
    }
}
