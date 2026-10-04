package org.com.model;


import lombok.Data;

@Data
public class TreeNode {

  private char c;
  private boolean isEndOfWord;
  private int freq;
  
  private TreeNode[] child=new TreeNode[26];
  
   public TreeNode(char c,boolean isEndOfWord,int freq){
	   this.freq=freq;
	   this.c=c;
	   this.isEndOfWord=isEndOfWord;
	   
	   for(int i=0;i<26;i++){
		   this.child[i]=null;
	   }
	   
   }
  
  
}
