package ru.pablo.Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.pablo.Services.DTO.ShelfDTO;
import ru.pablo.Services.ShelfService;

@RestController
@RequestMapping("/shelf")
public class ShelfController {
    private ShelfService shelfService;

    @Autowired
    public ShelfController(ShelfService shelfService){
        this.shelfService = shelfService;
    }

    @GetMapping("/{shelfID}")
    public Mono<ResponseEntity<?>> getShelfBooks(@RequestHeader("userID") long userId, @PathVariable("shelfID") long shelfID){
        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(shelfService.getShelf(shelfID))
        );
    }
}
