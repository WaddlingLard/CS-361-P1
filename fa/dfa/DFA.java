package fa.dfa;

import fa.State;

import java.util.Map; // I believe we need this for delta
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap; // add this in case 

public class DFA implements DFAInterface{

    private Set<DFAState> Q; // All states
    private Set<DFAState> F; // Final states
    private Set<Character> Sigma; // Alphabet
    private DFAState q0; // Initial state
    Map<DFAState, Map<Character,DFAState>> delta;//Need something to hold delta 
    // not sure if this is correct 


    public DFA() {
        this.Q = new HashSet<DFAState>();
        this.F = new HashSet<DFAState>();
        this.Sigma = new HashSet<Character>();
        this.q0 = null;
        this.delta = new HashMap<>();

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
        char[] traceString = s.toCharArray();
        for (int i = 0; i < traceString.length; i++) {

        }

        return false;
    }

    @Override
    public Set<Character> getSigma() {
        Set<Character> tempSigma = new HashSet<>(); // This is here for encapsulation
        Object[] temp = Sigma.toArray();
        for (int i = 0; i < temp.length; i++) {
            tempSigma.add((Character) temp[i]);
        }
        return tempSigma;
    }

    @Override
    public State getState(String name) {
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

    @Override
    public boolean isStart(String name) {
        return q0.getName().equals(name);
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        return false;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        return null;
    }

    /*
       @author
       @param state
       @param input
       @returns The next transition state
     */
    private DFAState getTransition(DFAState state, Character input) {

        return null;
    }
}
