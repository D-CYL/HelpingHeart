package nl.helpingheart.HelpingHeart.repository;

import nl.helpingheart.HelpingHeart.model.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GebruikerRepository extends JpaRepository<Gebruiker, Integer> {
}
