package dsa_templates;

class SgTree {
	
	int[] sgArr;
	int n;
	
	public SgTree(int[] arr){
		this.n = arr.length;
		sgArr = new int[4*n];
		
		build(0, 0, n-1, arr);
	}
	
	private void build(int ind, int lo, int hi, int[] arr) {
		if(lo > hi) return;
		
		if(lo == hi) {
			sgArr[ind] = arr[lo];
			return;
		}
		
		int mid = lo + (hi-lo)/2;
		build(2*ind+1, lo, mid, arr);
		build(2*ind+2, mid+1, hi, arr);
		
		sgArr[ind] = Math.min(sgArr[2*ind+1], sgArr[2*ind+2]);
	}
	
	public void update(int pos, int inc) {
		updateTemp(0, 0, n-1, pos, inc);
	}
	
	private void updateTemp(int ind, int lo, int hi, int pos, int inc) {
		if(lo > hi) return;
		
		if(pos < lo || hi < pos) return;
		
		if(lo == hi) {
			sgArr[ind] += inc;
			return;
		}
		
		int mid = lo + (hi-lo)/2;
		updateTemp(2*ind+1, lo, mid, pos, inc);
		updateTemp(2*ind+2, mid+1, hi, pos, inc);
		
		sgArr[ind] = Math.min(sgArr[2*ind+1], sgArr[2*ind+2]);
	}
	
	public int rangeQ(int qlo, int qhi) {
		return rangeQueryTemp(0, 0, n-1, qlo, qhi);
	} 

	private int rangeQueryTemp(int ind, int lo, int hi, int qlo, int qhi) {
		if(lo > hi) return Integer.MAX_VALUE;
		
		if(hi < qlo || qhi < lo) return Integer.MAX_VALUE;
		
		if(qlo <= lo && hi <= qhi) return sgArr[ind];
		
		int mid = lo + (hi-lo)/2;
		int left = rangeQueryTemp(2*ind+1, lo, mid, qlo, qhi);
		int right = rangeQueryTemp(2*ind+2, mid+1, hi, qlo, qhi);
		
		return Math.min(left, right);
	}
	
}


















