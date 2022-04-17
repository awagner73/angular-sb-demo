package net.wapro.angularsb.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository zum Zugriff auf die {@link Darlehen}-Entitäten.
 */
public interface DarlehenRepository extends JpaRepository<Darlehen, Long> {

    Optional<Darlehen> findByUuid(final UUID uuid);

}
