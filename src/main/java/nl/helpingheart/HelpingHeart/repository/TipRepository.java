package nl.helpingheart.HelpingHeart.repository;

import nl.helpingheart.HelpingHeart.model.Tip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipRepository extends JpaRepository<Tip, Integer> {
}
