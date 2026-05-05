package GraphPackage;

public class WalkingEdge {
    private String stationName1;
    private String stationName2;

    public WalkingEdge(String stationName1, String stationName2) {
        this.stationName1 = stationName1;
        this.stationName2 = stationName2;
    }

    public String getStationName1() {
        return stationName1;
    }

    public void setStationName1(String stationName1) {
        this.stationName1 = stationName1;
    }

    public String getStationName2() {
        return stationName2;
    }

    public void setStationName2(String stationName2) {
        this.stationName2 = stationName2;
    }
}