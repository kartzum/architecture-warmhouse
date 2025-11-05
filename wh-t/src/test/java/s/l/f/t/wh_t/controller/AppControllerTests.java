package s.l.f.t.wh_t.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;
import s.l.f.t.wh_t.dto.ThingCreateData;
import s.l.f.t.wh_t.dto.ThingData;
import s.l.f.t.wh_t.enums.ThingStatus;
import s.l.f.t.wh_t.service.ThingProcessingService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static s.l.f.t.wh_t.ContainerHolder.start;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AppControllerTests {
    @Autowired
    private WebApplicationContext context;

    private RestTestClient client;

    @Autowired
    private ThingProcessingService thingProcessingService;

    static {
        start();
    }

    @BeforeEach
    void setUp() {
        client = RestTestClient.bindToApplicationContext(context).build();
        thingProcessingService.deleteAll();
    }

    @Test
    void getTemperatureShouldSuccess() {
        Long result = client.get()
                .uri("/temperature?location=42")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Long.class).returnResult().getResponseBody();
        assertNotNull(result);
    }

    @Test
    void getThingByIdShouldSuccess() {
        UUID id = UUID.randomUUID();
        thingProcessingService.saveThing(id, id.toString(), ThingStatus.CONNECTED);
        ThingData result = client.get()
                .uri("/thing/" + id)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ThingData.class).returnResult().getResponseBody();
        assertEquals(id, result.getId());
        assertEquals(id.toString(), result.getName());
    }

    @Test
    void createThingShouldReturnSuccess() {
        UUID id = UUID.randomUUID();
        ThingData result = client.post()
                .uri("/thing")
                .body(new ThingCreateData(id, id.toString(), "", ThingStatus.CONNECTED.name()))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(ThingData.class).returnResult().getResponseBody();
        assertEquals(id, result.getId());
        assertEquals(id.toString(), result.getName());
    }
}
