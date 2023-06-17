package dsa_templates;

class Kmp {
	
    int strStr(String str, String pat) {

    	int np = pat.length(), ns = str.length();
	     
	    if(np == 0) return 0;
	    if(ns < np) return -1;
        
        int[] lps = getLps(pat);
        
        int i = 0,j = 0;
        while(i < ns){
            if(str.charAt(i) == pat.charAt(j)){
                i++;
                j++;
                if(j == np){
                    return i-j;
                    // in case of all the starting indices where pattern matches
                    // res.add(i-j+1);
                    // j = lps[j-1];
                }   
            }else{
                if(j > 0) j = lps[j-1];
                else i++;
            }
        }
        
        return -1;
    }
    
//    LPS -> Longest Prefix Suffix
    int[] getLps(String pat){
        
        int n = pat.length();
        int[] lps = new int[n];
        int i = 1, j = 0;
        
        while(i<n){
            if(pat.charAt(i) == pat.charAt(j)){
                lps[i] = j+1;
                i++;
                j++;
            }else{
                if(j > 0) j = lps[j-1];
                else i++;  
            }
        }
        
        return lps;
    }
    
}

//pat = AAAA;
//lps = [0,1,2,3]
//lps at a point j is the longest prefix in [0, j-1] that is also a suffix at j;











	
