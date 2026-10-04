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


public List<Suggestion> fuzzySearch(String query, int maxEdits) {
	List<Suggestion> result = new ArrayList<>();
	
	fuzzyDFS(
			root,
			query,
			0,          // queryIndex
			0,          // edits used
			maxEdits,
			"",         // built word
			result
	);
	
	return result;
}

private void fuzzyDFS(
		TreeNode curr,
		String query,
		int queryIndex,
		int edits,
		int maxEdits,
		String word,
		List<Suggestion> result) {
	
	if (curr == null || edits > maxEdits) {
		return;
	}
	
	// Query completely consumed
	if (queryIndex == query.length()) {
		
		// Current Trie node represents a complete word
		if (curr.isEndOfWord()) {
			result.add(new Suggestion(word, curr.getFreq()));
		}
		
		//"The query is finished, but let me continue down the Trie, treating every extra Trie character as an insertion."
		// INSERTION
		// Consume Trie character, but don't consume query
		for (int i = 0; i < curr.getChild().length; i++) {
			
			TreeNode child = curr.getChild()[i];
			
			if (child != null) {
				fuzzyDFS(
						child,
						query,
						queryIndex,
						edits + 1,
						maxEdits,
						word + child.getC(),
						result
				);
			}
		}
		
		return;
	}
	
	char queryChar = query.charAt(queryIndex);
	
	// Try every Trie child
	for (int i = 0; i < curr.getChild().length; i++) {
		
		TreeNode child = curr.getChild()[i];
		
		if (child == null) {
			continue;
		}
		
		char trieChar = child.getC();
		
		// MATCH / SUBSTITUTION
		int cost = (trieChar == queryChar) ? 0 : 1;
		
		fuzzyDFS(
				child,
				query,
				queryIndex + 1,
				edits + cost,
				maxEdits,
				word + trieChar,
				result
		);
		
		// INSERTION
		// Trie moves, Query doesn't move
		fuzzyDFS(
				child,
				query,
				queryIndex,
				edits + 1,
				maxEdits,
				word + trieChar,
				result
		);
	}
	
	// DELETION
	// Query moves, Trie doesn't move
	fuzzyDFS(
			curr,
			query,
			queryIndex + 1,
			edits + 1,
			maxEdits,
			word,
			result
	);
}
}
