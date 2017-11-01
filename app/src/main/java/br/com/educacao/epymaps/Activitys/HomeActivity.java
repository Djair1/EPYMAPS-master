package br.com.educacao.epymaps.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import java.util.List;

import br.com.educacao.epymaps.Adapter.TabAdpter;
import br.com.educacao.epymaps.DAO.FichaDAO;
import br.com.educacao.epymaps.Model.FichaDiaria;
import br.com.educacao.epymaps.R;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ImageButton ibStatus;
    private String status = "Doente";
    private EditText edtDescricao;
    private Button btnEnviar;
    private ListView lvFichasRespData;
    private ListView lvFichasRespStatus;
    private ListView lvFichasRespDescricao;
    private FichaDAO fichaDAO;
    //teste

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tbLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_reports));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_maps));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_search));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_user));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final TabAdpter tbAdapter = new TabAdpter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tbAdapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void atualizarSpinner() {

        List<FichaDiaria> arrayFicha = fichaDAO.listarFichas();
        if (arrayFicha != null) {

            ArrayAdapter<FichaDiaria> adapterFicha = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayFicha);
            lvFichasRespData.setAdapter(adapterFicha);
        }

        if (AccessToken.getCurrentAccessToken() == null) {
            IrParaLogin();
        }


    }

    private void IrParaLogin() {

        Intent it = new Intent(this, TelaLogin.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(it);


    }

public void logout(View view){


    LoginManager.getInstance().logOut();
    IrParaLogin();
}
/*


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itmPerfil:
                linearLayout.setVisibility(LinearLayout.VISIBLE);
                break;
            case R.id.itmSair:
                Intent intent = new Intent(this,TelaLogin.class);
                startActivity(intent);
                break;

            case android.R.id.home:
                finish();
        }
        return true;
    }
*/

}
/*

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        fichaDAO = new FichaDAO(HomeActivity.this);
        linearLayout = (LinearLayout) findViewById(R.id.llPerfil);
        ibStatus = (ImageButton) findViewById(R.id.ibStatus);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        lvFichasRespData = (ListView) findViewById(R.id.lvFichasRespData);
        lvFichasRespStatus = (ListView) findViewById(R.id.lvFichasRespStatus);
        lvFichasRespDescricao = (ListView) findViewById(R.id.lvFichasRespDescricao);

        atualizarSpinner();


        ibStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(status == "Doente") {
                    ibStatus.setImageResource(R.mipmap.sadio);
                    status = "Sadio";
                }else{
                    ibStatus.setImageResource(R.mipmap.doente);
                    status = "Doente";
                }

            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FichaDiaria fichaDiaria = new FichaDiaria();
                fichaDiaria.setStatusUsuario(status);
                fichaDiaria.setDescricao(edtDescricao.getText().toString());
                fichaDiaria.setData(new Date());

                fichaDAO.salvar(fichaDiaria);
                atualizarSpinner();

            }
        });
 */
/*
 <include layout="@layout/toolbar_fichadiaria"></include>
    <include layout="@layout/toolbar_listafichasrespondidas"></include>


    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:visibility="invisible"
        android:id="@+id/llPerfil">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:layout_marginTop="10pt">
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Nome:"/>

            <EditText
                android:id="@+id/edtNome"
                android:layout_width="50pt"
                android:layout_height="wrap_content" />
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Sobrenome:"/>
            <EditText
                android:layout_width="50pt"
                android:layout_height="wrap_content"
                android:id="@+id/edtSobrenome"
                />
        </LinearLayout>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Estado:"/>
        <Spinner
            android:layout_width="100pt"
            android:layout_height="wrap_content"
            android:id="@+id/spEstados"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Cidade:"/>
        <Spinner
            android:layout_width="100pt"
            android:layout_height="wrap_content"
            android:id="@+id/spCidades"/>

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Sexo:"/>
        <RadioGroup
            android:id="@+id/radioGenero"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            >

            <RadioButton
                android:id="@+id/rbMasculino"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Masculino"
                />

            <RadioButton
                android:id="@+id/rbFeminino"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Feminino" />

        </RadioGroup>

    </LinearLayout>
 */