package ru.pablo.Domain.MediaService.Persistence.Repositories;


import ru.pablo.Domain.MediaService.Entities.MediaFile;
import ru.pablo.Domain.MediaService.Persistence.MediaFileMapper;
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

    public long addMediaFile(MediaFile file){
        long result = filesTable.saveFileInfo(file.getUID(), file.getTitle(), file.getExtension());
        mediaStorage.saveFile(file.getUID(), file.getPayload());
        return result;
    }

    public MediaFile getMediaFile(String uid){
        Map<String, Object> fileInfo = filesTable.getFileInfoByUID(uid);
        MediaFile resultFile = MediaFileMapper.mapMediafile(fileInfo);
        resultFile.setPayload(mediaStorage.getFilePay(resultFile.getUID()));
        return resultFile;
    }

    public MediaFile getMediaFile(long id){
        Map<String, Object> fileInfo = filesTable.getFileInfoByID(id);
        MediaFile resultFile = MediaFileMapper.mapMediafile(fileInfo);
        resultFile.setPayload(mediaStorage.getFilePay(resultFile.getUID()));
        return resultFile;
    }

}
