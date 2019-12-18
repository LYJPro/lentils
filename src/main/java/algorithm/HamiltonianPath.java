package algorithm;

/**
 * 哈密尔顿路径问题
 * 哈密尔顿路径问题是用来解决一些疑问，如：
 * 一个图中可以通过行走不重复路径，经过每个点。
 * 
 * 哈密尔顿路径通过使用回溯算法求解。
 * 
 * @FAQ 
 * 1. 多个城市之间如何不走重复路的完成所有城市的旅行呢？
 */
public class HamiltonianPath {
	private int[][] graph;
	private boolean visited[];
	
	private int weight;
	private int height;
	
	private boolean success;
	private int specialNode;

	public HamiltonianPath() {
		weight = 5;
		height = 5;
		specialNode = 1;
		
		graph = new int[weight][height];
		visited = new boolean[weight * height];
		
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (!isSpecial(i, j)) {
					hamDfs(reduce(i, j));
					if (success) {
						return;
					}
				}
			}
		}
	}
	
	private boolean isSpecial(int i, int j) {
		int reduce = weight * j + i;
		return reduce == specialNode;
	}
	
	private int reduce(int i, int j) {
		return weight * j + i;
	}
	
	// 四联通，八联通
	private void hamDfs(int v) {
		visited[v] = true;
		
		if (hasLeft(v)) {
			hamDfs(getLeft(v));
		} 
		if (hasRight(v)) {
			hamDfs(getRight(v));
		} 
		if (hasAbove(v)) {
			hamDfs(getAbove(v));
		} 
		if (hasLow(v)) {
			hamDfs(getLow(v));
		}
		if (isAllVisited()) {
			success = true;
			return;
		}
		visited[v] = false;
	}
	
	private boolean isAllVisited() {
		for (int i = 0; i < visited.length; i++) {
			if (i != specialNode) {
				if (!visited[i]) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean isRight(int res) {
		return res >= 0 && res < weight * height - 1 
			&& res != specialNode && !visited[res];
	}
	
	private boolean hasAbove(int i) {
		return isRight(i - weight);
	}
	
	private int getAbove(int i) {
		return i - weight;
	}
	
	private boolean hasLow(int i) {
		return isRight(i + weight);
	}
	
	private int getLow(int i) {
		return i + weight;
	}
	
	private boolean hasLeft(int i) {
		int left = i % weight;
		return left != 0 
			&& i - 1 != specialNode 
			&& !visited[i - 1];
	}
	
	private int getLeft(int i) {
		return i - 1;
	}
	
	private boolean hasRight(int i) {
		int right = i % weight;
		return right != weight - 1 
			&& i + 1 != specialNode 
			&& !visited[i + 1];
	}
	
	private int getRight(int i) {
		return i + 1;
	}
}
