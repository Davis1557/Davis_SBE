package tw.com.aitc.SBE.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource // 能開放通用的 CRUD API
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
