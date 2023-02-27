package org.softparadigm.bankbackend.service;

import org.softparadigm.bankbackend.dto.TransactionDto;
import org.softparadigm.bankbackend.dto.TransactionPayload;
import org.softparadigm.bankbackend.model.Transaction;

public interface ITransactionService extends IBaseService<Transaction,String,TransactionDto, TransactionPayload> {
}
