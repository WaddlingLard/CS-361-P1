package fa.dfa;

import fa.State;
import java.util.HashSet;
import java.util.Set;

public class DFA implements DFAInterface{

    private Set<DFAState> Q; // All states
    private Set<DFAState> F; // Final states
    private Set<Character> Sigma; // Alphabet
    private DFAState q0; // Initial state

    public DFA() {
        this.Q = new HashSet<>();
        this.F = new HashSet<>();
        this.Sigma = new HashSet<>();
        this.q0 = null;
    }
    @Override
    public boolean addState(String name) {
        DFAState newState = new DFAState(name);
        for (DFAState state: Q) {
            if (state.getName().equals(newState.getName())) { // Already exists
                return false;
            }
        }
        return Q.add(newState);
    }

    @Override
    public boolean setFinal(String name) {
        for (DFAState state: Q) {
            if (state.getName().equals(name)) {
                return F.add(state);
            }
        }
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
    }

    @Override
    public boolean isStart(String name) {
        return q0.getName().equals(name);
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        if (inSigma(onSymb) && isValidState(fromState) && isValidState(toState)) {
            DFAState origin = getDFAState(fromState);
            DFAState destination = getDFAState(toState);

            if (origin == null) { // Checking if origin is valid object
                return false;
            }

            origin.addDFATransition(onSymb, destination);
            DFAState result = origin.getDFATransition(onSymb);
            if (result.getName().equals(destination.getName())) {
                return true;
            }
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

    @Override

    // further revision might needed
public String toString() {
    StringBuilder sb = new StringBuilder();
    
    // Append states (Q)
    sb.append("Q = { ");
    for (State state : Q) {
        sb.append(state.getName()).append(" ");
    }
    sb.append("}\n");
    
    // Append alphabet (Sigma)
    sb.append("Sigma = { ");
    for (Character symbol : Sigma) {
        sb.append(symbol).append(" ");
    }
    sb.append("}\n");
    
    // Append transition function (delta)
    sb.append("delta =\n\t");
    for (Character symbol : Sigma) {
        sb.append(symbol).append("\t");
    }
    sb.append("\n");
    for (State state : Q) {
        DFAState dfaState = (DFAState) state; // Cast to DFAState to access transitions
        sb.append(dfaState.getName()).append("\t");
        for (Character symbol : Sigma) {
            DFAState nextState = dfaState.getDFATransition(symbol);

            if (nextState != null) {
                sb.append(nextState.getName()).append("\t");
            } else {
                sb.append("-").append("\t");
            }
        }
        sb.append("\n");
    }
    
    // Append start state (q0)
    sb.append("q0 = ").append(q0.getName()).append("\n");
    
    // Append final states (F)
    sb.append("F = { ");
    for (State state : F) {
        sb.append(state.getName()).append(" ");
    }
    sb.append("}\n");
    
    return sb.toString();
}

}


