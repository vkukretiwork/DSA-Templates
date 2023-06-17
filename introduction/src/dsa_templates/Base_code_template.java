package dsa_templates;

import java.util.*;
import java.io.*;

// cf : add public, cc : remove public
class Base_code_template{
	
	static FastReader in; static FastWriter out;
	
	static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br=new BufferedReader(new InputStreamReader(System.in));
        }
        String next(){
            while(st==null || !st.hasMoreTokens()){
                try {
                    st=new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
        long nextLong(){
            return Long.parseLong(next());
        }
        double nextDouble(){
            return Double.parseDouble(next());
        }
        String nextLine(){
            String str="";
            try {
                str=br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }
	static int ni() {return in.nextInt();}
	static long nl() {return in.nextLong();}
	static double nd() {return in.nextDouble();}
	static String next() {return in.next();}
	static String nextLine() {return in.nextLine();}
	
    static class FastWriter {
		private final BufferedWriter bw;

		public FastWriter() {
			this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
		}

		public void print(Object object) throws IOException {
			bw.append("" + object);
		}

		public void println(Object object) throws IOException {
			print(object);
			bw.append("\n");
		}
		
		public void println() throws IOException {
			bw.append("\n");
		}

		public void close() throws IOException {
			bw.close();
		}
	}
    
    static void pr(Object object) throws IOException { out.print(object);} 
    static void pln(Object object) throws IOException { out.println(object);}
    static void pln() throws IOException {out.println();}
    
// *******************************************************************
	
	static final Random rand =new Random();
    
    static void rsort(int[] a) {
		int n=a.length; 
		for (int i=0; i<n; i++) {
			int oi=rand.nextInt(n), temp=a[oi];
			a[oi]=a[i]; a[i]=temp;
		}
		Arrays.sort(a);
	} 
    
    static void rsort(long[] a) {
		int n=a.length; 
		for (int i=0; i<n; i++) {
			int oi=rand.nextInt(n);
			long temp=a[oi];
			a[oi]=a[i]; a[i]=temp;
		}
		Arrays.sort(a);
	} 
    
    static boolean[] get_prime_bool(int n) {
    	boolean[] _res = new boolean[n+1];
    	Arrays.fill(_res, true);
    	_res[0] = false; _res[1] = false;
    	for(int i=2;i*i<=n;i++) 
    		if(_res[i]) for(int j=i*i;j<=n;j+=i) _res[j] = false;
    	
    	return _res;
    }
    
    static List<Integer> get_prime_src(int n){
    	boolean[] _prime_bool = get_prime_bool(n);
    	List<Integer> _res = new ArrayList<>();
    	for(int i=2;i<=n;i++) if(_prime_bool[i]) _res.add(i);
    	return _res;
    }
    
    static List<Integer> get_pf1(int n, List<Integer> _prime_src){
    	List<Integer> _res = new ArrayList<>();
    	for(int x : _prime_src) { 
    		if(1L * x * x > n) break;
    		if(n % x == 0) { 
    			while(n % x == 0) n = n / x; 
    			_res.add(x);
    		}
    	}
    	if(n > 1) _res.add(n);
    	return _res;
    }
    
    static List<int[]> get_pf2(int n, List<Integer> _prime_src){
    	List<int[]> _res = new ArrayList<>();
    	for(int x : _prime_src) { 
    		if(1L * x * x > n) break;
    		if(n % x == 0) { 
    			int count = 0;
    			while(n % x == 0) { count++; n = n / x; }
    			_res.add(new int[] {x, count});
    		}
    	}
    	if(n > 1) _res.add(new int[] {n, 1});
    	return _res;
    }
    
    static boolean is_prime(int n) {
    	if(n <= 1) return false;
    	for(int i=2;i*i<=n;i++) if(n % i == 0) return false;
    	return true;
    }
    
    static boolean is_prime_fast(int n, List<Integer> _prime_src) {
    	if(n <= 1) return false;
    	for(int p : _prime_src) {
    		if(1L * p * p > n) break;
    		if(n % p == 0) return false;
    	}
    	return true;
    }
    
    static int[] get_spf(int n) {
    	int[] _spf = new int[n+1];
    	for(int i=0;i<=n;i++) _spf[i] = i;
    	for(int i=2;i*i <= n;i++) 
    		if(_spf[i] == i) for(int j=i*i;j<=n;j += i) if(_spf[j] == j) _spf[j] = i;
    	
    	return _spf;
    }
    
    static long pow(long a, long n, long mod) {
    	long _res = 1;
    	while(n > 0) {
    		if(n % 2 != 0) _res = _res * a % mod;
    		a = a * a % mod;
    		n = n / 2;
    	}
    	return _res;
    }
    
    static int gcd(int a, int b) { return a == 0? b : gcd(b % a, a); }
    static long gcd(long a, long b) { return a == 0? b : gcd(b % a, a); }
    
    static int lcm(int a, int b) { return a * b / gcd(a, b); }
    static long lcm(long a, long b) { return a * b / gcd(a, b); }
    
    static long[] get_fac(int n, long mod) {
    	long[] _fac = new long[n+1];
    	_fac[0] = 1; _fac[1] = 1;
    	for(int i = 2; i <= n; i++) _fac[i] = _fac[i-1] * i % mod;
    	return _fac;
    }
    static long[] get_inv_fac(int n, long mod, long[] fac) {
    	long[] inv_fac = new long[n+1];
    	for(int i=0;i<=n;i++) inv_fac[i] = mod_inv(fac[i], mod);
    	return inv_fac;
    }
    
    static long mod_inv(long x, long mod) { return pow(x, mod - 2, mod); }
    
    static void swap(int[] a, int i, int j) {int _t = a[i]; a[i] = a[j]; a[j] = _t;}
    static void swap(long[] a, int i, int j) {long _t = a[i]; a[i] = a[j]; a[j] = _t;}
    
    
    static String yes = "yes", no = "no", YES = "YES", NO = "NO", Yes = "Yes", No = "No";
    
// ******************************************************************* 
    
    public static void main(String[] args) throws IOException {
    	in = new FastReader(); out = new FastWriter();
    	preCal();
//        solve();
    	int tc = in.nextInt(); while(tc-- > 0){ solve();} 
    	out.close();}
    
// *******************************************************************
    
    
    
    
    
    public static void preCal() {
    	
    }
    public static void solve() throws IOException {
    	
    	
    	
    } 






    

      
// *******************************************************************   
} // remove package line at top before submitting.