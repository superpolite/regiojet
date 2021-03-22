package APIsolution.Classes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;


public class LocationFinder {

    public APILocation findLocationByName(String name) throws IOException {
        JSONArray json = JSONHelper.readJsonArrayFromUrl("https://brn-ybus-pubapi.sa.cz/restapi/consts/locations");
        for (int i = 0; i < json.length(); i++) {
            JSONObject json_kantry = json.getJSONObject(i);
            JSONArray cities = json_kantry.getJSONArray("cities");

            for (int j = 0; j < cities.length(); j++) {
                JSONObject city = cities.getJSONObject(j);
                String cityName = city.getString("name");
                if (cityName.toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))) {
                    APILocation cityAPI = new APILocation(city.getInt("id"), cityName, "CITY");
                    JSONArray stations = city.getJSONArray("stations");
                    for (int k = 0; k < stations.length(); k++) {
                        JSONObject station = stations.getJSONObject(k);
                        APILocation stationAPI = new APILocation(station.getInt("id"),station.getString("name"),"STATION");
                        cityAPI.getLocations().add(stationAPI);
                    }

                    return cityAPI;
                }
            }
        }
        return null;
    }


}
