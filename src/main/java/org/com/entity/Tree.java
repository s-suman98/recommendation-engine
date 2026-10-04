package org.com.entity;


import org.com.model.Suggestion;
import org.com.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Tree {

TreeNode root;

public Tree () {
	
	root = new TreeNode ('k', false, 0);
}


public  void addAll(List<String> words){
	for(String word:words){
		this.addWord (word);
	}
}

public boolean addWord (String s) {
	int i = 0;
	int n = s.length ();
	TreeNode curr = this.root;
	while ( i < n ) {
		char c = s.charAt (i);
		
		int index=c - 'a';
		if ( curr.getChild ()[ index] != null ) {
			curr = curr.getChild ()[ index ];
		} else {
			curr.getChild ()[ index ] = new TreeNode (c, false, 0);
			curr = curr.getChild ()[ index ];
		}
		i++;
	}
	
	curr.setFreq (curr.getFreq () + 1);
	curr.setEndOfWord (true);
	return true;
}

public List< Suggestion > getSuggestions(String prefix) {
	
	List<Suggestion> result = new ArrayList<> ();
	
	TreeNode target = getTargetNode(prefix);
	
	if (target == null) {
		return result;
	}
	
	// Your collect() expects path BEFORE curr
	String pathBeforeTarget = prefix.substring(0, prefix.length() - 1);
	
	collect(target, pathBeforeTarget, result);
	
	return result;
}

private void collect (TreeNode curr, String path, List< Suggestion > res) {
	
	if ( curr == null )
		return;
	
	
	path += curr.getC ();
	
	if ( curr.isEndOfWord () ) {
		res.add (new Suggestion (path,curr.getFreq ()));
		
	}
	
	for ( int i = 0; i < curr.getChild ().length; i++ ) {
		
		collect (curr.getChild ()[ i ], path, res);
	}
	
}


private TreeNode getTargetNode (String prefix) {
	
	TreeNode curr = root;
	
	for ( char c : prefix.toCharArray () ) {
		
		
		
		if ( curr.getChild ()[  c - 'a'] == null ) {
			return null; // prefix doesn't exist
		}
		
		curr = curr.getChild ()[ c - 'a' ];
	}
	
	return curr;
}

}
