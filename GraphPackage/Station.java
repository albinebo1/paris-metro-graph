package GraphPackage;

public class Station implements Comparable<Station> {
    private int stationId;
    private String stopName;
    private int arrivalTime;
    private String routeShortName;


    public Station(int stationId,int arrivalTime, String stationName, String routeShortName) {
        this.stationId = stationId;
        this.stopName = stationName;
        this.routeShortName = routeShortName;
        this.arrivalTime = arrivalTime;

    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public String getRouteShortName() {
        return routeShortName;
    }

    public void setRouteShortName(String routeShortName) {
        this.routeShortName = routeShortName;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }


    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }


    @Override
    public int compareTo(Station other) {

        return Integer.compare(this.stationId, other.stationId);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Station other = (Station) obj;
        return stationId == other.stationId && stopName.equals(other.stopName);
    }
}
