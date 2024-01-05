package common.helper.workflow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Workflow implements Cloneable {
    private final String name;
    private final List<String> steps;

    public String getName() {
        return name;
    }

    public List<String> getSteps() {
        return steps;
    }

    public Workflow(String name) {
        this.name = name;
        this.steps = new LinkedList<>();
    }

    public Workflow(String name, List<String> steps) {
        this.name = name;
        this.steps = steps;
    }

    public Workflow(String name, String[] steps) {
        this.name = name;
        this.steps = new LinkedList<>(Arrays.asList(steps));
    }

    public void addWorkflowStep(String stepName) {
        this.steps.add(stepName);
    }

    public void removeWorkflowStep(String stepName) {
        this.steps.remove(stepName);
    }

    @Override
    public String toString() {
        return name + " BEGIN [ " + String.join(" -> ", steps) + "] END";
    }

    @Override
    public Workflow clone() {
        try {
            return (Workflow) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
