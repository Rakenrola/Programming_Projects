package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class CalcController 
{
	@FXML
	//Calculator Logic
	private TextArea outputText;
	private String stringOutput = "";
	private Double num1 = 0.0, num2 = 0.0, result = 0.0;
	private char operator;
	private boolean newEntry = true;
	
	//About Calculator
	@FXML
	private Button exitAboutPage;
	public Pane aboutPane;
	
	//Inputs for the calculator
	public void clearEntry (ActionEvent e)
	{
		stringOutput = "";
		outputText.setText("");
		
		System.out.println("Cleared entry!");
	}
	
	public void clear (ActionEvent e)
	{
		stringOutput = "";
		outputText.setText("");
		num1 = 0.0;
		num2 = 0.0;
		result = 0.0;
		operator = '\0';
		newEntry = true;
		
		System.out.println("Cleared all!");
		System.out.println("Num1: " + num1 + " Num2: " + num2 + " = " + result);
		System.out.println("Entry reset: " + newEntry);
	}
	
	public void backSpace (ActionEvent e)
	{
		if (stringOutput.length() > 0)
		{
			stringOutput = stringOutput.substring(0 , stringOutput.length() - 1);
			outputText.setText(stringOutput);
		}
		
		System.out.println("Backspace!");
	}
	
	public void divide (ActionEvent e)
	{
		handleOperators('/');
		
		System.out.println("Divide!");
	}
	
	public void multiply (ActionEvent e)
	{
		handleOperators('*');
		
		System.out.println("Multiply!");
	}
	
	public void subtract (ActionEvent e)
	{
		handleOperators('-');
		
		System.out.println("Subtract!");
	}
	
	public void add (ActionEvent e)
	{
		handleOperators('+');
		
		System.out.println("Add!");
	}
	
	public void equal (ActionEvent e)
	{
		try {
	        String inputText = outputText.getText();
	        if (inputText.isEmpty()) {
	            outputText.setText("Error: No input");
	            return;
	        }
		
		num2 = Double.parseDouble(outputText.getText());
		switch(operator)
		{
		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num1 - num2;
			break;
		case '*':
			result = num1 * num2;
			break;
		case '/':
			if (num2 != 0) 
			{
                result = num1 / num2;
            } else 
	            {
	                outputText.setText("Error: Div by 0");
	                return;
	            }
	            break;
        default:
            outputText.setText("Error");
            return;
		}
		outputText.setText(String.valueOf(result));
		num1 = result;
		
		System.out.println("Outcome: " + result);
		
		} 
		catch (NumberFormatException ex) 
		{
	        outputText.setText("Error: Invalid input");
	    } 
		catch (Exception ex) 
		{
	        outputText.setText("Error: Something went wrong");
	    }
	}
	
	@FXML
	private void handleButtonOutput(ActionEvent e)
	{
		Button sourceButton = (Button) e.getSource();
        String buttonText = sourceButton.getText();
        
        if (newEntry)
        {
        	stringOutput = buttonText;
        	newEntry = false;
        }
        else 
        {
        	stringOutput += buttonText;
        }
        
        outputText.setText(stringOutput);
        System.out.println("Input Registered: " + buttonText);
	}
	
	private void handleOperators(char op)
	{
		if (!stringOutput.isEmpty())
		{
			num1 = Double.parseDouble(outputText.getText());
			operator = op;
			stringOutput = "";
			newEntry = true;
		}
	}
	
	public void initialize()
	{		
		stringListener();
	}
	
	private void stringListener()
	{
		outputText.textProperty().addListener((observable, oldValue, newValue) -> 
		{
            String fixedValue = fixDecimalPoint(newValue);
            if (!newValue.equals(fixedValue))
            {
                outputText.setText(fixedValue);
                outputText.positionCaret(fixedValue.length());
            }
        });
	}
	
	private String fixDecimalPoint(String value) 
	{
        if (value.startsWith(".")) 
        {
            value = "0" + value;
        }

        int decimalPointIndex = value.indexOf('.');
        if (decimalPointIndex == -1) 
        {
            return value;
        }

        String beforeDecimal = value.substring(0, decimalPointIndex + 1);
        String afterDecimal = value.substring(decimalPointIndex + 1).replace(".", "");
        return beforeDecimal + afterDecimal;
    }
	
	
	@FXML
	//Calculator Description (acts as the credits for the application)
	public void aboutCalc(ActionEvent e)
	{
		aboutPane.setVisible(true);
	}
	
	@FXML
	public void initializePage()
	{
		
		exitAboutPage.setOnAction(event -> aboutPane.setVisible(false));
		
		aboutPane.setOnMouseClicked(event -> 
		{
			if (!exitAboutPage.isHover())
			{
				aboutPane.setVisible(false);
			}
		});
	}
}

