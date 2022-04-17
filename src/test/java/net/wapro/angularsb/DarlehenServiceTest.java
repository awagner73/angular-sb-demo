package net.wapro.angularsb;


import net.wapro.angularsb.dto.DarlehenResult;
import net.wapro.angularsb.dto.DarlehenDto;
import net.wapro.angularsb.dto.DarlehenOverviewDto;
import net.wapro.angularsb.dto.mapper.DarlehenMapperImpl;
import net.wapro.angularsb.persistence.Darlehen;
import net.wapro.angularsb.persistence.DarlehenRepository;
import net.wapro.angularsb.persistence.DarlehenStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DarlehenServiceTest {

    private DarlehenService service;
    @Mock
    private DarlehenRepository repository;
    @Captor
    private ArgumentCaptor<Darlehen> darlehenCaptor;

    @BeforeEach
    void setUp() {
        service = new DarlehenService(repository, new DarlehenMapperImpl());
    }

    @Test
    void overview() {
        final List<Darlehen> darlehen =
                List.of(Darlehen.builder().anwender("Anw").festzinsdatum(LocalDate.of(2020, 9, 1))
                        .status(DarlehenStatus.PLAN).build());
        doReturn(darlehen).when(repository).findAll(any(Sort.class));

        final DarlehenOverviewDto overview = service.getDarlehenOverview();

        assertThat(overview).isNotNull();
        assertThat(overview.getDarlehen()).hasSize(1);
        final DarlehenDto dto = overview.getDarlehen().get(0);

        //@formatter:off
        assertAll("DTO",
                () -> assertThat(dto.getAnwender()).isEqualTo("Anw"),
                () -> assertThat(dto.getFestzinsdatum()).isEqualTo(LocalDate.of(2020, 9, 1)),
                () -> assertThat(dto.getStatus()).isEqualTo(DarlehenStatus.PLAN),
                () -> assertThat(dto.getArt()).isNull()
        );
        //@formatter:on
    }

    @Test
    void create() {
        final DarlehenResult result =
                service.createDarlehen(DarlehenDto.builder().darlehensbetrag(new BigDecimal("99.75")).build());

        verify(repository).save(darlehenCaptor.capture());

        final Darlehen darlehen = darlehenCaptor.getValue();
        assertThat(darlehen).isNotNull();
        assertThat(darlehen.getDarlehensbetrag()).isEqualByComparingTo("99.75");

        assertThat(result).isNotNull();
        assertThat(result.isSuccess()).isTrue();
    }

    @Test
    void delete() {
        final DarlehenResult result = service.deleteDarlehen(1L);

        verify(repository).deleteById(1L);

        assertThat(result).isNotNull();
        assertThat(result.isSuccess()).isTrue();
    }
}
