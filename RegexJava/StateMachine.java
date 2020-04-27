import java.util.ArrayList;

public class StateMachine
{
	/* Inner class */
	public class State {

		public String symbol;
		public int nextState0;
		public int nextState1;

		//constructor
		public State(String symbol, int nextState0, int nextState1) {
			this.symbol = symbol;
			this.nextState0 = nextState0;
			this.nextState1 = nextState1;
		}

		//print this state
		public String printState() {
			return (symbol + " " + nextState0 + " " + nextState1); 
		}
	}


	/* Outer class */
	public ArrayList<State> states;
	public ArrayList<String> statesString;

	//constructor
	public StateMachine() {
		states = new ArrayList<State>();
	}

	public State getState(int index) {
		return states.get(index);
	}

	public void setState(int index, int nextState0, int nextState1) {
		State s = states.get(index);
		s.nextState0 = nextState0;
		s.nextState1 = nextState1;
	}

	public void addState(String symbol, int nextState0, int nextState1) {
		State newState = new State(symbol, nextState0, nextState1);
		states.add(newState);
	}

	public int size() {
		return states.size();
	}

	public ArrayList<String> getStringStates() {
		String result;
		statesString = new ArrayList<>();
        
		for (int i = 0; i < states.size(); i++) {
			result = (i + " " + states.get(i).printState());
			statesString.add(result);
		}
		return statesString; //returns to Compiler class
	}
}