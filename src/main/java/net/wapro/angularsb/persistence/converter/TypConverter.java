package net.wapro.angularsb.persistence.converter;


import net.wapro.angularsb.persistence.DarlehenTyp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter für {@link DarlehenTyp}
 *
 * @author bor
 */
@Converter
public class TypConverter implements AttributeConverter<DarlehenTyp, String> {

    @Override
    public String convertToDatabaseColumn(final DarlehenTyp status) {
        if (status == null) {
            return null;
        }
        return status.getDbValue();
    }

    @Override
    public DarlehenTyp convertToEntityAttribute(final String data) {
        return DarlehenTyp.valueByDBField(data);
    }

}
