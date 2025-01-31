package ma.abid.skypay_bank_kata_app.service;

import ma.abid.skypay_bank_kata_app.printer.StatementPrinter;
import ma.abid.skypay_bank_kata_app.model.Transaction;
import ma.abid.skypay_bank_kata_app.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final TransactionRepository transactionRepository;
    private final StatementPrinter statementPrinter;

    public AccountServiceImpl(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Le montant du dépôt doit être positif.");
        Transaction transaction = Transaction.builder()
                .amount(amount)
                .date(LocalDate.now())
                .build();
        transactionRepository.addTransaction(transaction);
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Le montant du retrait doit être positif.");
        Transaction transaction = Transaction.builder()
                .amount(-amount)
                .date(LocalDate.now())
                .build();
        transactionRepository.addTransaction(transaction);
    }

    @Override
    public void printStatement() {
        List<Transaction> transactions = transactionRepository.getAllTransactions();
        statementPrinter.print(transactions);
    }
}
