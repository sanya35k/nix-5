package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Balance> balances;

    public User(){}

    public User(String name, List<Balance> balances){
        this.name = name;
        this.balances = balances;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Balance> getBalances() {
        return balances;
    }
    public void setBalance(List<Balance> balances) {
        this.balances = balances;
    }

    public void addBalance(Balance balance){
        balances.add(balance);
        balance.setUser(this);
    }
}