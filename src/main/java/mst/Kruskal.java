package mst;

import java.util.*;

public class Kruskal {
    static class Subset { String parent; int rank; }

    private static String find(Map<String, Subset> subsets, String node) {
        if (!subsets.get(node).parent.equals(node))
            subsets.get(node).parent = find(subsets, subsets.get(node).parent);
        return subsets.get(node).parent;
    }

    private static void union(Map<String, Subset> subsets, String x, String y) {
        String rootX = find(subsets, x);
        String rootY = find(subsets, y);
        if (rootX.equals(rootY)) return;

        if (subsets.get(rootX).rank < subsets.get(rootY).rank)
            subsets.get(rootX).parent = rootY;
        else if (subsets.get(rootX).rank > subsets.get(rootY).rank)
            subsets.get(rootY).parent = rootX;
        else {
            subsets.get(rootY).parent = rootX;
            subsets.get(rootX).rank++;
        }
    }

    public static MSTResult run(Graph graph) {
        long start = System.nanoTime();
        int ops = 0;
        double cost = 0;
        List<Edge> mst = new ArrayList<>();

        graph.edges.sort(Comparator.comparingInt(e -> e.weight));
        Map<String, Subset> subsets = new HashMap<>();
        for (String n : graph.nodes) {
            Subset s = new Subset(); s.parent = n; s.rank = 0;
            subsets.put(n, s);
        }

        for (Edge e : graph.edges) {
            String root1 = find(subsets, e.from);
            String root2 = find(subsets, e.to);
            ops++;
            if (!root1.equals(root2)) {
                mst.add(e);
                cost += e.weight;
                union(subsets, root1, root2);
                ops++;
            }
            if (mst.size() == graph.nodes.size() - 1) break;
        }

        long end = System.nanoTime();
        return new MSTResult(mst, cost, (end - start) / 1_000_000.0, ops);
    }
}
