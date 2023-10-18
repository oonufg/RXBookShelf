package ru.pablo.Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfAlreadyExistsException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfNotExistException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;
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

    @GetMapping()
    public ResponseEntity<?> handleGetUserBookShelves(@RequestHeader("userID") long userId){
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookshelfService.getBookshelves(userId));
    }

    @PostMapping()
    public ResponseEntity<?> handleCreateBookshelf(@RequestHeader("userID") long userId, @RequestBody BookshelfDTO bookshelfDTO){
        try {
            bookshelfService.createBookshelf(userId, bookshelfDTO);
            return ResponseEntity.ok().body("");
        }catch (BookshelfAlreadyExistsException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping()
    public ResponseEntity<?> handleDeleteBookshelf(@RequestHeader("userId") long userId, @RequestBody BookshelfDTO bookshelfDTO){
        try {
            bookshelfService.deleteBookshelf(userId, bookshelfDTO.id());
            return ResponseEntity.ok("");
        }catch (UserNotHaveAccessException e){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(403)).build();
        }
    }

    @GetMapping("/{bookshelfId}")
    public ResponseEntity<?> handleGetBookshelf(@RequestHeader("userId") long userId, @PathVariable("bookshelfId") long bookshelfId){
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(bookshelfService.getBookshelf(userId, bookshelfId));
        }catch (BookshelfNotExistException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{bookshelfId}")
    public ResponseEntity<?> handleAddShelfToBookshelf(@RequestHeader("userId") long userId, @PathVariable("bookshelfId") long bookshelfId, @RequestBody ShelfDTO shelfDTO){
        try {
            bookshelfService.addShelfToBookshelf(userId, bookshelfId, shelfDTO);
            return ResponseEntity.ok("");

        }catch (BookshelfNotExistException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bookshelfId}")
    public ResponseEntity<?> handleDeleteShelfFromBookshelf(@RequestHeader("userId") long userId, @PathVariable("bookshelfId") long bookshelfId, @RequestBody ShelfDTO bookshelfDTO){
        try {
            bookshelfService.deleteShelfFromBookshelf(userId, new BookshelfDTO(bookshelfId, null, null), bookshelfDTO);
            return ResponseEntity.ok("");
        }catch (BookshelfNotExistException e){
            return ResponseEntity.notFound().build();
                    }
    }


}
