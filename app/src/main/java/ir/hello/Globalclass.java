package ir.hello;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by spring on 2019-04-15.
 */

public class Globalclass {
    void DisPlayToast(Context Ctx,String Msg){
        Toast.makeText(Ctx,Msg,Toast.LENGTH_SHORT).show();
    }
}

