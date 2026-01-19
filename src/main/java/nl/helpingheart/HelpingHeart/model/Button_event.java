package nl.helpingheart.HelpingHeart.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "button_event")
public class Button_event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String button;

    @Column(name = "event_time")
    private LocalDateTime eventTime;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }
}
