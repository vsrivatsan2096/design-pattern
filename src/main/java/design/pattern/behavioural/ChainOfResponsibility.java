package design.pattern.behavioural;

import common.helper.leave.LeaveApplication;

import java.time.LocalDate;

/**
 * {@link ChainOfResponsibility} will be used to decouple the sender of request and the handler of request so that
 *              multiple handler can process the request and then pass the request to next handler
 * */
public interface ChainOfResponsibility {}

interface LeaveApprover extends ChainOfResponsibility {
    void approveLeave(LeaveApplication leaveApplication);

    public String getApproverRole();
}

abstract class Employee implements LeaveApprover {
    private final String role;
    private final Employee successor;

    public Employee(String role, Employee successor) {
        this.role = role;
        this.successor = successor;
    }

    @Override
    public String getApproverRole() {
        return role;
    }

    @Override
    public void approveLeave(LeaveApplication leaveApplication) {
        System.out.println("Leave application is in the inbox of the " + getApproverRole());

        if (!processLeaveApproval(leaveApplication) && successor != null) {
            System.out.println("Leave application is not processed by the " + getApproverRole());
            successor.approveLeave(leaveApplication);

            return;
        }

        System.out.println("Leave application: " + leaveApplication);
    }

    public abstract boolean processLeaveApproval(LeaveApplication leaveApplication);

    public static void main(String[] args) {
        LeaveApplication leaveApplication = LeaveApplication.getBuilder()
                .withEmployeeName("Srivatsan")
                .withEmployeeId("10112")
                .leaveStartsOn(LocalDate.now())
                .leaveEndsOn(LocalDate.of(2024, 1, 5))
                .withLeaveType(LeaveApplication.Type.PRIVILEGED)
                .build();

        LeaveApplication leaveApplication2 = LeaveApplication.getBuilder()
                .withEmployeeName("Gautham")
                .withEmployeeId("10134")
                .leaveStartsOn(LocalDate.now())
                .leaveEndsOn(LocalDate.of(2024, 1, 15))
                .withLeaveType(LeaveApplication.Type.PRIVILEGED)
                .build();

        Employee employee = createChain();

        employee.approveLeave(leaveApplication);
        employee.approveLeave(leaveApplication2);
    }

    public static Employee createChain() {
        Director director = new Director(null);
        Manager manager = new Manager(director);

        return new Lead(manager);
    }
}

class Lead extends Employee {

    public Lead(Employee successor) {
        super("Lead", successor);
    }

    @Override
    public boolean processLeaveApproval(LeaveApplication leaveApplication) {

        if (leaveApplication.getLeaveType() == LeaveApplication.Type.CASUAL) {
            leaveApplication.accept();
            return true;
        }

        return false;
    }
}

class Manager extends Employee {

    public Manager(Employee successor) {
        super("Manager", successor);
    }

    @Override
    public boolean processLeaveApproval(LeaveApplication leaveApplication) {

        if (leaveApplication.getLeaveType() == LeaveApplication.Type.PRIVILEGED &&
            leaveApplication.getLeaveDays() < 7) {
            leaveApplication.accept();
            return true;
        }

        return false;
    }
}

class Director extends Employee {

    public Director(Employee successor) {
        super("Director", successor);
    }

    @Override
    public boolean processLeaveApproval(LeaveApplication leaveApplication) {

        if (leaveApplication.getLeaveType() == LeaveApplication.Type.LOSS_OF_PAY ||
                leaveApplication.getLeaveDays() < 10) {
            leaveApplication.accept();
            return true;
        }

        leaveApplication.reject();
        return false;
    }
}