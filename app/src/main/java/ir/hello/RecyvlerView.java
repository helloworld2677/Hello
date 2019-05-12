package ir.hello;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;

class RecyclerView extends AppCompatActivity {
    Context Ctx;
    ClassDatabase _ClassDatabase;
    Globalclass _Globalclass=new Globalclass();
    android.support.v7.widget.RecyclerView Recycler;
    View.OnClickListener RowClick=new View.OnClickListener() {
        @Override
        public void onClick(View _v) {
            ContentValues _ContentValues=(ContentValues)_v.getTag();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        Ctx=this;
        _ClassDatabase=new ClassDatabase();
        _ClassDatabase.CreateTable(Ctx);
        Recycler= findViewById(R.id.Recycler);
        PrintData(null,null);
    }void PrintData(String search,String Order){
        try {
            ArrayList<ContentValues> _ArrayList=_ClassDatabase.SelectData(Ctx,search,0,Order);
            _Globalclass.DisPlayToast(this,Integer.toString(_ArrayList.size()));
            Recycler.setLayoutManager(new LinearLayoutManager(Ctx));
            //Recycler.setAdapter();

        }catch (Exception Error){
            _Globalclass.DisPlayToast(RecyclerView.this,Error.getMessage());
        }
    }
}