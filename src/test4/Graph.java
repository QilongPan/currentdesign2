package test4;

public interface Graph<V> {
	/** 返回图中的顶点数 */
	public int getSize();

	/** 返回图中的顶点 */
	public java.util.List<V> getVertices();

	/** 返回指定顶点索引的对象 */
	public V getVertex(int index);

	/** 返回指定顶点对象的索引 */
	public int getIndex(V v);

	/** 用指定的索引返回顶点的邻居 */
	public java.util.List<Integer> getNeighbors(int index);

	/** 返回指定顶点的度 */
	public int getDegree(int v);

	/** 返回邻接矩阵 */
	public int[][] getAdjacencyMatrix();

	/** 打印邻接矩阵 */
	public void printAdjacencyMatrix();

	/** 打印边缘 */
	public void printEdges();

	/** 获得深度优先搜索树 */
	public AbstractGraph<V>.Tree dfs(int v);

	/** 获得广度优先搜索树 */
	public AbstractGraph<V>.Tree bfs(int v);

	/**
	 *如果图中不包含一个哈密顿路径，返回一个指定的顶点返回零的哈密顿路径	 
	 */
	public java.util.List<Integer> getHamiltonianPath(V vertex);

	/**
	 * 如果不包含哈密顿路径，返回一个指定的顶点标号返回空的哈密顿路径
	 */
	public java.util.List<Integer> getHamiltonianPath(int inexe);
}
