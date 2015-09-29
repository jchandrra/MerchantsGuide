package com.jc.merchantguide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class gets the input data from file and populates into various filed variables. 
 * These fields are later used to perform various logic.
 * @author jchandrra
 *
 */
public class TestInputData {
	private List<String> questions = new ArrayList<String>();
	private List<String> basicEquations = new ArrayList<String>();
	private List<String> complexEquations = new ArrayList<String>();
	
	/**
	 * This method reads the input and populates it into questions and equations 
	 * @param resourcePath
	 */
	public void populateInputData (String resourcePath) {
		try {
			FileReader reader = new java.io.FileReader(new File(resourcePath));
			BufferedReader br = new BufferedReader(reader);
			String currentline;
			while((currentline=br.readLine())!=null){
				if(currentline.indexOf("?")==-1){
					segregateEquations(currentline);
				}else{
					questions.add(currentline);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method segregates basic and complex equations
	 * @param equation
	 */
	private void segregateEquations(String equation){
		if(equation.indexOf("Credits") == -1){
			basicEquations.add(equation);
		}else{
			complexEquations.add(equation);
		}
	}
	
	public List<String> getQuestions() {
		return questions;
	}
	
	public List<String> getBasicEquations() {
		return basicEquations;
	}
	
	public List<String> getComplexEquations() {
		return complexEquations;
	}
	
	
	
}
