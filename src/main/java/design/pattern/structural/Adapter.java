package design.pattern.structural;

import common.helper.customer.BankCustomer;
import common.helper.customer.InsuranceCustomer;

import java.time.LocalDate;

/**
 * {@link Adapter} is used to adapt two different object when client has one object
 *          and our api expects a different object
 * */
public interface Adapter {}

class InsuranceClassAdapter extends BankCustomer implements Adapter, InsuranceCustomer {

    @Override
    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    @Override
    public Integer getAgeInYears() {
        return LocalDate.now().getYear() - this.getDateOfBirth().getYear();
    }

    private static void populateBankCustomer(BankCustomer bankCustomer) {
        bankCustomer.setFirstName("Srivatsan");
        bankCustomer.setLastName("Veeraraghavan");
        bankCustomer.setDateOfBirth(LocalDate.of(1996, 11, 20));
    }

    private static void processInsurance(InsuranceCustomer insuranceCustomer) {
        System.out.println(insuranceCustomer.getFullName() + " with " + insuranceCustomer.getAgeInYears() + " is applied for insurance.");
    }

    public static void main(String[] args) {
        InsuranceClassAdapter insuranceClassAdapter = new InsuranceClassAdapter();
        populateBankCustomer(insuranceClassAdapter);
        processInsurance(insuranceClassAdapter);
    }
}

class InsuranceObjectAdapter implements InsuranceCustomer {
    private final BankCustomer bankCustomer;
    private InsuranceObjectAdapter(BankCustomer bankCustomer) {
        this.bankCustomer = bankCustomer;
    }

    @Override
    public String getFullName() {
        return bankCustomer.getFirstName() + " " + bankCustomer.getLastName();
    }

    @Override
    public Integer getAgeInYears() {
        return LocalDate.now().getYear() - bankCustomer.getDateOfBirth().getYear();
    }

    private static BankCustomer getBankCustomer() {
        BankCustomer bankCustomer = new BankCustomer();

        bankCustomer.setFirstName("Srivatsan");
        bankCustomer.setLastName("Veeraraghavan");
        bankCustomer.setDateOfBirth(LocalDate.of(1996, 11, 20));

        return bankCustomer;
    }

    private static void processInsurance(InsuranceCustomer insuranceCustomer) {
        System.out.println(insuranceCustomer.getFullName() + " with " + insuranceCustomer.getAgeInYears() + " is applied for insurance.");
    }

    public static void main(String[] args) {
        BankCustomer bankCustomer = getBankCustomer();
        InsuranceObjectAdapter insuranceClassAdapter = new InsuranceObjectAdapter(bankCustomer);
        processInsurance(insuranceClassAdapter);
    }
}