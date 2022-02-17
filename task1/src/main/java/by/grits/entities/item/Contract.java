package by.grits.entities.item;

import by.grits.entities.people.CommonUser;

import java.util.Map;

public class Contract {
    private static int contractId;
    private String signingDate;
    private Item contractSubject;
    private Map<CommonUser, CommonUser> contractParties;

    public Contract(String signingDate, Item contractSubject, Map<CommonUser, CommonUser> contractParties) {
        this.signingDate = signingDate;
        this.contractSubject = contractSubject;
        this.contractParties = contractParties;
        contractId = 4578;
    }

    public static int getContractId() {
        return contractId;
    }

    public String getSigningDate() {
        return signingDate;
    }

    public Item getContractSubject() {
        return contractSubject;
    }

    public Map<CommonUser, CommonUser> getContractParties() {
        return contractParties;
    }
}
