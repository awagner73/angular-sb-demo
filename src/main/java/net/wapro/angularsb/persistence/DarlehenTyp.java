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
 * Typ des Darlehens
 * <p>
 * Handelt es sich um eine aktives (vergebenes) oder passives (genommenes) Darlehen
 *
 * @author bor
 */
@RequiredArgsConstructor
@Getter
public enum DarlehenTyp {

    /**
     * Aktivdarlehen
     */
    AKTIV("Aktivdarlehen(vergeben)", "A"),
    /**
     * Standardwert. Passivdarlehen
     */
    PASSIV("Passivdarlehen (aufgenommen)", "P");

    private final String bezeichnung;
    private final String dbValue;

    private static final Map<String, DarlehenTyp> DB_VALUE_ZU_DARLEHEN_TYP;

    static {
        DB_VALUE_ZU_DARLEHEN_TYP =
                Arrays.stream(values()).collect(Collectors.toMap(DarlehenTyp::getDbValue, Function.identity()));
    }


    public static DarlehenTyp valueByDBField(final String dbValue) {
        if (StringUtils.isEmpty(dbValue)) {
            return null;
        }
        return Optional.ofNullable(DB_VALUE_ZU_DARLEHEN_TYP.get(dbValue))
                .orElseThrow(() -> new IllegalArgumentException("Der Statustyp wird nicht unterstützt"));
    }

}
