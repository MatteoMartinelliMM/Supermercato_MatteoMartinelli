package mateomartinelli.user2cadem.it.supermercato.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mateomartinelli.user2cadem.it.supermercato.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText userField;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private FirebaseDatabase db;
    private DatabaseReference myRef;
    private  int howManyUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = FirebaseDatabase.getInstance();
        myRef = db.getReferenceFromUrl("https://supermercato-41757.firebaseio.com/Users");
        super.onCreate(savedInstanceState);
        hidingTheTitleBar();
        setContentView(R.layout.activity_register);

        userField = findViewById(R.id.userName);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }



    public void salvaUser(View v){
        Intent intent = new Intent(this, MainActivity.class);
        String userId = "0";
        int userIdModifyer = 1;
        String userName = userField.getText().toString();
        if (userName != null) {
            editor = preferences.edit();
            editor.putString("loggedUser",userName);
            editor.commit();
            obtainNextProgressiveUserId();
            myRef.child(userId+howManyUsers).setValue(userName);
            startActivity(intent);
        }

    }

    private void obtainNextProgressiveUserId() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                howManyUsers= (int ) dataSnapshot.getChildrenCount();
                Log.i("figli",howManyUsers+"");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        howManyUsers++;
    }

    private void hidingTheTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
}
