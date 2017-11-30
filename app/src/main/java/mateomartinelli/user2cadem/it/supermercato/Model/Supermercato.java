package mateomartinelli.user2cadem.it.supermercato.Model;

import java.util.ArrayList;

/**
 * Created by utente2.academy on 11/30/2017.
 */

public class Supermercato {
    private ArrayList<Prodotti> prodotti;

    public Supermercato(ArrayList<Prodotti> prodotti) {
        this.prodotti = prodotti;
    }

    public Supermercato() {
        prodotti = new ArrayList<>();
    }

    public void addProduct(Prodotti p){
        prodotti.add(p);
    }

    public Prodotti removeAProduct(int index){
        return prodotti.remove(index);

    }
    public  void removeThisProduct(Prodotti p){
        prodotti.remove(prodotti);
    }

    public int getNumberOfProducts(){
        return prodotti.size();
    }

    public Prodotti getProdotto(int index){
        return  prodotti.get(index);
    }

    public ArrayList<Prodotti> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotti> prodotti) {
        this.prodotti = prodotti;
    }
}
