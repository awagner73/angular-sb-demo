package net.wapro.angularsb.dto.mapper;

import net.wapro.angularsb.dto.DarlehenDto;
import net.wapro.angularsb.persistence.Darlehen;
import org.mapstruct.Mapper;

/**
 * Mapper-Interface, welches von {@link Darlehen} zu {@link DarlehenDto} und umgekehrt mappt.
 */
@Mapper(componentModel = "spring")
public interface DarlehenMapper {

    DarlehenDto darlehenToDarlehenDto(Darlehen darlehen);

    Darlehen darlehenDtoToDarlehen(DarlehenDto darlehen);

}
