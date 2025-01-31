package ma.abid.skypay_bank_kata_app;

import ma.abid.skypay_bank_kata_app.model.Transaction;
import ma.abid.skypay_bank_kata_app.printer.StatementPrinter;
import ma.abid.skypay_bank_kata_app.repository.TransactionRepository;
import ma.abid.skypay_bank_kata_app.service.AccountService;
import ma.abid.skypay_bank_kata_app.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SkypayBankKataAppApplicationTests {

	private AccountService accountService;
	private TransactionRepository transactionRepository;
	private StatementPrinter statementPrinter;

	@BeforeEach
	void setUp() {
		transactionRepository = mock(TransactionRepository.class);
		statementPrinter = mock(StatementPrinter.class);
		accountService = new AccountServiceImpl(transactionRepository, statementPrinter);
	}

	@Test
	void depositShouldStoreTransaction() {
		// Arrange
		int depositAmount = 1000;

		// Act
		accountService.deposit(depositAmount);

		// Assert
		ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
		verify(transactionRepository).addTransaction(captor.capture());

		Transaction capturedTransaction = captor.getValue();
		assertEquals(depositAmount, capturedTransaction.getAmount());
		assertTrue(LocalDate.now().isEqual(capturedTransaction.getDate())); // Comparaison de date améliorée
	}

	@Test
	void withdrawShouldStoreTransaction() {
		// Arrange
		int withdrawAmount = 500;

		// Act
		accountService.withdraw(withdrawAmount);

		// Assert
		ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
		verify(transactionRepository).addTransaction(captor.capture());

		Transaction capturedTransaction = captor.getValue();
		assertEquals(-withdrawAmount, capturedTransaction.getAmount()); // Vérifie le montant négatif
		assertTrue(LocalDate.now().isEqual(capturedTransaction.getDate())); // Comparaison de date améliorée
	}

	@Test
	void printStatementShouldCallStatementPrinter() {
		Transaction transaction1 = Transaction.builder()
				.amount(1000)
				.date(LocalDate.of(2012, 1, 10))
				.build();
		Transaction transaction2 = Transaction.builder()
				.amount(2000)
				.date(LocalDate.of(2012, 1, 13))
				.build();
		Transaction transaction3 = Transaction.builder()
				.amount(-500)
				.date(LocalDate.of(2012, 1, 14))
				.build();
		List<Transaction> transactions = Arrays.asList(
				transaction1,
				transaction2,
				transaction3
		);

		when(transactionRepository.getAllTransactions()).thenReturn(transactions);

		accountService.printStatement();
		verify(statementPrinter).print(transactions);
	}

}
