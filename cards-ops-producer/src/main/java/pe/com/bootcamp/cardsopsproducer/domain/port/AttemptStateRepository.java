package pe.com.bootcamp.cardsopsproducer.domain.port;

import io.reactivex.rxjava3.core.Single;

import java.time.Duration;

public interface AttemptStateRepository {

    Single<Boolean> existsByRequestId(String requestId);
    Single<Boolean> saveFirstAttempt(String requestId);
    Single<Boolean> saveEventSnapshot(String requestId, String json, Duration ttl);

    // Conveniencia: TTL por defecto (4h)
    default Single<Boolean> saveEventSnapshot(String requestId, String json) {
        return saveEventSnapshot(requestId, json, Duration.ofHours(4));
    }


}
