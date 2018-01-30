package com.dmb.davidmariexamen.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmb.davidmariexamen.Adapters.CyclesAdapter;
import com.dmb.davidmariexamen.Models.Cycle;
import com.dmb.davidmariexamen.R;

import java.util.ArrayList;

public class ListCyclesFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerCycles;
    CyclesAdapter cyclesAdapter;

    public ListCyclesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ListCyclesFragment newInstance(String param1, String param2) {
        ListCyclesFragment fragment = new ListCyclesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_cycles, container, false);

        recyclerCycles = v.findViewById(R.id.recyclerCycles);

        cyclesAdapter = new CyclesAdapter(mListener.getCycles(), new CyclesAdapter.RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(final View v, final int position) {
                Snackbar.make(v,mListener.getCycles().get(position).getCourse()+" "+
                        mListener.getCycles().get(position).getTitle()+" "+
                        mListener.getCycles().get(position).getPromotionYear(),Snackbar.LENGTH_LONG).show();
                AlertDialog.Builder alertBox = new AlertDialog.Builder(v.getRootView().getContext());
                alertBox.setMessage("¿Estás seguro de que quieres eliminar este Ciclo?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Snackbar.make(v,"Ciclo: "+mListener.getCycles().get(position).getCourse()+" "+
                                        mListener.getCycles().get(position).getTitle()+" "+
                                        mListener.getCycles().get(position).getPromotionYear()+" eliminado",Snackbar.LENGTH_LONG).show();
                                mListener.getCycles().remove(position);
                                recyclerCycles.getAdapter().notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                alertBox.show();
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerCycles.setLayoutManager(llm);
        recyclerCycles.setAdapter(cyclesAdapter);

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
        ArrayList<Cycle> getCycles();
    }
}
