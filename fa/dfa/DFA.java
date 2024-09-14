package fa.dfa;

import fa.State;

// import java.util.Map;
import java.util.HashSet;
import java.util.Set;
// import java.util.HashMap;

public class DFA implements DFAInterface{

    private Set<DFAState> Q; // All states
    private Set<DFAState> F; // Final states
    private Set<Character> Sigma; // Alphabet
    private DFAState q0; // Initial state
    // Map<DFAState, Map<Character,DFAState>> delta;//Need something to hold delta
    // not sure if this is correct 
    // might not be needed

    public DFA() {
        this.Q = new HashSet<>();
        this.F = new HashSet<>();
        this.Sigma = new HashSet<>();
        this.q0 = null;
        // this.delta = new HashMap<>();

    }
    @Override
    public boolean addState(String name) {
        DFAState newState = new DFAState(name);
        return Q.add(newState);
    }

    @Override
    public boolean setFinal(String name) {
//        DFAState tempState = new DFAState(name);
        for (DFAState state: Q) {
            if (state.getName().equals(name)) {
                return F.add(state);
            }
        }
        // Not necessary, I believe
//        if (Q.contains(tempState)) {
//            F.add(tempState);
//            return true;
//        }
        return false;
    }

    @Override
    public boolean setStart(String name) {
        for (DFAState state: Q) {
            if (state.getName().equals(name)) {
                q0 = state;
                return true;
            }
        }
        return false;
    }

    @Override
    public void addSigma(char symbol) {
        Sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {

        DFAState currentState = this.q0;
        // char[] traceString = s.toCharArray();
        // for (int i = 0; i < traceString.length; i++) {

        // }
        for (char c: s.toCharArray()){
            currentState = currentState.getDFATransition(c);
        }
        if(currentState == null){
            return false;
        }

        return this.F.contains(currentState); //return true if final state is accepting
    }
   

    

    @Override
    public Set<Character> getSigma() {
        Set<Character> tempSigma = new HashSet<>(); // This is here for encapsulation
        Object[] temp = Sigma.toArray();
        for (Object item: temp) {
            tempSigma.add((Character) item);
        }
        return tempSigma;
    }

    @Override
    public State getState(String name) { // revision might needed
        for( State state: Q){
            if(state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        for (DFAState state: F) {
            if (state.getName().equals(name)) {
                return true;
            }
        }
        return false;
//        return F.contains(name);
    }

    // This might need to be revised
    @Override
    public boolean isStart(String name) {
        for(DFAState state: F){
            if(state.getName().equals(name)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        if (inSigma(onSymb) && isValidState(fromState) && isValidState(toState)) {
            DFAState origin = getDFAState(fromState);
            DFAState destination = getDFAState(toState);

            if (origin == null) { // Checking if origin is valid object
                return false;
            }

            DFAState result = origin.addDFATransition(onSymb, destination);
            return !(result == null);
        }
        return false;
    }

    @Override

    //revision might needed
    
    public DFA swap(char symb1, char symb2) {
        DFA newDFA = new DFA();

        newDFA.Q = new HashSet<>(this.Q);
        newDFA.F = new HashSet<>(this.F);
        newDFA.Sigma = new HashSet<>(this.Sigma);
        newDFA.q0 = this.q0;

        for(DFAState state : this.Q){

            for (Character sym : this.Sigma){

                DFAState nextState = state.getDFATransition(sym);

                if(nextState != null){
                     char newSym;
                    
                    if(sym == symb1 ){
                        newSym = symb2;
                    }else if(sym == symb2){
                        newSym = symb1;
                    } else{
                        newSym = sym;
                    }

                    DFAState newState = newDFA.getDFAState(state.getName());
                    DFAState newNextState = newDFA.getDFAState(nextState.getName());
                    if (newState != null && newNextState != null) {
                        newDFA.addTransition(newState.getName(), newNextState.getName(), newSym);
                    }
                }
                
            }
        }


        return newDFA;
    }

    /*

       @param state
       @param input
       @return The next transition state
     */
    private DFAState getTransition(DFAState state, Character input) {
     
        return null;
    }

    /*

       @param check
       @return
     */
    private boolean inSigma(char check) {
        Set<Character> alphabet = getSigma();
        for (Character letter: alphabet) {
            if (letter.equals(check)) {
                return true;
            }
        }
        return false;
    }

    /*

       @param name
       @return
     */
    private boolean isValidState(String name) {
        for (DFAState state: Q) {
            if (state.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /*

       @param name
       @return
     */
    private DFAState getDFAState(String name) {
        for (DFAState state: Q) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }
}
