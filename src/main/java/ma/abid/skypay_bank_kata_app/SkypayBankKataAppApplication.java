package ma.abid.skypay_bank_kata_app;

import ma.abid.skypay_bank_kata_app.printer.StatementPrinter;
import ma.abid.skypay_bank_kata_app.repository.TransactionRepository;
import ma.abid.skypay_bank_kata_app.service.AccountService;
import ma.abid.skypay_bank_kata_app.service.AccountServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SkypayBankKataAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkypayBankKataAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TransactionRepository transactionRepository, StatementPrinter statementPrinter){
		return args -> {
			AccountService account = new AccountServiceImpl(transactionRepository, statementPrinter);
			account.deposit(1000);
			account.deposit(2000);
			account.withdraw(500);
			account.printStatement();
		};
	}

}
