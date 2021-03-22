package APIsolution.Classes;

import java.util.ArrayList;
import java.util.List;

public class APILocation {
    Integer id;
    String name;
    String type;
    List<APILocation> locations;


    public APILocation(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.locations = new ArrayList<>();

    }

    public List<APILocation> getLocations() {
        return locations;
    }

    public void setLocations(List<APILocation> locations) {
        this.locations = locations;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
