package APIsolution.Classes;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RouteSelector
{

    public void findShortestTravelingTime(List<APIRoute> routesAPI) {
        Collections.sort(routesAPI, Comparator.comparing(APIRoute::getTravelTimeSeconds));
        APIRoute shortestTravelTimeRoute = routesAPI.get(0);
        System.out.println("findShortestTravelingTime is: " + shortestTravelTimeRoute.id + " Traveling time: " + shortestTravelTimeRoute.travelTime);
//        for(APIRoute )

    }

    public void findFastestArrival(List<APIRoute> routesAPI) {
        Collections.sort(routesAPI, Comparator.comparing(APIRoute::getArrivalTime));
        APIRoute fastestRoute = routesAPI.get(0);
        System.out.println("findFastestArrival is: " + fastestRoute.id + " Arrival time: " + fastestRoute.arrivalTime);

    }

    public void findLowestPriceRoute(List<APIRoute> routesAPI) {
        Collections.sort(routesAPI, Comparator.comparing(APIRoute::getPriceFrom));
        APIRoute cheapestRoute = routesAPI.get(0);
        System.out.println("findLowestPriceRoute is: " + cheapestRoute.id + " Price: " + cheapestRoute.priceFrom);

    }

    public void validateInputParameters(LocalDate date, APILocation from, APILocation to, List<APIRoute> routesAPI) throws Exception {
        for(APIRoute rAPI: routesAPI ) {
            if(rAPI.departureTime.toLocalDate().isBefore(date) || rAPI.departureTime.toLocalDate().isAfter(date)) {
                throw new Exception("Journey date is either higher ir lower than input date!");
            }
            boolean departureFound = rAPI.departureStationId.equals(from.id);
            if(!departureFound){
                for(APILocation station : from.locations){
                    departureFound = departureFound || rAPI.departureStationId.equals(station.id);
                }
            }
             if(!departureFound) {
                 throw new Exception("Departure doesn't match!");
             }
//
            boolean arrivalFound = rAPI.arrivalStationId.equals(to.id);
            if(!arrivalFound){
                for(APILocation station : to.locations){
                    arrivalFound = arrivalFound || rAPI.arrivalStationId.equals(station.id);
                }
            }
            if(!arrivalFound) {
                throw new Exception("Arrival doesn't match!");
            }

            if(rAPI.transfersCount > 0) {
//                Cannot filter direct connections, cannot provide valid results
//                throw new Exception("Transfer limit exceeded (>0)");
            }

        }

    }
}
