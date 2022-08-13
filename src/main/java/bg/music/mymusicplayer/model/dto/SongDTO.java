package bg.music.mymusicplayer.model.dto;

import javax.validation.constraints.NotBlank;

public class SongDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String album;

    @NotBlank
    private String artist;


    public SongDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
