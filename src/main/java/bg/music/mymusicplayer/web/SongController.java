package bg.music.mymusicplayer.web;

import bg.music.mymusicplayer.model.dto.SongDTO;
import bg.music.mymusicplayer.model.user.MusicUserDetails;
import bg.music.mymusicplayer.service.SongService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SongController {

    private SongService songService;

    @GetMapping("/add-song")
    public String addSong() {
        return "add-song";
    }

    @ModelAttribute(name = "songDTO")
    private SongDTO songDTO() {
        return new SongDTO();
    }

    @PostMapping("/add-song")
    public String addSong(@Valid SongDTO songDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          @AuthenticationPrincipal MusicUserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("songDTO", songDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.songDTO",
                    bindingResult);

            return "redirect:/add-song";
        }

        this.songService.createSong(songDTO, userDetails);

        return "redirect:/home";
    }
}
