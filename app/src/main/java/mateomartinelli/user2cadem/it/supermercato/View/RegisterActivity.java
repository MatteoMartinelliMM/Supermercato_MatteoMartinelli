package mateomartinelli.user2cadem.it.supermercato.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import mateomartinelli.user2cadem.it.supermercato.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText userField;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidingTheTitleBar();
        setContentView(R.layout.activity_register);

        userField = findViewById(R.id.userName);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }



    public void salvaUser(View v){
        Intent intent = new Intent(this, MainActivity.class);

        String userName = userField.getText().toString();
        if (userName != null) {
            editor = preferences.edit();
            editor.putString("loggedUser",userName);
            startActivity(intent);
        }

    }

    private void hidingTheTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
}
