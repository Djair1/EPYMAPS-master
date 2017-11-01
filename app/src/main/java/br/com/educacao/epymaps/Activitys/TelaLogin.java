package br.com.educacao.epymaps.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import br.com.educacao.epymaps.DAO.UsuarioDAO;
import br.com.educacao.epymaps.R;

public class TelaLogin extends AppCompatActivity {

    private Button btnNovaConta;
    private Button btnLogar;
    private Button btnRecuperarSenha;
    private EditText edtEmail;
    private EditText edtSenha;
    private TextView tvStatusLogin;
    UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inserido para comunicar com o facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.tela_login);

        btnNovaConta = (Button) findViewById(R.id.btnNovaConta);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        tvStatusLogin = (TextView) findViewById(R.id.tvStatusLogin);
        btnRecuperarSenha = (Button) findViewById(R.id.btnRecupSenha);
        edtEmail.getBackground().setAlpha(60);
        edtSenha.getBackground().setAlpha(60);


        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);














        usuarioDAO = new UsuarioDAO(TelaLogin.this);

        btnNovaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(intent);
            }
        });

        btnRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(TelaLogin.this, TelaRecuperarConta.class);
                startActivity(intent);
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    if (usuarioDAO.logar(edtEmail.getText().toString(), edtSenha.getText().toString())) {
                        finish();
                        Intent intent = new Intent(TelaLogin.this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        tvStatusLogin.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


    }

    private boolean validarCampos() {
        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            edtEmail.setError("Preecha o email");
            return false;
        }

        if (TextUtils.isEmpty(edtSenha.getText().toString())) {
            edtSenha.setError("Preecha a senha");
            return false;
        }

        return true;
    }




    }




/*
 private Button btnDesativar;
    private Button btnAtivar;
 btnDesativar = (Button) findViewById(R.id.btnDesativar);
        btnAtivar = (Button) findViewById(R.id.btnAtivar);


        btnDesativar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioDAO.desativarUsuario(edtEmail.getText().toString());
            }
        });

 */