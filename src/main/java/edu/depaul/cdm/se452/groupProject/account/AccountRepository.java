package edu.depaul.cdm.se452.groupProject.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Custom finder to find account by username
    Account findByUsername(String username);
}
