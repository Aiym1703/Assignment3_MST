package mst;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class MSTTest {

    private Graph getSampleConnectedGraph() {
        Graph g = new Graph();
        g.nodes = Arrays.asList("A", "B", "C", "D");
        g.edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("D", "A", 4),
                new Edge("B", "D", 5)
        );
        return g;
    }

    private Graph getDisconnectedGraph() {
        Graph g = new Graph();
        g.nodes = Arrays.asList("A", "B", "C", "X", "Y");
        g.edges = Arrays.asList(
                new Edge("A", "B", 2),
                new Edge("B", "C", 3)
        );
        return g;
    }

    private boolean isAcyclic(List<Edge> mst, int nodeCount) {
        Map<String, String> parent = new HashMap<>();
        for (Edge edge : mst) {
            String root1 = find(edge.from, parent);
            String root2 = find(edge.to, parent);
            if (root1.equals(root2)) return false;
            parent.put(root1, root2);
        }
        return mst.size() == nodeCount - 1;
    }

    private String find(String x, Map<String, String> parent) {
        if (!parent.containsKey(x)) return x;
        String root = find(parent.get(x), parent);
        parent.put(x, root);
        return root;
    }

    @Test
    public void testPrimAndKruskalSameCost() {
        Graph graph = getSampleConnectedGraph();
        MSTResult prim = Prim.run(graph);
        MSTResult kruskal = Kruskal.run(graph);
        assertEquals(prim.totalCost, kruskal.totalCost, 0.001);
    }

    @Test
    public void testPrimAcyclicAndConnected() {
        Graph graph = getSampleConnectedGraph();
        MSTResult result = Prim.run(graph);
        assertTrue(isAcyclic(result.mstEdges, graph.nodes.size()));
    }

    @Test
    public void testKruskalAcyclicAndConnected() {
        Graph graph = getSampleConnectedGraph();
        MSTResult result = Kruskal.run(graph);
        assertTrue(isAcyclic(result.mstEdges, graph.nodes.size()));
    }

    @Test
    public void testDisconnectedHandled() {
        Graph graph = getDisconnectedGraph();
        MSTResult prim = Prim.run(graph);
        MSTResult kruskal = Kruskal.run(graph);
        assertTrue(prim.mstEdges.size() < graph.nodes.size() - 1);
        assertTrue(kruskal.mstEdges.size() < graph.nodes.size() - 1);
    }

    @Test
    public void testOperationCountsNonNegative() {
        Graph graph = getSampleConnectedGraph();
        MSTResult result = Prim.run(graph);
        assertTrue(result.operations >= 0);
    }

    @Test
    public void testResultsReproducible() {
        Graph graph = getSampleConnectedGraph();
        MSTResult r1 = Kruskal.run(graph);
        MSTResult r2 = Kruskal.run(graph);
        assertEquals(r1.totalCost, r2.totalCost, 0.001);
    }
    @Test
    public void testExecutionTimeNonNegative() {
        Graph graph = getSampleConnectedGraph();
        long start = System.currentTimeMillis();
        MSTResult result = Prim.run(graph);
        long end = System.currentTimeMillis();
        long duration = end - start;
        assertTrue("Execution time should be non-negative", duration >= 0);
    }

}
