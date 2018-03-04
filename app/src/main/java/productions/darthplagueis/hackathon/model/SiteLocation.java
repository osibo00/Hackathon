package productions.darthplagueis.hackathon.model;

import java.io.Serializable;

public class SiteLocation implements Serializable {

    private String type;
    private float[] coordinates;

    public String getType() {
        return type;
    }

    public float[] getCoordinates() {
        return coordinates;
    }
}
