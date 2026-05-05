# Paris Metro/RER Pathfinding

Java implementation of a graph-based pathfinding system for the Paris Metro and RER network.

## Overview

This project models the Paris public transit system as a directed graph, enabling shortest path and cheapest path calculations between stations. It supports both in-vehicle travel and walking transfers between nearby stations.

## Features

- **Graph representation** of metro/RER network with stations as vertices and routes as edges
- **Shortest path** algorithm (BFS-based) - minimizes number of stops
- **Cheapest path** algorithm (Dijkstra's) - minimizes travel time
- **Walking transfers** between nearby stations
- Custom ADT implementations (Stack, Queue, Dictionary, List)

## Structure

```
src/
├── ADTPackage/          # Abstract data type implementations
│   ├── DictionaryInterface.java
│   ├── SortedLinkedDictionary.java
│   ├── LinkedListWithIterator.java
│   ├── LinkedQueue.java
│   ├── MyStack.java
│   └── ...
└── GraphPackage/        # Graph and transit-specific classes
    ├── DirectedGraph.java
    ├── Vertex.java
    ├── Station.java
    ├── WalkingEdge.java
    └── ...
```

## Data Files

- `Paris_RER_Metro_v2.csv` - Station and route data
- `walk_edges.txt` - Walking connections between nearby stations

## Key Algorithms

- **BFS traversal** for shortest path by stops
- **Dijkstra's algorithm** for minimum-cost path by time
- **Priority queue** implementation for efficient path finding

## Usage

The `Main.java` file sets up the graph and executes pathfinding queries between stations.

## Requirements

- Java 8 or higher
- Data files must be in the same directory as the executable
