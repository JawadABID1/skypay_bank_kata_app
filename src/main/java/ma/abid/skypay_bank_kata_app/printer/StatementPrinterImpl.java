package ma.abid.skypay_bank_kata_app.printer;

import ma.abid.skypay_bank_kata_app.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatementPrinterImpl implements StatementPrinter {
    @Override
    public void print(List<Transaction> transactions) {
        int balance = 0;
        System.out.println("Date       || Amount || Balance");
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
//            System.out.println("balance: "+ balance);
        }

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            System.out.printf("%s || %d || %d%n", transaction.getDate(), transaction.getAmount(), balance);
            balance -= transaction.getAmount();
        }
    }
}
