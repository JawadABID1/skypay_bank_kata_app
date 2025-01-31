package ma.abid.skypay_bank_kata_app.printer;

import ma.abid.skypay_bank_kata_app.model.Transaction;

import java.util.List;

public interface StatementPrinter {
    void print(List<Transaction> transactions);
}
