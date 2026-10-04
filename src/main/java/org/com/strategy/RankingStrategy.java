package org.com.strategy;



import java.util.List;

import org.com.model.Suggestion;

public interface RankingStrategy {

  List< Suggestion > getSuggesiton(List<Suggestion> words);
}
