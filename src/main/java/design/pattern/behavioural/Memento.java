package design.pattern.behavioural;

import common.helper.workflow.Workflow;

import java.util.List;

/**
 * {@link Memento} will be used to snapshot an object's state, and then it can be used to restore
 *      the state at later point with the help of {@link Command}.
 * */
public interface Memento {}

class WorkflowDesigner {
    private Workflow workflow;

    public void createWorkflow(String workflowName) {
        this.workflow = new Workflow(workflowName);
    }

    public void addStep(String stepName) {
        this.workflow.addWorkflowStep(stepName);
    }


    public void print() {
        System.out.println(this.workflow.toString());
    }

    public WorkflowMemento snapshot() {
        if (this.workflow == null) {
            return new WorkflowMemento();
        }

        return new WorkflowMemento(workflow.getName(), workflow.getSteps());
    }

    public void setMemento(WorkflowMemento workflowMemento) {
        if (workflowMemento.workflowName == null && workflowMemento.workflowSteps == null) {
            this.workflow = null;
        } else {
            this.workflow = new Workflow(workflowMemento.workflowName, workflowMemento.workflowSteps);
        }
    }

    public static class WorkflowMemento implements Memento {
        private String workflowName;
        private String[] workflowSteps;

        private WorkflowMemento() {}

        private WorkflowMemento(String workflowName, List<String> workflowSteps) {
            this.workflowName = workflowName;
            this.workflowSteps = workflowSteps.toArray(new String[0]);
        }
    }

    public static void main(String[] args) {
        WorkflowDesigner workflowDesigner = new WorkflowDesigner();
        workflowDesigner.createWorkflow("Leave workflow");

        workflowDesigner.print();

        AddStepCommand.addStep(workflowDesigner, "Apply leave");
        workflowDesigner.print();

        AddStepCommand.addStep(workflowDesigner, "Send for approval");
        workflowDesigner.print();

        AddStepCommand addStepCommand3 = AddStepCommand.addStep(workflowDesigner, "Rejected leave");
        workflowDesigner.print();

        addStepCommand3.undo();

        AddStepCommand.addStep(workflowDesigner, "Leave approved");
        workflowDesigner.print();
    }
}

class AddStepCommand implements Command {
    private final WorkflowDesigner workflowDesigner;
    private final String stepName;
    private WorkflowDesigner.WorkflowMemento workflowMemento;

    public AddStepCommand(WorkflowDesigner workflowDesigner, String stepName) {
        this.workflowDesigner = workflowDesigner;
        this.stepName = stepName;
    }

    @Override
    public void execute() {
        this.workflowMemento = this.workflowDesigner.snapshot();
        this.workflowDesigner.addStep(this.stepName);
    }

    @Override
    public void undo() {
        this.workflowDesigner.setMemento(workflowMemento);
    }

    public static AddStepCommand addStep(WorkflowDesigner workflowDesigner,String stepName) {
        AddStepCommand addStepCommand = new AddStepCommand(workflowDesigner, stepName);

        addStepCommand.execute();

        return addStepCommand;
    }
}
