package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import util.Graph;

/**
 * 迪杰斯特拉算法
 */
public class Dijkstra {
	private final static int MAXDIS = Integer.MAX_VALUE;
	private final static int DEFPRE = -1;
	
	private Graph graph;
	private int source;
	
	private boolean[] confirmed;
	private int[] pre;
	private int[] dis;
	
	public Dijkstra(Graph graph, int source) {
		this.graph = graph;
		this.source = source;
		
		dis = new int[graph.nodes()];
		pre = new int[graph.nodes()];
		confirmed = new boolean[graph.nodes()];
		
		for (int i = 0; i < dis.length; i++) {
			dis[i] = MAXDIS;
		}
		dis[source] = 0;
		
		for (int i = 0; i < pre.length; i++) {
			pre[i] = DEFPRE;
		}
		pre[source] = source;
		setDis();
	}
	
	public void setDis() {
		while (true) {
			int curdis = MAXDIS; int cur = 0;
			for (int i = 0; i < dis.length; i++) {
				if (!confirmed[i]) {
					if (curdis > dis[i]) {
						curdis = dis[i];
						cur = i;
					}
				}
			}
			
			if (curdis == MAXDIS) {
				break;
			}
			
			for (int n : graph.nextAdj(cur)) {
				int nowDis = curdis + graph.getDis(cur, n);
				if (dis[n] > nowDis) {
					dis[n] = nowDis;
					pre[n] = cur;
				}
			}
			confirmed[cur] = true;
		}
	}
	
	public String getPath(int target) {
		List<String> path = new ArrayList<>();
		
		int cur = target;
		while (true) {
			path.add(cur + "");
			if (pre[cur] == source) {
				path.add(source + "");
				Collections.reverse(path);
				return StringUtils.join(path, " --> ");
			}
			cur = pre[cur]; 
		}
	}
	
}
