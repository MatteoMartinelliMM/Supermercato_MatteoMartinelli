package mateomartinelli.user2cadem.it.supermercato.Model;

import java.io.Serializable;

/**
 * Created by utente2.academy on 11/30/2017.
 */

public abstract class Prodotti implements Serializable{
    private String marca;
    private double Prezzo;
    private String idProdotto;

    public String getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(String idProdotto) {
        this.idProdotto = idProdotto;
    }

    public Prodotti(String marca, double prezzo) {
        this.marca = marca;
        Prezzo = prezzo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(double prezzo) {
        Prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "Marca: '" + marca + "\n Prezzo=" + Prezzo + '\n';
    }
}
