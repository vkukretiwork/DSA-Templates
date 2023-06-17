package dsa_templates;

import java.util.*;

//	1. floor sqrt using binary search
//  2. digit DP
//  3. minimum swaps to sort the array.
//	4. max non-overlapping intervals
//	5. find all palindromic substring in a string.
//	6. Length of Longest Increasing Sub O(nlog(n))
//	7. Dijkstra's algorithm

public class Util {

// 1 
//	floor sqrt using binary search. Use when constraints are very high ~10^18
//	from https://codeforces.com/blog/entry/107567 (problem B)
	
	long sqrt(long x) {
		long lo = 0, hi = Integer.MAX_VALUE;
		
		while(lo < hi) {
			long mid = lo + (hi-lo)/2;
			
			if(mid*mid > x) hi = mid;
			else lo = mid+1;
		}
		
		return lo-1;
	}
	
// 2
//	digit DP. Find how many numbers in the range [0, num] have their digits-sum equals to x;
//	n : number of digits left in num; Note : always set tight = 1 intitally.
//	https://www.youtube.com/watch?v=heUFId6Qd1A&list=PLb3g_Z8nEv1hB69JL9K7KfEyK8iQNj9nX&index=1
	
	static int digitDP(String num, int n, int x, int tight, int[][][] dp) {
		if(x < 0) return 0;
		if(n == 0) return x == 0? 1 : 0;
		
		if(dp[n][x][tight] != -1) return dp[n][x][tight];
		
		int ub = tight == 0? 9 : num.charAt(num.length()-n) - '0';
		int res = 0;
		
		for(int i=0;i<=ub;i++) {
			int t = (i == ub && tight == 1)? 1 : 0;
			res += digitDP(num, n-1, x-i, t, dp);
		}
		
		return dp[n][x][tight] = res;
	}
	
// 3	
//	minimum swaps to sort the array
	
	static int minSwaps(int[] arr) {
		int n = arr.length, res = 0;
		
		List<int[]> list = new ArrayList<>();
		for(int i=0;i<n;i++) list.add(new int[] {arr[i], i});
		
		Collections.sort(list, (a, b) -> a[0] - b[0]);
		
		for(int i=0;i<n;i++){
            if(list.get(i)[1] == i) continue;
            
            int ind = i, cycleSize = 0;
            
            while(list.get(ind)[1] != ind){
                cycleSize += 1;
                
                int nextInd = list.get(ind)[1];
                list.get(ind)[1] = ind;
                ind = nextInd;
            }
            
            res += cycleSize-1;
        }
        
        return res;
	}
	
// 4
//	max non-overlapping intervals
	
	static int maxNonOverlapping(List<int[]> list) {
		int count = 0, end = -1;
		
		Collections.sort(list, (a, b) -> a[1] - b[1]);
        
        for(int[] inter : list){
            if(end < inter[0]){
                count += 1;
                end = inter[1];
            }
        }
        
        return count;
	}
	
// 5
//	find all palindromic substring in a string.
	
	static void palindromic_substrings(String s) {
		int n = s.length();
		boolean[][] dp = new boolean[n][n];
		
		for(int i = n-1; i >= 0; i--){
            for(int j = i; j < n; j++){
                dp[i][j] = (s.charAt(i) == s.charAt(j))
                            && ((j-i+1 <= 3) || dp[i+1][j-1]);
            }
        }
	}
	
// 6
//	Length of Longest Increasing Sub O(nlog(n))
	
	static int funLIS(int[] arr) {
    	int n = arr.length;
        List<Integer> list = new ArrayList<>();
        
        for(int num : arr){
            int lo = 0, hi = list.size() - 1, ind = -1;
            
//  		bs is to find smallest element in list >= or > (depends) than target.
            while(lo <= hi){
                int mid = lo + (hi-lo)/2;
                
                // (strictly increasing : >=) (non-decreasing : >)
                if(list.get(mid) >= num){
                    ind = mid;
                    hi = mid-1;
                }else lo = mid+1;
            }
            
            if(ind == -1) list.add(num);
            else list.set(ind, num);
        }
        
        return list.size();
    }
	
// 7
//	Dijkstra's algorithm
	
	static  void dijk(List<List<int[]>> adj, int src, int n) {
		int[] dist = new int[n];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.add(new int[] {src, 0});
		
		while(pq.isEmpty() == false) {
			int[] temp = pq.poll();
			int node = temp[0], d = temp[1];
			
			if(dist[node] < d) continue;
			else dist[node] = d;
			
			for(int[] item : adj.get(node)) {
				int child = item[0], cost = item[1];
				
				if(dist[node] + cost < dist[child]) {
					dist[child] = dist[node] + cost;
					pq.add(new int[] {child, dist[child]});
				}
			}
		}
	}
	
	
}

































