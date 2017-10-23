package br.com.educacao.epymaps.Fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import br.com.educacao.epymaps.R;


/**
 * Created by Jaelson on 17/06/2017.
 */

public class FragmentoPerfil extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static FragmentoPerfil newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentoPerfil fragmento = new FragmentoPerfil();
        fragmento.setArguments(args);
        return fragmento;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        Button btnMinhasFichas = (Button) view.findViewById(R.id.btnMinhasFichas);

        return view;
    }

    public void mudarFragmento(View view){
            FragmentoPerfil fragInicio = new FragmentoPerfil();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.card_view, fragInicio, "fragInicio");
            ft.commit();
   }
}
