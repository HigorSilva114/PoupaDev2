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
    public String insereDados(String descricao, String tipo, double valor){
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
    public Cursor carregaDadosId(int id){
        String[] campos = {financas.ID,financas.DESCRICAO, financas.VALOR, financas.TIPO};
        String sWhere = CriaBanco.ID+"="+id;
        db = financas.getReadableDatabase();
        Cursor cursor = db.query(financas.TABELA, campos, sWhere,null,null,null,null,null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public void alterarDados(int id, String descricao, String tipo, double valor){
        ContentValues valores = new ContentValues();
        String sWhere = CriaBanco.ID+"="+id;
        db = financas.getWritableDatabase();
        valores.put(CriaBanco.DESCRICAO, descricao);
        valores.put(CriaBanco.TIPO, tipo);
        valores.put(CriaBanco.VALOR, valor);
        db.update(CriaBanco.TABELA, valores, sWhere,null);
        db.close();

    }
    public void apagarDados(int id){
        String sWhere = CriaBanco.ID+"="+id;
        db = financas.getReadableDatabase();
        db.delete(CriaBanco.TABELA, sWhere, null);
        db.close();
    }
}
