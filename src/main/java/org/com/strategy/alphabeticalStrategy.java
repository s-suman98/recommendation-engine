package org.com.strategy;


import org.com.model.Suggestion;

import java.util.Collections;
import java.util.List;


public class alphabeticalStrategy implements  RankingStrategy{

@Override
public List< Suggestion > getSuggesiton (List< Suggestion > words) {
	
	Collections.sort (words,(a,b)-> a.getWord ().compareTo (b.getWord ()));
	
	return words;
}
}
