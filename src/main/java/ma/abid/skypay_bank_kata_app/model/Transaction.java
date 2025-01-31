package ma.abid.skypay_bank_kata_app.model;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class Transaction {
    private LocalDate date;
    private int amount;
//    private int balance;
}
