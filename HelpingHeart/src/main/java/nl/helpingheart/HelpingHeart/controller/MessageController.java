package nl.helpingheart.HelpingHeart.controller; //folder waar deze file in staat

import nl.helpingheart.HelpingHeart.model.Message;
import nl.helpingheart.HelpingHeart.repository.MessageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*") // allows Framer to access API
public class MessageController {

    private final MessageRepository repo;

    public MessageController(MessageRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Message> getMessages() {
        return repo.findAll();
    }

    @PostMapping
    public Message addMessage(@RequestBody Message message) {
        return repo.save(message);
    }
}
