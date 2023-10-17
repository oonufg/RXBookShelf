package ru.pablo.Services.DTO;

import java.util.List;

public record ShelfDTO(Long id, String title, List<BookDTO> books) {
}
