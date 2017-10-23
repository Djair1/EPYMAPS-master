package br.com.educacao.epymaps.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.educacao.epymaps.Fragmentos.FragmentoMaps;
import br.com.educacao.epymaps.Fragmentos.FragmentoPerfil;
import br.com.educacao.epymaps.Fragmentos.FragmentoRelatorio;

/**
 * Created by Jaelson on 17/06/2017.
 */

public class TabAdpter extends FragmentStatePagerAdapter {
    int numeroPags;

    public TabAdpter(FragmentManager fm, int numeroPags){
        super(fm);
        this.numeroPags = numeroPags;
    }



    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FragmentoRelatorio.newInstance(position+1);
            case 1:
                return FragmentoMaps.newInstance(position+1);
            case 2:
                return FragmentoMaps.newInstance(position+1);
            case 3:
                return FragmentoPerfil.newInstance(position+1);
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numeroPags;
    }


}
