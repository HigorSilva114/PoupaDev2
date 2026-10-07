package sp.senai.br.poupadev;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper{
    static String NOME_BANCO = "financas.db";
    static String TABELA = "Transacoes";
    static String ID = "_id";
    static String DESCRICAO = "descricao";
    static String VALOR = "valor";
    static String TIPO = "tipo";
    static int VERSAO = 1;
    public CriaBanco(Context ctx){
        super(ctx, NOME_BANCO,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE "+TABELA+"("+ID+" integer primary key autoincrement,"+DESCRICAO+" text,"+VALOR+" double,"+TIPO+" text)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
