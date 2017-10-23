package br.com.educacao.epymaps.Model;

import android.provider.ContactsContract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jaelson on 10/06/2017.
 */

public class FichaDiaria {
    private int idFicha;
    private String statusUsuario;
    private String descricao;
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public String getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(String statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return getDataFormatada(data)+" "+getStatusUsuario()+" "+getDescricao();
    }

    private String getDataFormatada(Date data) {
        SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFromatada = spf.format(data);
        return dataFromatada;
    }
}
