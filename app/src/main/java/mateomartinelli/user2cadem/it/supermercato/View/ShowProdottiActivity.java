package mateomartinelli.user2cadem.it.supermercato.View;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.CarrierConfigManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import mateomartinelli.user2cadem.it.supermercato.Controller.JSONParsing;
import mateomartinelli.user2cadem.it.supermercato.Controller.MyProductsAdapter;
import mateomartinelli.user2cadem.it.supermercato.Controller.ProductFirebaseRequest;
import mateomartinelli.user2cadem.it.supermercato.Controller.ReadNWriteObject;
import mateomartinelli.user2cadem.it.supermercato.Controller.WaitToComplitionTasks;
import mateomartinelli.user2cadem.it.supermercato.Model.Supermercato;
import mateomartinelli.user2cadem.it.supermercato.R;

public class ShowProdottiActivity extends AppCompatActivity implements WaitToComplitionTasks {
    private ProgressDialog progressDialog;
    private Supermercato supermercato;
    private RecyclerView listOfProducts;
    private MyProductsAdapter adapter;
    private LinearLayoutManager lm;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidingTheTitleBar();
        setContentView(R.layout.activity_show_prodotti);

        WaitToComplitionTasks wait = this;
        listOfProducts = findViewById(R.id.listaProdotti);

        lm = new LinearLayoutManager(this);
        listOfProducts.setLayoutManager(lm);

        supermercato = new Supermercato();


        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.onStart();
        restCallToGetProducts(wait);





    }

    private void restCallToGetProducts(final WaitToComplitionTasks wait) {
        String result;
        ProductFirebaseRequest.get("https://supermercato-41757.firebaseio.com/Prodotti.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    String prodottiTree = new String(responseBody);
                    if(prodottiTree!=null) {
                        supermercato = JSONParsing.parseAllProducts(prodottiTree, getApplicationContext());
                    }
                }
                wait.waitToComplition("Caricamento Completato");
                adapter = new MyProductsAdapter(supermercato);
                listOfProducts.setAdapter(adapter);
                ReadNWriteObject.writeObject(getApplicationContext(),"supermercato",supermercato);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                wait.waitToComplition("ERRORE: "+statusCode);
            }
        });
    }


    private void hidingTheTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    @Override
    public void waitToComplition(String s) {
        progressDialog.dismiss();
        progressDialog.cancel();
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}
