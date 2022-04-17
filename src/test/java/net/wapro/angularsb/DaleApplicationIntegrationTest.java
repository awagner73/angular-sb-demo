package net.wapro.angularsb;

import net.wapro.angularsb.dto.DarlehenOverviewDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DaleApplicationIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getOverview() {
        final ResponseEntity<DarlehenOverviewDto> overview =
                restTemplate.getForEntity("/api/dale/overview", DarlehenOverviewDto.class);

        assertThat(overview).isNotNull();
        assertThat(overview.getBody()).isNotNull();
        assertThat(overview.getBody().getDarlehen()).hasSize(354);
    }

}
