package bg.music.mymusicplayer.service;

import bg.music.mymusicplayer.model.dto.SongDTO;
import bg.music.mymusicplayer.model.entity.Song;
import bg.music.mymusicplayer.model.entity.User;
import bg.music.mymusicplayer.repository.SongRepository;
import bg.music.mymusicplayer.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    private final SongRepository songRepository;
    private UserRepository userRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }


    public void createSong(SongDTO songDTO, UserDetails userDetails) {
        Song newSong = new Song();
                newSong.setName(songDTO.getName());
                newSong.setAlbum(songDTO.getAlbum());
                newSong.setArtist(songDTO.getArtist());

        User owner = userRepository.findByEmail(
                userDetails.getUsername()).orElseThrow();

        newSong.setUser(owner);

        songRepository.save(newSong);
    }
}
