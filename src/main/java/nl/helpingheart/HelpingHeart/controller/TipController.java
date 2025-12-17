package nl.helpingheart.HelpingHeart.controller;

import nl.helpingheart.HelpingHeart.model.Tip;
import nl.helpingheart.HelpingHeart.repository.TipRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tips")
@CrossOrigin(origins = "*") // Framer-friendly (we’ll tighten this later)
public class TipController {

    private final TipRepository tipRepository;

    public TipController(TipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }

    @GetMapping
    public List<Tip> getAllTips() {
        return tipRepository.findAll();
    }
}
