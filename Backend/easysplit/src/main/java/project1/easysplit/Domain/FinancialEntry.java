package project1.easysplit.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "financialEntry")
public class FinancialEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    private Bill bill;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
    private Double expense;
    private Double debt;

    public FinancialEntry(Person person, Double expense, Double debt) {
        this.person = person;
        this.expense = expense;
        this.debt = debt;
    }

    public FinancialEntry() {

    }
}
