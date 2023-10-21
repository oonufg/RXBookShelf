package ru.pablo.Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.pablo.Domain.Exceptions.Shelf.ShelfNotExistsException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;
import ru.pablo.Domain.MediaService.Entities.MediaFile;
import ru.pablo.Services.DTO.BookDTO;
import ru.pablo.Services.DTO.ShelfDTO;
import ru.pablo.Services.ShelfService;

import java.io.IOException;

@RestController
@RequestMapping("/shelf")
public class ShelfController {
    private ShelfService shelfService;

    @Autowired
    public ShelfController(ShelfService shelfService){
        this.shelfService = shelfService;
    }

    @GetMapping("/{shelfID}")
    public ResponseEntity<?> handleGetShelfBooks(@RequestHeader("userID") long userId, @PathVariable("shelfID") long shelfID){
        return ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(shelfService.getShelf(shelfID));
    }

    @GetMapping("/{shelfID}/{bookID}")
    public ResponseEntity<?> handleGetBookFromShelf(@RequestHeader("userID") long userId, @PathVariable("shelfID") Long shelfID, @PathVariable("bookID") Long bookID){
        MediaFile mFile = shelfService.getBook(shelfID, bookID);
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
    }

    @PostMapping("/{shelfID}")
    public ResponseEntity<?> handleAddBookToShelf(@RequestHeader("userID") long userId, @PathVariable("shelfID") Long shelfID, @RequestParam("title") String title, @RequestParam("description") String description ,@RequestParam("file") MultipartFile file){
        try {
            MediaFile mFile = new MediaFile(file.getOriginalFilename(), file.getBytes());
            BookDTO bookDTO = new BookDTO(null, title, description, null, mFile);
            shelfService.addBookToShelf(userId, shelfID, bookDTO);
            return ResponseEntity.ok().body("");
        }catch (IOException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }catch (UserNotHaveAccessException e){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(403)).build();
        }
    }
    @PutMapping()
    public ResponseEntity<?> handleUpdateShelf(@RequestHeader("userID") long userId, @RequestBody ShelfDTO shelfDTO){
        try {
            shelfService.updateShelf(userId, shelfDTO);
            return ResponseEntity.ok().build();
        }catch (UserNotHaveAccessException e){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(403)).build();
        }catch (ShelfNotExistsException e){
            return ResponseEntity.notFound().build();
        }
    }
}
