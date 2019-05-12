package ir.hello;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by spring on 11/20/2018.
 */

public class ClassDatabase {
    String DatabaseName="Product";
    SQLiteDatabase sql;
    Globalclass _Globalclass=new Globalclass();
    void CreateTable(Context Ctx){
        try {
            sql =Ctx.openOrCreateDatabase(DatabaseName,Context.MODE_PRIVATE,null);
            sql.execSQL("CREATE TABLE IF NOT EXISTS TableProduct(ID_Auto INTEGER PRIMARY KEY AUTOINCREMENT,PriceProduct INTEGER,NameProduct TEXT);");
            //  sql.execSQL("CREATE TABLE IF NOT EXISTS TableUser(ID_Auto INTEGER AUTOINCREMENT,NumberUser INTEGER,NameUser TEXT);");
//TEXT hamon string hastesh k inja TEXT minamim,INTEGER ham baraye adad ya shomarehamon hastesh (int)
            _Globalclass.DisPlayToast(Ctx,"Create Database");
            sql.close();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Ctx,Error.getMessage());
        }
    }
    //--------------------
    void Insert(Context Ctx,int PriceProduct,String NameProduct){
        //Insert baraye ine k chizi bekhahim toye table berizim ya ja konim
        try {
            sql =Ctx.openOrCreateDatabase(DatabaseName,Context.MODE_PRIVATE,null);
            sql.execSQL("INSERT INTO TableProduct(PriceProduct,NameProduct)VALUES("+PriceProduct+",'"+NameProduct+"')");
            //single coteyshen baraye string ha bayad b kar bere
            //  sql.execSQL("CREATE TABLE IF NOT EXISTS TableUser(ID_Auto INTEGER AUTOINCREMENT,NumberUser INTEGER,NameUser TEXT);");
//TEXT hamon string hastesh k inja TEXT minamim,INTEGER ham baraye adad ya shomarehamon hastesh (int)
            //Static ha baraye in hastan k dg az shey garaii estefade nakonim
            _Globalclass.DisPlayToast(Ctx,"Inserted");
            sql.close();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Ctx,Error.getMessage());
        }
    }void Delete(Context Ctx,String ID_Product){
        try {
            sql =Ctx.openOrCreateDatabase(DatabaseName,Context.MODE_PRIVATE,null);
            sql.execSQL("DELETE FROM TableProduct WHERE ID_Auto="+ID_Product);
            _Globalclass.DisPlayToast(Ctx,"Delete");
            sql.close();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Ctx,Error.getMessage());
        }
    }void Update(Context Ctx, ContentValues Data){
        try {
            sql =Ctx.openOrCreateDatabase(DatabaseName,Context.MODE_PRIVATE,null);
            sql.execSQL("UPDATE TableProduct SET PriceProduct="+Data.getAsString("PriceProduct")+",NameProduct='"+Data.getAsString("NameProduct")+"'WHERE ID_Auto="+Data.getAsString("ID_Auto")+"");
//Contentvalues dar vaqe vase ine k dg hey narim parametr vorody bedy (dakhey parantez haye bad update,delete,imsert,search)
            _Globalclass.DisPlayToast(Ctx,"Update");
            sql.close();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Ctx,Error.getMessage());
        }
    }ArrayList<ContentValues> SelectData(Context Ctx,String Search,int _Offset,String _Order){
        //void qabeliat ino nadare k dade haro bargrdone
        ArrayList<ContentValues> _ArrayList=new ArrayList<>();
        String _Limit=" LIMIT "+_Offset+"4";
        try {
            if(Search==null)
                Search="";
            if(_Order==null)
                _Order="";
            //String _Order=" ORDER BY ID_Auto DESC ";
            //String _Order=" ORDER BY NameProduct ASC ";
            sql =Ctx.openOrCreateDatabase(DatabaseName,Context.MODE_PRIVATE,null);

            Cursor _Cursor=sql.rawQuery("SELECT ID_Auto,PriceProductوNameProduct FROM TableProduct "+Search+_Order,null);
            //Cursor _Cursor=sql.rawQuery("SELECT COUNT(*) FROM TableProduct "+Search,null);
            //Khat bala vase ine k kole kalahamono beshmore va b ma bege k chanta kala darim
            // Cursor _Cursor=sql.rawQuery("SELECT * FROM TableProduct "+Search+_Order+_Limit,null);
           /* rawquery vlan k 2 parametre dovoman execSQL miad fqt anjam mide
             vali in miad injam mide va pasokhiam b ma mide (qarar bod select k mikonim bbinim chia to hafeze darim k
             in miad baramon pasokh haro miare)*/
            //-----------------------------------
            if (_Cursor.moveToFirst()){
                do {
                    ContentValues _ContentValues=new ContentValues();
                    _ContentValues.put("ID_Auto",_Cursor.getString(0));
                    _ContentValues.put("Name",_Cursor.getString(1));
                    _ContentValues.put("Price",_Cursor.getString(2));
                    _ArrayList.add(_ContentValues);
                    //Contentvalues ezafe mikone b arayamon k _ArrayList hast(hamishe az rast b chap bkhon)
                    //Cursor ye jadval majazi misaze k

                }
                while (_Cursor.moveToNext());
            }
            //------------------------------------
            _Globalclass.DisPlayToast(Ctx,"Select");
            sql.close();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Ctx,Error.getMessage());
        }
        return _ArrayList;
        //bade return bayad hamon chizi bashe k b jaye void gozashtim
    }
    int Sum_Count(Context Ctx,String Search,String SumOrCount){
        int Result=0;
        try {
            if(Search==null)
                Search="";
            sql =Ctx.openOrCreateDatabase(DatabaseName,Context.MODE_PRIVATE,null);
            // Cursor _Cursor=sql.rawQuery("SELECT * FROM TableProduct "+Search+_Order+_Limit,null);
            Cursor _Cursor=sql.rawQuery("SELECT "+SumOrCount+" FROM TableProduct "+Search,null);
            //Cursor _Cursor=sql.rawQuery("SELECT SUM(PriceProduct) FROM TableProduct "+Search,null);
            if (_Cursor.moveToFirst())

                Result=_Cursor.getInt(0);





            //---------------
            sql.close();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Ctx,Error.getMessage());
        }
        return Result;
    }
}
