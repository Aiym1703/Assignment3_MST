package mst;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        String inputFile = "data/ass_3_input.json";
        String outputFile = "results/ass_3_output.json";
        String csvFile = "results/result_summary.csv";

        List<Graph> graphs = Graph.loadFromJson(inputFile);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode resultsArray = mapper.createArrayNode();
        new File("results").mkdirs();

        FileWriter csvWriter = new FileWriter(csvFile);
        csvWriter.write("GraphID,Vertices,Edges,PrimCost,KruskalCost,PrimOps,KruskalOps,PrimTimeMs,KruskalTimeMs,SameCost\n");

        for (Graph graph : graphs) {
            MSTResult prim = Prim.run(graph);
            MSTResult kruskal = Kruskal.run(graph);

            ObjectNode graphNode = mapper.createObjectNode();
            graphNode.put("graph_id", graph.id);

            ObjectNode statsNode = mapper.createObjectNode();
            statsNode.put("vertices", graph.nodes.size());
            statsNode.put("edges", graph.edges.size());
            graphNode.set("input_stats", statsNode);

            ObjectNode primNode = mapper.createObjectNode();
            ArrayNode primEdges = mapper.createArrayNode();
            for (Edge e : prim.mstEdges) {
                ObjectNode edgeNode = mapper.createObjectNode();
                edgeNode.put("from", e.from);
                edgeNode.put("to", e.to);
                edgeNode.put("weight", e.weight);
                primEdges.add(edgeNode);
            }
            primNode.set("mst_edges", primEdges);
            primNode.put("total_cost", prim.totalCost);
            primNode.put("operations_count", prim.operations);
            primNode.put("execution_time_ms", prim.executionTimeMs);
            graphNode.set("prim", primNode);

            ObjectNode kruskalNode = mapper.createObjectNode();
            ArrayNode kruskalEdges = mapper.createArrayNode();
            for (Edge e : kruskal.mstEdges) {
                ObjectNode edgeNode = mapper.createObjectNode();
                edgeNode.put("from", e.from);
                edgeNode.put("to", e.to);
                edgeNode.put("weight", e.weight);
                kruskalEdges.add(edgeNode);}
            kruskalNode.set("mst_edges", kruskalEdges);
            kruskalNode.put("total_cost", kruskal.totalCost);
            kruskalNode.put("operations_count", kruskal.operations);
            kruskalNode.put("execution_time_ms", kruskal.executionTimeMs);
            graphNode.set("kruskal", kruskalNode);

            resultsArray.add(graphNode);

            csvWriter.write(String.format(
                    "%d,%d,%d,%.2f,%.2f,%d,%d,%.3f,%.3f,%s\n",
                    graph.id,
                    graph.nodes.size(),
                    graph.edges.size(),
                    prim.totalCost,
                    kruskal.totalCost,
                    prim.operations,
                    kruskal.operations,
                    prim.executionTimeMs,
                    kruskal.executionTimeMs,
                    prim.totalCost == kruskal.totalCost ? "Yes" : "No"
            ));
        }

        ObjectNode finalResult = mapper.createObjectNode();
        finalResult.set("results", resultsArray);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), finalResult);

        csvWriter.close();
        System.out.println("✅ Results saved to " + outputFile + " and " + csvFile);
    }
}
