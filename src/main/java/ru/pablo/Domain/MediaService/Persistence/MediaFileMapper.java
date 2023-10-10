package ru.pablo.Domain.MediaService.Persistence;

import ru.pablo.Domain.MediaService.Entities.MediaFile;

import java.util.Map;

public class MediaFileMapper {
    public static MediaFile mapMediafile(Map<String, Object> unmappedMediafile){
        return new MediaFile(
                (Long)unmappedMediafile.get("id"),
                (String)unmappedMediafile.get("uid"),
                (String)unmappedMediafile.get("title"),
                (String)unmappedMediafile.get("extension"),
                null
        );
    }
}
