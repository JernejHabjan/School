package LETNIK2.Semester2.AIPS2.domaceNaloge.Dn06;

import java.util.*;

class Pair<K, V> {

    private final K element0;
    private final V element1;

    public Pair(K element0, V element1) {
        this.element0 = element0;
        this.element1 = element1;
    }

    public K getKey() {
        return element0;
    }

    public V getValue() {
        return element1;
    }
}

public class ShortestPath {

    /// GRAPH
    Vector<String> nodes = new Vector<>();
    private Map<String, Integer> shortestPath = new HashMap<>(); //shranjujes vozlisca na shortest pathu in njeno razdaljo do tu
    private Map<String, String> previous = new HashMap<>(); //key je vozlisce, value je pa prev --- pazi start ima null
    public Map<String, List<Pair<String, Integer>>> edges = new HashMap<>(); //vsak vozlisce ima listo useh svojih sosedov pa weight do njih

    public boolean addNode(String s) {
        for (String n : nodes) {
            if (n.equals(s)) return false;
        }
        nodes.add(s);
        return true;
    }

    public Vector<String> getNodes() {
        return nodes;
    }

    public void addEdge(String a, String b, int w) {
        //System.out.println("input: "+ a +" "+ b +" "+ w);
        List<Pair<String, Integer>> value = edges.get(a);
        if (value != null) { //check if utez je ze vpisana za povezavo med A-B
            for (int i = 0; i < value.size(); i++) {
                if (value.get(i).getKey().equals(b)) {
                    //spremenimo utez
                    Pair<String, Integer> new_pair = new Pair<>(b, w);
                    value.remove(value.get(i));
                    value.add(new_pair);
                    //System.out.println("changed old " + a +" "+ b +" "+ w);
                    return;
                }
            }
            //cene dodamo pair
            value.add(new Pair<>(b, w));
            //System.out.println("added to existing list " + a+" " + b+" "+ w);
            return;

        }
        //else dodamo novo povezavo
        List<Pair<String, Integer>> connection = new ArrayList<>();
        connection.add(new Pair<>(b, w));
        edges.put(a, connection);
        //System.out.println("added new " + a+" " + b+" "+ w);
    }

    private String findMin(Vector<String> Q, Map<String, Integer> dist) {
        Integer min = Integer.MAX_VALUE;
        String min_K = null;
        for (String key : Q) {
            //System.out.println(key);
            Integer tmp_distance = dist.get(key);
            //System.out.println(tmp_distance);
            if (tmp_distance < min) {
                min = tmp_distance;
                min_K = key;
            }
        }
        return min_K;
    }

    Integer dist_od_u_v(String start, String dest) {
        List<Pair<String, Integer>> povezave = edges.get(start);
        for (Pair<String, Integer> povezava : povezave) {
            String key = povezava.getKey();
            Integer value = povezava.getValue();


            if (key.equals(dest)) {
                return value;
            }
        }
        return Integer.MAX_VALUE;
    }

    public void computeShortestPaths(String start) {
        /* DIJKSTRA */

        //1. init
        for (String name : nodes) {
            shortestPath.put(name, name.equals(start) ? 0 : Integer.MAX_VALUE); //infinite else start 0
            previous.put(name, null);
        }
        Vector<String> Q = new Vector<>(nodes);
        while (!Q.isEmpty()) {

            String u = findMin(Q, shortestPath); // u is node in Q with smallest distance
            Integer uD = shortestPath.get(u);
            Q.remove(u);
            System.out.println(u);

            if (!edges.containsKey(u)) { //TODO DUNNO--- ZA DIRECTED GRAPH
                break;
            }
            for (Pair<String, Integer> key_value : edges.get(u)) { //for each neighbor v of u:	// where v has not yet been removed from Q.
                String v = key_value.getKey();
                Integer neighD = shortestPath.get(v);


                System.out.println("    sosed " + v);
                // relax
                Integer alt = uD + dist_od_u_v(u, v);

                if (alt < neighD) {// Relax (u,v)
                    shortestPath.put(v, alt);
                    previous.put(v, u);
                    System.out.println("        relaxed iz " + neighD + " na " + alt);
                }
            }

        }
        System.out.println("\nIzpis:");
        for (String k : shortestPath.keySet()) {
            System.out.println(k + " " + shortestPath.get(k));
        }

    }

    /**
     * Returns a list of nodes on the shortest path from the given origin to
     * destination node. Returns null, if a path does not exist or there is a
     * negative cycle in the graph.
     *
     * @param start Origin node
     * @param dest  Destination node
     * @return List of nodes on the shortest path from start to dest or null, if the nodes are not connected or there is a negative cycle in the graph.
     */
    public Vector<String> getShortestPath(String start, String dest) {
        //preveri za neg utez:
        for (String U : edges.keySet()) {
            List<Pair<String, Integer>>l = edges.get(U);
            for (Pair <String, Integer> p : l){
                String V = p.getKey();
                Integer w = p.getValue();

                if(shortestPath.get(U)  == Integer.MAX_VALUE) continue;
                if(shortestPath.get(U) + w < shortestPath.get(V)){
                    System.out.println("ZAZNAN NEGATIVEN CIKEL");
                    return null;
                }
            }
        }

        Vector<String> path = new Vector<>();

        boolean found_end = false;
        String dest_node = dest;

        while (dest_node != null) {
            path.add(dest_node);
            if(dest_node.equals(start)){
                found_end = true;
                break;

            }
            System.out.println(dest_node);
            dest_node = previous.get(dest_node);

        }
        if(!found_end){return null;}

        Collections.reverse(path);
        return path;
    }

    /**
     * Returns the distance of the shortest path from the given origin to
     * destination node. Returns Integer.MIN_VALUE, if a path does not exist
     * or there is a negative cycle in the graph.
     *
     * @param start Origin node
     * @param dest  Destination node
     * @return Distance of the shortest path from start to dest or Integer.MIN_VALUE, if the nodes are not connected or there is a negative cycle in the graph.
     */
    public int getShortestDist(String start, String dest) {
        Vector<String> path = getShortestPath(start, dest);
        if (path == null) return Integer.MIN_VALUE;            //neg cikelj
        else if (path.size() == 0) return Integer.MAX_VALUE;  //nista povezani
        else return getPathLength(path);                      //distance

    }

    private int getPathLength(Vector<String> path) {
        if (path.size() == 1) return 0;

        Integer distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String U = path.elementAt(i);
            String V = path.elementAt(i + 1);

            for (Pair<String, Integer> p : edges.get(U)) {
                if (p.getKey().equals(V)) {
                    distance += p.getValue();
                    break;
                }
            }

        }
        System.out.println("PATH:");
        for (String v : path) {
            System.out.println(v);
        }
        System.out.println("DISTANCE PATHA " + distance);
        return distance;
    }
}
