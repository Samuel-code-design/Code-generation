package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.AccountType;
import io.swagger.model.User;
import io.swagger.model.dto.AccountCreateDTO;
import io.swagger.model.dto.AccountUpdateDTO;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void willThrowWhenIbanNotFound(){
        assertThatThrownBy(() -> accountService.getAccountByIban("NL01INHO0000000122"))
                .hasMessage("404 NOT_FOUND \"No account found for this iban\"")
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    public void willThrowWhenBankIban(){
        assertThatThrownBy(() -> accountService.getAccountByIban("NL01INHO0000000001"))
                .hasMessage("422 UNPROCESSABLE_ENTITY \"Cannot access the bank's account\"")
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    public void willThrowWhenIbanNotFoundUpdate(){
        AccountUpdateDTO update = new AccountUpdateDTO("NL01INHO0000000122", AccountType.CURRENT, 20.0, false, 2L);
        assertThatThrownBy(() -> accountService.updateAccount(update, "JD0001"))
                .hasMessage("404 NOT_FOUND \"IBAN does not exist\"")
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    public void willThrowWhenUserNotFoundCreate(){
        AccountCreateDTO create = new AccountCreateDTO(AccountType.CURRENT, 20.0, false, 10L);
        assertThatThrownBy(() -> accountService.addAccount(create))
                .hasMessage("404 NOT_FOUND \"There is no user for this UserId\"")
                .isInstanceOf(ResponseStatusException.class);
    }
}
