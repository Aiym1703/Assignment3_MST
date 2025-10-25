# MST Java Project (Prim & Kruskal)

This project solves the Minimum Spanning Tree problem for a city transportation network using:
- ✅ Prim’s Algorithm
- ✅ Kruskal’s Algorithm

## 📂 Project Structure

```
mst_java_project/
├── src/
│   ├── main/java/mst/        # Java source files
│   └── test/java/mst/        # JUnit tests
├── data/                     # Input JSON files
├── results/                  # Output JSON + CSV files
├── docs/                     # Report
├── pom.xml                   # Maven config
```

## 🚀 How to Run

### Prerequisites
- Java 11 or higher
- Maven installed

### Compile and Run
```bash
cd mst_java_project
mvn compile exec:java -Dexec.mainClass="mst.Main"
```

### Output
- 📤 `results/ass_3_output.json`: MST edges, costs, operations
- 📊 `results/result_summary.csv`: Summary table

## 🧪 How to Test

Run unit tests with:
```bash
mvn test
```

Includes:
- MST cost comparison (Prim vs Kruskal)
- Edge count = V - 1
- Cycle detection
- Disconnected graph handling
- Time and performance checks

## 📥 Input Format

```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C"],
      "edges": [
        {"from": "A", "to": "B", "weight": 2},
        {"from": "B", "to": "C", "weight": 3}
      ]
    }
  ]
}
```

## 🧾 Output Format

```json
{
  "results": [
    {
      "graph_id": 1,
      "input_stats": { "vertices": 3, "edges": 2 },
      "prim": {
        "mst_edges": [...],
        "total_cost": 5
      },
      "kruskal": {
        ...
      }
    }
  ]
}
```

---

Feel free to customize the code, inputs, or visualizations as needed!# Assignment3_MST
