package fa.dfa;

import fa.State;

import java.util.HashMap;
import java.util.Map;
/*
    This class is to mimic a DFAState that is used for the DFA class which can then create Finite Automata.

    @author Brian Wu
    @author Max Ma
    @version 1.0
 */
public class DFAState extends State {

    private Map<Character, DFAState> transitions;

    @SuppressWarnings("unused")
    public DFAState() {}

    public DFAState(String name) {
        super(name); // Calling the super constructor on the State abstract class
        transitions = new HashMap<>();
    }

    /*
       This method adds a DFA transition to a DFAState provided a key and value
       @param sigma
       @param location
       @return The origin DFAState that now has a transition attacted to it
     */
    public DFAState addDFATransition(Character sigma, DFAState location) {
        return transitions.put(sigma, location);
    }

    /*
       This method gets a DFA transition provided by a key
       @param sigma
       @return The DFA state that is in the HashMap
     */
    public DFAState getDFATransition(Character sigma) {
        return transitions.get(sigma);
    }

}
