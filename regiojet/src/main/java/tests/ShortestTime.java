package tests;
import com.codeborne.selenide.Configuration;
import solution.RegioJet_SearchRouteClass;

import static com.codeborne.selenide.Selenide.*;


public class ShortestTime {
    public ShortestTime() {
        Data.clearAcquiredData();
    }


    public  void run() {

        //Configuration
        Configuration.startMaximized=true;
        Configuration.holdBrowserOpen=true;
        Configuration.timeout=20000;
        Configuration.headless=false;

        open("https://shop.regiojet.sk/");

        RegioJet_SearchRouteClass mainPage = new RegioJet_SearchRouteClass();
        mainPage
                .setRouteFrom(Data.from)
                .setRouteTo(Data.to)
                .expandRouteThere()
                .setYearAndMonth(Data.departureDate)
                .setDay(Data.departureDate)
                .search()
                .expandAllConnectionsThere()
                .getTimesOfAllConnectionsThere(Data.departureDate)
                .getTravelTimesOfAllConnectionsThere()
                .getTransfersOfAllConnectionsThere(Data.stopslimit)
                .getPlacesOfAllConnectionsThere(Data.from,Data.to)
                .getPricesOfAllConnectionsThere()
                .calculateShortestTimeRoute()


        ;
    }
}