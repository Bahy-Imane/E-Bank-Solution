package ebank.solution.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(name = "number")
    private String number;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked;

    @Column(name = "block_reason")
    private String blockReason;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

}
