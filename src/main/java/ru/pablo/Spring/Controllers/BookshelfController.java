package ru.pablo.Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.pablo.Services.BookshelfService;
import ru.pablo.Services.DTO.BookshelfDTO;
import ru.pablo.Services.DTO.ShelfDTO;

@RestController
@RequestMapping("/bookshelf")
public class BookshelfController {
    private BookshelfService bookshelfService;

    @Autowired
    public BookshelfController(BookshelfService service){
        this.bookshelfService = service;
    }

    @GetMapping("")
    public Mono<ResponseEntity<?>> handleGetUserBookShelves(@RequestHeader("userID") long userId){
        return Mono.just(
                ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookshelfService.getBookshelves(userId))
        );
    }

    @PostMapping("")
    public Mono<ResponseEntity<?>> handleCreateBookshelf(@RequestHeader("userID") long userId, @RequestBody BookshelfDTO bookshelfDTO){
        bookshelfService.createBookshelf(userId, bookshelfDTO);
        return Mono.just(
                ResponseEntity
                        .ok().body("")
        );
    }

    @DeleteMapping("")
    public Mono<ResponseEntity<?>> handleDeleteBookshelf(@RequestHeader("userId") long userId, @RequestBody BookshelfDTO bookshelfDTO){
        bookshelfService.deleteBookshelf(userId, bookshelfDTO.id());
        return Mono.just(
                ResponseEntity.ok("")
        );
    }

    @GetMapping("/{bookshelfId}")
    public Mono<ResponseEntity<?>> handleGetBookshelf(@RequestHeader("userId") long userId, @PathVariable("bookshelfId") long bookshelfId){
        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(bookshelfService.getBookshelf(userId, bookshelfId))
        );
    }

    @PostMapping("/{bookshelfId}")
    public Mono<ResponseEntity<?>> handleAddShelfToBookshelf(@RequestHeader("userId") long userId, @PathVariable("bookshelfId") long bookshelfId, @RequestBody ShelfDTO shelfDTO){
        bookshelfService.addShelfToBookshelf(userId, bookshelfId, shelfDTO);
        return Mono.just(
                ResponseEntity.ok("")
        );
    }

    @DeleteMapping("/{bookshelfId}")
    public Mono<ResponseEntity<?>> handleDeleteShelfFromBookshelf(@RequestHeader("userId") long userId, @PathVariable("bookshelfId") long bookshelfId, @RequestBody ShelfDTO bookshelfDTO){
        bookshelfService.deleteShelfFromBookshelf(userId, new BookshelfDTO(bookshelfId, null, null), bookshelfDTO);
        return Mono.just(
                ResponseEntity.ok("")
        );
    }
}
