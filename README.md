# 📊 MST Algorithm Report – Assignment 3

## ✅ 1. Summary of Input Data and Algorithm Results

The program processed multiple graphs of varying sizes (small, medium, large, extra):
- Input Format: JSON with nodes, edges (with weights)
- Output: JSON and CSV with MST results per graph


Prim’s algorithm is a greedy algorithm used to find a Minimum Spanning Tree (MST) for a weighted undirected graph. It starts from one vertex and repeatedly adds the smallest edge that connects a vertex in the MST to a vertex outside it, until all vertices are connected. It uses a priority queue (min-heap) to efficiently select the next minimum edge.
- 1)Best for: Dense graphs (where number of edges ≈ V²)
- 2)Time complexity: O(E log V)

Kruskal’s algorithm is also a greedy algorithm, but instead of starting from a vertex, it starts by sorting all edges by weight. It adds edges one by one to the MST, skipping edges that form a cycle, which is checked using a Union-Find (Disjoint Set) structure.
- 1)Best for: Sparse graphs (where edges are fewer than V²)
- 2)Time complexity: O(E log E)


Each algorithm records:
- MST edge list
- Total cost
- Vertex and edge count
- Execution time (ms)
- Operations performed (comparisons, unions, etc.)

All data is recorded in:
- results/ass_3_output.json
- results/result_summary.csv

---

## ⚙️ 2. Algorithm Comparison: Prim vs Kruskal

<img width="659" height="580" alt="image" src="https://github.com/user-attachments/assets/698d33e5-1eaa-409b-817a-23383e5e6b46" />

1. Both algorithms correctly computed MSTs with identical costs.
2. Kruskal’s algorithm was consistently faster and required fewer operations.
3. Prim’s algorithm performed well only on small or dense graphs.
4. For large or sparse networks, Kruskal is much more efficient.
5. Real transportation systems are usually sparse, making Kruskal the most practical choice.
6. Experimental data fully matched theoretical expectations: O(E log E) < O(E log V).


- Prim is more efficient on dense graphs, Kruskal excels on sparse graphs.

---

## 📈 3. Conclusion

- Prim's Algorithm is ideal for dense graphs due to heap-based optimization.
- Kruskal’s Algorithm works well with sorted edge lists and sparse connections.
- Implementation complexity is comparable, with Kruskal needing Union-Find.
  In both theory and practice, Kruskal’s algorithm consistently demonstrates superior performance across almost all datasets, especially for real-world transportation or infrastructure optimization tasks involving large-scale, edge-based networks. Its combination of simplicity, speed, and scalability makes it the preferred choice for modern graph-processing systems. While Prim’s algorithm remains an excellent conceptual and educational tool, its reliance on priority queue updates limits its performance on massive or dense networks. Therefore, for practical implementation in city transportation planning, network optimization, or large-scale connectivity problems, Kruskal’s algorithm is the most efficient and reliable choice.
---
- ## References:
- Lecture
 -  GeeksforGeeks. (n.d.). Difference between Prim’s and Kruskal’s algorithm for MST. Retrieved from https://www.geeksforgeeks.org/dsa/difference-between-prims-and-kruskals-algorithm-for-mst/
