package project1.easysplit.Domain;

import jakarta.persistence.*;
import lombok.Getter;


import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill", cascade = CascadeType.ALL)
    private Set<Person> people;

    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY)
    private List<FinancialEntry> financialEntryList;

    public Bill(String name, Set<Person> people) {
        this.name = name;
        this.people = people;
    }

    public Bill() {

    }
}
