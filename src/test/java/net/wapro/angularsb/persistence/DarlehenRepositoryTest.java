package net.wapro.angularsb.persistence;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DarlehenRepositoryTest {

    @Autowired
    private DarlehenRepository repository;

    @Test
    void testDaten() {
        assertThat(repository.count()).isEqualTo(354L);
    }

    @Test
    void findByUuid() {
        assertThat(repository.findByUuid(UUID.fromString("8A47E9FE-BA90-4DCC-B4C2-BA8161EC48AB"))).isPresent();
    }

    @Test
    void insert() {
        final UUID uuid = UUID.fromString("52ff8882-5249-4b0e-9136-7ad33ba8f716");
        repository.save(Darlehen.builder().anwender("Anwender")
                .status(DarlehenStatus.PLAN)
                .uuid(uuid).build());

        assertThat(repository.count()).isEqualTo(355L);

        final Optional<Darlehen> darlehenOptional = repository.findByUuid(uuid);
        Assertions.assertThat(darlehenOptional).isPresent();

        assertThat(darlehenOptional.get().getAnwender()).isEqualTo("Anwender");
    }
}
