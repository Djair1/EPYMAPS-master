package br.com.educacao.epymaps.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import br.com.educacao.epymaps.Model.FichaDiaria;
import br.com.educacao.epymaps.Model.UserAtivoSingleton;
import br.com.educacao.epymaps.Model.Usuario;

/**
 * Created by Jaelson on 10/06/2017.
 */

public class FichaDAO extends GenericDAO<FichaDiaria> implements DAO<FichaDiaria> {

    private SQLiteDatabase database;
    private Context context;
    private Cursor c;


    public FichaDAO(Context context) {
        super(context);
        database = getWritableDatabase();
        this.context = context;
    }

    @Override
    public boolean salvar(FichaDiaria fichaDiaria) {
        if(fichaDiaria.getDescricao().isEmpty()) {
            database.execSQL("INSERT INTO fichaDiaria(statusUsuario,data,idCliente) VALUES(?,?,?)",
                    new Object[]{fichaDiaria.getStatusUsuario(), fichaDiaria.getData(), UserAtivoSingleton.getUsuario().getIdUsuario()});
            Toast.makeText(context, "Ficha Respondida", Toast.LENGTH_LONG).show();
        }else{
            database.execSQL("INSERT INTO fichaDiaria(statusUsuario,descricao,data,idCliente) VALUES(?,?,?,?)",
                    new Object[]{fichaDiaria.getStatusUsuario(), fichaDiaria.getDescricao(), fichaDiaria.getData(), UserAtivoSingleton.getUsuario().getIdUsuario()});
            Toast.makeText(context, "Ficha Respondida", Toast.LENGTH_LONG).show();

        }
        return true;
    }

    public List<FichaDiaria> listarFichas() {
      //  database.execSQL("delete from fichaDiaria");
        c = database.rawQuery("SELECT statusUsuario,descricao,data FROM fichaDiaria WHERE idCliente='"+UserAtivoSingleton.getUsuario().getIdUsuario()+"'",null);
        c.moveToFirst();
        if(c.getCount()>=1){
            List<FichaDiaria> dados = new ArrayList();

            do {
                FichaDiaria fichaDiaria = new FichaDiaria();
                fichaDiaria.setData(getDataAtual(c.getString(c.getColumnIndex("data"))));
                fichaDiaria.setDescricao(c.getString(c.getColumnIndex("descricao")));
                fichaDiaria.setStatusUsuario(c.getString(c.getColumnIndex("statusUsuario")));
                dados.add(fichaDiaria);
            }while(c.moveToNext());
            return dados;
        }
        return null;
    }

    private Date getDataAtual(String data) {
        Date date = new Date();
        SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
        data = spf.format(date);

        try {
            date = spf.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public boolean filtrar(FichaDiaria fichaDiaria) {
        return false;
    }

    @Override
    public boolean atualiza(FichaDiaria fichaDiaria) {
        return false;
    }
}
