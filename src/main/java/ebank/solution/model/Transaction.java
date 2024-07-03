package ebank.solution.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "description")
    private String description;

    @Column(name = "trans_type")
    private String transType;

    @Column(name = "trans_date")
    private LocalDateTime transDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "beneficiary_id")
    private Beneficiary beneficiary;

}
