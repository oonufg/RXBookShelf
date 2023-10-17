package ru.pablo.Services.DTO;

import ru.pablo.Domain.MediaService.Entities.MediaFile;

public record BookDTO(long id, String tile, String description,long payloadId ,MediaFile mediaFile) {
}
