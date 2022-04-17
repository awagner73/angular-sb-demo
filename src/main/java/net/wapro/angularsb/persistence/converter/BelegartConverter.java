package net.wapro.angularsb.persistence.converter;

import net.wapro.angularsb.persistence.BelegArt;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * Converter für {@link BelegArt}
 *
 * @author bor
 */
@Converter
public class BelegartConverter implements AttributeConverter<BelegArt, String> {

    @Override
    public String convertToDatabaseColumn(final BelegArt art) {
        return art != null ? art.getDbValue() : "";
    }

    @Override
    public BelegArt convertToEntityAttribute(final String data) {
        return BelegArt.valueByDBField(data);
    }

}
