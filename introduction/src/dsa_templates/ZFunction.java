package dsa_templates;

class ZFunction {
	
	 public int strStr(String s, String pat) {
	     int np = pat.length(), ns = s.length();
	     
	     if(np == 0) return 0;
		 if(ns < np) return -1;
	     
	     String concat = pat + "$" + s;
	     int n = concat.length();
	     
	     int[] z = getZarray(concat);
	     
	     for(int i = 0;i<n;i++){
	         if(z[i] == pat.length())
	             return i-pat.length()-1;
	     }
	     
	     return -1;
	 }
	 
//	 z[i] is longest prefix starting from 'i' which is also a prefix of str[0..n-1].
//	 note that in the above statement it is 0..n-1 and not 0..i-1 (case of lps);
	 
	 int[] getZarray(String s) {
	        int n = s.length();
	        int[] z = new int[n];
	        
	        for (int i = 1, l = 0, r = 0; i < n; i++) {
	            if (i <= r) z[i] = Math.min(r - i + 1, z[i - l]);
	            
	            while (i + z[i] < n &&
	                   s.charAt(z[i]) == s.charAt(i + z[i])){
	                z[i]++;
	            } 
	            
	            if (i + z[i] - 1 > r){
	                l = i;
	                r = i + z[i] - 1;
	            }
	                
	        }
	        
	        return z;
	    }
	 
	 
//	 Another implementation, just in case.
	 
//	 int[] getZarray(String s){
//	     int n = s.length();
//	     int[] z = new int[n];
//	     int left = 0, right = 0;
//	     
//	     for(int i = 1;i<n;i++){
//	         if(i > right){
//	             left = right = i;
//	             while(right < n && s.charAt(right) == s.charAt(right - left))
//	                 right++;
//	             z[i] = right - left;
//	             right--;
//	         }else{
//	             int i1 = i-left;
//	             if(z[i1] < right - i + 1)
//	                 z[i] = z[i1];
//	             else{
//	                 left = i;
//	                 while(right < n && s.charAt(right) == s.charAt(right - left))
//	                     right++;
//	                 z[i] = right - left;
//	                 right--;
//	             }
//	         }            
//	     }
//	     
//	     return z;
//	 }
}
