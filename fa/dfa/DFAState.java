package fa.dfa;

import fa.State;

import java.util.HashMap;
import java.util.Map;

public class DFAState extends State {

    private Map<Character, DFAState> transitions;

    public DFAState() {

    }

    public DFAState(String name) {
        super(name); // Calling the super constructor on the State abstract class
        transitions = new HashMap<>();
    }

    /*

     */
    public boolean addDFATransition(Character sigma, DFA location) {



        return false;
    }

}
