package ma.abid.skypay_bank_kata_app.repository;

import ma.abid.skypay_bank_kata_app.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    void addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
}
