package test4;

public interface Graph<V> {
	/** ����ͼ�еĶ����� */
	public int getSize();

	/** ����ͼ�еĶ��� */
	public java.util.List<V> getVertices();

	/** ����ָ�����������Ķ��� */
	public V getVertex(int index);

	/** ����ָ�������������� */
	public int getIndex(V v);

	/** ��ָ�����������ض�����ھ� */
	public java.util.List<Integer> getNeighbors(int index);

	/** ����ָ������Ķ� */
	public int getDegree(int v);

	/** �����ڽӾ��� */
	public int[][] getAdjacencyMatrix();

	/** ��ӡ�ڽӾ��� */
	public void printAdjacencyMatrix();

	/** ��ӡ��Ե */
	public void printEdges();

	/** ���������������� */
	public AbstractGraph<V>.Tree dfs(int v);

	/** ��ù������������ */
	public AbstractGraph<V>.Tree bfs(int v);

	/**
	 *���ͼ�в�����һ�����ܶ�·��������һ��ָ���Ķ��㷵����Ĺ��ܶ�·��	 
	 */
	public java.util.List<Integer> getHamiltonianPath(V vertex);

	/**
	 * ������������ܶ�·��������һ��ָ���Ķ����ŷ��ؿյĹ��ܶ�·��
	 */
	public java.util.List<Integer> getHamiltonianPath(int inexe);
}
