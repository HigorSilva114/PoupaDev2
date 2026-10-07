package sp.senai.br.poupadev;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvValor;
    ListView lvDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvValor = findViewById(R.id.tvValor);
        lvDados = findViewById(R.id.lvDados);

        lvDados = findViewById(R.id.lvDados);
        BancoController crud = new BancoController(this);
        Cursor cursor = crud.carregaDados();


        String[] nomeCampos = {CriaBanco.ID,CriaBanco.VALOR,CriaBanco.TIPO};
        int[] idComponentes={R.id.tvId,R.id.tvValor1,R.id.tvTipo};

        SimpleCursorAdapter adpt = new SimpleCursorAdapter(this,R.layout.exibicao, cursor,nomeCampos,idComponentes,0);

        lvDados.setAdapter(adpt);

        lvDados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sCodigo;
                cursor.moveToPosition(i);
                sCodigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                Intent it = new Intent(MainActivity.this, Alteracao.class);
                it.putExtra("CODIGO", sCodigo);
                startActivity(it);
                finish();
            }


        });
    }
    public void adicionar(View c){
        Intent it = new Intent(MainActivity.this, Transacoes.class);
        startActivity(it);


    }
}