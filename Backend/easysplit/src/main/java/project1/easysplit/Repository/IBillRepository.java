package project1.easysplit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project1.easysplit.Domain.Bill;

public interface IBillRepository extends JpaRepository<Bill, Long> {

}
