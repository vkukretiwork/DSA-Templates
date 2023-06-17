package dsa_templates;
import java.util.*;

class Lca {
	int LOG, n;
	List<List<Integer>> adj;
	int[][] par;
	int[] depth;
	
	Lca(List<List<Integer>> adj, int n){
		this.n = n;
		LOG = 0;
		while((1 << LOG) <= n) LOG++;
		this.adj = adj;
		par = new int[n+1][LOG];
		depth = new int[n+1];
		build(1, 0);
	}
	
	void build(int node, int parent) {
		depth[node] = depth[parent] + 1;
		par[node][0] = parent;
		
		for(int j = 1; j < LOG; j++) {
			par[node][j] = par[par[node][j-1]][j-1];
		}
		
		for(int child : adj.get(node)) {
			if(child != parent) build(child, node);	
		}
	}
	
	int get_lca(int a, int b) {
		if(depth[a] < depth[b]) {
			int temp = a; a = b; b = temp;
		}
		
		int k = depth[a] - depth[b];
		for(int j = LOG - 1; j >= 0; j--) {
			if((k & (1 << j)) != 0) a = par[a][j];
		}
		
		if(a == b) return a;
		
		for(int j = LOG - 1; j >= 0; j--) {
			if(par[a][j] != par[b][j]) {
				a = par[a][j];
				b = par[b][j];
			}
		}
		
		return par[a][0];
	}
	
	int get_dist(int a, int b) {
		return depth[a] + depth[b] - 2 * depth[get_lca(a, b)];
	}
}
