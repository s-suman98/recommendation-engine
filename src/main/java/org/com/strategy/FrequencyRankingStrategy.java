package org.com.strategy;

import org.com.model.Suggestion;

import java.util.Collections;
import java.util.List;

public class FrequencyRankingStrategy implements  RankingStrategy{



//SOrt the most freuent pahle
@Override
public List< Suggestion > getSuggesiton (List< Suggestion > words) {
	
	Collections.sort (words,(a, b)-> a.getWeight () - b.getWeight ());
	
	return words;
}
}
