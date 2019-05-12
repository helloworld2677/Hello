package ir.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button Btn_Login, Btn_Sign;
    ImageView Img_Back;
    Globalclass _Globalclass = new Globalclass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (!isFirstTimeStartApp()){
            StartMainActivity();
            finish();
        }*/

        setContentView(R.layout.activity_main);
        CheckUser();
        Init();
        DisPlayToast("Welcome to your app");
    }

    void Init() {
        Btn_Login = (Button) findViewById(R.id.Btn_Login);
        Btn_Sign = (Button) findViewById(R.id.Btn_Sign);
        Img_Back = (ImageView) findViewById(R.id.Img_Back);
    }

    public void BtnSign(View _v) {
        startActivity(new Intent(this, Act_Sign.class));
        finish();
    }

    public void BtnLogin(View _v) {
        Intent _Intent = new Intent(this, Act_Login.class);
        startActivity(_Intent);
        finish();
    }

    public void Backclick(View _v) {
        if (_v.getTag().equals("Back")) {
            finish();
        }
    }

    void CheckUser() {
        try {
            SharedPreferences _SharedPreferences = getSharedPreferences("SignUser", Context.MODE_PRIVATE);
            String _Email = _SharedPreferences.getString("EmailUser", null);
            String _Pass = _SharedPreferences.getString("PassUser", null);
            if (_Email == null && _Pass == null)
                Btn_Login.setEnabled(false);
            else
                Btn_Sign.setText("Edit");
        } catch (Exception Error) {
            _Globalclass.DisPlayToast(MainActivity.this, Error.getMessage());
        }

    }

    void DisPlayToast(String Msg) {
        Toast.makeText(this, Msg, Toast.LENGTH_SHORT).show();
    }

   /* private void setFirstTimeStartStatus(boolean stt) {
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSlider", Context.MODE_PRIVATE);
        SharedPreferences.Editor _Editor = ref.edit();
        _Editor.putBoolean("FirsTimeStartFlag", stt);
        _Editor.commit();
    }*/

 /*   private boolean isFirstTimeStartApp() {
        SharedPreferences _SharedPreferences = getApplicationContext().getSharedPreferences("MainUser", Context.MODE_PRIVATE);
        return _SharedPreferences.getBoolean("FirsTimeStartFlag", true);
    }*/
    /*private void StartLoginActivity(){
        setFirstTimeStartStatus(false);
        Intent _Intent=new Intent(this,Act_Login.class);
        startActivity(_Intent);
        finish();
    }*/


    /*    private boolean isFirstTimeStartApp(){
            SharedPreferences _SharedPreferences=getApplicationContext().getSharedPreferences("SignUser", Context.MODE_PRIVATE);
            return _SharedPreferences.getBoolean("FirsTimeStartFlag",true);
        }private void setFirstTimeStartStatus(boolean stt){
            SharedPreferences _SharedPreferences=getApplicationContext().getSharedPreferences("SignUser", Context.MODE_PRIVATE);
            SharedPreferences.Editor _Editor=_SharedPreferences.edit();
            _Editor.putBoolean("SignUser",stt);
            _Editor.commit();
            String _EmailUser = _SharedPreferences.getString("EmailUser", null);
            String _PassUser = _SharedPreferences.getString("PassUser", null);
            if (_EmailUser == null) {
                Btn_Login.setEnabled(false);
            } else if (_PassUser == null) {
                Btn_Login.setEnabled(false);
            } else
                Btn_Sign.setText("Edit");
        }

         private void StartMainActivity(){
            setFirstTimeStartStatus(false);
            Intent _Intent=new Intent(this,MainActivity.class);
            startActivity(_Intent);
            finish();
        }*/
    public void BtnResy(View _v){
        startActivity(new Intent(this,RecyclerView.class));
        finish();
    }
}

