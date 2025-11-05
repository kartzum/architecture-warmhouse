package s.l.f.t.wh_t.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import s.l.f.t.wh_t.entity.Thing;
import s.l.f.t.wh_t.enums.ThingStatus;
import s.l.f.t.wh_t.repository.ThingRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class ThingProcessingService {
    private final ThingRepository thingRepository;
    private final TransactionTemplate transactionTemplate;

    public ThingProcessingService(
            PlatformTransactionManager transactionManager,
            ThingRepository thingRepository
    ) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.thingRepository = thingRepository;
    }

    public void deleteAll() {
        transactionTemplate.executeWithoutResult(t -> thingRepository.deleteAll());
    }

    public Optional<Thing> findThingById(UUID id) {
        return transactionTemplate.execute(t -> thingRepository.findById(id));
    }

    public Thing saveThing(UUID id, String name, ThingStatus thingStatus) {
        return transactionTemplate.execute(t -> {
            Thing thing = new Thing(id, name, thingStatus);
            return thingRepository.save(thing);
        });
    }

    public void deleteThingById(UUID id) {
        transactionTemplate.executeWithoutResult(t -> {
            Optional<Thing> thingOptional = thingRepository.findById(id);
            thingOptional.ifPresent(thingRepository::delete);
        });
    }

    public Thing updateThingStatus(UUID id, ThingStatus thingStatus) {
        return transactionTemplate.execute(t -> {
            Thing thing = thingRepository.findById(id).orElseThrow();
            thing.setThingStatus(thingStatus);
            return thing;
        });
    }

    public void sendThingCommand(UUID id, Object payload) {
    }
}
