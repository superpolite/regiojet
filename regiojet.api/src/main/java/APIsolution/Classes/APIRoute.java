package APIsolution.Classes;

import java.time.ZonedDateTime;

public class APIRoute {
    String id;
    ZonedDateTime departureTime;
    String travelTime;
    Integer departureStationId;
    Integer transfersCount;
    Integer arrivalStationId;
    ZonedDateTime arrivalTime;
    Double priceFrom;
    Double priceTo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(ZonedDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public Integer getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(Integer departureStationId) {
        this.departureStationId = departureStationId;
    }

    public Integer getTransfersCount() {
        return transfersCount;
    }

    public void setTransfersCount(Integer transfersCount) {
        this.transfersCount = transfersCount;
    }

    public Integer getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(Integer arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public ZonedDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(ZonedDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }

    public Integer getTravelTimeSeconds() {
        String cleanedTime = travelTime.replaceAll("[^0-9:]", "");
        Integer totalSeconds = (Integer.parseInt(cleanedTime.split(":")[0]) * 3600
                + Integer.parseInt(cleanedTime.split(":")[1]) * 60);
        return totalSeconds;
    }

}
