package net.wapro.angularsb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.wapro.angularsb.persistence.BelegArt;
import net.wapro.angularsb.persistence.DarlehenArt;
import net.wapro.angularsb.persistence.DarlehenStatus;
import net.wapro.angularsb.persistence.DarlehenTyp;
import net.wapro.angularsb.persistence.Kennzeichen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DarlehenDto {

    private long id;
    private UUID uuid;
    private String anwender;
    private String dalenummerbank;
    private DarlehenTyp typ;
    private DarlehenArt art;
    private DarlehenStatus status;
    private BelegArt belegart;
    private Kennzeichen kennzeichen;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate festzinsdatum;
    private String darlehen;
    private String laufzeitende;
    private BigDecimal darlehensbetrag;
    private BigDecimal zinsNominal;

}
