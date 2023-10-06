package Domain.Entities;

public class Book {
    private long id;
    private String title;
    private byte[] payload;

    public Book(long id, String title, byte[] payload){
        this.id = id;
        this.title = title;
        this.payload = payload;
    }

    public long getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public byte[] getPayload(){
        return payload;
    }
}
