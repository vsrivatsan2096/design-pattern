package design.pattern.behavioural;

import common.helper.entity.Employee;

import java.util.List;


/**
 * {@link Visitor} is used to add new operations that work on objects without modifying class definitions
 * */
public interface Visitor {}

interface EmployeeVisitor extends Visitor {
    void visit(SoftwareEngineer softwareEngineer);
    void visit(TechnicalLead technicalLead);
    void visit(ProjectManager projectManager);
}

class SoftwareEngineer extends Employee {
    private final String skill;
    public SoftwareEngineer(Long id, String name, String skill) {
        this.skill = skill;
        this.setId(id);
        this.setName(name);
    }

    public String getSkill() {
        return skill;
    }

    public void accept(EmployeeVisitor visitor) {
        visitor.visit(this);
    }
}

class TechnicalLead extends Employee {
    private final List<Employee> directReports;

    public TechnicalLead(Long id, String name, SoftwareEngineer... softwareEngineers) {
        directReports = List.of(softwareEngineers);
        this.setId(id);
        this.setName(name);
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void accept(EmployeeVisitor visitor) {
        visitor.visit(this);
    }
}

class ProjectManager extends Employee {
    private final List<Employee> directReports;

    public ProjectManager(Long id, String name, TechnicalLead... technicalLeads) {
        directReports = List.of(technicalLeads);
        this.setId(id);
        this.setName(name);
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void accept(EmployeeVisitor visitor) {
        visitor.visit(this);
    }
}

class PrintEmployeeVisitor implements EmployeeVisitor {

    @Override
    public void visit(SoftwareEngineer softwareEngineer) {
        System.out.println("\t\t" + softwareEngineer.getName() + " is a software engineer with skill in "
                + softwareEngineer.getSkill());
    }

    @Override
    public void visit(TechnicalLead technicalLead) {
        System.out.println("\t" + technicalLead.getName() + " is a technical lead with direct reports of "
                + technicalLead.getDirectReports().size() + " software engineers");
    }

    @Override
    public void visit(ProjectManager projectManager) {
        System.out.println(projectManager.getName() + " is a technical lead with direct reports of "
                + projectManager.getDirectReports().size() + " technical leads");
    }

    public static void main(String[] args) {
        SoftwareEngineer engineer1 = new SoftwareEngineer(10001L, "S1", "Java");
        SoftwareEngineer engineer2 = new SoftwareEngineer(10002L, "S2", "Python");
        SoftwareEngineer engineer3 = new SoftwareEngineer(10003L, "S3", "C#");
        SoftwareEngineer engineer4 = new SoftwareEngineer(10004L, "S4", "C++");

        TechnicalLead lead1 = new TechnicalLead(10010L, "L1", engineer1, engineer2);
        TechnicalLead lead2 = new TechnicalLead(10020L, "L1", engineer3, engineer4);

        ProjectManager manager1 = new ProjectManager(10100L, "M1", lead1, lead2);

        PrintEmployeeVisitor printEmployeeVisitor = new PrintEmployeeVisitor();

        manager1.accept(printEmployeeVisitor);

        lead1.accept(printEmployeeVisitor);

        engineer1.accept(printEmployeeVisitor);
        engineer2.accept(printEmployeeVisitor);

        lead2.accept(printEmployeeVisitor);

        engineer3.accept(printEmployeeVisitor);
        engineer4.accept(printEmployeeVisitor);
    }
}