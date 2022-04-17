package net.wapro.angularsb.persistence;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Status des Darlehens
 *
 * @author bor
 */
@RequiredArgsConstructor
@Getter
public enum DarlehenStatus {

    /**
     * Darlehen zum "Ausprobieren". Diese Darlehen werden bei Buchungen nicht berücksichtigt
     */
    PLAN("Plan", "P"),
    /**
     * Standardwert. Aus einem gültigen Darlehen können Buchungen erzeugt werden
     */
    GUELTIG("Gültig", "G"),

    /**
     * Alte Darlehen die keine Relevanz mehr für das Tagesgeschäft haben. Diese Darlehen werden beim Buchen nicht
     * berücksichtigt
     */
    ARCHIV("Archiv", "A");

    private final String bezeichnung;
    private final String dbValue;

    private static final Map<String, DarlehenStatus> DB_VALUE_ZU_DARLEHEN_STATUS;

    static {
        DB_VALUE_ZU_DARLEHEN_STATUS =
                Arrays.stream(values()).collect(Collectors.toMap(DarlehenStatus::getDbValue, Function.identity()));
    }


    public static DarlehenStatus valueByDBField(final String dbValue) {
        if (StringUtils.isEmpty(dbValue)) {
            return null;
        }
        return Optional.ofNullable(DB_VALUE_ZU_DARLEHEN_STATUS.get(dbValue))
                .orElseThrow(() -> new IllegalArgumentException("Der Statustyp wird nicht unterstützt"));
    }

}
