package net.wapro.angularsb.persistence;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Art des Darlehens
 *
 * @author bor
 */
@RequiredArgsConstructor
@Getter
public enum DarlehenArt {

    /**
     * Feste Tilgung
     */
    FEST("Feste Tilgung", 1),

    /**
     * Annuitätendarlehen (Standard)
     */
    ANNU("Annuitätendarlehen", 2);

    private final String bezeichnung;
    private final int dbValue;

    private static final Map<Integer, DarlehenArt> DB_VALUE_ZU_DARLEHEN_ART;

    static {
        DB_VALUE_ZU_DARLEHEN_ART =
                Arrays.stream(values()).collect(Collectors.toMap(DarlehenArt::getDbValue, Function.identity()));
    }


    public static DarlehenArt valueByDBField(final int dbValue) {
        return Optional.ofNullable(DB_VALUE_ZU_DARLEHEN_ART.get(dbValue))
                .orElseThrow(() -> new IllegalArgumentException("Die Darlehensart wird nicht unterstützt"));
    }

}
