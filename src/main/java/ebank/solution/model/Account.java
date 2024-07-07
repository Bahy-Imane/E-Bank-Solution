package ebank.solution.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "acc_number")
    private Long accNumber;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private String blockRaison;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Beneficiary> beneficiaries = new ArrayList<>();

    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private User user;

}
