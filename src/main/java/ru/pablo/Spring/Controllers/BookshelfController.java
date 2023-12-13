package ru.pablo.Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfAlreadyExistsException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfAlreadyInSubscribesException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfNotExistException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfNotInSubscribesException;
import ru.pablo.Domain.Exceptions.Shelf.ShelfAlreadyExistException;
import ru.pablo.Domain.Exceptions.Shelf.ShelfNotExistsException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;
import ru.pablo.Services.BookshelfService;
import ru.pablo.Services.DTO.BookshelfDTO;
import ru.pablo.Services.DTO.ShelfDTO;
import ru.pablo.Spring.Security.ApplicationUser;

@RestController
@RequestMapping("/api/v1/bookshelf")
public class BookshelfController {
    private final BookshelfService bookshelfService;

    @Autowired
    public BookshelfController(BookshelfService service){
        this.bookshelfService = service;
    }
    @PutMapping()
    public ResponseEntity<?> handleUpdateBookshelf(@AuthenticationPrincipal ApplicationUser user, @RequestBody BookshelfDTO bookshelfDTO){
        try {
            bookshelfService.changeBookshelf(user.getId(), bookshelfDTO);
            return ResponseEntity.ok("");
        }catch (BookshelfNotExistException e){
            return ResponseEntity.notFound().build();
        }catch(UserNotHaveAccessException e){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(403)).build();
        }
    }

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<?> handleGetUserBookShelves(@AuthenticationPrincipal ApplicationUser user){
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookshelfService.getBookshelves(userId));
    }


    @PostMapping()
    public ResponseEntity<?> handleCreateBookshelf(@AuthenticationPrincipal ApplicationUser user, @RequestBody BookshelfDTO bookshelfDTO){
        try {
            bookshelfService.createBookshelf(user.getId(), bookshelfDTO);
            return ResponseEntity.ok().body("");
        }catch (BookshelfAlreadyExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @DeleteMapping()
    public ResponseEntity<?> handleDeleteBookshelf(@AuthenticationPrincipal ApplicationUser user, @RequestBody BookshelfDTO bookshelfDTO){
        try {
            bookshelfService.deleteBookshelf(user.getId(), bookshelfDTO.id());
            return ResponseEntity.ok("");
        }catch (UserNotHaveAccessException e){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(403)).build();
        }catch (BookshelfNotExistException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{bookshelfId}")
    public ResponseEntity<?> handleGetBookshelf(@AuthenticationPrincipal ApplicationUser user, @PathVariable("bookshelfId") long bookshelfId){
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(bookshelfService.getBookshelf(user.getId(), bookshelfId));
        }catch (BookshelfNotExistException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{bookshelfId}/shelves")
    public ResponseEntity<?> handleGetBookshelfShelves(@AuthenticationPrincipal ApplicationUser user, @PathVariable("bookshelfId") long bookshelfId){
        try{
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(bookshelfService.getShelves(user.getId(), bookshelfId));
        }catch (BookshelfNotExistException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{bookshelfId}")
    public ResponseEntity<?> handleAddShelfToBookshelf(@AuthenticationPrincipal ApplicationUser user, @PathVariable("bookshelfId") long bookshelfId, @RequestBody ShelfDTO shelfDTO){
        try {
            bookshelfService.addShelfToBookshelf(user.getId(), bookshelfId, shelfDTO);
            return ResponseEntity.ok("");
        } catch (BookshelfNotExistException e){
            return ResponseEntity.notFound().build();
        } catch (UserNotHaveAccessException e){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(403)).build();
        } catch (ShelfAlreadyExistException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{bookshelfId}")
    public ResponseEntity<?> handleDeleteShelfFromBookshelf(@AuthenticationPrincipal ApplicationUser user, @PathVariable("bookshelfId") long bookshelfId, @RequestBody ShelfDTO shelfDTO) {
        try {
            bookshelfService.deleteShelfFromBookshelf(user.getId(), new BookshelfDTO(bookshelfId, null, null, null), shelfDTO);
            return ResponseEntity.ok("");
        } catch (BookshelfNotExistException | ShelfNotExistsException e) {
            return ResponseEntity.notFound().build();
        } catch (UserNotHaveAccessException e){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(403)).build();
        }
    }

    @GetMapping("/s")
    public ResponseEntity<?> handleGetSubscribeanBookshelves(@AuthenticationPrincipal ApplicationUser user){
        System.out.println(user.getUsername());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookshelfService.getSubscribeBookshelves(user.getId()));
    }

    @PostMapping("/s")
    public ResponseEntity<?> handleSubscribeToBookshelf(@AuthenticationPrincipal ApplicationUser user, @RequestBody BookshelfDTO bookshelfDTO) {
        try {
            bookshelfService.subscribeToBookshelf(user.getId(), bookshelfDTO);
            return ResponseEntity.ok().build();
        } catch (BookshelfAlreadyInSubscribesException e) {
            return ResponseEntity.badRequest().build();
        } catch (BookshelfNotExistException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/s")
    public ResponseEntity<?> handleUnsubscribeFromBookshelf(@AuthenticationPrincipal ApplicationUser user, @RequestBody BookshelfDTO bookshelfDTO) {
        try {
            bookshelfService.unsubscribeFromBookshelf(user.getId(), bookshelfDTO);
            return ResponseEntity.ok().build();
        } catch (BookshelfNotInSubscribesException e) {
            return ResponseEntity.badRequest().build();
        } catch (BookshelfNotExistException e) {
            return ResponseEntity.notFound().build();
        }
    }
}