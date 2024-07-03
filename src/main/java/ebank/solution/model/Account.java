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
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "account_type")
    private String accountType;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private Date createdDate;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Beneficiary> beneficiaries = new ArrayList<>();

    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToOne
    private User user;

}
