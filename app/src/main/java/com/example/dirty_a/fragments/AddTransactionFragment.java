package com.example.dirty_a.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dirty_a.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTransactionFragment extends Fragment {

    // UI elements
    private EditText descriptionEditText;
    private EditText priceEditText;
    private Button addButton;
    private List<TextView> persons = new ArrayList<>();
    private List<TextView> amounts = new ArrayList<>();
    private List<TextView> leftArrows = new ArrayList<>();
    private List<TextView> rightArrows = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Add Transaction");
        return inflater.inflate(R.layout.fragment_add_transaction, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set UI elements
        descriptionEditText = getActivity().findViewById(R.id.add_transaction_description_edit_text);
        priceEditText = getActivity().findViewById(R.id.add_transaction_price_edit_text);
        addButton = getActivity().findViewById(R.id.add_transaction_add_btn);
        TextView person1 = getActivity().findViewById(R.id.add_transaction_person1);
        TextView person2 = getActivity().findViewById(R.id.add_transaction_person2);
        TextView person3 = getActivity().findViewById(R.id.add_transaction_person3);
        TextView person4 = getActivity().findViewById(R.id.add_transaction_person4);
        TextView person5 = getActivity().findViewById(R.id.add_transaction_person5);
        TextView person6 = getActivity().findViewById(R.id.add_transaction_person6);
        TextView person7 = getActivity().findViewById(R.id.add_transaction_person7);
        TextView person8 = getActivity().findViewById(R.id.add_transaction_person8);
        TextView person1Amount = getActivity().findViewById(R.id.add_transaction_person1_amount);
        TextView person2Amount = getActivity().findViewById(R.id.add_transaction_person2_amount);
        TextView person3Amount = getActivity().findViewById(R.id.add_transaction_person3_amount);
        TextView person4Amount = getActivity().findViewById(R.id.add_transaction_person4_amount);
        TextView person5Amount = getActivity().findViewById(R.id.add_transaction_person5_amount);
        TextView person6Amount = getActivity().findViewById(R.id.add_transaction_person6_amount);
        TextView person7Amount = getActivity().findViewById(R.id.add_transaction_person7_amount);
        TextView person8Amount = getActivity().findViewById(R.id.add_transaction_person8_amount);
        TextView person1Left = getActivity().findViewById(R.id.add_transaction_person1_left);
        TextView person2Left = getActivity().findViewById(R.id.add_transaction_person2_left);
        TextView person3Left = getActivity().findViewById(R.id.add_transaction_person3_left);
        TextView person4Left = getActivity().findViewById(R.id.add_transaction_person4_left);
        TextView person5Left = getActivity().findViewById(R.id.add_transaction_person5_left);
        TextView person6Left = getActivity().findViewById(R.id.add_transaction_person6_left);
        TextView person7Left = getActivity().findViewById(R.id.add_transaction_person7_left);
        TextView person8Left = getActivity().findViewById(R.id.add_transaction_person8_left);
        TextView person1Right = getActivity().findViewById(R.id.add_transaction_person1_right);
        TextView person2Right = getActivity().findViewById(R.id.add_transaction_person2_right);
        TextView person3Right = getActivity().findViewById(R.id.add_transaction_person3_right);
        TextView person4Right = getActivity().findViewById(R.id.add_transaction_person4_right);
        TextView person5Right = getActivity().findViewById(R.id.add_transaction_person5_right);
        TextView person6Right = getActivity().findViewById(R.id.add_transaction_person6_right);
        TextView person7Right = getActivity().findViewById(R.id.add_transaction_person7_right);
        TextView person8Right = getActivity().findViewById(R.id.add_transaction_person8_right);

        persons.addAll(Arrays.asList(person1, person2, person3, person4, person5, person6, person7, person8));
        amounts.addAll(Arrays.asList(person1Amount, person2Amount, person3Amount, person4Amount, person5Amount, person6Amount, person7Amount, person8Amount));
        leftArrows.addAll(Arrays.asList(person1Left, person2Left, person3Left, person4Left, person5Left, person6Left, person7Left, person8Left));
        rightArrows.addAll(Arrays.asList(person1Right, person2Right, person3Right, person4Right, person5Right, person6Right, person7Right, person8Right));

        setOnClickListeners(leftArrows, false);
        setOnClickListeners(rightArrows, true);
    }

    private void setOnClickListeners(List<TextView> views, boolean increaseOnClick) {
        for (int i=0; i<views.size(); i++) {
            int index = i;
            views.get(i).setOnClickListener(v -> updateAmount(index, increaseOnClick));
        }
    }

    private void updateAmount(int index, boolean increase) {
        TextView textView = amounts.get(index);
        int amount = Integer.parseInt(textView.getText().toString()) + (increase ? 1 : -1);
        textView.setText(String.valueOf(amount));
    }

}
