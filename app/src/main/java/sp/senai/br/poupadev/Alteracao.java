package sp.senai.br.poupadev;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Alteracao extends AppCompatActivity {

    EditText etDescricaoA, etValorA;
    RadioGroup rgTipoA;
    RadioButton rbEntradaA, rbSaidaA;
    String sCodigo;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alteracao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etDescricaoA = findViewById(R.id.etDescricao);
        rgTipoA = findViewById(R.id.rgTipo);
        rbEntradaA = findViewById(R.id.rbEntrada);
        rbSaidaA = findViewById(R.id.rbSaida);
        etValorA = findViewById(R.id.etValor);
        BancoController crud = new BancoController(this);
        sCodigo = this.getIntent().getStringExtra("CODIGO");
        cursor = crud.carregaDadosId(Integer.parseInt(sCodigo));
        etDescricaoA.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.DESCRICAO)));
        etValorA.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.VALOR)));
        String tipo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TIPO));
        if ("Entrada".equalsIgnoreCase(tipo)) {
            rbEntradaA.setChecked(true);
            tipo = rbEntradaA.getText().toString();
        } else if ("Saida".equalsIgnoreCase(tipo)) {
            rbSaidaA.setChecked(true);
            tipo = rbSaidaA.getText().toString();
        }
    }

    public void Alterar(View a) {
        int selectedId = rgTipoA.getCheckedRadioButtonId();
        String tipo = "";
        if (selectedId != -1) {
            RadioButton rbSelecionado = findViewById(selectedId);
            tipo = rbSelecionado.getText().toString();
        }
        BancoController crud = new BancoController(this);
        crud.alterarDados(Integer.parseInt(sCodigo), etDescricaoA.getText().toString(),  tipo, Double.parseDouble(etValorA.getText().toString()));
        Intent it = new Intent(Alteracao.this, MainActivity.class);
        startActivity(it);
        finish();
    }

    public void excluir(View c) {
        BancoController crud = new BancoController(this);
        AlertDialog.Builder cancelAlert = new AlertDialog.Builder(this);
        cancelAlert.setTitle("Deseja Excluir Realmente?");
        cancelAlert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                crud.apagarDados(Integer.parseInt(sCodigo));
                Intent it = new Intent(Alteracao.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });
        cancelAlert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alerta = cancelAlert.create();
        alerta.show();
    }
}