package net.wapro.angularsb.persistence.converter;

import net.wapro.angularsb.persistence.DarlehenStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * Converter für {@link DarlehenStatus}
 *
 * @author bor
 */
@Converter
public class StatusConverter implements AttributeConverter<DarlehenStatus, String> {

    @Override
    public String convertToDatabaseColumn(final DarlehenStatus typ) {
        return typ.getDbValue();
    }

    @Override
    public DarlehenStatus convertToEntityAttribute(final String data) {
        return DarlehenStatus.valueByDBField(data);
    }

}
