package com.example.dirty_a.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.dirty_a.R;
import com.example.dirty_a.dataproviders.TransactionDataProvider;
import com.example.dirty_a.model.Transaction;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BalanceOverviewFragment extends Fragment {

    // UI elements
    private TableLayout transactionTable;

    // Data
    private List<Transaction> transactions = new ArrayList<>();
    private DateFormat dateFormat = new SimpleDateFormat("MM-dd");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Balance");
        return inflater.inflate(R.layout.fragment_balance_overview, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set UI elements
        transactionTable = getActivity().findViewById(R.id.balance_overview_transaction_table);

        // Get test data
        this.transactions.addAll(TransactionDataProvider.getInstance().getTransactions());

        // Set table data
        setTableData();
    }

    private void setTableData() {
        for (int i=0; i<this.transactions.size(); i++) {
            transactionTable.addView(createTableRow(transactions.get(i), i%2 == 0));
        }
    }

    private TableRow createTableRow(Transaction transaction, boolean hasEvenIndex) {
        TableRow tableRow = new TableRow(this.getContext());

        tableRow.addView(createTableRowTextView(this.dateFormat.format(transaction.getDate()), 1f), 0);
        tableRow.addView(createTableRowTextView(transaction.getOwner(), 1f), 1);
        tableRow.addView(createTableRowTextView(transaction.getDescription(), 2f), 2);
        tableRow.addView(createTableRowTextView(transaction.getAmount().toPlainString(), 1f), 3);

        if (hasEvenIndex) {
            tableRow.setBackgroundColor(Color.parseColor("#f1f1f1"));
        }

        return tableRow;
    }

    private TextView createTableRowTextView(String text, float weight) {
        TextView textView = new TextView(this.getContext());
        textView.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, weight));
        textView.setText(text);
        return textView;
    }

}
