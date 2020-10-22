package seedu.address.model.problem;

import java.util.ArrayList;

public class ProblemList {
    private ArrayList<String> problems;
    public ProblemList() {
        this.problems = new ArrayList<>();
    }
    public ArrayList<String> getProblems() {
        return problems;
    }
    public void addProblem(String problem) {
        problems.add(problem);
    }
}
