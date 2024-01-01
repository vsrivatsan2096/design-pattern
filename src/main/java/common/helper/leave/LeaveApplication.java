package common.helper.leave;

import common.helper.entity.Entity;

import java.time.LocalDate;
import java.time.Period;

public class LeaveApplication implements Entity {

    public enum Type { CASUAL, PRIVILEGED, LOSS_OF_PAY };

    public enum Status { PENDING, ACCEPTED, REJECTED };

    private String employeeName;
    private String employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Type type;
    private Integer leaveDays;
    private Status status = Status.PENDING;

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Type getLeaveType() {
        return type;
    }

    public Integer getLeaveDays() {
        return leaveDays;
    }

    public Status getStatus() {
        return status;
    }

    public void accept() {
        this.status = Status.ACCEPTED;
    }

    public void reject() {
        this.status = Status.REJECTED;
    }

    public static class LeaveApplicationBuilder {
        private String employeeName;
        private String employeeId;
        private LocalDate startDate;
        private LocalDate endDate;
        private Type type;

        public LeaveApplicationBuilder withEmployeeName(String employeeName) {
            this.employeeName = employeeName;
            return this;
        }

        public LeaveApplicationBuilder withEmployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public LeaveApplicationBuilder leaveStartsOn(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public LeaveApplicationBuilder leaveEndsOn(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public LeaveApplicationBuilder withLeaveType(Type type) {
            this.type = type;
            return this;
        }

        public LeaveApplication build() {
            LeaveApplication leaveApplication = new LeaveApplication();

            leaveApplication.employeeName = employeeName;
            leaveApplication.employeeId = employeeId;
            leaveApplication.startDate = startDate;
            leaveApplication.endDate = endDate;
            leaveApplication.type = type;
            leaveApplication.leaveDays = Period.between(startDate, endDate).getDays();

            return leaveApplication;
        }
    }

    public static LeaveApplicationBuilder getBuilder() {
        return new LeaveApplicationBuilder();
    }

    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "[" + getEmployeeName() + "(" + getEmployeeId() + ")" + " from " + getStartDate() + " to " + getEndDate() + " is " + getStatus() + "]";
    }
}
