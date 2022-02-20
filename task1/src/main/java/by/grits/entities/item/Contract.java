package by.grits.entities.item;

public class Contract {
  private String signingDate;
  private Integer contractSubjectId;
  private String ownerId;
  private String buyerId;

  public Contract(String signingDate, int contractSubject, String ownerId, String buyerId) {
    this.signingDate = signingDate;
    this.contractSubjectId = contractSubject;
    this.buyerId = buyerId;
    this.ownerId = ownerId;
  }

  public String getSigningDate() {
    return signingDate;
  }

  public Integer getContractSubject() {
    return contractSubjectId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getBuyerId() {
    return buyerId;
  }
}
