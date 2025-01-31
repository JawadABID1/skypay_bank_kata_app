package ma.abid.skypay_bank_kata_app;

import ma.abid.skypay_bank_kata_app.model.Transaction;
import ma.abid.skypay_bank_kata_app.printer.StatementPrinter;
import ma.abid.skypay_bank_kata_app.repository.TransactionRepository;
import ma.abid.skypay_bank_kata_app.service.AccountService;
import ma.abid.skypay_bank_kata_app.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountAcceptanceTest {

    @Test
    void shouldPrintStatementWithTransactions() {
        // Arrange : Création des mocks
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        StatementPrinter statementPrinter = mock(StatementPrinter.class);
        AccountService account = new AccountServiceImpl(transactionRepository, statementPrinter);

        // Fake transactions
        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);

        // Capturer les transactions ajoutées
        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepository, times(3)).addTransaction(transactionCaptor.capture());

        // Vérifier que les transactions ont bien été enregistrées
        List<Transaction> capturedTransactions = transactionCaptor.getAllValues();
        assertEquals(1000, capturedTransactions.get(0).getAmount());
        assertEquals(LocalDate.now(), capturedTransactions.get(0).getDate());

        assertEquals(2000, capturedTransactions.get(1).getAmount());
        assertEquals(LocalDate.now(), capturedTransactions.get(1).getDate());

        assertEquals(-500, capturedTransactions.get(2).getAmount());
        assertEquals(LocalDate.now(), capturedTransactions.get(2).getDate());

        // Act: Appeler la méthode printStatement
        account.printStatement();

        // Vérifier que StatementPrinter.print() a été appelée avec la bonne liste de transactions
        verify(statementPrinter).print(anyList());
    }
}
