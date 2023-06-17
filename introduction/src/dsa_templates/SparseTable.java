package dsa_templates;

// use for idempotent operations(A op A = A) like min, max, gcd, AND, OR etc;
class SparseTable {
	int n, ub;
	int[][] st;
	
	int[] log;
	
	SparseTable(int[] arr){
		this.n = arr.length;
		
		log = new int[n+1];
		getLog();
		
		this.ub = log[n] + 5;
		st = new int[n][ub];
		
		buildSt(arr);
	}
	
	void getLog(){
		for(int i=2;i<=n;i++) log[i] = log[i / 2] + 1;
	}
	
//	O(N*log(N))
	void buildSt(int[] arr) {
		for(int i = 0; i < n; i++) st[i][0] = arr[i];
			
		for(int k = 1; k < ub; k++) {
			for(int i = 0; i + (1 << k) - 1 < n; i++) {
				st[i][k] = Math.min(st[i][k-1], st[i+(1<<(k-1))][k-1]);
			}
		}
	}
	
//	O(1)
	int rangeQ(int qlo, int qhi) {
		if(qlo > qhi) return Integer.MAX_VALUE;
		
		int k = log[qhi - qlo + 1];
		return Math.min(st[qlo][k], st[qhi - (1 << k) + 1][k]);
	}
}

