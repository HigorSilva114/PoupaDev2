package sp.senai.br.poupadev;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Transacoes extends AppCompatActivity {
    EditText etDescricao,etValor,etTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transacoes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etDescricao = findViewById(R.id.etDescricao);
        etTipo = findViewById(R.id.etTipo);
        etValor = findViewById(R.id.etValor);

    }
    public void cadastrar(View ca){
        BancoController crud = new BancoController(this);
        String descricao = etDescricao.getText().toString();
        String tipo = etTipo.getText().toString();
        String valor = etValor.getText().toString();
        String resultado;
        if (valor.isEmpty()){
            etValor.setError("Campo VALOR precisa ser preenchido");
            etValor.requestFocus();
            return;
        } else if (descricao.isEmpty()) {
            etDescricao.setError("Campo DESCRIÇÃO precisa ser preenchido");
            etDescricao.requestFocus();
            return;
        }else if (tipo.isEmpty()) {
            etTipo.setError("Campo TIPO precisa ser preenchido");
            etTipo.requestFocus();
            return;
        }
        float valor1 = Float.parseFloat(valor);
        resultado = crud.insereDados(descricao,tipo,valor1);
        Toast.makeText(this,resultado,Toast.LENGTH_LONG).show();
        Intent it = new Intent(Transacoes.this, MainActivity.class);
        startActivity(it);
    }
    public void cancelar(View c){
        AlertDialog.Builder cancelAlert = new AlertDialog.Builder(this);
        cancelAlert.setTitle("Cancelar Operação?");
        cancelAlert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent it = new Intent(Transacoes.this, MainActivity.class);
                startActivity(it);
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