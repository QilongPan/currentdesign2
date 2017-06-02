package test4;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraph<V> implements Graph<V> {
	protected List<V> vertices; // 存储顶点
	protected List<List<Integer>> neighbors; // 邻接表

	/** 从数组中存储的边和顶点构造一个图 */
	protected AbstractGraph(int[][] edges, V[] vertices) {
		this.vertices = new ArrayList<V>();
		for (int i = 0; i < vertices.length; i++)
			this.vertices.add(vertices[i]);

		createAdjacencyLists(edges, vertices.length);
	}

	/** 从列表中存储的边和顶点构造一个图 */
	protected AbstractGraph(List<Edge> edges, List<V> vertices) {
		this.vertices = vertices;
		createAdjacencyLists(edges, vertices.size());
	}

	/** 构建一个图的整数顶点0，1，2和边列表 */
	@SuppressWarnings("unchecked")
	protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
		vertices = new ArrayList<V>(); 
		for (int i = 0; i < numberOfVertices; i++) {
			vertices.add((V) (new Integer(i))); 
		}
		createAdjacencyLists(edges, numberOfVertices);
	}

	/** 构建一个图，从整数的顶点0，1，和边缘阵列 */
	@SuppressWarnings("unchecked")
	protected AbstractGraph(int[][] edges, int numberOfVertices) {
		vertices = new ArrayList<V>(); // 创建顶点
		for (int i = 0; i < numberOfVertices; i++) {
			vertices.add((V) (new Integer(i))); // 顶点为{ 0，1，…}
		}
		createAdjacencyLists(edges, numberOfVertices);
	}

	/** 为每个顶点创建邻接表 */
	private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
		// 创建一个链表
		neighbors = new ArrayList<List<Integer>>();
		for (int i = 0; i < numberOfVertices; i++) {
			neighbors.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			neighbors.get(u).add(v);
		}
	}

	/** 为每个顶点创建邻接表 */
	private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
		// 创建一个链表
		neighbors = new ArrayList<List<Integer>>();
		for (int i = 0; i < numberOfVertices; i++) {
			neighbors.add(new ArrayList<Integer>());
		}

		for (Edge edge : edges) {
			neighbors.get(edge.u).add(edge.v);
		}
	}

	/** 返回图中的顶点数 */
	public int getSize() {
		return vertices.size();
	}

	/** 返回图中的顶点 */
	public List<V> getVertices() {
		return vertices;
	}

	/** 返回指定顶点的对象 */
	public V getVertex(int index) {
		return vertices.get(index);
	}

	/** 返回指定顶点对象的索引 */
	public int getIndex(V v) {
		return vertices.indexOf(v);
	}

	/** 用指定的索引返回顶点的邻居 */
	public List<Integer> getNeighbors(int index) {
		return neighbors.get(index);
	}

	/** 返回指定顶点的度 */
	public int getDegree(int v) {
		return neighbors.get(v).size();
	}

	/** 返回邻接矩阵 */
	public int[][] getAdjacencyMatrix() {
		int[][] adjacencyMatrix = new int[getSize()][getSize()];

		for (int i = 0; i < neighbors.size(); i++) {
			for (int j = 0; j < neighbors.get(i).size(); j++) {
				int v = neighbors.get(i).get(j);
				adjacencyMatrix[i][v] = 1;
			}
		}

		return adjacencyMatrix;
	}

	/** 打印邻接矩阵 */
	public void printAdjacencyMatrix() {
		int[][] adjacencyMatrix = getAdjacencyMatrix();
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[0].length; j++) {
				System.out.print(adjacencyMatrix[i][j] + " ");
			}

			System.out.println();
		}
	}

	/** 打印边缘 */
	public void printEdges() {
		for (int u = 0; u < neighbors.size(); u++) {
			System.out.print("Vertex " + u + ": ");
			for (int j = 0; j < neighbors.get(u).size(); j++) {
				System.out.print("(" + u + ", " + neighbors.get(u).get(j)
						+ ") ");
			}
			System.out.println();
		}
	}

	/** 边缘内部类里面的抽象图形类 */
	public static class Edge {
		public int u; // 边的起点
		public int v; // 边的端点

		/** 构造一个边为 (u, v) */
		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
	}

	/** 获得一个DFS树从顶点v */
	/** To be discussed in Section 27.6 */
	public Tree dfs(int v) {
		List<Integer> searchOrders = new ArrayList<Integer>();
		int[] parent = new int[vertices.size()];
		for (int i = 0; i < parent.length; i++)
			parent[i] = -1; // 初始化 parent[i] 为 -1

		// 标记访问顶点
		boolean[] isVisited = new boolean[vertices.size()];

		// 递归搜索
		dfs(v, parent, searchOrders, isVisited);

		// 返回搜索树 
		return new Tree(v, parent, searchOrders);
	}

	/** DFS搜索递归方法 */
	private void dfs(int v, int[] parent, List<Integer> searchOrders,
			boolean[] isVisited) {
		// 存储访问顶点
		searchOrders.add(v);
		isVisited[v] = true; // 顶点访问

		for (int i : neighbors.get(v)) {
			if (!isVisited[i]) {
				parent[i] = v; // 顶点i的父节点是v
				dfs(i, parent, searchOrders, isVisited); // 递归搜索
			}
		}
	}

	/** 从顶点v开始BFS搜索 */
	/** To be discussed in Section 27.7 */
	public Tree bfs(int v) {
		List<Integer> searchOrders = new ArrayList<Integer>();
		int[] parent = new int[vertices.size()];
		for (int i = 0; i < parent.length; i++)
			parent[i] = -1; // 初始化parent[i]为 -1

		java.util.LinkedList<Integer> queue = new java.util.LinkedList<Integer>(); // list used as a queue 用作队列的列表
		boolean[] isVisited = new boolean[vertices.size()];
		queue.offer(v); // 入队 v
		isVisited[v] = true; // 访问标志

		while (!queue.isEmpty()) {
			int u = queue.poll(); // Dequeue to u
			searchOrders.add(u); // u searched
			for (int w : neighbors.get(u)) {
				if (!isVisited[w]) {
					queue.offer(w); // Enqueue w
					parent[w] = u; // The parent of w is u
					isVisited[w] = true; // Mark it visited
				}
			}
		}

		return new Tree(v, parent, searchOrders);
	}

	
	public class Tree {
		private int root;
		private int[] parent; 
		private List<Integer> searchOrders; 

		
		public Tree(int root, int[] parent, List<Integer> searchOrders) {
			this.root = root;
			this.parent = parent;
			this.searchOrders = searchOrders;
		}

	
		public Tree(int root, int[] parent) {
			this.root = root;
			this.parent = parent;
		}

	
		public int getRoot() {
			return root;
		}

	
		public int getParent(int v) {
			return parent[v];
		}

		public List<Integer> getSearchOrders() {
			return searchOrders;
		}

		public int getNumberOfVerticesFound() {
			return searchOrders.size();
		}

		public List<V> getPath(int index) {
			ArrayList<V> path = new ArrayList<V>();

			do {
				path.add(vertices.get(index));
				index = parent[index];
			} while (index != -1);

			return path;
		}

		public void printPath(int index) {
			List<V> path = getPath(index);
			System.out.print("A path from " + vertices.get(root) + " to "
					+ vertices.get(index) + ": ");
			for (int i = path.size() - 1; i >= 0; i--)
				System.out.print(path.get(i) + " ");
		}

	
		public void printTree() {
			System.out.println("Root is: " + vertices.get(root));
			System.out.print("Edges: ");
			for (int i = 0; i < parent.length; i++) {
				if (parent[i] != -1) {
					System.out.print("(" + vertices.get(parent[i]) + ", "
							+ vertices.get(i) + ") ");
				}
			}
			System.out.println();
		}
	}

	
	public List<Integer> getHamiltonianPath(V vertex) {
		return getHamiltonianPath(getIndex(vertex));
	}

	
	public List<Integer> getHamiltonianPath(int v) {
	
		int[] next = new int[getSize()];
		for (int i = 0; i < next.length; i++)
			next[i] = -1; 

		boolean[] isVisited = new boolean[getSize()];

	
		List<Integer> result = null;

		
		for (int i = 0; i < getSize(); i++)
			reorderNeigborsBasedOnDegree(neighbors.get(i));

		if (getHamiltonianPath(v, next, isVisited)) {
			result = new ArrayList<Integer>(); 
			int vertex = v; 
			while (vertex != -1) {
				result.add(vertex);
				vertex = next[vertex];
			}
		}

		return result; 
	}

	
	private void reorderNeigborsBasedOnDegree(List<Integer> list) {
		for (int i = list.size() - 1; i >= 1; i--) {
		
			int currentMaxDegree = getDegree(list.get(0));
			int currentMaxIndex = 0;

			for (int j = 1; j <= i; j++) {
				if (currentMaxDegree < getDegree(list.get(j))) {
					currentMaxDegree = getDegree(list.get(j));
					currentMaxIndex = j;
				}
			}

			
			if (currentMaxIndex != i) {
				int temp = list.get(currentMaxIndex);
				list.set(currentMaxIndex, list.get(i));
				list.set(i, temp);
			}
		}
	}

	private boolean allVisited(boolean[] isVisited) {
		boolean result = true;

		for (int i = 0; i < getSize(); i++)
			result = result && isVisited[i];

		return result;
	}

	
	private boolean getHamiltonianPath(int v, int[] next, boolean[] isVisited) {
		isVisited[v] = true; 

		if (allVisited(isVisited))
			return true; 

		for (int i = 0; i < neighbors.get(v).size(); i++) {
			int u = neighbors.get(v).get(i);
			if (!isVisited[u] && getHamiltonianPath(u, next, isVisited)) {
				next[v] = u; 
				return true;
			}
		}

		isVisited[v] = false; 
		return false; 
	}

	public void addVertex(V vertex) {
		vertices.add(vertex);
		neighbors.add(new ArrayList<Integer>());
	}

	public void addEdge(int u, int v) {
		neighbors.get(u).add(v);
		neighbors.get(v).add(u);
	}
}
