package mateomartinelli.user2cadem.it.supermercato.Controller;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mateomartinelli.user2cadem.it.supermercato.Model.Prodotti;
import mateomartinelli.user2cadem.it.supermercato.R;

/**
 * Created by utente2.academy on 11/30/2017.
 */

public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.ViewHolder> {
    ArrayList<Prodotti> listOfProducts;

    public MyProductsAdapter(ArrayList<Prodotti> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView productCard;
        public TextView marcaPrododtto, costoProdotto;
        public ViewHolder(View v) {
            super(v);

            productCard = itemView.findViewById(R.id.mCard);
            marcaPrododtto = v.findViewById(R.id.marcaProdotto);
            costoProdotto = v.findViewById(R.id.costoProdotto);

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_element_list,parent,false);

        ViewHolder toReturn = new ViewHolder(v);
        return toReturn;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Prodotti prodotto =listOfProducts.get(position);
        holder.marcaPrododtto.setText(prodotto.getMarca());
        holder.costoProdotto.setText(prodotto.getPrezzo()+"");

    }

    @Override
    public int getItemCount() {
        return listOfProducts.size();
    }
}
