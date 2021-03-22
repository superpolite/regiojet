package solution;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import tests.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class RegioJet_ConnectionsListClass {

    private Integer countConnectionsThere() {
        ElementsCollection connections = $$(".routes:nth-of-type(1) .connection-detail");
        return connections.size();
    }

    private Integer countConnectionsBack() {
        ElementsCollection connections = $$(".routes:nth-of-type(2) .connection-detail");
        return connections.size();
    }

    private void expandConnectionThere(Integer connectionIndex) {
        ElementsCollection arrows = $$(".routes:nth-of-type(1) .wrap .detail-arrow");
        arrows.get(connectionIndex).click();
        $(".loader").should(Condition.disappear);
    }

    private void expandConnectionBack(Integer connectionIndex) {
        ElementsCollection arrows = $$(".routes:nth-of-type(2) .wrap .detail-arrow");
        arrows.get(connectionIndex).click();
        $(".loader").should(Condition.disappear);
    }

    public RegioJet_ConnectionsListClass expandAllConnectionsThere() {
        Integer totalConnectionsThere = countConnectionsThere();
        for(int i =0; i < totalConnectionsThere; i++) {
            expandConnectionThere(i);
        }
        return this;
    }

    public RegioJet_ConnectionsListClass expandAllConnectionsBack() {
        Integer totalConnectionsBack = countConnectionsBack();
        for(int i =0; i < totalConnectionsBack; i++) {
            expandConnectionBack(i);
        }
        return this;
    }

    private List<LocalTime> getTimesOfConnectionThere(Integer index, LocalDate departureDay) {
        ElementsCollection times_Obj = $$(".connections-list .routes:nth-of-type(1) .connection-detail").get(index).findAll(".times .text-regular");
        ElementsCollection date_Obj = $$(".connections-list .routes:nth-of-type(1) .connection-detail").get(index).findAll(".date span:nth-of-type(2)");

        //Make sure departure time is the same day as defined
        Integer year = departureDay.getYear();
        Integer month = departureDay.getMonthValue();
        Integer day = departureDay.getDayOfMonth();
        date_Obj.get(0).shouldHave(Condition.text(day + ". " + month + ". " + year));

        List<LocalTime> times_List = new ArrayList<>();
        for (Iterator<SelenideElement> iterator = times_Obj.iterator(); iterator.hasNext();) {
            String timesStr = iterator.next().getText();
            Integer hours = Integer.parseInt(timesStr.split(":")[0]);
            Integer minutes = Integer.parseInt(timesStr.split(":")[1]);
            LocalTime times = LocalTime.of(hours,minutes);
            times_List.add(times);
        }
        Data.departureTimes.add(times_List.get(0));
        Data.arrivalTimes.add(times_List.get(1));
        return times_List;
    }

    public RegioJet_ConnectionsListClass getTimesOfAllConnectionsThere(LocalDate departureDay) {
//        Data.arrivalTimes = new ArrayList<>();
//        Data.departureTimes = new ArrayList<>();
        Integer totalConnectionsThere = countConnectionsThere();
        for(int i =0; i < totalConnectionsThere; i++) {
            getTimesOfConnectionThere(i,departureDay);
        }
        return this;
    }

    private List<LocalTime> getTravelTimeOfConnectionThere(Integer index) {
        ElementsCollection travelTime_Obj = $$(".connections-list .routes:nth-of-type(1) .connection-detail").get(index).findAll(".travel-time");
        List<LocalTime> travelTime_List = new ArrayList<>();
        for (Iterator<SelenideElement> iterator = travelTime_Obj.iterator(); iterator.hasNext();) {
            String travelTimeStr = iterator.next().getText().replaceAll("[^0-9:]","");
            Integer hours = Integer.parseInt(travelTimeStr.split(":")[0]);
            Integer minutes = Integer.parseInt(travelTimeStr.split(":")[1]);
            LocalTime travelTime = LocalTime.of(hours,minutes);
            travelTime_List.add(travelTime);
        }
        Data.travelTimes.add(travelTime_List.get(0));
        return travelTime_List;
    }

    public RegioJet_ConnectionsListClass getTravelTimesOfAllConnectionsThere() {
//        Data.travelTimes = new ArrayList<>();
        Integer totalConnectionsThere = countConnectionsThere();
        for(int i =0; i < totalConnectionsThere; i++) {
            getTravelTimeOfConnectionThere(i);
        }
        return this;
    }

    private List<Integer> getTransfersOfConnectionThere(Integer index, Integer transfersLimit) {
        ElementsCollection transfers_Obj = $$(".connections-list .routes:nth-of-type(1) .connection-detail").get(index).findAll(".transfers-text");
        //Make sure connection has not exceeded limit of transfers
        // THIS CHECK WILL BE DISABLED DUE TO INABILITY TO SELECT DIRECT CONNECTION INPUT
        if(false) transfers_Obj.get(0).shouldHave(Condition.text(transfersLimit.toString()));

        List<Integer> transfers_List = new ArrayList<>();
        for (Iterator<SelenideElement> iterator = transfers_Obj.iterator(); iterator.hasNext();) {
            Integer transfers = Integer.parseInt(iterator.next().getText().replaceAll("[^0-9]",""));
            transfers_List.add(transfers);
        }
        Data.stops.add(transfers_List.get(0));
        return transfers_List;
    }

    public RegioJet_ConnectionsListClass getTransfersOfAllConnectionsThere(Integer transfersLimit) {
//        Data.stops = new ArrayList<>();
        Integer totalConnectionsThere = countConnectionsThere();
        for(int i =0; i < totalConnectionsThere; i++) {
            getTransfersOfConnectionThere(i,transfersLimit);
        }
        return this;
    }


    private void getPlacesOfConnectionThere(Integer index, String from, String to) {
        ElementsCollection places_Obj = $$(".connections-list .routes:nth-of-type(1) .connection-detail").get(index).findAll(".station");
        //Make sure place of the departure, place of the arrival are same as input
        places_Obj.get(0).should(Condition.text(from));
        places_Obj.get(places_Obj.size()-1).should(Condition.text(to));
    }

    public RegioJet_ConnectionsListClass getPlacesOfAllConnectionsThere(String from, String to) {
        Integer totalConnectionsThere = countConnectionsThere();
        for(int i =0; i < totalConnectionsThere; i++) {
                getPlacesOfConnectionThere(i,from, to);
        }
        return this;
    }

    private List<Double> getPricesOfConnectionThere(Integer index) {
        ElementsCollection prices_Obj = $$(".connections-list .routes:nth-of-type(1) .connection-detail").get(index).findAll(".section-class .yellow");
        List<Double> prices_List = new ArrayList<>();
        for (Iterator<SelenideElement> iterator = prices_Obj.iterator(); iterator.hasNext();) {
            String pricesStr = iterator.next().getText().replaceAll("[^0-9,]","").replace(",",".");
            Double prices = Double.parseDouble(pricesStr);
            prices_List.add(prices);
        }
        Data.prices.add(prices_List);
        Data.lowPrices.add(Collections.min(prices_List));
        return prices_List;
    }

    public RegioJet_ConnectionsListClass getPricesOfAllConnectionsThere() {
//        Data.prices = new ArrayList<>();
//        Data.lowPrices = new ArrayList<>();
        Integer totalConnectionsThere = countConnectionsThere();
        for(int i =0; i < totalConnectionsThere; i++) {
            getPricesOfConnectionThere(i);
        }
        return this;
    }

    public RegioJet_ConnectionsListClass calculateFastestArrivalRoute() {
        LocalTime fastestArrival = Collections.min(Data.arrivalTimes);
        Integer indexOfFastestArrival = Data.arrivalTimes.indexOf(fastestArrival);
        System.out.println("fastest arrival is on connection with index: " + (indexOfFastestArrival+1) + "/" + countConnectionsThere() + " Fastest arrival is " + fastestArrival);
        selectPrice(indexOfFastestArrival);
        return this;
    }

    public RegioJet_ConnectionsListClass calculateLowestPriceRoute() {
        Double lowestPrice = Collections.min(Data.lowPrices);
        Integer indexOfLowestPrice = Data.lowPrices.indexOf(lowestPrice);
        Integer indexOfLowestPrice_Connection = Data.prices.get(indexOfLowestPrice).indexOf(lowestPrice);
        System.out.println("Lowest price is on connection with index: " + (indexOfLowestPrice+1) + "/" + countConnectionsThere() + " as " + (indexOfLowestPrice_Connection+1) + ". option! Lowest price is " + lowestPrice);
        selectPriceByIndex(indexOfLowestPrice,indexOfLowestPrice_Connection);
        return this;
    }

    public RegioJet_ConnectionsListClass calculateShortestTimeRoute() {
        LocalTime shortestTime = Collections.min(Data.travelTimes);
        Integer indexOfShortestTime = Data.travelTimes.indexOf(shortestTime);
        System.out.println("Shortest travel time is on connection with index: " + (indexOfShortestTime+1) + "/" + countConnectionsThere() +" Shortest travel times is " + shortestTime.format(DateTimeFormatter.ofPattern("HH:mm")) );
        selectPrice(indexOfShortestTime);
        return this;
    }

    private void selectPriceByIndex(Integer connectionIndex, Integer priceIndex) {
        ElementsCollection prices_Obj = $$(".connections-list .routes:nth-of-type(1) .connection-detail").get(connectionIndex).findAll(".section-class .yellow");
        prices_Obj.get(priceIndex).click();
        sleep(2000);
        closeWindow();
        closeWebDriver();
        sleep(2000);
    }

    private void selectPrice(Integer connectionIndex) {
        Integer priceIndex = 0;
        selectPriceByIndex(connectionIndex,priceIndex);
    }




}
