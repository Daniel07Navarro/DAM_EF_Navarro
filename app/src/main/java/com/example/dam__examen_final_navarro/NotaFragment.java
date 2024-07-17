package com.example.dam__examen_final_navarro;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;

    private List<Nota> notaList;
    private MyNotaRecyclerViewAdapter adapterNotas;

    private NotasInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                int numeroColumnas = (int) (dpWidth / 180);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager( numeroColumnas, StaggeredGridLayoutManager.VERTICAL));
            }
            notaList = new ArrayList<>();
            notaList.add(new Nota("Recordatorio UC4", "Preparar bien para la evaluación de la UC4 - Caso: Notas y Listas", true, android.R.color.holo_blue_light));
            notaList.add(new Nota("Recordar el coche", "Estacioné el coche en la calle República Argentina, no olvidarme de donde está aparcado", false, android.R.color.holo_green_light));
            notaList.add(new Nota("Preparativos cumpleaños", "No olvidar comprar las velas para el cumpleaños (fiesta)", true, android.R.color.holo_orange_light));
            notaList.add(new Nota("Deporte después de DAM", "Organizar un juego de futsal al terminar la clase en la semana 16", true, android.R.color.holo_blue_light));
            notaList.add(new Nota("Trabajo Académico", "Cada estudiante debe crear un video sobre el caso Notas y Listas, desde el inicio hasta el final", false, android.R.color.holo_green_light));
            notaList.add(new Nota("Preparativos cumpleaños Rakauskas", "Para el cumpleaños del delegado, traer un bocadito; la profesora traerá la torta para la fiesta con temática: GitHub for Ever", true, android.R.color.holo_orange_light));
            notaList.add(new Nota("Lista de compras", "Incluir en la lista comprar pan tostado y fruta", true, android.R.color.holo_blue_light));
            adapterNotas = new MyNotaRecyclerViewAdapter(notaList,mListener);
            recyclerView.setAdapter(adapterNotas);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof NotasInteractionListener){
            mListener = (NotasInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    + "Debe implementarse NotasInteractionListener");
        }
    }

}