package org.softparadigm.bankbackend.service;

import org.softparadigm.bankbackend.dto.AccountDto;
import org.softparadigm.bankbackend.dto.AccountPayload;
import org.softparadigm.bankbackend.model.Account;

public interface IAccountService extends IBaseService<Account,String, AccountDto, AccountPayload> {
}
