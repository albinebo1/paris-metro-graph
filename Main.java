import ADTPackage.StackInterface;
import GraphPackage.DirectedGraph;
import GraphPackage.Station;
import GraphPackage.WalkingEdge;
import GraphPackage.stationInfo;
import ADTPackage.MyStack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static ArrayList<stationInfo> arrayList = new ArrayList<>();
    static DirectedGraph<Station> metro = new DirectedGraph<>();
    static ArrayList<WalkingEdge> walkingEdgeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        readCSVToArrayList("src/Paris_RER_Metro_v2.csv");
        readWalkingEdges("src/walk_edges.txt"); // read files

        addStations(); // call the method where stations are added in the arraylist

        Scanner scanner = new Scanner(System.in); // get user input
        System.out.println("Origin station: ");
        String originStation = scanner.nextLine();
        System.out.println("Destination: ");
        String destination = scanner.nextLine();

        long startTime = System.currentTimeMillis();  // record start time


        printShortest(originStation, destination); // print shortest and cheapest path according to input

        printCheapest(originStation, destination);
        
        long endTime = System.currentTimeMillis();  // record end time
        long elapsedTime = endTime - startTime;  // calculate elapsed time

        System.out.println("Query time: " + elapsedTime + " milliseconds");
    }

    public static void printCheapest(String originStation, String destination) {

        StackInterface<Station> cheapestpath = new MyStack<>();

        double cheapestpathlength = metro.getCheapestPath(getEdgeStation(originStation), getEdgeStation(destination), cheapestpath);

        //print the length and path by popping elements one by one from stack
        System.out.println();
        System.out.println("Cheapest path length: " + cheapestpathlength);
        System.out.println();
        System.out.print("Cheapest path: ");
        System.out.println();
        System.out.println();
        System.out.println("Line " + cheapestpath.peek().getRouteShortName());
        while (!cheapestpath.isEmpty()) {
            String x = cheapestpath.pop().getStopName();

            if (!cheapestpath.isEmpty() && !x.equals(cheapestpath.peek().getStopName())) {
                System.out.print(x + " => ");
            } else if (!cheapestpath.isEmpty() && x.equals(cheapestpath.peek().getStopName())) {
                System.out.println(x + "\nLine " + cheapestpath.peek().getRouteShortName());
            } else if (cheapestpath.isEmpty()) {
                System.out.println(x);
            }


        }
    }

    public static void printShortest(String originStation, String destination) {

        StackInterface<Station> path = new MyStack<>();

        int pathLength = metro.getShortestPath(getEdgeStation(originStation), getEdgeStation(destination), path);

        //print the length and path by popping elements one by one from stack
        System.out.println("Shortest path length: " + pathLength);
        System.out.println();
        System.out.print("Shortest path: ");
        System.out.println();
        System.out.println();
        System.out.println("Line " + path.peek().getRouteShortName());
        while (!path.isEmpty()) {
            String x = path.pop().getStopName();

            if (!path.isEmpty() && !x.equals(path.peek().getStopName())) {
                System.out.print(x + " => ");
            } else if (!path.isEmpty() && x.equals(path.peek().getStopName())) {
                System.out.println(x + "\nLine " + path.peek().getRouteShortName());
            } else if (path.isEmpty()) {
                System.out.println(x);
            }


        }
    }

    public static void readCSVToArrayList(String filePath) throws IOException { // read file and add elements to arraylist

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                int stationId = Integer.parseInt(fields[0].trim());
                String stationName = fields[1].trim();
                int arrivalTime = Integer.parseInt(fields[2].trim());
                int stopSequence = Integer.parseInt(fields[3].trim());
                int directionId = Integer.parseInt(fields[4].trim());
                String routeShortName = fields[5].trim();
                String routeLongName = fields[6].trim();
                int routeType = Integer.parseInt(fields[7].trim());

                Station station = new Station(stationId, arrivalTime, stationName, routeShortName);
                stationInfo stationInfo = new stationInfo(station, stopSequence, directionId, routeLongName, routeType);


                arrayList.add(stationInfo);
            }
        }
    }

    public static void readWalkingEdges(String filePath) throws IOException { // read walking edges file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                String stationName1 = fields[0].trim();
                String stationName2 = fields[1].trim();

                WalkingEdge walkingEdge = new WalkingEdge(stationName1, stationName2);
                walkingEdgeList.add(walkingEdge);
            }

        }
    }

    public static Station getEdgeStation(String stationName) { // converter for getting the equivalent element in arraylist
        Station station = null;                                // when we write the station ourselves
        for (GraphPackage.stationInfo stationInfo : arrayList) {
            if (stationInfo.getStation().getStopName().equals(stationName)) {
                station = stationInfo.getStation();
                break;
            }

        }
        return station;
    }

    public static void addStations() {

        for (int i = 0; i < arrayList.size() - 1; i++) {
            int edgeWeight = getEdgeStation(arrayList.get(i + 1).getStation().getStopName()).getArrivalTime() -
                    getEdgeStation(arrayList.get(i).getStation().getStopName()).getArrivalTime();  // arrival time is time of 2nd stop - time of 1st


            metro.addVertex(arrayList.get(i).getStation()); // add all vertices

            metro.addEdge(arrayList.get(i).getStation(), arrayList.get(i + 1).getStation(), edgeWeight); // add edge from first to second vertex
            metro.addEdge(arrayList.get(i + 1).getStation(), arrayList.get(i).getStation(), edgeWeight); // add edge from second to first vertex


            for (WalkingEdge walkingEdge : walkingEdgeList) {
                if (arrayList.get(i).getStation().getStopName().equals(walkingEdge.getStationName1())) {

                    edgeWeight = getEdgeStation(walkingEdge.getStationName2()).getArrivalTime() - arrayList.get(i).getStation().getArrivalTime();
                    metro.addEdge(arrayList.get(i).getStation(), getEdgeStation(walkingEdge.getStationName2()), edgeWeight); // add edges between walking stops after reading file

                }
            }
            for (GraphPackage.stationInfo stationInfo : arrayList) {
                if (arrayList.get(i).getStation().getStopName().equals(stationInfo.getStation().getStopName()) && // connect stations that are the same for different metro lines
                        !arrayList.get(i).getStation().getRouteShortName().equals(stationInfo.getStation().getRouteShortName())) {

                    metro.addEdge(arrayList.get(i).getStation(), stationInfo.getStation(), edgeWeight);
                    metro.addEdge(stationInfo.getStation(), arrayList.get(i).getStation(), edgeWeight);
                }
            }


        }
    }
}
