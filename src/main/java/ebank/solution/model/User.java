package ebank.solution.model;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(name = "psswd")
    private  String password;

    @Column(name = "adress")
    private String address;

    @Column(unique = true, nullable = false)
    private String email;

//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    @JsonIgnore
//    private List<Account> accounts = new ArrayList<>();

}
