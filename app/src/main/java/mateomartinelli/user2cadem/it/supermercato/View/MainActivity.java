package mateomartinelli.user2cadem.it.supermercato.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import mateomartinelli.user2cadem.it.supermercato.R;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private TextView welcomeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidingTheTitleBar();
        setContentView(R.layout.activity_main);

        welcomeUser = findViewById(R.id.welcome);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.contains("loggedUser")) {
            String loggedUser = preferences.getString("loggedUser", null);
            welcomeUser.setText(loggedUser);

        } else {
            welcomeUser.setText("Ospite");
        }
        editor = preferences.edit();

    }
    public void registraUser(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void mostraPrdotti(View v){
        Intent intent = new Intent(this,ShowProdottiActivity.class);
    }


    private void hidingTheTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
}

