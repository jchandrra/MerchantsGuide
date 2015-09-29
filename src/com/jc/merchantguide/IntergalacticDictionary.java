package com.jc.merchantguide;

import java.util.LinkedHashMap;
import java.util.Map;

public class IntergalacticDictionary {

	private Map<String, String> intergalacticRomanDict = new LinkedHashMap<String, String>();
	private Map<String, Float> intergalacticDecimalDict = new LinkedHashMap<String, Float>();
	public Map<String, String> getIntergalacticRomanDict() {
		return intergalacticRomanDict;
	}

	public void updateIntergalacticRomanDict(String key, String string) {
		intergalacticRomanDict.put(key, string);
	}

	public Map<String, Float> getIntergalacticDecimalDict() {
		return intergalacticDecimalDict;
	}

	public void updateIntergalacticDecimalDict(String key, Float value) {
		intergalacticDecimalDict.put(key , value);
	}
	

}
