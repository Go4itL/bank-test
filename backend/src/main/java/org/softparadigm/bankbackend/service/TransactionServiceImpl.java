package org.softparadigm.bankbackend.service;

import org.softparadigm.bankbackend.dto.TransactionDto;
import org.softparadigm.bankbackend.dto.TransactionPayload;
import org.softparadigm.bankbackend.model.Transaction;
import org.softparadigm.bankbackend.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionServiceImpl
        extends BaseService<Transaction, String, TransactionDto, TransactionPayload>
        implements ITransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Override
    public JpaRepository<Transaction, String> getRepository() {
        return transactionRepository;
    }

    @Override
    public TransactionDto getDto(@NonNull Transaction model) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setSender(model.getSender());
        transactionDto.setReceiver(model.getReceiver());
        transactionDto.setCreatedOn(model.getCreatedOn());

        return transactionDto;
    }

    @Override
    public Transaction mapEntity(TransactionPayload payload) {
        return null;
    }

    @Override
    public Transaction buildCriteria(Map<String, String> filters) {
        return null;
    }
}
