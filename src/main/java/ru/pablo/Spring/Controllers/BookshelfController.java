package ru.pablo.Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> handleGetUserBookShelves(@RequestHeader("userID") long userId){
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookshelfService.getBookshelves(userId));
    }

    @PostMapping("")
    public ResponseEntity<?> handleCreateBookshelf(@RequestHeader("userID") long userId, @RequestBody BookshelfDTO bookshelfDTO){
        bookshelfService.createBookshelf(userId, bookshelfDTO);
        return ResponseEntity
                        .ok().body("");
    }

    @DeleteMapping("")
    public ResponseEntity<?> handleDeleteBookshelf(@RequestHeader("userId") long userId, @RequestBody BookshelfDTO bookshelfDTO){
        bookshelfService.deleteBookshelf(userId, bookshelfDTO.id());
        return ResponseEntity.ok("");
    }

    @GetMapping("/{bookshelfId}")
    public ResponseEntity<?> handleGetBookshelf(@RequestHeader("userId") long userId, @PathVariable("bookshelfId") long bookshelfId){
        return ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(bookshelfService.getBookshelf(userId, bookshelfId));
    }

    @PostMapping("/{bookshelfId}")
    public ResponseEntity<?> handleAddShelfToBookshelf(@RequestHeader("userId") long userId, @PathVariable("bookshelfId") long bookshelfId, @RequestBody ShelfDTO shelfDTO){
        bookshelfService.addShelfToBookshelf(userId, bookshelfId, shelfDTO);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/{bookshelfId}")
    public ResponseEntity<?> handleDeleteShelfFromBookshelf(@RequestHeader("userId") long userId, @PathVariable("bookshelfId") long bookshelfId, @RequestBody ShelfDTO bookshelfDTO){
        bookshelfService.deleteShelfFromBookshelf(userId, new BookshelfDTO(bookshelfId, null, null), bookshelfDTO);
        return ResponseEntity.ok("");
    }


}
