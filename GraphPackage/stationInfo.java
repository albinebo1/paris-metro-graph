package GraphPackage;

public class stationInfo {
    private Station station;
    private int stopSequence;
    private int directionId;
    private String  routeLongName;
    private int routeType;

    public stationInfo(Station station, int stopSequence, int directionId, String routeLongName, int routeType) {
        this.station = station;
//        this.arrivalTime = arrivalTime;
        this.stopSequence = stopSequence;
        this.directionId = directionId;
        this.routeLongName = routeLongName;
        this.routeType = routeType;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public int getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(int stopSequence) {
        this.stopSequence = stopSequence;
    }

    public int getDirectionId() {
        return directionId;
    }

    public void setDirectionId(int directionId) {
        this.directionId = directionId;
    }

    public String getRouteLongName() {
        return routeLongName;
    }

    public void setRouteLongName(String routeLongName) {
        this.routeLongName = routeLongName;
    }

    public int getRouteType() {
        return routeType;
    }

    public void setRouteType(int routeType) {
        this.routeType = routeType;
    }
}
