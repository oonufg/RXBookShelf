package ru.pablo.Domain.MediaService.Persistence.Repositories;


import ru.pablo.Domain.MediaService.Entities.MediaFile;
import ru.pablo.Domain.MediaService.Persistence.Tables.FilesTable;
import ru.pablo.Domain.MediaService.Entities.MediaStorage;

import java.util.Map;


public class MediaFileRepository {
    private static FilesTable filesTable;
    private static MediaStorage mediaStorage;

    static{
        filesTable = new FilesTable();
        mediaStorage = new MediaStorage();
    }

    public void addMediaFile(MediaFile file){
        filesTable.saveFileInfo(file.getUID(), file.getTitle(), file.getExtension());
        mediaStorage.saveFile(file.getUID(), file.getPayload());
    }

    public MediaFile getMediaFile(String uid){
        Map<String, Object> fileInfo = filesTable.getFileInfoByUID(uid);
        MediaFile resultFile = new MediaFile(
                (String)fileInfo.get("uid"),
                (String)fileInfo.get("title"),
                (String)fileInfo.get("extension"),
                mediaStorage.getFilePay((String)fileInfo.get("uid"))
        );
        return resultFile;
    }

}
