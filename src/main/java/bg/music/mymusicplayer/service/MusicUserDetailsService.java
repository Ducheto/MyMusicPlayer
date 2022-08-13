package bg.music.mymusicplayer.service;

import bg.music.mymusicplayer.model.entity.User;
import bg.music.mymusicplayer.model.entity.UserRole;
import bg.music.mymusicplayer.model.user.MusicUserDetails;
import bg.music.mymusicplayer.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class MusicUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public MusicUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.
                findByEmail(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }

    private UserDetails map(User user) {

        return new MusicUserDetails(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.
                        getUserRoles().
                        stream().
                        map(this::map).
                        toList()
        );
    }

    private GrantedAuthority map(UserRole userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.
                        getUserRole().name());
    }
}
