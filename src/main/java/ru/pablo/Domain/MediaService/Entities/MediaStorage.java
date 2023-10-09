package ru.pablo.Domain.MediaService.Entities;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class MediaStorage {
    private String location = System.getProperty("user.dir") + "/" + "Media";

    public MediaStorage(){
        creatDirectoryIfNotExist(Path.of(location));
    }
    public byte[] getFilePay(String fileName){
        Path pathToFile = getPathToFile(fileName);
        byte[] filePayload = {};
        try {
            filePayload = new byte[(int)Files.size(pathToFile)];
            readBytesFromFile(filePayload, pathToFile);

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return filePayload;
    }

    public void saveFile(String filename, byte[] payload) {
        Path pathToFileinFileSystem = getPathToFile(filename );
        recordByteInFileIfNotExst(pathToFileinFileSystem, payload);
    }

    private Path getPathToFile(String filename){
        return Path.of(location, filename);
    }

    private void creatDirectoryIfNotExist(Path intendedPathToFile){
        try {
            if (!Files.exists(intendedPathToFile)) {
                Files.createDirectory(intendedPathToFile);
            }
        }catch(IOException exception){
            System.out.println("Failed to create directory -> " + exception.getMessage());
        }
    }

    private void recordByteInFileIfNotExst(Path pathToFileInFileSystem, byte[] payload){
        if(!Files.exists(pathToFileInFileSystem)){
            recordBytesInFile(pathToFileInFileSystem,payload);
        }
    }

    private void recordBytesInFile(Path pathToFile, byte[] filePayload){
        try (SeekableByteChannel channel = Files.newByteChannel(pathToFile, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            channel.truncate(filePayload.length);
            ByteBuffer buffer = ByteBuffer.allocate(filePayload.length);
            for (byte b : filePayload) {
                buffer.put(b);
            }
            buffer.flip();
            channel.write(buffer);
        } catch(IOException exception){
            System.out.println("->> " + exception.getCause());
        }
    }

    private void readBytesFromFile(byte[] arrayToWrite, Path pathToFile){
        try(ByteChannel channel = Files.newByteChannel(pathToFile,StandardOpenOption.READ)){
            ByteBuffer buffer = ByteBuffer.allocate(arrayToWrite.length);
            channel.read(buffer);
            for(int i = 0; i < arrayToWrite.length ; i++){
                arrayToWrite[i] = buffer.get(i);
            }
            System.out.println();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }


}

