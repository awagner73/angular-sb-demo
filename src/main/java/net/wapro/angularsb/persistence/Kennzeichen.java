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
 * Kennzeichen des Darlehens
 *
 * @author bor
 */
@RequiredArgsConstructor
@Getter
public enum Kennzeichen {

    /**
     * Standard. Kein Kennzeichen gesetzt
     */
    LEER("", "L"),

    /**
     * Investitionskredit
     */
    INVEST("Investitionskredit", "I"),

    /**
     * Kassenkredit
     */
    KASSE("Kassenkredit", "K"),

    /**
     * Anleihe
     */
    ANLEIHE("Anleihe", "A");

    private final String bezeichnung;
    private final String dbValue;

    private static final Map<String, Kennzeichen> DB_VALUE_ZU_KENNZEICHEN;

    static {
        DB_VALUE_ZU_KENNZEICHEN =
                Arrays.stream(values()).collect(Collectors.toMap(Kennzeichen::getDbValue, Function.identity()));
    }

    public static Kennzeichen valueByDBField(final String dbValue) {
        if (StringUtils.isEmpty(dbValue)) {
            return null;
        }
        return Optional.ofNullable(DB_VALUE_ZU_KENNZEICHEN.get(dbValue))
                .orElseThrow(() -> new IllegalArgumentException("Der Kredittyp wird nicht unterstützt"));
    }

}
