package ru.pablo.Services.DTO;

import ru.pablo.Domain.MediaService.Entities.MediaFile;

public record BookDTO(Long id, String tile, String description,Long payloadId ,MediaFile mediaFile) {
}
