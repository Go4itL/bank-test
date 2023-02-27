package org.softparadigm.bankbackend.controller;

import org.softparadigm.bankbackend.dto.TransactionDto;
import org.softparadigm.bankbackend.dto.TransactionPayload;
import org.softparadigm.bankbackend.model.Transaction;
import org.softparadigm.bankbackend.service.IBaseService;
import org.softparadigm.bankbackend.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController extends BaseController<Transaction,String, TransactionDto, TransactionPayload>{

    @Autowired
    private ITransactionService transactionService;
    @Override
    public IBaseService<Transaction, String, TransactionDto, TransactionPayload> getService() {
        return transactionService;
    }
}
