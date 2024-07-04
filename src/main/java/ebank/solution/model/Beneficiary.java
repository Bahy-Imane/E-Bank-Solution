package ebank.solution.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "beneficiary")
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long benefId;

    @Column(name = "account_type")
    private String benefName;

    @Column(nullable = false)
    private Double benefAccNumber;

    @OneToMany(mappedBy = "beneficiary", fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

}
