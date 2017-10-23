package br.com.educacao.epymaps.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.educacao.epymaps.Model.UserAtivoSingleton;
import br.com.educacao.epymaps.Model.Usuario;

/**
 * Created by Jaelson on 25/04/2017.
 */

public class UsuarioDAO extends GenericDAO<Usuario> implements DAO<Usuario> {

    private SQLiteDatabase database;
    private Context context;
    private Cursor c;

    public UsuarioDAO(Context context) {
        super(context);
        database = getWritableDatabase();
        this.context = context;
    }

    @Override
    public boolean salvar(Usuario usuario) {
        database.execSQL("INSERT INTO usuario(nome,email,senha,sexo,cidade,estado,status) VALUES(?,?,?,?,?,?,?)",
                new Object[]{usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getSexo(), usuario.getCidade(), usuario.getEstado(), true});
        Toast.makeText(context, "Usuario Cadastrado", Toast.LENGTH_LONG).show();
        return true;
    }

    public ArrayList<String> getMunicipios(String estado) {
        c = database.rawQuery("SELECT nome FROM municipio WHERE uf='" + estado + "'", null);
        ArrayList<String> dados = new ArrayList<>();
        c.moveToFirst();
        do {
            dados.add(c.getString(c.getColumnIndex("nome")));
        } while (c.moveToNext());
        return dados;
    }

    public ArrayList<String> getEstados() {
        c = database.rawQuery("SELECT uf FROM estado", null);
        ArrayList<String> dados = new ArrayList<>();
        c.moveToFirst();
        do {
            dados.add(c.getString(c.getColumnIndex("uf")));
        } while (c.moveToNext());
        return dados;
    }

    public boolean verificarEmailBanco(String email) {
        c = database.rawQuery("SELECT email FROM usuario WHERE email='" + email + "'", null);
        c.moveToFirst();

        if (c.getCount() == 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean logar(String email, String senha) {
        c = database.rawQuery("SELECT email,senha,status FROM usuario WHERE email='" + email + "' AND senha='" + senha + "'", null);
        c.moveToFirst();

        if (c.getCount() == 1) {
            if (c.getInt(c.getColumnIndex("status")) == 1) {
                salvarSessao(email);
                return true;
            } else if (c.getInt(c.getColumnIndex("status")) == 0) {
                Toast.makeText(context, "Usuario Desativado", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return false;
    }

    private void salvarSessao(String email) {
        c = database.rawQuery("SELECT idCliente,nome,sexo,estado,cidade FROM usuario WHERE email='"+email+"'",null);
        c.moveToFirst();

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(c.getInt(c.getColumnIndex("idCliente")));
        usuario.setNome(c.getString(c.getColumnIndex("nome")));
        usuario.setSexo(c.getString(c.getColumnIndex("sexo")));
        usuario.setCidade(c.getString(c.getColumnIndex("cidade")));
        usuario.setEstado(c.getString(c.getColumnIndex("estado")));

        UserAtivoSingleton.getInstacia().setUsuario(usuario);

    }

    public void desativarUsuario(String email) {
        c = database.rawQuery("SELECT email FROM usuario WHERE email='" + email + "'", null);

        if (c.getCount() == 1) {
            database.execSQL("UPDATE usuario SET status='" + 0 + "' WHERE email=?", new Object[]{email});
            Toast.makeText(context, "O usuario foi desativado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Usuario Inexistente", Toast.LENGTH_LONG).show();
        }


    }

    public void ativarUsuario(String email) {
        c = database.rawQuery("SELECT email FROM usuario WHERE email='" + email + "'", null);

        if (c.getCount() == 1) {
            database.execSQL("UPDATE usuario SET status='" + 1 + "' WHERE email=?", new Object[]{email});
            Toast.makeText(context, "O usuario foi ativado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Usuario Inexistente", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean filtrar(Usuario usuario) {
        return false;
    }

    @Override
    public boolean atualiza(Usuario usuario) {
        return false;
    }
}
