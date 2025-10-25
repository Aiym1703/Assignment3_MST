package mst;

import java.util.*;

public class Prim {
    public static MSTResult run(Graph graph) {
        long start = System.nanoTime();
        int operations = 0;
        double totalCost = 0;
        List<Edge> mstEdges = new ArrayList<>();

        if (graph.nodes.isEmpty()) return new MSTResult(mstEdges, 0, 0, operations);

        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        String startNode = graph.nodes.get(0);
        visited.add(startNode);
        for (Edge e : graph.edges) {
            if (e.from.equals(startNode) || e.to.equals(startNode)) {
                pq.add(e);
                operations++;
            }
        }

        while (!pq.isEmpty() && mstEdges.size() < graph.nodes.size() - 1) {
            Edge edge = pq.poll();
            operations++;
            String nextNode = visited.contains(edge.from) ? edge.to : edge.from;

            if (!visited.contains(nextNode)) {
                visited.add(nextNode);
                mstEdges.add(edge);
                totalCost += edge.weight;

                for (Edge e : graph.edges) {
                    if ((visited.contains(e.from) && !visited.contains(e.to)) ||
                            (visited.contains(e.to) && !visited.contains(e.from))) {
                        pq.add(e);
                        operations++;
                    }
                }
            }
        }

        long end = System.nanoTime();
        double execMs = (end - start) / 1_000_000.0;
        return new MSTResult(mstEdges, totalCost, execMs, operations);
    }
}
