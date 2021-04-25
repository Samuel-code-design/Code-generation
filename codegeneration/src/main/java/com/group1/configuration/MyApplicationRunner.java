package com.group1.configuration;

import com.group1.model.Account;
import com.group1.model.AccountType;
import com.group1.model.Customer;
import com.group1.model.User;
import com.group1.repository.AccountRepository;
import com.group1.repository.EmployeeRepository;
import com.group1.service.AccountService;
import com.group1.service.EmployeeService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private AccountRepository accountRepository;

    private EmployeeRepository employeeRepository;

    public MyApplicationRunner(AccountRepository accountRepository, EmployeeRepository employeeRepository) {
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user1 = new User( "JanSmit", "1234", 50, 100);
        User user2 = new User("meneerYeet", "abcdefg", 70, 500);

        employeeRepository.save(user1);
        employeeRepository.save(user2);


        List<Account> accounts =
                Arrays.asList(
                        new Account( "NL03INHO02345678910", AccountType.CURRENT, 500, 1500, user1),
                        new Account("NL06INHO02345565430", AccountType.SAVING, 20000, 30000, user1),
                        new Account("NL09INHO02342345456", AccountType.CURRENT, 1356, 1000, user2));
        accounts.forEach(accountRepository::save);

    }
}
