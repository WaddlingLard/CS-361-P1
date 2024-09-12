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
        if (Q.add(newState)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setFinal(String name) {
        DFAState tempState = new DFAState(name);
        if (Q.contains(tempState)) {
            F.add(tempState);
            return true;
        }
        return false;
    }

    @Override
    public boolean setStart(String name) {
        DFAState tempState = new DFAState(name);
        if (Q.contains(tempState)) {
            q0 = tempState;
        }
        return false;
    }

    @Override
    public void addSigma(char symbol) {
        Sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
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
            if(state.getName().equals(name)){
                return state;
            }
            return null;
        }
    }

    @Override
    public boolean isFinal(String name) {
        return F.contains(name);
    }

    @Override
    public boolean isStart(String name) {
        return q0.contains(name);
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        return false;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        return null;
    }
}
