package br.com.educacao.epymaps.Fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.educacao.epymaps.Adapter.FichaAdapter;
import br.com.educacao.epymaps.DAO.FichaDAO;
import br.com.educacao.epymaps.R;


/**
 * Created by Jaelson on 17/06/2017.
 */

public class FragmentoFichas extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static FragmentoFichas newInstance() {
        Bundle args = new Bundle();
        FragmentoFichas fragmento = new FragmentoFichas();
        fragmento.setArguments(args);
        return fragmento;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.toolbar_listafichasrespondidas, container, false);
        FichaDAO fichaDAO = new FichaDAO(view.getContext());
        new FichaAdapter(fichaDAO.listarFichas());
        return view;
    }
}
