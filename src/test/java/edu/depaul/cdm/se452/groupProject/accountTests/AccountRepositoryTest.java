package edu.depaul.cdm.se452.groupProject.accountTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.groupProject.account.Account;
import edu.depaul.cdm.se452.groupProject.account.AccountRepository;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepo;

    @BeforeEach
    void cleanDatabase() {
        accountRepo.deleteAll();
    }

    @Test
    public void testSaveAccount() {
        Account account = new Account();
        account.setUsername("john_doe");
        account.setPassword("password123");

        Account savedAccount = accountRepo.save(account);

        Optional<Account> retrievedAccount = accountRepo.findById(savedAccount.getId());
        assertNotNull(retrievedAccount);
        assertEquals(retrievedAccount.get().getUsername(), "john_doe");
    }

    @Test
    public void testFindById() {
        Account account = new Account();
        account.setUsername("john_doe");
        account.setPassword("password123");
        Account savedAccount = accountRepo.save(account);

        Optional<Account> retrievedAccount = accountRepo.findById(savedAccount.getId());
        assertNotNull(retrievedAccount);
        assertEquals(retrievedAccount.get().getUsername(), "john_doe");
    }

    @Test
    public void testDeleteAccount() {
        Account account = new Account();
        account.setUsername("john_doe");
        account.setPassword("password123");
        Account savedAccount = accountRepo.save(account);

        accountRepo.delete(savedAccount);
        Optional<Account> retrievedAccount = accountRepo.findById(savedAccount.getId());
        assertEquals(retrievedAccount.isPresent(), false);
    }
}