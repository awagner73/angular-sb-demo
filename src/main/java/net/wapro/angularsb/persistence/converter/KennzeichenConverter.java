package net.wapro.angularsb.persistence.converter;

import net.wapro.angularsb.persistence.Kennzeichen;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * Converter für {@link Kennzeichen}
 *
 * @author bor
 */
@Converter
public class KennzeichenConverter implements AttributeConverter<Kennzeichen, String> {

    @Override
    public String convertToDatabaseColumn(final Kennzeichen kennzeichen) {
        return kennzeichen != null ? kennzeichen.getDbValue() : "";
    }

    @Override
    public Kennzeichen convertToEntityAttribute(final String data) {
        return Kennzeichen.valueByDBField(data);
    }

}
