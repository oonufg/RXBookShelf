package ru.pablo.Domain.MediaService.Entities;

import java.util.UUID;

public class MediaFile {
    private long id;
    private UUID uid;
    private byte[] payload;
    private String extension;
    private String title;

    public MediaFile(long id, String UID, String title, String extension, byte[] payload){
        this.id = id;
        this.uid = UUID.fromString(UID);
        this.title = title;
        this.extension = extension;
        this.payload = payload;
    }

    public MediaFile(String title, String extension, byte[] payload){
        this.title = title;
        this.extension = extension;
        this.payload = payload;
        this.uid = UUID.nameUUIDFromBytes(payload);
    }

    public MediaFile(String filename, byte[] payload){
        String reversVersion = new StringBuilder(filename).reverse().toString();
        String[] parsedFile = reversVersion.split("\\.", 2);
        this.title = new StringBuilder(parsedFile[1]).reverse().toString();
        this.extension = new StringBuilder(parsedFile[0]).reverse().toString();;
        this.payload = payload;
        this.uid = UUID.nameUUIDFromBytes(payload);
    }

    public String getExtension() {
        return extension;
    }

    public String getTitle() {
        return title;
    }

    public String getFullName(){
        return title + "." +extension;

    }

    public byte[] getPayload(){
        return this.payload;
    }


    public String getUID(){
        return uid.toString();
    }

    public void setPayload(byte[] payload){
        this.payload = payload;
    }

}
