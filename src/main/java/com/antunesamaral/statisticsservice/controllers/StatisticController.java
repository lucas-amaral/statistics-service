package com.antunesamaral.statisticsservice.controllers;

import com.antunesamaral.statisticsservice.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticController {

    private final TransactionService transactionService;

    public StatisticController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<?> getStatistics() {
        return ResponseEntity.ok(transactionService.getLast60SecondsStatistics());
    }
}
