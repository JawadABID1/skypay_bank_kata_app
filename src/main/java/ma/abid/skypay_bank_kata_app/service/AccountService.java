package ma.abid.skypay_bank_kata_app.service;

public interface AccountService {
    public void deposit(int amount);
    public void withdraw(int amount);
    public void printStatement();

}
