package br.com.educacao.epymaps.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.com.educacao.epymaps.R;

/**
 * Created by Jaelson on 19/06/2017.
 */

public class FichaViewHolder extends RecyclerView.ViewHolder {

    protected EditText tvData;
    protected EditText tvStatus;
    protected EditText tvDescricao;

    public FichaViewHolder(View itemView) {
        super(itemView);
        tvData = (EditText) itemView.findViewById(R.id.edtData);
        tvStatus = (EditText) itemView.findViewById(R.id.edtStatus);
        tvDescricao = (EditText) itemView.findViewById(R.id.edtDescricao);


    }
}
