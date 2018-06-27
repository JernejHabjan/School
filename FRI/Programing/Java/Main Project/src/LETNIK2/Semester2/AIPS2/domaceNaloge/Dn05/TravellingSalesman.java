package LETNIK2.Semester2.AIPS2.domaceNaloge.Dn05;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class TravellingSalesman {


    class Node {
        int id;
        int x;
        int y;


        Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

    }

    public class Route {
        Route() {
        }

        Route(int n) {
            add_route_node(n);
        }

        ArrayList<Integer> route_indices = new ArrayList<>();
        private double distance = 0;


        void add_route_node(int n) {
            route_indices.add(n);
            if (route_indices.size() > 1)
                calculate_distance();
        }

        void calculate_distance() {
            distance += distanceMatrix[route_indices.size() - 1][route_indices.size() - 2];
        }

        double getDistance() {
            return distance;
        }

        int getLength() {
            return route_indices.size();
        }
    }


    /**
     * To solve the travelling salesman problem (TSP) you need to find a shortest
     * tour over all nodes in the graph where each node must be visited exactly
     * once. You need to finish at the origin node.
     * <p>
     * In this task we will consider complete undirected graphs only with
     * Euclidean distances between the nodes.
     */

    private List<Node> nodes = new ArrayList<>();
    private double[][] distanceMatrix;

    private double[][] calculateDistanceMatrix() {
        double[][] tmpDistanceMatrix = new double[nodes.size()][nodes.size()];
        for (int i = 0; i < nodes.size(); i++)
            for (int j = 0; j < nodes.size(); j++)
                tmpDistanceMatrix[i][j] = getDistance(i, j);
        return tmpDistanceMatrix;
    }

    public void addNode(int x, int y) {
        shortestTourStart = -1;
        nodes.add(new Node(nodes.size(), x, y));
        distanceMatrix = calculateDistanceMatrix(); // maybe optimize... zdaj se za vsak add zracuna vse se enkrat
    }

    public double getDistance(int v1, int v2) {
        Node v1_node = nodes.get(v1);
        Node v2_node = nodes.get(v2);
        return Math.sqrt(Math.pow((v1_node.x - v2_node.x), 2) + Math.pow((v1_node.y - v2_node.y), 2));
    }

    /**
     * Finds and returns an optimal shortest tour from the origin node and
     * returns the order of nodes to visit.
     * <p>
     * Implementation note: Avoid exploring paths which are obviously longer
     * than the existing shortest tour.
     *
     * @param start Index of the origin node
     * @return List of nodes to visit in specific order
     */

    private static int shortestTourStart = -1;
    double temp_shortestpath_distance = Double.MAX_VALUE;
    private Route shortestTour = new Route();
    private static LinkedList<String> stdoutHistory = new LinkedList<>();

    private void permute(Route route, int k) {
        if (k == route.route_indices.size() - 1) {
            int[] temp = toIntArray(route);
            double temp_distance = calculateDistanceTravelled(temp);

            if (temp_distance < temp_shortestpath_distance) {
                temp_shortestpath_distance = temp_distance;
                shortestTour = route;

                System.out.println(temp_shortestpath_distance);
                StringBuilder s = new StringBuilder();
                for (Integer bla : shortestTour.route_indices)
                    s.append(bla).append(" ");
                stdoutHistory.add(s.toString());

            }
        } else {
            for (int i = k; i < route.route_indices.size(); i++) {


                //CHECK:
                int[] temp = toIntArray(route);
                temp = Arrays.copyOfRange(temp, 0, k+1);

                double temp_distance = calculateDistanceTravelled(temp);
                if (temp_distance >= temp_shortestpath_distance) {
                    continue;
                }
                //endcheck

                java.util.Collections.swap(route.route_indices, i, k);

                permute(route, k + 1);


                java.util.Collections.swap(route.route_indices, i, k);
            }
        }


    }

    int[] toIntArray(Route r) {
        int[] tmp = new int[r.route_indices.size()];
        for (int i = 0; i < r.route_indices.size(); i++) {
            tmp[i] = r.route_indices.get(i);
        }
        return tmp;
    }

    public Route getExactTour(int start) {
        if (start != -1 && start == shortestTourStart) {
            String[] str = stdoutHistory.get(stdoutHistory.size() - 1).split(" ");
            Route finalRoute = new Route();
            for (int i = 0; i < str.length; i++) {
                //System.out.println(str[i]);
                finalRoute.add_route_node(Integer.parseInt(str[i]));
            }
            return finalRoute;
        }




        shortestTourStart = start;
        shortestTour = new Route();
        temp_shortestpath_distance = Double.MAX_VALUE;


        System.out.println(start);

        for (Node node : nodes) {
            shortestTour.add_route_node(node.id);
        }

        if(start != 0){

            java.util.Collections.swap(shortestTour.route_indices, 0, start);
            shortestTourStart = 0;
            start = 0;

        }

        permute(shortestTour, start);

        String[] str = stdoutHistory.get(stdoutHistory.size() - 1).split(" ");
        Route finalRoute = new Route();
        for (int i = 0; i < str.length; i++) {

            finalRoute.add_route_node(Integer.parseInt(str[i]));
        }


        return finalRoute;
    }


    public int[] calculateExactShortestTour(int start) {

        return toIntArray(getExactTour(start));
    }

    /**
     * Returns an optimal shortest tour and returns its distance given the
     * origin node.
     *
     * @param start Index of the origin node
     * @return Distance of the optimal shortest TSP tour
     */
    public double calculateExactShortestTourDistance(int start) {
        Route bla = getExactTour(start);
        //System.out.println("neki " + calculateDistanceTravelled(toIntArray(bla)));
        return calculateDistanceTravelled(toIntArray(bla));
    }


    /**
     * Returns an approximate shortest tour and returns the order of nodes to
     * visit given the origin node.
     * <p>
     * Implementation note: Use a greedy nearest neighbor apporach to construct
     * an initial tour. Then use iterative 2-opt method to improve the
     * solution.
     *
     * @param start Index of the origin node
     * @return List of nodes to visit in specific order
     */
    private int[] neki2(int start) { //TODO
        int[] initial_run = nearestNeighborGreedy(start);
        return twoOptExchange(initial_run);
    }

    public int[] calculateApproximateShortestTour(int start) {
        return neki2(start);
    }

    /**
     * Returns an approximate shortest tour and returns its distance given the
     * origin node.
     *
     * @param start Index of the origin node
     * @return Distance of the approximate shortest TSP tour
     */
    public double calculateApproximateShortestTourDistance(int start) {
        return calculateDistanceTravelled(neki2(start));
    }


    private int nearest(int id, boolean unmarked_condition, Route r) {
        int index = -1;
        double min = Double.MAX_VALUE;
        double[] vrsta = distanceMatrix[id];
        for (int i = 0; i < vrsta.length; i++) {
            if (vrsta[i] != 0 && vrsta[i] <= min) {
                if (unmarked_condition) {
                    if (!r.route_indices.contains(i)) {
                        min = vrsta[i];
                        index = i;
                    }
                } else {
                    min = vrsta[i];
                    index = i;
                }
            }
        }
        return index;
    }

    public int[] nearestNeighborGreedy(int start) {
        Route route = new Route(start);
        int temp = start;
        while (route.route_indices.size() != nodes.size()) {
            int nearest = nearest(temp, true, route);
            route.add_route_node(nearest);
            temp = nearest;

        }
        return route.route_indices.stream().mapToInt(i -> i).toArray(); //convert to normal int array
    }

    /**
     * Improves the given route using 2-opt exchange.
     * <p>
     * Implementation note: Repeat the procedure until the route is not
     * improved in the next iteration anymore.
     *
     * @param route Original route
     * @return Improved route using 2-opt exchange
     */
    private int[] twoOptExchange(int[] route) {
        int[] tmp_route = route;
        int index = 0;
        while (true) {
            double bestDistance = calculateDistanceTravelled(tmp_route);
            double prev_best_distance = bestDistance;


            for (int i = 0; i < tmp_route.length - 1; i++) {
                boolean nastavi = true;
                for (int k = i + 1; k < tmp_route.length; k++) {
                    int[] new_route = twoOptSwap(tmp_route, i, k);
                    double new_distance = calculateDistanceTravelled(new_route);
                    if (new_distance < bestDistance) {
                        tmp_route = new_route;
                        nastavi = false;
                        break;

                    }
                }
                if (nastavi == false) break;
            }
            index++;

            if (prev_best_distance == calculateDistanceTravelled(tmp_route)) {
                System.out.println("broke" + index);
                break;
            }


        }


        return tmp_route;
    }

    /**
     * Swaps the nodes i and k of the tour and adjusts the tour accordingly.
     * <p>
     * Implementation note: Consider the new order of some nodes due to the
     * swap!
     *
     * @param route Original tour
     * @param i     Name of the first node
     * @param k     Name of the second node
     * @return The newly adjusted tour
     */
    public int[] twoOptSwap(int[] route, int i, int k) {
        ArrayList<Integer> routeNew = new ArrayList<>();
        for (int j = 0; j < i; j++) routeNew.add(route[j]);
        for (int j = k; j >= i; j--) routeNew.add(route[j]);
        for (int j = k + 1; j < route.length; j++) routeNew.add(route[j]);
        return routeNew.stream().mapToInt(index -> index).toArray();
    }

    public double calculateDistanceTravelled(int[] tour) {
        if (tour.length == 0) return 0;
        double dist = 0;
        for (int i = 0; i < tour.length - 1; i++)
            dist += getDistance(tour[i], tour[i + 1]);
        dist += getDistance(tour[0], tour[tour.length - 1]);
        return dist;
    }
}
