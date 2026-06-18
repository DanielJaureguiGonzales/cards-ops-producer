package pe.com.bootcamp.cardsopsproducer.api;

import io.reactivex.rxjava3.core.Single;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.bootcamp.cardsopsproducer.api.dto.CardReplacementRequestDto;
import pe.com.bootcamp.cardsopsproducer.domain.service.EventService;

@RestController
@RequestMapping("/api/card-replacements")
public class CardReplacementController {

    private final EventService eventService;

    public CardReplacementController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Single<ResponseEntity<String>> create(@Valid @RequestBody CardReplacementRequestDto dto){

        return eventService.process(dto)
                .map(id -> ResponseEntity.accepted().body(id));

    }

}
