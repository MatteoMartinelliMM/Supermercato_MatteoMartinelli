package mateomartinelli.user2cadem.it.supermercato.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mateomartinelli.user2cadem.it.supermercato.Controller.ReadNWriteObject;
import mateomartinelli.user2cadem.it.supermercato.Model.Carne;
import mateomartinelli.user2cadem.it.supermercato.Model.Latte;
import mateomartinelli.user2cadem.it.supermercato.Model.Pesce;
import mateomartinelli.user2cadem.it.supermercato.Model.Prodotti;
import mateomartinelli.user2cadem.it.supermercato.Model.Supermercato;
import mateomartinelli.user2cadem.it.supermercato.R;

public class AddProductActivity extends AppCompatActivity {
    private Spinner selectTypeOfProduct ;
    private EditText nome;
    private Supermercato supermercato;
    private SharedPreferences preferences;
    private FirebaseDatabase db;
    private DatabaseReference myRef;
    private int counterLatte, counterPesce, counterCarne;
    private SeekBar costoNuovo;
    private TextView costo;
    private double costoToInsert,value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidingTheTitleBar();
        setContentView(R.layout.activity_add_product);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        costo = findViewById(R.id.visualCosto);
        db = FirebaseDatabase.getInstance();
        myRef = db.getReferenceFromUrl("https://supermercato-41757.firebaseio.com/Prodotti");

        costoNuovo = findViewById(R.id.changeCosto);
        selectTypeOfProduct = findViewById(R.id.scegliProdotto);
        costo = findViewById(R.id.visualCosto);
        nome = findViewById(R.id.marca);
        String fileName = preferences.getString("FileName",null);
        supermercato = (Supermercato) ReadNWriteObject.readObject(this,fileName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            costoNuovo.setMin(0);
        }
        costoNuovo.setMax(3000);

        costoNuovo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = ((double) progress / 100.0);
                costo.setText(value+"");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                costoToInsert =value;
            }
        });

    }

    public void Inserisci(View v){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        counterLatte = preferences.getInt("Latte",0);
        counterCarne = preferences.getInt("Carne",0);
        counterPesce = preferences.getInt("Pesce",0);

        Prodotti p;
        final String tipoProdotto = selectTypeOfProduct.getSelectedItem().toString();
        final String nomeProdotto= nome.getText().toString();
        aggiornaSupermercato(tipoProdotto, costoToInsert, nomeProdotto);
        Intent intent = new Intent(this, ShowProdottiActivity.class);
        startActivity(intent);


    }

    private void aggiornaSupermercato(String tipoProdotto, double costoToinsert, String nomeProdotto) {
        Prodotti p;
        switch (tipoProdotto){
            case "Latte":
                p = new Latte(nomeProdotto,costoToinsert);
                supermercato.addProduct(p);
                myRef.child(tipoProdotto).child("0" + counterLatte).child("Marca").setValue(nomeProdotto);
                myRef.child(tipoProdotto).child("0"+counterLatte).child("Prezzo").setValue(costoToinsert);
                break;
            case "Carne":
                p = new Carne(nomeProdotto,costoToinsert);
                supermercato.addProduct(p);
                myRef.child(tipoProdotto).child("0" + counterCarne).child("Marca").setValue(nomeProdotto);
                myRef.child(tipoProdotto).child("0"+counterCarne).child("Prezzo").setValue(costoToinsert);
                break;
            case "Pesce":
                p = new Pesce(nomeProdotto,costoToinsert);
                supermercato.addProduct(p);
                myRef.child(tipoProdotto).child("0" + counterPesce).child("Marca").setValue(nomeProdotto);
                myRef.child(tipoProdotto).child("0"+counterPesce).child("Prezzo").setValue(costoToinsert);
                break;
            default:
                break;
        }
    }

    private void hidingTheTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
}
