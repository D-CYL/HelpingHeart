package nl.helpingheart.HelpingHeart.repository; //folder waar deze file in staat

import nl.helpingheart.HelpingHeart.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {}
