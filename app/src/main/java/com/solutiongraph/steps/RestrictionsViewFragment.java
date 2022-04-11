package com.solutiongraph.steps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.model.simplexdata.Restriction;
import com.solutiongraph.R;
import com.solutiongraph.restrictions.RestrictAdapter;
import com.viewmodel.SharedViewModel;

public class RestrictionsViewFragment extends Fragment {
    public static final String RESTRICTIONS_NUMBER = "restrictions_number";
    public static final String VARIABLES_NUMBER = "variables_number";

    private int restNumber;
    private int varblNumber;
    private SharedViewModel viewModel;

    public RestrictionsViewFragment() {
        // Required empty public constructor
    }

    public static RestrictionsViewFragment newInstance(int restNumber, int varblNumber) {
        RestrictionsViewFragment fragment = new RestrictionsViewFragment();
        Bundle args = new Bundle();
        args.putInt(RESTRICTIONS_NUMBER, restNumber);
        args.putInt(VARIABLES_NUMBER, varblNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() == null) return;
        viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        if (getArguments() != null) {
            restNumber = getArguments().getInt(RESTRICTIONS_NUMBER);
            varblNumber = getArguments().getInt(VARIABLES_NUMBER);
            viewModel.createRestrictionData(restNumber, varblNumber);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_restrictions_view, container, false);
        Restriction[] restrictions = viewModel.restricts.getValue();
        RecyclerView restrictionRecyclerView = root.findViewById(R.id.restriction_recycler_view);
        restrictionRecyclerView.setAdapter(
                new RestrictAdapter(this.getContext(), restrictions));
        restrictionRecyclerView.setLayoutManager(
                new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}