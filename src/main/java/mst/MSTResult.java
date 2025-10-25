package mst;

import java.util.List;

public class MSTResult {
    public List<Edge> mstEdges;
    public double totalCost;
    public double executionTimeMs;
    public int operations;

    public MSTResult(List<Edge> mstEdges, double totalCost, double executionTimeMs, int operations) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.executionTimeMs = executionTimeMs;
        this.operations = operations;
    }
}
