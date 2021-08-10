package paidagogos.speck.account;

public class Account {
    private String mainAccountOwner;
    private String mainAccountNumber;
    private String mainAccountBank;
    private String subAccountOwner;
    private String subAccountNumber;
    private String subAccountBank;

    public String getMainAccountOwner() {
        return mainAccountOwner;
    }

    public void setMainAccountOwner(String mainAccountOwner) {
        this.mainAccountOwner = mainAccountOwner;
    }

    public String getMainAccountNumber() {
        return mainAccountNumber;
    }

    public void setMainAccountNumber(String mainAccountNumber) {
        this.mainAccountNumber = mainAccountNumber;
    }

    public String getMainAccountBank() {
        return mainAccountBank;
    }

    public void setMainAccountBank(String mainAccountBank) {
        this.mainAccountBank = mainAccountBank;
    }

    public String getSubAccountOwner() {
        return subAccountOwner;
    }

    public void setSubAccountOwner(String subAccountOwner) {
        this.subAccountOwner = subAccountOwner;
    }

    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }

    public String getSubAccountBank() {
        return subAccountBank;
    }

    public void setSubAccountBank(String subAccountBank) {
        this.subAccountBank = subAccountBank;
    }
}
