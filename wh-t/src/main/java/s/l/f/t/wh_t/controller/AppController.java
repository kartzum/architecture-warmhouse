package s.l.f.t.wh_t.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import s.l.f.t.wh_t.dto.ThingCreateData;
import s.l.f.t.wh_t.dto.ThingData;
import s.l.f.t.wh_t.dto.ThingInputCommandData;
import s.l.f.t.wh_t.dto.ThingUpdateStatusData;
import s.l.f.t.wh_t.entity.Thing;
import s.l.f.t.wh_t.enums.ThingStatus;
import s.l.f.t.wh_t.service.ThingProcessingService;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RestController
public class AppController {
    private final Random random = new Random();
    private final ThingProcessingService thingProcessingService;

    public AppController(ThingProcessingService thingProcessingService) {
        this.thingProcessingService = thingProcessingService;
    }

    @GetMapping("/temperature")
    public ResponseEntity<Double> getTemperature(@RequestParam(value = "location") String location) {
        double result = random.nextFloat(-50, 50);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/thing")
    public ResponseEntity<ThingData> createThing(@RequestBody ThingCreateData data) {
        UUID id = data.getId() != null ? data.getId() : UUID.randomUUID();
        String name = data.getName();
        String status = data.getThingStatus();
        Thing thing = thingProcessingService.saveThing(id, name, ThingStatus.valueOf(status));
        ThingData thingData = new ThingData(
                thing.getId(),
                thing.getName(),
                "",
                thing.getThingStatus().name()
        );
        return new ResponseEntity<>(thingData, HttpStatus.CREATED);
    }

    @GetMapping("/thing/{id}")
    public ResponseEntity<ThingData> getThingById(@PathVariable("id") UUID id) {
        Optional<Thing> thingOptional = thingProcessingService.findThingById(id);
        if (thingOptional.isPresent()) {
            ThingData thingData = new ThingData(
                    thingOptional.get().getId(),
                    thingOptional.get().getName(),
                    "",
                    thingOptional.get().getThingStatus().name()
            );
            return new ResponseEntity<>(thingData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/thing/{id}")
    public ResponseEntity<Boolean> deleteThingById(@PathVariable("id") UUID id) {
        thingProcessingService.deleteThingById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/thing/{id}/updateThingStatus")
    public ResponseEntity<String> updateThingStatus(
            @PathVariable("id") UUID id,
            @RequestBody ThingUpdateStatusData data
    ) {
        return new ResponseEntity<>(
                thingProcessingService.updateThingStatus(
                        id, ThingStatus.valueOf(data.getName())
                ).getThingStatus().name(),
                HttpStatus.OK
        );
    }

    @GetMapping("/thing/{id}/getThingStatus")
    public ResponseEntity<String> getThingStatus(@PathVariable("id") UUID id) {
        Optional<Thing> thingOptional = thingProcessingService.findThingById(id);
        if (thingOptional.isPresent()) {
            return new ResponseEntity<>(thingOptional.get().getThingStatus().name(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/thing/{id}/sendThingCommand")
    public ResponseEntity<?> sendThingCommand(
            @PathVariable("id") UUID id,
            @RequestBody ThingInputCommandData data
    ) {
        thingProcessingService.sendThingCommand(id, data.getPayload());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
