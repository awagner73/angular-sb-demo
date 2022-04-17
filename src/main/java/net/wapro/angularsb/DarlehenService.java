package net.wapro.angularsb;

import net.wapro.angularsb.dto.DarlehenDto;
import net.wapro.angularsb.dto.DarlehenOverviewDto;
import net.wapro.angularsb.dto.DarlehenResult;
import net.wapro.angularsb.dto.mapper.DarlehenMapper;
import net.wapro.angularsb.persistence.Darlehen;
import net.wapro.angularsb.persistence.DarlehenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Businessschicht für die DaLe-Anwendung.
 * <p>
 * Macht die Zugriffe auf die Persistenzschicht und mappt das Ergebnis auf Datentransferobjekte.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DarlehenService {

    private final DarlehenRepository darlehenRepository;
    private final DarlehenMapper darlehenMapper;

    /**
     * Holt alle Darlehen aus der Datenbank und mappt die {@link Darlehen} zu {@link DarlehenDto}.
     *
     * @return {@link DarlehenOverviewDto}
     */
    public DarlehenOverviewDto getDarlehenOverview() {
        final List<Darlehen> darlehen = darlehenRepository.findAll(Sort.by(Sort.Order.desc("id")));
        return DarlehenOverviewDto.builder()
                .darlehen(darlehen.stream().map(darlehenMapper::darlehenToDarlehenDto).collect(Collectors.toList()))
                .build();
    }

    /**
     * Mappt das {@link DarlehenDto} zu {@link Darlehen} und speichert dies in der Datenbank.
     *
     * @param darlehenDto Das zu speichernde Darlehen
     * @return Das Ergebnisobjekt vom Typ {@link DarlehenResult}.
     */
    public DarlehenResult createDarlehen(final DarlehenDto darlehenDto) {
        final Darlehen darlehen = darlehenMapper.darlehenDtoToDarlehen(darlehenDto);

        darlehenRepository.save(darlehen);

        return new DarlehenResult(true);
    }

    /**
     * Löscht das Darlehen mit der übergebenen ID.
     *
     * @param id Die id des zu löschenden Darlehens.
     * @return Das Ergebnisobjekt vom Typ {@link DarlehenResult}.
     */
    public DarlehenResult deleteDarlehen(long id) {
        darlehenRepository.deleteById(id);
        return new DarlehenResult(true);
    }
}
