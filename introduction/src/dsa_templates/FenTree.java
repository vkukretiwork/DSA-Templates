package dsa_templates;

class FenTree {
	int n;
	int[] fenArr;
	
	FenTree(int[] arr) {
		this.n = arr.length;
		fenArr = new int[n+1];
		
		for(int i=0;i<n;i++) update(i, arr[i]);
	}
	
	void update(int ind, int inc) {
		if(ind < 0 || ind >= n) return;
		ind++;
		
		while(ind <= n) {
			fenArr[ind] += inc;
			ind += ind & -ind;
		}
	}
	
	int sum(int ind) {
		if(ind < 0) return 0;
		if(ind >= n) ind = n-1;
		
		ind++;
		int sum = 0;
		
		while(ind > 0) {
			sum += fenArr[ind];
			ind -= ind & -ind;
		}
		
		return sum;
	}
	
	int rangeQ(int lo, int hi) {
		if(lo > hi) return 0;
		return sum(hi) - sum(lo-1);
	}
	
}
