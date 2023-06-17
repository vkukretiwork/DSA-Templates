package dsa_templates;

class SgTreeLazy {
	
	int[] sgArr, lazy;
	int n;
	
	public SgTreeLazy(int[] arr) {
		this.n = arr.length;
		sgArr = new int[4*n];
		lazy = new int[4*n];
		
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
	
	public void update(int qlo, int qhi, int inc) {
		updateTemp(0, 0, n-1, qlo, qhi, inc);
	}
	
	private void updateTemp(int ind, int lo, int hi, int qlo, int qhi, int inc) {
		if(lo > hi) return;
		
		if(lazy[ind] != 0) {
			sgArr[ind] += lazy[ind];
			
			if(lo != hi) {
				lazy[2*ind+1] += lazy[ind];
				lazy[2*ind+2] += lazy[ind];
			}
			
			lazy[ind] = 0;
		}
		
		if(hi < qlo || qhi < lo) return;
		
		if(qlo <= lo && hi <= qhi) {
			sgArr[ind] += inc;
			
			if(lo != hi) {
				lazy[2*ind+1] += inc;
				lazy[2*ind+2] += inc;
			}
			
			return;
		}
		
		int mid = lo + (hi-lo)/2;
		updateTemp(2*ind+1, lo, mid, qlo, qhi, inc);
		updateTemp(2*ind+2, mid+1, hi, qlo, qhi, inc);
		
		sgArr[ind] = Math.min(sgArr[2*ind+1], sgArr[2*ind+2]);
	}
	
	public int rangeQ(int qlo, int qhi) {
		return rangeQueryTemp(0, 0, n-1, qlo, qhi);
	}
	
	private int rangeQueryTemp(int ind, int lo, int hi, int qlo, int qhi) {
		if(lo > hi) return Integer.MAX_VALUE;
		
		if(lazy[ind] != 0) {
			sgArr[ind] += lazy[ind];
			
			if(lo != hi) {
				lazy[2*ind+1] += lazy[ind];
				lazy[2*ind+2] += lazy[ind];
			}
			
			lazy[ind] = 0;
		}
		
		if(hi < qlo || qhi < lo) return Integer.MAX_VALUE;
		
		if(qlo <= lo && hi <= qhi) return sgArr[ind];
		
		int mid = lo + (hi-lo)/2;
		int left = rangeQueryTemp(2*ind+1, lo, mid, qlo, qhi);
		int right = rangeQueryTemp(2*ind+2, mid+1, hi, qlo, qhi);
		
		return Math.min(left, right);
	}
}

















