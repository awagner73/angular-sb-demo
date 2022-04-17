package net.wapro.angularsb.persistence;

import net.wapro.angularsb.persistence.converter.ArtConverter;
import net.wapro.angularsb.persistence.converter.BelegartConverter;
import net.wapro.angularsb.persistence.converter.KennzeichenConverter;
import net.wapro.angularsb.persistence.converter.StatusConverter;
import net.wapro.angularsb.persistence.converter.TypConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Darlehen {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "darlehen_sequence")
    @SequenceGenerator(name = "darlehen_sequence", sequenceName = "darlehen_sequence")
    private long id;
    private UUID uuid;
    private String anwender;
    @Column(name = "dale_nr_bank")
    private String dalenummerbank;
    @Convert(converter = TypConverter.class)
    private DarlehenTyp typ;
    @Convert(converter = ArtConverter.class)
    private DarlehenArt art;
    @Convert(converter = StatusConverter.class)
    private DarlehenStatus status;
    @Convert(converter = BelegartConverter.class)
    private BelegArt belegart;
    @Convert(converter = KennzeichenConverter.class)
    private Kennzeichen kennzeichen;
    private LocalDate festzinsdatum;
    private String darlehen;
    private String laufzeitende;
    @Column(name = "betrag")
    private BigDecimal darlehensbetrag;
    private BigDecimal zinsNominal;
}
