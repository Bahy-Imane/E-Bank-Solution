package ebank.solution.model;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
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
    private Date transDateTime;

    @Column(name = "target")
    private Long target;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_num")
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "benef_acc_number")
    private Beneficiary beneficiary;

}
