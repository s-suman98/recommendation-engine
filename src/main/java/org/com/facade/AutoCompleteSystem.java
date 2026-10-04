package org.com.facade;

import lombok.Builder;
import org.com.entity.Tree;
import org.com.model.Suggestion;
import org.com.strategy.RankingStrategy;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteSystem {

private Tree tree;
private RankingStrategy rankingStrategy;
private int maxResult;


public AutoCompleteSystem(
		RankingStrategy rankingStrategy,
		int maxResult) {
	
	this.tree = new Tree ();
	this.rankingStrategy = rankingStrategy;
	this.maxResult = maxResult;
	
	init();
}

private void init() {
	
	//seed some words
	List<String> wordsInit = new ArrayList<>();
	
	String[] prefixes = {
			"an", "ap", "ar", "ba", "be",
			"ca", "ch", "co", "de", "do",
			"el", "en", "ex", "fa", "fi",
			"go", "ha", "he", "in", "ja",
			"jo", "ma", "me", "mo", "pa",
			"pr", "re", "sa", "sp", "st",
			"te", "th", "tr", "un", "us"
	};
	
	for (String prefix : prefixes) {
		for (char suffix = 'a'; suffix <= 'n'; suffix++) {
			wordsInit.add(prefix + "word" + suffix);
		}
	}
	
	for(int i=1;i<=10;i++) {
		wordsInit.add ("anwordc");
		wordsInit.add ("anwordc");
		wordsInit.add ("anwordc");
	}
	
	tree.addAll(wordsInit);
}

public List<String> getSuggest(String prefix) {
	
	List< Suggestion > candidates = tree.getSuggestions(prefix);
	
	return rankingStrategy
			       .getSuggesiton(candidates)
			       .stream()
			       .map(Suggestion::getWord)
			       .limit(maxResult)
			       .toList();
}
}