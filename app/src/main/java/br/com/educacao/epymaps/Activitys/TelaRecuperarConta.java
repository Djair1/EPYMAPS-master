package br.com.educacao.epymaps.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.educacao.epymaps.DAO.UsuarioDAO;
import br.com.educacao.epymaps.R;

public class TelaRecuperarConta extends AppCompatActivity {

    private EditText edtEmail;
    private Button btnEnviar;
    private Button btnVoltarLogin;
    private UsuarioDAO usuarioDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_recuperar_conta);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtEmail.getBackground().setAlpha(60);

        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnVoltarLogin = (Button) findViewById(R.id.btnVoltarLogin);
        usuarioDAO = new UsuarioDAO(this);

        btnEnviar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                usuarioDAO.ativarUsuario(edtEmail.getText().toString());
            }
        });

        btnVoltarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(TelaRecuperarConta.this, TelaLogin.class);
                startActivity(intent);
            }
        });
    }
}
