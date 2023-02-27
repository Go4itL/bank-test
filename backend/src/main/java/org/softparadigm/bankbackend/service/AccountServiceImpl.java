package org.softparadigm.bankbackend.service;

import org.softparadigm.bankbackend.dto.AccountDto;
import org.softparadigm.bankbackend.dto.AccountPayload;
import org.softparadigm.bankbackend.model.Account;
import org.softparadigm.bankbackend.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccountServiceImpl
        extends BaseService<Account, String, AccountDto, AccountPayload>
        implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public JpaRepository<Account, String> getRepository() {
        return accountRepository;
    }

    @Override
    public AccountDto getDto(Account model) {

        AccountDto dto = new AccountDto();

        dto.setAccountNumber(model.getAccountNumber());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setCreatedOn(model.getCreatedOn());

        return dto;
    }

    @Override
    public Account mapEntity(AccountPayload payload) {
        Account account = new Account();
        account.setAccountNumber(payload.getAccountNumber());
        account.setFirstName(payload.getFirstName());
        account.setLastName(payload.getLastName());

        return account;
    }

    @Override
    public Account buildCriteria(Map<String, String> filters) {
        Account account = new Account();
        if(!filters.isEmpty()) {
            String firstNameFilter = filters.remove("firstName");
            if(firstNameFilter != null){
                account.setFirstName(firstNameFilter);
            }
            String lastNameFilter = filters.remove("lastName");
            if(lastNameFilter != null){
                account.setLastName(lastNameFilter);
            }
            String accountNUmberFilter = filters.remove("accountNumber");
            if(accountNUmberFilter != null){
                account.setAccountNumber(accountNUmberFilter);
            }
        }
        return account;
    }
}