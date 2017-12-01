package mateomartinelli.user2cadem.it.supermercato.Controller;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import mateomartinelli.user2cadem.it.supermercato.Model.Carne;
import mateomartinelli.user2cadem.it.supermercato.Model.Latte;
import mateomartinelli.user2cadem.it.supermercato.Model.Pesce;
import mateomartinelli.user2cadem.it.supermercato.Model.Supermercato;

/**
 * Created by utente2.academy on 12/1/2017.
 */

public class JSONParsing {

    public static final String MARCA = "Marca";
    public static final String PREZZO = "Prezzo";

    public static Supermercato parseAllProducts(String toParse, Context context){ //NB: CPL = carne/pesce/latte
        Supermercato supermercato = new Supermercato();
        try {
            JSONObject ramoProdotti = new JSONObject(toParse);
            Iterator<String> keysProdotti = ramoProdotti.keys();

            Iterator<String> keysCPL;
            while (keysProdotti.hasNext()){
                String keyArticoliInExam = keysProdotti.next();
                JSONObject ramoCPL = null;
                ramoCPL = ramoProdotti.getJSONObject(keyArticoliInExam);
                keysCPL = ramoCPL.keys();
                while (keysCPL.hasNext()){
                    String keyCPLInExam = keysCPL.next();
                    String marca;
                    String prezzoString;
                    double prezzo;
                    switch (keyArticoliInExam){
                        case "Carne":
                            JSONObject jCarne = ramoCPL.getJSONObject(keyCPLInExam);
                            marca = jCarne.getString(MARCA);
                            prezzoString = jCarne.getString(PREZZO);
                            prezzo = -1;
                            if(isDigit(prezzoString))
                                prezzo = Double.parseDouble(prezzoString);
                            else prezzo =-1;
                            Carne carne = new Carne(marca,prezzo);
                            supermercato.addProduct(carne);
                            break;
                        case "Pesce":
                            JSONObject jPesce = ramoCPL.getJSONObject(keyCPLInExam);
                            marca = jPesce.getString(MARCA);
                            prezzoString = jPesce.getString(PREZZO);
                            if(isDigit(prezzoString))
                                prezzo = Double.parseDouble(prezzoString);
                            else prezzo = -1;
                            Pesce pesce = new Pesce(marca,prezzo);
                            supermercato.addProduct(pesce);
                            break;
                        case "Latte":
                            JSONObject jLatte = ramoCPL.getJSONObject(keyCPLInExam);
                            marca = jLatte.getString(MARCA);
                            prezzoString = jLatte.getString(PREZZO);
                            if(isDigit(prezzoString))
                                prezzo = Double.parseDouble(prezzoString);
                            else prezzo = -1;
                            Latte latte = new Latte(marca,prezzo);
                            supermercato.addProduct(latte);
                            break;
                    }
                }

            }
            return supermercato;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  supermercato;
    }

    private static boolean isDigit(String s){
        return s.matches("[0-9].*");
    }
}
