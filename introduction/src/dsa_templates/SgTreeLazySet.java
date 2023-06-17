package dsa_templates;

class SgTreeLazySet {
	SgTreeLazySet lChild, rChild;
	int leftmost, rightmost, _sum, setTo = -1;
	
	//when making an object, pass (0, n-1) in constructor;
	//then, for each i, call update(i, i, vi);
	
	public SgTreeLazySet(int l, int r) {
		this.leftmost = l;
		this.rightmost = r;
		
		if (l != r) {
			int mid = (leftmost + rightmost) / 2;
			
			lChild = new SgTreeLazySet(l, mid);
			rChild = new SgTreeLazySet(mid+1, r);
		}
	}
	
	public int sum(int l, int r) {
		prop();
		
		if (l <= leftmost && r >= rightmost) return sum();
		if (r < leftmost || l > rightmost) return 0;
		
		return lChild.sum(l, r) + rChild.sum(l, r);
	}
	
	public void update(int l, int r, int setTo) {
		prop();
		
		if (l <= leftmost && r >= rightmost) {
			this.setTo = setTo;
			return;
		}
		
		if (r < leftmost|| l > rightmost) return;
		
		lChild.update(l, r, setTo);
		rChild.update(l, r, setTo);
		
		recalc();
	}
	
	private int sum() {
		if (setTo == -1) return _sum;
		return setTo == 0? 0 : rightmost - leftmost + 1;
	}
	
	private void recalc() {
		//assume that toProp == 0
		if (leftmost == rightmost) return;
		_sum = lChild.sum() + rChild.sum();
	}
	
	private void prop() {
		if (setTo == -1) return;
		if (leftmost == rightmost) {
			_sum = setTo;
			setTo = -1;
			return;
		}
		
		lChild.setTo = setTo;
		rChild.setTo = setTo;
		setTo = -1;
		
		recalc();
	}
}
