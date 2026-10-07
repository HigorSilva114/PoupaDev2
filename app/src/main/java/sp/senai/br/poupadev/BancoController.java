package sp.senai.br.poupadev;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco financas;
    public BancoController(Context ctx){
        financas = new CriaBanco(ctx);
    }
    public String insereDados(String descricao, String tipo, Float valor){
        db = financas.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(financas.DESCRICAO, descricao);
        valores.put(financas.VALOR, valor);
        valores.put(financas.TIPO, tipo);
        long resultado = db.insert(financas.TABELA,null,valores);
        db.close();
        if (resultado == -1){
            return "Erro ao inserir dados";
        }else {
            return "Dados inserido com sucesso!";
        }
    }
    public Cursor carregaDados(){
        String[] campos = {financas.ID,financas.VALOR,financas.TIPO};
        db = financas.getReadableDatabase();
        Cursor cursor = db.query(financas.TABELA, campos, null,null,null,null,null,null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
