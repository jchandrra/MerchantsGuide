package com.jc.merchantguide;
import java.util.Iterator;
import java.util.Map;
/**
 * This is the main class with most of the logic.
 * @author jchandrra
 *
 */
public class MerchantGalaxyMain {
	
	/**
	 * The input string only contains Roman mapping to intergalactic separated by is.
	 * @param str
	 * @param romanNeumarlsHelper
	 * @param intergalacticDictionary 
	 */
	private void mapIntergelecticToRoman(String str, RomanNumerlsHelper romanNeumarlsHelper, IntergalacticDictionary intergalacticDictionary) {
		String[] splitEq = str.split(" is ");
		intergalacticDictionary.updateIntergalacticRomanDict( splitEq[0].trim(), splitEq[1].trim());
		//galaxyToRomanDic.put(splitEq[0].trim(), splitEq[1].trim());
	}
	
	/**
	 * Input contains an equation with one variable. 
	 * @param str
	 * @param romanNeumarlsHelper
	 * @param intergalacticDictionary
	 */
	
	private void storeIntergelecticToDecimal(String equation, RomanNumerlsHelper romanNeumarlsHelper, IntergalacticDictionary intergalacticDictionary){
		String[] splitEq = equation.split(" is ");
		String lhs = splitEq[0];
		String rhs = splitEq[1].replaceAll("[^0-9]", "");
		float value = new Float(rhs);
		String operators[] = lhs.split(" ");
		String newElement = "";
		Map<String, String> intergalacticRomanDict = intergalacticDictionary.getIntergalacticRomanDict();
		for (int i = 0; i < operators.length; i++) {
			if(intergalacticRomanDict.containsKey(operators[i])){
				operators[i]=intergalacticRomanDict.get(operators[i]);
			}else{
				newElement = operators[i];
				operators[i]="";
			}
		}
		
		//By this time the equation is converted to total roman values.
		intergalacticDictionary.updateIntergalacticDecimalDict(newElement,value/getValueForEquation(operators, romanNeumarlsHelper));
	}
	
	
	
	private float getValueForEquation(String[] operators, RomanNumerlsHelper romanNumerlsHelper) {
		float result= 0;
		String romanDictSequence  = romanNumerlsHelper.getRomanDictSequence();
		for (int i = 0; i < operators.length && operators[i]!=""; i++) {
			if(i<operators.length-1 && romanDictSequence.indexOf(operators[i]) < romanDictSequence.indexOf(operators[i+1])){
				result += romanNumerlsHelper.getRomanDict().get(operators[i+1]) - romanNumerlsHelper.getRomanDict().get(operators[i]);
				i++;
			}else{
				result += romanNumerlsHelper.getRomanDict().get(operators[i]);
			}
			
		}
		return result;
	}

	
	private void solveQuestionAndPrintAnswer(String question, RomanNumerlsHelper romanNumerlsHelper, IntergalacticDictionary intergalacticDictionary) {
		//There can be other ways to solve this but since I found only two ways of asking questions, I would proceed with these two types.
		question = question.replaceAll("\\?", "");
		String typeA = "how much is ";
		String typeB = "how many Credits is ";
		String operators[];
		String answerType = "A";
		if(question.indexOf(typeA)!=-1){
			question = question.replaceAll(typeA, "");
			operators = question.split(" ");
			
		}else if(question.indexOf(typeB)!=-1){
			question = question.replaceAll(typeB, "");
			operators = question.split(" ");
			answerType = "B";
		}else{
			System.out.println("I have no idea what you are talking about");
			return;
		}
		
		float result= 0;
		float multiplier = 1;
		String romanDictSequence  = romanNumerlsHelper.getRomanDictSequence();
		for (int i = 0; i < operators.length && operators[i]!=""; i++) {
			if(intergalacticDictionary.getIntergalacticDecimalDict().containsKey(operators[i])){
				multiplier *= intergalacticDictionary.getIntergalacticDecimalDict().get(operators[i]);
			}else{
				if(i<operators.length-1 && romanDictSequence.indexOf(operators[i]) < romanDictSequence.indexOf(operators[i+1])){
					result += romanNumerlsHelper.getRomanDict().get(operators[i+1]) - romanNumerlsHelper.getRomanDict().get(operators[i]);
					i++;
				}else if(i<operators.length-1 && romanDictSequence.indexOf(intergalacticDictionary.getIntergalacticRomanDict().get(operators[i])) < 
						romanDictSequence.indexOf(intergalacticDictionary.getIntergalacticRomanDict().get(operators[i+1]))){
					result += romanNumerlsHelper.getRomanDict().get(intergalacticDictionary.getIntergalacticRomanDict().get(operators[i+1])) 
							- romanNumerlsHelper.getRomanDict().get(intergalacticDictionary.getIntergalacticRomanDict().get(operators[i]));
					i++;
				}
				else{
					if(intergalacticDictionary.getIntergalacticRomanDict().containsKey(operators[i])){
						operators[i]= intergalacticDictionary.getIntergalacticRomanDict().get(operators[i]);
					}
					result += romanNumerlsHelper.getRomanDict().get(operators[i]);
				}
			}
			
			
		}
		result *= multiplier;
		
		if(answerType == "A"){
			System.out.println(question+"is "+(int)result);
		}else if(answerType == "B"){
			System.out.println(question + "is "+(int)result+" Credits");
		}
		
	}

	
	public static void main(String args[]){
		MerchantGalaxyMain merchantGalaxyMain = new MerchantGalaxyMain();
		IntergalacticDictionary intergalacticDictionary = new IntergalacticDictionary();
		TestInputData testInputData = new TestInputData();
		RomanNumerlsHelper romanNumerlsHelper = new RomanNumerlsHelper();
		testInputData.populateInputData("resources/testInput.txt");
		//romanNeumarlsHelper.printMap(romanNeumarlsHelper.getRomanDict());
		for (Iterator iterator = testInputData.getBasicEquations().iterator(); iterator.hasNext();) {
			String str = (String) iterator.next();
			merchantGalaxyMain.mapIntergelecticToRoman(str, romanNumerlsHelper, intergalacticDictionary);
		}
		for (Iterator iterator = testInputData.getComplexEquations().iterator(); iterator.hasNext();) {
			String str = (String) iterator.next();
			merchantGalaxyMain.storeIntergelecticToDecimal(str, romanNumerlsHelper, intergalacticDictionary);
		}
		for (Iterator iterator = testInputData.getQuestions().iterator(); iterator.hasNext();) {
			String question = (String) iterator.next();
			merchantGalaxyMain.solveQuestionAndPrintAnswer(question, romanNumerlsHelper, intergalacticDictionary);
		}
	}

	
	
}