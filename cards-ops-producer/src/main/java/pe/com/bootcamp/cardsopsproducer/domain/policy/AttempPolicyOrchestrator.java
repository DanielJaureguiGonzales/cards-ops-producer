package pe.com.bootcamp.cardsopsproducer.domain.policy;

import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pe.com.bootcamp.cardsopsproducer.api.dto.CardReplacementRequestDto;
import pe.com.bootcamp.cardsopsproducer.domain.port.AttemptStateRepository;

@Component @Primary @RequiredArgsConstructor
public class AttempPolicyOrchestrator implements AttemptPolicy{

    private final FirstTimePolicy first;
    private final SecondTimePolicy second;
    private final AttemptStateRepository repo;

    @Override
    public Single<Integer> resolveAttempt(CardReplacementRequestDto dto) {
        final String id = dto.getRequestId();
        return repo.existsByRequestId(id)
                .flatMap(exists -> exists ?
                        second.resolveAttempt(dto) :
                        repo.saveFirstAttempt(id).flatMap(ok ->
                                first.resolveAttempt(dto)));
    }

    @Override
    public String name() {
        return "orchestrator";
    }
}
