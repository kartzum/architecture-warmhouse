package s.l.f.t.wh_t.dto;

import java.util.UUID;

public class ThingCreateData {
    private UUID id;

    private String name;

    private String thingType;

    private String thingStatus;

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

    public String getThingType() {
        return thingType;
    }

    public void setThingType(String thingType) {
        this.thingType = thingType;
    }

    public String getThingStatus() {
        return thingStatus;
    }

    public void setThingStatus(String thingStatus) {
        this.thingStatus = thingStatus;
    }

    public ThingCreateData() {
    }

    public ThingCreateData(UUID id, String name, String thingType, String thingStatus) {
        this.id = id;
        this.name = name;
        this.thingType = thingType;
        this.thingStatus = thingStatus;
    }
}
