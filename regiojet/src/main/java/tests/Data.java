package tests;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Data {
    //input values
    public static String from = "Ostrava";
    public static String to = "Brno";
    public static Integer stopslimit = 0;
    public static LocalDate departureDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));

    //Acquired values
    public static List<LocalTime> departureTimes = new ArrayList<>();
    public static List<LocalTime> arrivalTimes = new ArrayList<>();
    public static List<LocalTime> travelTimes = new ArrayList<>();
    public static List<LocalTime> departureLocations = new ArrayList<>();
    public static List<LocalTime> arrivalLocations = new ArrayList<>();
    public static List<Integer> stops = new ArrayList<>();
    public static List<List<Double>> prices = new ArrayList<>();
    public static List<Double> lowPrices = new ArrayList<>();


    public static void clearAcquiredData() {
        //Delete Acquired values
        Data.departureTimes = new ArrayList<>();
        Data.arrivalTimes = new ArrayList<>();
        Data.travelTimes = new ArrayList<>();
        Data.departureLocations = new ArrayList<>();
        Data.arrivalLocations = new ArrayList<>();
        Data.stops = new ArrayList<>();
        Data.prices = new ArrayList<>();
        Data.lowPrices = new ArrayList<>();
    }
}
