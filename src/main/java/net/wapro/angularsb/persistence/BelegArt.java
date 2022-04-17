package net.wapro.angularsb.persistence;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum BelegArt {

    /**
     * Standardbelegart
     */
    STANDARD("normale Buchung - Standardbelegart", "0"),

    /**
     * Überweisung
     */
    UEBERWEISUNG("Überweisung", "1"),

    /**
     * Lastschrifteinzug
     */
    LASTSCHRIFT("Lastschrifteinzug", "3");

    private final String bezeichnung;
    private final String dbValue;

    private static final Map<String, BelegArt> DB_VALUE_ZU_BELEG_ART;

    static {
        DB_VALUE_ZU_BELEG_ART =
                Arrays.stream(values()).collect(Collectors.toMap(BelegArt::getDbValue, Function.identity()));
    }

    public static BelegArt valueByDBField(final String dbValue) {
        if (StringUtils.isEmpty(dbValue)) {
            return null;
        }
        return Optional.ofNullable(DB_VALUE_ZU_BELEG_ART.get(dbValue))
                .orElseThrow(() -> new IllegalArgumentException("Der Kredittyp wird nicht unterstützt"));
    }

}
