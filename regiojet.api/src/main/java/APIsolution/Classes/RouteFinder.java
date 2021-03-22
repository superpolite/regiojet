package APIsolution.Classes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RouteFinder {


    public List<APIRoute> findConnections(LocalDate date, APILocation fromLocation, APILocation toLocation) throws IOException {
        List<APIRoute> result = new ArrayList<>();
        String connectionUrl = "https://brn-ybus-pubapi.sa.cz/restapi/routes/search/simple";
        connectionUrl+= "?departureDate=" + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        connectionUrl+= "&fromLocationId=" + fromLocation.getId();
        connectionUrl+= "&fromLocationType=" + fromLocation.getType();
        connectionUrl+= "&tariffs=REGULAR";
        connectionUrl+= "&toLocationId=" + toLocation.getId();
        connectionUrl+= "&toLocationType=" + toLocation.getType();
        System.out.println(connectionUrl);
        //https://brn-ybus-pubapi.sa.cz/restapi/routes/search/simple
        // ?departureDate=2021-03-22
        // &fromLocationId=10202000
        // &fromLocationType=CITY
        // &tariffs=REGULAR
        // &toLocationId=10202002
        // &toLocationType=CITY
        JSONObject connectionsJSON = JSONHelper.readJsonObjectFromUrl(connectionUrl);
//        System.out.println(connectionsJSON);
        JSONArray routesJSON = connectionsJSON.getJSONArray("routes");

        for(int i =0; i<routesJSON.length(); i++) {
                JSONObject route = routesJSON.getJSONObject(i);
//                System.out.println(route.toString());
                APIRoute routeAPI = new APIRoute();
                routeAPI.id = route.getString("id");
                routeAPI.departureTime = ZonedDateTime.parse(route.getString("departureTime"));
                routeAPI.travelTime = route.getString("travelTime");
                routeAPI.departureStationId = route.getInt("departureStationId");
                routeAPI.transfersCount = route.getInt("transfersCount");
                routeAPI.arrivalStationId = route.getInt("arrivalStationId");
                routeAPI.arrivalTime =  ZonedDateTime.parse(route.getString("arrivalTime"));
                routeAPI.priceFrom = route.getDouble("priceFrom");
                routeAPI.priceTo = route.getDouble("priceTo");
                result.add(routeAPI);
        }
        return result;
    }
}
