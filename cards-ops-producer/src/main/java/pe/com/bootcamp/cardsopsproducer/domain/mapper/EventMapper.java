package pe.com.bootcamp.cardsopsproducer.domain.mapper;


import org.springframework.stereotype.Component;
import pe.com.bootcamp.cardsopsproducer.api.dto.CardReplacementRequestDto;
import pe.com.bootcamp.events.CardReplacementEvent;

import java.util.UUID;

@Component
public class EventMapper {

    public CardReplacementEvent toEvent(CardReplacementRequestDto dto, int attemptNumber){

        // Ajusta exactamente los nombres/tipos a tu AVSC
        return CardReplacementEvent.newBuilder()
                .setEventId(UUID.randomUUID().toString())
                .setRequestId(dto.getRequestId())
                .setCustomerId(dto.getCustomerId())
                .setCardPANMasked(dto.getCardPANMasked())
                .setReasonCode(dto.getReasonCode())
                .setPriority(dto.getPriority())
                .setBranchCode(dto.getBranchCode())
                .setDeliveryAddress(dto.getDeliveryAddress())
                .setRequestedAt(dto.getRequestedAt())  // ahora es Instant directo
                .setAttemptNumber(attemptNumber)
                .setCorrelationId(dto.getCorrelationId())
                .setStatus(dto.getStatus())
                .build();

    }

}



