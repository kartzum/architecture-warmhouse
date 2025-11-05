package s.l.f.t.wh_t.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import s.l.f.t.wh_t.enums.ThingStatus;

import java.util.UUID;

@Entity
public class Thing {
    @Id
    private UUID id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private ThingStatus thingStatus;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThingStatus getThingStatus() {
        return thingStatus;
    }

    public void setThingStatus(ThingStatus thingStatus) {
        this.thingStatus = thingStatus;
    }

    public Thing() {
    }

    public Thing(UUID id, String name, ThingStatus thingStatus) {
        this.id = id;
        this.name = name;
        this.thingStatus = thingStatus;
    }
}
