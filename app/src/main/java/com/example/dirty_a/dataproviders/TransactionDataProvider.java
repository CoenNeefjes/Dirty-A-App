package com.example.dirty_a.dataproviders;

import com.example.dirty_a.model.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TransactionDataProvider {

    private static final TransactionDataProvider instance = new TransactionDataProvider();

    private List<Transaction> transactions = new ArrayList<>();

    private TransactionDataProvider() {
        setTestData();
    }

    public static TransactionDataProvider getInstance() {
        return instance;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    private void setTestData() {
        this.transactions.addAll(Arrays.asList(
                new Transaction(new Date(), "melk", "Junior", BigDecimal.valueOf(-1.96)),
                new Transaction(new Date(), "Subway", "Sandy", BigDecimal.valueOf(5.20)),
                new Transaction(new Date(), "uit eten", "Junior", BigDecimal.valueOf(-1.96)),
                new Transaction(new Date(), "bakfiets", "Sandy", BigDecimal.valueOf(5.20)),
                new Transaction(new Date(), "holle bolle gijs", "Junior", BigDecimal.valueOf(-1.96)),
                new Transaction(new Date(), "Eten k-lijst", "Sandy", BigDecimal.valueOf(5.20)),
                new Transaction(new Date(), "Je moeder", "Junior", BigDecimal.valueOf(-1.96)),
                new Transaction(new Date(), "Hottentottententententoonstelling", "Sandy", BigDecimal.valueOf(5.20)),
                new Transaction(new Date(), "Een hele lange zin gaat hier niet op passen", "Junior", BigDecimal.valueOf(-1.96)),
                new Transaction(new Date(), "Nog een broodje", "Sandy", BigDecimal.valueOf(5.20)),
                new Transaction(new Date(), "Nog meer melk", "Junior", BigDecimal.valueOf(-1.96)),
                new Transaction(new Date(), "Zwarte piet", "Sandy", BigDecimal.valueOf(5.20)),
                new Transaction(new Date(), "Geen idee", "Junior", BigDecimal.valueOf(-1.96)),
                new Transaction(new Date(), "Geld", "Sandy", BigDecimal.valueOf(5.20))
        ));
    }
}
