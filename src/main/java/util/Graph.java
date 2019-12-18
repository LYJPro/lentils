package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Graph {
	private int n;
	private int r;
	private TreeMap<Integer, Integer>[] connectAdj;
	
	@SuppressWarnings("unchecked")
	public Graph() {
		File file = new File(Graph.class.getResource("").getPath() + File.separator + "Graph.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String firstLine = br.readLine();
			
			n = Integer.valueOf(firstLine.split(" ")[0]);
			r = Integer.valueOf(firstLine.split(" ")[1]);
			
			connectAdj = new TreeMap[n];
			for (int i = 0; i < n; i++) {
				connectAdj[i] = new TreeMap<>();
			}
			
			while (br.ready()) {
				String line = br.readLine();
				int a = Integer.valueOf(line.split(" ")[0]);
				int b = Integer.valueOf(line.split(" ")[1]);
				int dis = Integer.valueOf(line.split(" ")[2]);

				connectAdj[a].put(b, dis);
				connectAdj[b].put(a, dis);
			}
		} catch (Exception exception) {}
	}
	
	public int nodes() {
		return n;
	}
	
	public int releations() {
		return r;
	}
	
	public Iterable<Integer> adj(int n) {
		return connectAdj[n].keySet();
	}
	
	/**
	 * 查询节点下的所有节点
	 * @param n
	 */
	public Iterable<Integer> nextAdj(int n) {
		return connectAdj[n].keySet();
	}
	
	/**
	 * 查询距离
	 * @param n
	 */
	public int getDis(int s, int t) {
		return connectAdj[s].get(t);
	}
	
	/**
	 * 求两点之间的所有路径
	 * @param source
	 * @param target
	 */
	public List<LinkedList<Integer>> getAllPath(int source, int target) {
		AllGraphPath allGraphPath = new AllGraphPath(source, target);
		return allGraphPath.getAllPath();
	}
	
	/**
	 * 查询所有路径类
	 */
	private class AllGraphPath {
		private final static int INIT_PRE = -1;
		private final static int MAX_LEVEL = 50;
		
		// 递归查询记录路径
		private boolean pathVisited[][];
		
		// 生成pre矩阵
		private boolean[][] visited;
		private int[][] next;
		
		// 层序遍历层级
		private int level;

		// 广度优先遍历 
		private LinkedList<Node> queue = new LinkedList<>();
		
		private int source;
		private int target;
		
		// 递归生成节点
		private Node node; // Tree Node;

		// 所有路径结果
		List<LinkedList<Integer>> allPathList = new ArrayList<>(); // result
		
		/**
		 * @param graph
		 * @param source 始发点
		 * @param target 终止点
		 */
		public AllGraphPath(int source, int target) {
			int nodes = nodes();
			
			next = new int[nodes][nodes];
			visited = new boolean[nodes][nodes];
			pathVisited = new boolean[nodes][nodes];
			
			for (int i = 0; i < next.length; i++) {
				for (int j = 0; j < next[i].length; j++) {
					next[i][j] = INIT_PRE;
				}
			}
			
			this.source = source;
			this.target = target;
			
			allPathRec(source, source);
			createNode();
		}
		
		/**
		 * 图的所有路径遍历
		 * @param source
		 * @param son
		 */
		private void allPathRec(int source, int w) {
			visited[w][source] = true;
			next[w][source] = source;
			
			for (Integer n : nextAdj(source)) {
				if (!visited[source][n]) {
					allPathRec(n, source);
				}
			}
		}
		
		/**
		 * 判断两点之间是否联通
		 */
		private boolean isConnectedComponent() {
			// TODO 有向图联通，需要考虑被连接和连接情况
			return true;
		}
		
		/**
		 * 创建节点
		 */
		private void createNode() {
			node = new Node(source, 0);
			queue.add(node);
			setPath();
		}
		
		/**
		 * 层序遍历、广度优先遍历next，判断层数是否达到最大值并且队列中是否有数据。
		 * 每遍历一层，将Node中h + 1，判断h遍历到的最深层级，如果达到最高则不继续遍历
		 */
		private void setPath() {
			while (queue.peek() != null && level < MAX_LEVEL) {
				Node topNode = queue.remove();
				int h = topNode.getH() + 1;
				int n = topNode.getN();
				level = h;
				int[] sonArr = next[n];
				
				List<Node> nextNodes = new ArrayList<>();
				for (int i = 0; i < sonArr.length; i++) {
					int son = sonArr[i]; // next -> topNode
					if (son != INIT_PRE && !pathVisited[n][son]) {
						Node nextNode = new Node(son, h);
						// 如果遍历到source则不再放入队列中
						if (son != target) {
							queue.add(nextNode);
						}
						nextNodes.add(nextNode);
						pathVisited[n][son] = true;
					}
				}
				topNode.setTree(nextNodes);
			}
		}
		
		/**
		 * 根据生成的树结构，查询所有路径
		 * 递归节点时有可能出现三种终止情况：
		 * 1. 出现环形路径
		 * 2. 到达source节点
		 * 3. 遍历结束未到达source节点
		 * 所有需要判断allPath每个集合的get(0)是否等于source值
		 */
		public List<LinkedList<Integer>> getAllPath() {
			if (!isConnectedComponent()) {
				return allPathList;
			}
			List<Integer> prePath = new ArrayList<>();
			treeDfs(node, prePath);
			
			List<LinkedList<Integer>> filter = new ArrayList<>();
			for (LinkedList<Integer> subList : allPathList) {
				int subSize = subList.size();
				if (subList.get(0) == source && subList.get(subSize - 1) == target) {
					filter.add(subList);
				}
			}
			return filter;
		}

		/**
		 * 深度优先遍历所有树结构
		 * @param node
		 * @param prePath
		 */
		private void treeDfs(Node node, List<Integer> prePath) {
			LinkedList<Integer> tmpPath = new LinkedList<>();
			for (Integer p : prePath) {
				tmpPath.add(p);
			}
			tmpPath.add(node.getN());
			
			for (Node n : node.getTree()) {
				treeDfs(n, tmpPath);
			}
			
			if (node.getTree().size() == 0) {
				allPathList.add(tmpPath);
			}
		}
		
		class Node {
			private int n;
			private int h;
			private List<Node> tree;

			public Node(int n, int h) {
				this.n = n;
				this.h = h;
				this.tree = new ArrayList<>();
			}

			public int getH() {
				return h;
			}

			public int getN() {
				return n;
			}

			public List<Node> getTree() {
				return tree;
			}

			public void setTree(List<Node> tree) {
				this.tree = tree;
			}
		}
	}
}
