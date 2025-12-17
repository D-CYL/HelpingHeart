package nl.helpingheart.HelpingHeart.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tip")
public class Tip {

    @Id
    @Column(name = "tipnr")
    private Integer tipnr;

    @Column(length = 30)
    private String categorie;

    @Column(length = 90)
    private String titel;

    @Column(name = "Beschrijving", columnDefinition = "TEXT")
    private String beschrijving;

    // --- getters & setters ---

    public Integer getTipnr() {
        return tipnr;
    }

    public void setTipnr(Integer tipnr) {
        this.tipnr = tipnr;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
}
