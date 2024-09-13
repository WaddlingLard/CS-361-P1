package fa.dfa;

import fa.State;

import java.util.HashMap;
import java.util.Map;

public class DFAState extends State {

    private Map<Character, DFAState> transitions;

    @SuppressWarnings("unused")
    public DFAState() {} // not needed

    public DFAState(String name) {
        super(name); // Calling the super constructor on the State abstract class
        transitions = new HashMap<>();
    }

    /*

       @param
       @param
       @return
     */
    public DFAState addDFATransition(Character sigma, DFAState location) {
        return transitions.put(sigma, location);
    }

    /*

       @param
       @return
     */
    public DFAState getDFATransition(Character sigma) {
        return transitions.get(sigma);
    }

}
