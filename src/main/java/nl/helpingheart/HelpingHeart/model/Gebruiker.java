package nl.helpingheart.HelpingHeart.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "gebruiker")
public class Gebruiker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gebruiker_id")
    private Integer gebruikerId;

    @Column(name = "datum")
    private LocalDateTime datum;    // DATETIME → LocalDateTime

    @Column(name = "keuze")
    private String keuze;

    // getters & setters

    public Integer getGebruikerId() {
        return gebruikerId;
    }

    public void setGebruikerId(Integer gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public String getKeuze() {
        return keuze;
    }

    public void setKeuze(String keuze) {
        this.keuze = keuze;
    }
}
