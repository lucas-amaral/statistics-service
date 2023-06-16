package com.antunesamaral.statisticsservice.controllers;

import com.antunesamaral.statisticsservice.entities.requests.TransactionRequestBody;
import com.antunesamaral.statisticsservice.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<?> postTransaction(@RequestBody TransactionRequestBody body) {
        return transactionService.saveTransaction(body);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTransaction() {
        transactionService.deleteAllTransactions();
        return ResponseEntity.noContent().build();
    }
}
