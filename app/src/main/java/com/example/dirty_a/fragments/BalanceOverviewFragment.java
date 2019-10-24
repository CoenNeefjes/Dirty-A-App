package com.example.dirty_a.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.dirty_a.R;
import com.example.dirty_a.dataproviders.TransactionDataProvider;
import com.example.dirty_a.model.Transaction;

import java.math.BigDecimal;
import java.text.DateFormat;
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

        tableRow.addView(createTableRowTextView(this.dateFormat.format(transaction.getDate()), 1f, null));
        tableRow.addView(createTableRowTextView(transaction.getOwner(), 1f, null));
        tableRow.addView(createTableRowTextView(transaction.getDescription(), 2f, null));
        tableRow.addView(createTableRowTextView(formatPrice(transaction.getAmount()), 1f, transaction.getAmount().compareTo(BigDecimal.ZERO) > 0 ? Color.parseColor("#006400") : Color.parseColor("#ff0000")));

        if (hasEvenIndex) {
            tableRow.setBackgroundColor(Color.parseColor("#f1f1f1"));
        }

        return tableRow;
    }

    private TextView createTableRowTextView(String text, float weight, @Nullable Integer textColor) {
        TextView textView = new TextView(this.getContext());
        textView.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, weight));
        textView.setPadding(5,0,5,0);
        textView.setText(text);

        if (textColor != null) {
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(textColor);
        }

        return textView;
    }

    private String formatPrice(BigDecimal bigDecimal) {
        String result = bigDecimal.toString();
        if (result.contains(".")) {
            if (result.split("\\.")[1].length() == 1) {
                result += "0";
            }
        }
        return result;
    }

}
