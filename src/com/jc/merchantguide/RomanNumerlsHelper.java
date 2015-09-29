package com.jc.merchantguide;
import java.util.*;

/**
 * This class contains  roman to decimal dictionary
 * @author jchandrra
 *
 */
public class RomanNumerlsHelper{
	private Map<String, Integer> romanDict = new LinkedHashMap<String, Integer>(); 
	private String romanDictSequence;
	public RomanNumerlsHelper(){
		// init Roman numerals
		romanDict.put("I", 1);
		romanDict.put("V", 5);
		romanDict.put("X", 10);
		romanDict.put("L", 50);
		romanDict.put("C", 100);
		romanDict.put("D", 500);
		romanDict.put("M", 1000);
		romanDictSequence = "IVXLCDM";
	}
	
	public boolean validate(String romanString){
		return false;
		
	}
	
	public int getDecimalValue(String[] romanString){
		for (int i = 0; i < romanString.length; i++) {
			switch (romanString[i]) {
			case "I":
				romanString[i]="1";
				break;

			default:
				break;
			}
		}
		return 0;
	}
	
	public Map<String, Integer> getRomanDict() {
		return romanDict;
	}
	
	public boolean isRoman(String key){
		return romanDict.containsKey(key);
	}
	
	public Integer getDecimalEquivalent(String romanKey){
		return romanDict.get(romanKey);
	}
	
	public String[] replaceWithRomans(String inputString){
		String splitInput[] = inputString.split(inputString);
		for (int i = 0; i < splitInput.length; i++) {
			if(isRoman(splitInput[i])){
				splitInput[i] = Integer.toString(getDecimalEquivalent(splitInput[i]));
			}
		}
		return splitInput;
	}
	
	public void printMap(Map map){
		 Iterator it = map.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		    }
	}

	public String getRomanDictSequence() {
		return romanDictSequence;
	}

	
}