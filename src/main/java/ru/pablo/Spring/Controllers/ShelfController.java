package ru.pablo.Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.pablo.Domain.Exceptions.Book.BookAlreadyOnShelfException;
import ru.pablo.Domain.Exceptions.Book.BookNotExistException;
import ru.pablo.Domain.Exceptions.Shelf.ShelfNotExistsException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;
import ru.pablo.Domain.MediaService.Entities.MediaFile;
import ru.pablo.Services.DTO.BookDTO;
import ru.pablo.Services.DTO.ShelfDTO;
import ru.pablo.Services.ShelfService;
import ru.pablo.Spring.Security.ApplicationUser;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/shelf")
public class ShelfController {
    private ShelfService shelfService;

    @Autowired
    public ShelfController(ShelfService shelfService){
        this.shelfService = shelfService;
    }

    @GetMapping("/{shelfID}")
    public ResponseEntity<?> handleGetShelfBooks(@AuthenticationPrincipal ApplicationUser user, @PathVariable("shelfID") long shelfID){
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(shelfService.getShelf(shelfID));
        }catch(ShelfNotExistsException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{shelfID}/{bookID}")
    public ResponseEntity<?> handleGetBookFromShelf(@AuthenticationPrincipal ApplicationUser user, @PathVariable("shelfID") Long shelfID, @PathVariable("bookID") Long bookID){
        try {
            MediaFile mFile = shelfService.getBookPayload(shelfID, bookID);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentDisposition(
                    ContentDisposition.attachment()
                            .filename(mFile.getFullName())
                            .build()
            );
            return ResponseEntity.ok().
                    contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .headers(httpHeaders)
                    .body(mFile.getPayload());
        }catch (BookNotExistException e){
            return ResponseEntity.notFound().build();
        }catch(ShelfNotExistsException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{shelfID}")
    public ResponseEntity<?> handleAddBookToShelf(@AuthenticationPrincipal ApplicationUser user, @PathVariable("shelfID") Long shelfID, @RequestParam("title") String title, @RequestParam("description") String description ,@RequestParam("file") MultipartFile file){
        try {
            MediaFile mFile = new MediaFile(file.getOriginalFilename(), file.getBytes());
            BookDTO bookDTO = new BookDTO(null, title, description, null, mFile);
            shelfService.addBookToShelf(user.getId(), shelfID, bookDTO);
            return ResponseEntity.ok().body("");
        }catch (IOException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }catch (UserNotHaveAccessException e){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(403)).build();
        }catch (BookAlreadyOnShelfException e){
            return ResponseEntity.badRequest().build();
        }catch(ShelfNotExistsException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{shelfId}")
    public ResponseEntity<?> handleDeleteBookFromShelf(@AuthenticationPrincipal ApplicationUser user, @PathVariable("shelfID") Long shelfID, @RequestBody BookDTO bookDTO){
        try {
            shelfService.deleteBookFromShelf(user.getId(), shelfID, bookDTO);
            return ResponseEntity.ok().build();
        }catch (BookNotExistException e){
            return ResponseEntity.notFound().build();
        }catch (UserNotHaveAccessException e){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(403)).build();
        }catch(ShelfNotExistsException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping()
    public ResponseEntity<?> handleUpdateShelf(@AuthenticationPrincipal ApplicationUser user, @RequestBody ShelfDTO shelfDTO){
        try {
            shelfService.updateShelf(user.getId(), shelfDTO);
            return ResponseEntity.ok().build();
        }catch (UserNotHaveAccessException e){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(403)).build();
        }catch (ShelfNotExistsException e){
            return ResponseEntity.notFound().build();
        }
    }
}
