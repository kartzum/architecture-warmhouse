package s.l.f.t.wh_t.dto;

public class ThingInputCommandData {
    private Object payload;

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public ThingInputCommandData() {
    }

    public ThingInputCommandData(String payload) {
        this.payload = payload;
    }
}
