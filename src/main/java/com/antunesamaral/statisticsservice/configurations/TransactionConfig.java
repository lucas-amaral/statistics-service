package com.antunesamaral.statisticsservice.configurations;

import com.antunesamaral.statisticsservice.entities.Statistic;
import com.antunesamaral.statisticsservice.entities.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ConcurrentLinkedQueue;

@Configuration
@EnableScheduling
public class TransactionConfig {

    @Bean("transactions")
    public ConcurrentLinkedQueue<Transaction> getTransactions() {
        //todo: change in next versions to use a message broker like RabbitMQ, Kafka, SQS, etc
        return new ConcurrentLinkedQueue<>();
    }

    @Bean("lastStatistic")
    public Statistic getLastCalculatedStatistic() {
        return Statistic.empty();
    }
}
