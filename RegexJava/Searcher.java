import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Searcher { //search the file using the compiled regex output 

	public int patternStartIndex = 0;
	public int patternEndIndex = 0;

	public void search(ArrayList<String> statesString, String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			StateMachine machine = new StateMachine();

			//read compiled state strings
			for(String line: statesString){
				String[] inputs = line.split(" ");
				String state = inputs[1];
				int nextState0 = Integer.parseInt(inputs[2]);
				int nextState1 = Integer.parseInt(inputs[3]);
				machine.addState(state, nextState0, nextState1);			
			}

			//read file
			String line;
			int lineCount = 0;
			while ((line = reader.readLine()) != null) {
				boolean result = patternCheck(machine, line);
				lineCount++;
				
				//true is pattern is found
				if (result){
					if(patternEndIndex == 0){
						patternEndIndex = line.length();
						System.out.println("Match found on line " + lineCount + ", starting at position " + (patternStartIndex+1) 
						+ " and ending at position " + patternEndIndex + ": " + line.substring(patternStartIndex, patternEndIndex));
					}
					
					else
						System.out.println("Match found on line " + lineCount + ", starting at position " + (patternStartIndex+1) 
						+ " and ending at position " + patternEndIndex + ": " + line.substring(patternStartIndex, patternEndIndex));	
				}
			}
			System.out.println();
			reader.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//search for matching patterns in the line
	public boolean patternCheck(StateMachine machine, String line) {

		for (int i = 0; i < line.length(); i++) {

			patternStartIndex = i;
			String subString = line.substring(i, line.length()); //the substring to be checked
			int substringIndex = 0; 
			
			//create a double ended queue
			Deque<Integer> deque = new ArrayDeque<>();
			
			deque.addFirst(-1);
			deque.addLast(machine.getState(0).nextState0); //insert starting state to the rear of the queue
            
			//true if there are next states found
			while (deque.getLast() != -1) {
				if (deque.getLast() == machine.size()) //if final state is reached
					return true;
				
				if (machine.getState(deque.getLast()).symbol.equals("BR")) { //if branching state is at the rear of the queue 
					
					//get the two next states
					int nextState0 = machine.getState(deque.getLast()).nextState0;
					int nextState1 = machine.getState(deque.getLast()).nextState1;
				
					deque.removeLast();

					//if both next states are the same only add one state to the front of the queue
					if (nextState0 == nextState1) {
						deque.addFirst(nextState0);
					}
					
					else { 
						deque.addFirst(nextState0);
						deque.addFirst(nextState1);	
					}	
				}

				else {
					int stateAtRear = deque.getLast();
					deque.removeLast();
					deque.addFirst(stateAtRear);
				}
		
				//if current state exist
				while (deque.getFirst() != -1) {
					if (deque.getFirst() == machine.size()) //if final state is reached
						return true;

					if (substringIndex < subString.length()) { //if there are characters to be checked for pattern
					
						String symbol = machine.getState(deque.getFirst()).symbol; //get symbol from the current state
						char c = subString.charAt(substringIndex); //get current char

						//if the state is []
						if (symbol.charAt(0) == '[' && symbol.charAt(symbol.length() - 1) == ']') {

							//treat the inner characters as literals
							String literals = symbol.substring(1, symbol.length() - 1);

							if (literals.indexOf(c) >= 0) {

								substringIndex++;
								int nextState0 = machine.getState(deque.getFirst()).nextState0;
								int nextState1 = machine.getState(deque.getFirst()).nextState1;
								deque.removeFirst();

								if (nextState0 == nextState1)
									deque.addLast(nextState0);
								
								else {
									deque.addLast(nextState0);
									deque.addLast(nextState1);
								}
							}
							else { //match not found
								deque.removeFirst(); 
							}
							
						}
				
						else {
							if (symbol.indexOf(c) >= 0) { //if symbol and current character is same
							
								substringIndex++;
								int nextState0 = machine.getState(deque.getFirst()).nextState0;
								int nextState1 = machine.getState(deque.getFirst()).nextState1;
								deque.removeFirst();

								if (nextState0 == nextState1)
									deque.addLast(nextState0);

								else {
									deque.addLast(nextState0);
									deque.addLast(nextState1);
								}
							}

							else 
								deque.removeFirst();					
						}

						patternEndIndex = patternStartIndex + substringIndex;
					}

					else 
						break; 
				}
			}
		}	
		return false;
	}
}