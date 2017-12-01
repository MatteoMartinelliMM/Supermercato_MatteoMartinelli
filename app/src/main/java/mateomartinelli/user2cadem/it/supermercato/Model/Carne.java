package mateomartinelli.user2cadem.it.supermercato.Model;

import java.io.Serializable;

/**
 * Created by utente2.academy on 11/30/2017.
 */

public class Carne extends Prodotti implements Serializable{
    public final int tipo = 0;
    public Carne(String marca, double prezzo) {
        super(marca, prezzo);
    }
}
