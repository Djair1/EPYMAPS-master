package br.com.educacao.epymaps.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.educacao.epymaps.Model.FichaDiaria;
import br.com.educacao.epymaps.R;

/**
 * Created by Jaelson on 19/06/2017.
 */

public class FichaAdapter extends RecyclerView.Adapter<FichaViewHolder> {

    private List<FichaDiaria> dadosFichas;

    public FichaAdapter(List<FichaDiaria> dadosFichas) {
        this.dadosFichas = dadosFichas;
    }

    @Override
    public FichaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.toolbar_listafichasrespondidas,parent,false);
        return new FichaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FichaViewHolder fichaHolder, int position) {
        FichaDiaria fichaDiaria = dadosFichas.get(position);
        fichaHolder.tvData.setText(fichaDiaria.getData().toString());
        fichaHolder.tvStatus.setText(fichaDiaria.getStatusUsuario());
        fichaHolder.tvDescricao.setText(fichaDiaria.getDescricao());
    }

    @Override
    public int getItemCount() {
        return dadosFichas.size();
    }
}
