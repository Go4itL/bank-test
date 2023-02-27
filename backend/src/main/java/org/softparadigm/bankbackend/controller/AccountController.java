package org.softparadigm.bankbackend.controller;

import org.softparadigm.bankbackend.dto.AccountDto;
import org.softparadigm.bankbackend.dto.AccountPayload;
import org.softparadigm.bankbackend.model.Account;
import org.softparadigm.bankbackend.service.IAccountService;
import org.softparadigm.bankbackend.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseController<Account,String,AccountDto,AccountPayload> {

    @Autowired
    private IAccountService accountService;
    @Override
    public IBaseService<Account, String, AccountDto, AccountPayload> getService() {
        return accountService;
    }
}
