package nl.helpingheart.HelpingHeart.model; //folder waar deze file in staat

import jakarta.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    public Message() {}

    public Message(String content) {
        this.content = content;
    }

    public Long getId() { return id; }
    public String getContent() { return content; }

    public void setId(Long id) { this.id = id; }
    public void setContent(String content) { this.content = content; }
}
