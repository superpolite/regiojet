package APIsolution;

import APIsolution.Classes.APIRoute;
import APIsolution.Classes.LocationFinder;
import APIsolution.Classes.RouteFinder;
import APIsolution.Classes.RouteSelector;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class MainClass {



    public static void main(String[] args) throws Exception {
        String locationFrom = "Ostrava";
        String locationTo = "Brno";
        LocalDate nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        LocationFinder lf = new LocationFinder();
        APIsolution.Classes.APILocation from = lf.findLocationByName(locationFrom);
        APIsolution.Classes.APILocation to = lf.findLocationByName(locationTo);
        RouteFinder cf = new RouteFinder();
        List<APIRoute> foundRoutes = cf.findConnections(nextMonday,from,to);
        RouteSelector rs = new RouteSelector();
        rs.validateInputParameters(nextMonday,from,to,foundRoutes);
        rs.findShortestTravelingTime(foundRoutes);
        rs.findFastestArrival(foundRoutes);
        rs.findLowestPriceRoute(foundRoutes);

    }


}
