package br.com.educacao.epymaps.Fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.educacao.epymaps.R;


/**
 * Created by Jaelson on 17/06/2017.
 */

public class FragmentoBusca extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static FragmentoBusca newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,page);
        FragmentoBusca fragmento = new FragmentoBusca();
        fragmento.setArguments(args);
        return fragmento;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_busca,container,false);

        return view;
    }
}
