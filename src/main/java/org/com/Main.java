package org.com;

import org.com.entity.Tree;
import org.com.facade.AutoCompleteSystem;
import org.com.model.Suggestion;
import org.com.model.TreeNode;
import org.com.strategy.FrequencyRankingStrategy;
import org.com.strategy.alphabeticalStrategy;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
public static void main (String[] args) {
	
	
	AutoCompleteSystem autoCompleteSystem=new AutoCompleteSystem(new alphabeticalStrategy (),3);
	
	 List<String> result=autoCompleteSystem.getSuggest ("an");
	
	System.out.println (result);
	
	
	AutoCompleteSystem autoCompleteSystem2=new AutoCompleteSystem(new FrequencyRankingStrategy (),4);
	
	List<String> result2=autoCompleteSystem2.getSuggest ("an");
	 
		 System.out.println (result2);
	 
	 
	  
	
}




}