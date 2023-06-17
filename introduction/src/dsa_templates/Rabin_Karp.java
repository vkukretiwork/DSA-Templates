package dsa_templates;

class Rabin_Karp {
	
	int strStr(String str, String pat) {
		
	    int np = pat.length(), ns = str.length();
	     
	    if(np == 0) return 0;
	    if(ns < np) return -1;
	     
	    int d = 256, h = 1, q = 101;
	     
	    for(int i =0;i<np-1;i++) h = (h*d) % q;
	         
	    int pHash = 0, sHash = 0;
	     
	    for(int i=0;i<np;i++){
	        pHash = (d*pHash + pat.charAt(i)) % q;
	        sHash = (d*sHash + str.charAt(i)) % q;
	    }
	     
	    for(int i =0;i<=ns-np;i++){
	        if(sHash == pHash){
	            int j = 0;
	            for(j = 0;j<np;j++){
	                if(str.charAt(i+j) != pat.charAt(j))
	                    break;
	            }
	            if(j == np)
	                return i;
	        }
	         
	        if(i < ns-np){
	            sHash = (d*(sHash - str.charAt(i)*h) + str.charAt(i+np)) % q;
	            if(sHash < 0) sHash = sHash + q;
	        }
	    }
	         
	    return -1;
	}
}
