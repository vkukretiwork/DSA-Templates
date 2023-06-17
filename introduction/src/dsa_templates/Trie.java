package dsa_templates;

class Trie {
    TrieNode root;
    
    Trie(){
        root = new TrieNode();
    }
    
    void insert(String s){
        TrieNode node = root;
        
        for(char ch : s.toCharArray()){
            if(node.child[ch-'a'] == null)
                node.child[ch-'a'] = new TrieNode();
            
            node = node.child[ch-'a'];
        }
        
        node.end = true;
    }
    
    boolean search(String s){
        TrieNode node = root;
        
        for(char ch : s.toCharArray()){
            if(node.child[ch-'a'] == null) return false;
            node = node.child[ch-'a'];
        }
        
        return node.end;
    }
    
    class TrieNode{
        boolean end;
        TrieNode[] child;
        
        TrieNode(){
            end = false;
            child = new TrieNode[26];
        }
    }
}
