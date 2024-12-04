package ru.lyubakova.application.utils;

import java.util.List;

public class TravelingSalesmanProblemSolver {

    public static int[] nearestNeighbor(Double[][] adjacencyMatrix, int startIndex) {
        int numPoints = adjacencyMatrix.length;
        int[] path = new int[numPoints];
        boolean[] visited = new boolean[numPoints];

        int currentPoint = startIndex;
        path[0] = currentPoint;
        visited[currentPoint] = true;

        for (int i = 1; i < numPoints; i++) {
            int nearestNeighbor = -1;
            double minDistance = Double.MAX_VALUE;
            for (int j = 0; j < numPoints; j++) {
                if (!visited[j] && adjacencyMatrix[currentPoint][j] < minDistance) {
                    nearestNeighbor = j;
                    minDistance = adjacencyMatrix[currentPoint][j];
                }
            }
            path[i] = nearestNeighbor;
            visited[nearestNeighbor] = true;
            currentPoint = nearestNeighbor;
        }

        return path;
    }

    public static Double[][] toMatrix(List<List<Double>> matrix) {
        Double[][] distances = new Double[matrix.size()][matrix.size()];
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                distances[i][j] = matrix.get(i).get(j);
            }
        }
        return distances;
    }

}
