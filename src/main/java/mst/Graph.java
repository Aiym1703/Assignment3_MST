package mst;
import java.io.File;
import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.databind.*;
public class Graph {
    public int id;
    public List<String> nodes;
    public List<Edge> edges;
    public static List<Graph> loadFromJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(filePath));
        if (root.has("graphs")) root = root.get("graphs");

        List<Graph> graphs = new ArrayList<>();
        for (JsonNode gNode : root) {
            Graph g = new Graph();
            g.id = gNode.get("id").asInt();
            g.nodes = new ArrayList<>();
            g.edges = new ArrayList<>();
            for (JsonNode n : gNode.get("nodes")) g.nodes.add(n.asText());
            for (JsonNode e : gNode.get("edges")) {
                g.edges.add(new Edge(
                        e.get("from").asText(),
                        e.get("to").asText(),
                        e.get("weight").asInt()));}
            graphs.add(g);}
        return graphs;}}
