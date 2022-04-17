package net.wapro.angularsb.web;

import net.wapro.angularsb.DarlehenService;
import net.wapro.angularsb.dto.DarlehenDto;
import net.wapro.angularsb.dto.DarlehenOverviewDto;
import net.wapro.angularsb.dto.DarlehenResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * REST-Schicht der Anwendung. Stellt die Ressourcen zum Holen, Erstellen und Löschen zur Verfügung.
 */
@RestController
@RequestMapping("/api/dale")
@CrossOrigin(origins = {"http://localhost:4200"})
@RequiredArgsConstructor
public class DarlehenController {

    private final DarlehenService darlehenService;

    /**
     * Liefert die Darlehensübersicht mit der Liste aller Darlehen.
     *
     * @return {@link DarlehenOverviewDto} mit allen Darlehen
     */
    @GetMapping(value = "/overview", produces = MediaType.APPLICATION_JSON_VALUE)
    public DarlehenOverviewDto getOverview() {
        return darlehenService.getDarlehenOverview();
    }

    /**
     * Erstellt ein neues Darlehen in der Datenbank.
     *
     * @param darlehenDto Das zu erstellende Darlehen
     * @return Ergebnisobjekt vom Typ {@link DarlehenResult}
     */
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DarlehenResult createDarlehen(@RequestBody final DarlehenDto darlehenDto) {
        return darlehenService.createDarlehen(darlehenDto);
    }

    /**
     * Löscht das Darlehen mit der übergebenen ID.
     *
     * @param id Die zu löschende ID.
     * @return Ergebnisobjekt vom Typ {@link DarlehenResult}
     */
    @DeleteMapping(value = "/delete/{id}")
    public DarlehenResult deleteDarlehen(@PathVariable final long id) {
        return darlehenService.deleteDarlehen(id);
    }
}
