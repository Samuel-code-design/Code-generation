package io.swagger.repository;

import io.swagger.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findByUserId(Long userId);

    List<Account> findByIban(String iban);

    boolean existsByiban(String iban);

    Account findOneByIban(String iban);
}
