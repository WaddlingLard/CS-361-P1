package fa.dfa;

import fa.State;

import java.util.Map; // I believe we need this for delta
import java.util.HashSet;
import java.util.Set;

public class DFA implements DFAInterface{

    private Set<DFAState> Q; // All states
    private Set<DFAState> F; // Final states
    private Set<Character> Sigma; // Alphabet
    private DFAState q0; // Initial state
    //Need something to hold delta


    public DFA() {
        Q = new HashSet<DFAState>();
        F = new HashSet<DFAState>();
        Sigma = new HashSet<Character>();
        q0 = null;
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
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        return false;
    }

    @Override
    public boolean isStart(String name) {
        return false;
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
