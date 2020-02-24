package tw.com.aitc.SBE.WebThymeleaf.account;

import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource // 能開放通用的 CRUD API
public interface AccountRepository extends JpaRepository<Account, String> {
}
