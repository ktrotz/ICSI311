
/* 	Kimberley Trotz

	Compiles a regular expression and sends it to Searcher class to use the compiled string
	to search for patterns in a file.
	
	<<<<<   MAIN DRIVER IN THIS CLASS  >>>>>
	*/


import java.lang.IllegalArgumentException;
import java.util.ArrayList;

public class Compiler //compile the regular expression input
{
	public String regex;
	public StateMachine machine;
	public int pointer; //pointer to the index position of the regex string
	public int state; //points to the next state
	public static ArrayList<String> stringStates = new ArrayList<>(); //list of compiled states

	public Compiler(String regex) {
		this.regex = regex;
		machine = new StateMachine();
		pointer = 0;
		state = 1;
	}

	//check if a character is a symbol
	public boolean isSpecialChar(char c) {
		return ("*+[]".indexOf(c) == -1);
	}

	public int expressionCompiler() throws IllegalArgumentException {
		
		int initialState;
		initialState = plusStarCheck(); 

		//if regex string has more to process
		if (pointer < regex.length()) {
			if (isSpecialChar(regex.charAt(pointer)) || "[".indexOf(regex.charAt(pointer)) >= 0)
				expressionCompiler(); //recursive loop
		}	
		return initialState;
	}

	//checks for * and + 
	public int plusStarCheck() throws IllegalArgumentException {
		
		int initialState, plusStar, finalState;
		finalState = state - 1; //set the final state to the last state processed
		
		initialState = plusStar = bracketCheck(); 

		if (pointer < regex.length()) {
			if (regex.charAt(pointer) == '*') {
                machine.getState(finalState).nextState0 = state;
				machine.addState("BR", state + 1, plusStar); //add a branching state to the machine
				pointer++;
				initialState = state; //set initial state to be this branching state
				state++;
			}

			else if (regex.charAt(pointer) == '+') {
				machine.setState(initialState - 1, state, state); //set machine to next state to make sure at least 1 is processed
				machine.addState("BR", state + 1, plusStar);
				pointer++;
				initialState = state;
				state++;
			}
		}
		return initialState;
	}

	//check for []
	public int bracketCheck() throws IllegalArgumentException {
	
		int initialState = 0;
		if (pointer < regex.length()) {

			//if looking at a letter or num
			if (isSpecialChar(regex.charAt(pointer))) {

				machine.addState(Character.toString(regex.charAt(pointer)), state + 1, state + 1); //add a state for this character
				pointer++;
				initialState = state;
				state++;
			}

			else {
				if (regex.charAt(pointer) == '[') {
					pointer++;
					String bracket = "[";

					//builds a list of literals while ] is not found
					while (regex.charAt(pointer) != ']') {
						bracket += regex.charAt(pointer);
						pointer++;
					}
					bracket += "]";
									
					machine.addState(bracket, state + 1, state + 1); //add a state to the machine
					pointer++;
					initialState = state;
					state++;
				}
			}
		}
		return initialState;
	}

	//parses through the regular expression
	public void parse() throws IllegalArgumentException {
		
		//the first state is a branching state that points to the start state of the machine
		machine.addState("BR", 1, 1); 

		int initial = expressionCompiler(); //compiles the regular expression
		machine.setState(0, initial, initial); //set the initial state
		
		stringStates = machine.getStringStates(); //list of compiled regex
		System.out.println();
	}


	public static void main(String [] args) {
		
		String regex = args[0];
		String fileName = args[1];

		try {
			Compiler compiler = new Compiler(regex);
			compiler.parse();

			Searcher searcher = new Searcher();
			searcher.search(stringStates, fileName);
		}
		catch (Exception e) {
			e.printStackTrace(); }
		
	}
}