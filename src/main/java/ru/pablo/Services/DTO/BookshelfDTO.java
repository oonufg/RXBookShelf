package ru.pablo.Services.DTO;

import java.util.List;

public record BookshelfDTO(Long id, String title, String ownerNickname ,List<ShelfDTO> shelves) {
}