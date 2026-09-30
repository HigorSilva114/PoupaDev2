package sp.senai.br.poupadev;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco financas;
    public BancoController(Context ctx){
        financas = new CriaBanco(ctx);
    }
}
