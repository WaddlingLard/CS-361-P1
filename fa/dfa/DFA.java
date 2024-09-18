package fa.dfa;

import fa.State;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
/*
    This class is used as an acting DFA which enables the user to create a DFA and in turn, a Finite Automata.

    @author Brian Wu
    @author Max Ma
    @version 1.0
 */
public class DFA implements DFAInterface{

    private Set<DFAState> Q; // All states
    private Set<DFAState> F; // Final states
    private Set<Character> Sigma; // Alphabet
    private DFAState q0; // Initial state

    public DFA() {
        this.Q = new LinkedHashSet<>();
        this.F = new LinkedHashSet<>();
        this.Sigma = new LinkedHashSet<>();
        this.q0 = null;
    }
    @Override
    public boolean addState(String name) {
        DFAState newState = new DFAState(name);
        for (DFAState state: Q) {
            if (state.getName().equals(newState.getName())) { // State already exists
                return false;
            }
        }
        return Q.add(newState);
    }

    @Override
    public boolean setFinal(String name) {
        for (DFAState state: Q) {
            if (state.getName().equals(name)) { // State exists!
                return F.add(state);
            }
        }
        return false;
    }

    @Override
    public boolean setStart(String name) {
        for (DFAState state: Q) {
            if (state.getName().equals(name)) { // State exists!
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
        for (char c: s.toCharArray()){ // This is to divide each operation by a character
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
    public State getState(String name) {
        for( State state: Q){
            if(state.getName().equals(name)) { // State found!
                return state;
            }
        }
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        for (DFAState state: F) {
            if (state.getName().equals(name)) { // State is in the F set!
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
        if (inSigma(onSymb) && isValidState(fromState) && isValidState(toState)) { // Validate all data provided
            DFAState origin = getDFAState(fromState);
            DFAState destination = getDFAState(toState);

            if (origin == null) { // Checking if origin is valid object
                return false;
            }

            origin.addDFATransition(onSymb, destination);
            DFAState result = origin.getDFATransition(onSymb); // Validating if transition correctly occurred
            if (result.getName().equals(destination.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        DFA newDFA = new DFA();

        newDFA.Sigma = new LinkedHashSet<>(this.Sigma); // This is the only thing that is copied over from the original DFA
        for (DFAState state: this.Q) { // Instantiating the new DFA's fields
            DFAState tempState = new DFAState(state.getName());
            newDFA.Q.add(tempState);

            if(this.isFinal(tempState.getName())) {
                newDFA.setFinal(tempState.getName());
            }

            if(this.isStart(tempState.getName())) {
                newDFA.setStart(tempState.getName());
            }

        }

        for(DFAState state : this.Q){ // Now flipping the transitions

            for (Character sym : this.Sigma){
                DFAState nextState = state.getDFATransition(sym);

                if(nextState != null){ // Valid transition found
                     char newSym;
                    
                    if(sym == symb1 ){ // These branches of if statements is to determine what Character to use
                        newSym = symb2;
                    }else if(sym == symb2){
                        newSym = symb1;
                    } else{
                        newSym = sym;
                    }

                    DFAState newState = newDFA.getDFAState(state.getName());
                    DFAState newNextState = newDFA.getDFAState(nextState.getName());
                    if (newState != null && newNextState != null) {
                        newDFA.addTransition(newState.getName(), newNextState.getName(), newSym); // 'Swapped' transition applied!
                    }
                }
                
            }
        }
        return newDFA;
    }

    /*
       A helper method that determines if the provided alphabet is inside the Sigma set
       @param check
       @return True if inSigma, False if not
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
       A helper method that determines if the provided String 'state' is in the set
       @param name
       @return True if valid state, false if not
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
       A helper method that returns the DFA state that is reflective of the provided name
       @param name
       @return The DFAState that reflects the given name
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
        for (DFAState state : Q) {
            DFAState dfaState = state; // Cast to DFAState to access transitions
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


