package net.wapro.angularsb.web;

import net.wapro.angularsb.DarlehenService;
import net.wapro.angularsb.dto.DarlehenDto;
import net.wapro.angularsb.dto.DarlehenOverviewDto;
import net.wapro.angularsb.dto.DarlehenResult;
import net.wapro.angularsb.persistence.DarlehenArt;
import net.wapro.angularsb.persistence.DarlehenStatus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DarlehenController.class)
class DarlehenControllerTest {

    @MockBean
    private DarlehenService darlehenService;
    @Autowired
    private MockMvc mvc;
    @Captor
    private ArgumentCaptor<DarlehenDto> darlehenDtoArgumentCaptor;

    @Test
    void getOverview() throws Exception {
        final DarlehenOverviewDto dto = DarlehenOverviewDto.builder().darlehen(
                List.of(DarlehenDto.builder().anwender("Anwender").art(DarlehenArt.FEST).build(),
                        DarlehenDto.builder().dalenummerbank("Nummer").build())).build();
        doReturn(dto).when(darlehenService).getDarlehenOverview();

        mvc.perform(get("/api/dale/overview").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.darlehen").isArray())
                .andExpect(jsonPath("$.darlehen[0].anwender").value("Anwender"))
                .andExpect(jsonPath("$.darlehen[0].art").value("FEST"))
                .andExpect(jsonPath("$.darlehen[1].dalenummerbank").value("Nummer"));
    }

    @Test
    void createDarlehen() throws Exception {
        Mockito.doReturn(new DarlehenResult(true)).when(darlehenService)
                .createDarlehen(darlehenDtoArgumentCaptor.capture());

        mvc.perform(post("/api/dale/create").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content("{\"anwender\":\"Anw\", \"status\":\"GUELTIG\"}")).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        final DarlehenDto dto = darlehenDtoArgumentCaptor.getValue();

        //@formatter:off
        assertAll("DTO",
                () -> assertThat(dto).isNotNull(),
                () -> assertThat(dto.getAnwender()).isEqualTo("Anw"),
                () -> assertThat(dto.getStatus()).isEqualTo(DarlehenStatus.GUELTIG)
        );
        //@formatter:on
    }

    @Test
    void deleteDarlehen() throws Exception {
        doReturn(new DarlehenResult(true)).when(darlehenService).deleteDarlehen(1L);

        mvc.perform(delete("/api/dale/delete/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
