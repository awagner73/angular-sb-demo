package net.wapro.angularsb.persistence.converter;


import net.wapro.angularsb.persistence.DarlehenArt;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter für {@link DarlehenArt}
 *
 * @author bor
 */
@Converter
public class ArtConverter implements AttributeConverter<DarlehenArt, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final DarlehenArt art) {
        if (art == null) {
            return null;
        }
        return art.getDbValue();
    }

    @Override
    public DarlehenArt convertToEntityAttribute(final Integer data) {
        if (data == null) {
            return null;
        }
        return DarlehenArt.valueByDBField(data);
    }

}
