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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import br.com.educacao.epymaps.DAO.UsuarioDAO;
import br.com.educacao.epymaps.Fragmentos.FragmentoMaps;
import br.com.educacao.epymaps.R;

public class TelaLogin extends AppCompatActivity {

    private Button btnNovaConta;
    private Button btnLogar;
    private Button btnRecuperarSenha;
    private EditText edtEmail;
    private LoginButton loginButton;
    private CallbackManager CallbackManager;
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


        loginButton = (LoginButton) findViewById(R.id.login_button);

         loginButton.registerCallback(CallbackManager, new FacebookCallback<LoginResult>() {
                     @Override
                     public void onSuccess(LoginResult loginResult) {
                         // App code
                          IrTelaInicial();


                     }

                     @Override
                     public void onCancel() {
                         // App code
                     }

                     @Override
                     public void onError(FacebookException exception) {
                         // App code
                     }
         });



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

    private void IrTelaInicial() {

        Intent it = new Intent(this, HomeActivity.class);
       it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(it);




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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CallbackManager.onActivityResult(requestCode, resultCode, data);
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