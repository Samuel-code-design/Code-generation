package io.swagger.repository;

import io.swagger.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUserId(Long userId);

    List<Account> findByIban(String iban);

    Account findOneByIban(String iban);

    boolean existsByiban(String iban);
}
