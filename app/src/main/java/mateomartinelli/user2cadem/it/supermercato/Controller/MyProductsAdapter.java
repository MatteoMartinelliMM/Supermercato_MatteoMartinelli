package mateomartinelli.user2cadem.it.supermercato.Controller;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mateomartinelli.user2cadem.it.supermercato.Model.Prodotti;
import mateomartinelli.user2cadem.it.supermercato.Model.Supermercato;
import mateomartinelli.user2cadem.it.supermercato.R;

/**
 * Created by utente2.academy on 11/30/2017.
 */

public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.ViewHolder> {
    Supermercato supermercato;

    public MyProductsAdapter(Supermercato supermercato) {
        this.supermercato = supermercato;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView productCard;
        public TextView marcaPrododtto, costoProdotto;
        public ImageView immagineProdotto;
        public ViewHolder(View v) {
            super(v);

            productCard = itemView.findViewById(R.id.mCard);
            marcaPrododtto = v.findViewById(R.id.marcaProdotto);
            costoProdotto = v.findViewById(R.id.costoProdotto);
            immagineProdotto = v.findViewById(R.id.immagineProdotto);

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
        Prodotti prodotto = supermercato.getProdotto(position);
        String temp = prodotto.getMarca();
        String marca = temp.substring(0,1).toUpperCase()+temp.substring(1);
        holder.marcaPrododtto.setText(marca);
        holder.costoProdotto.setText(prodotto.getPrezzo()+"");
        String tipo = prodotto.getClass().getSimpleName();
        switch (tipo){
            case "Latte":
                holder.immagineProdotto.setImageResource(R.drawable.latte_esselunga);
                break;
            case "Carne":
                holder.immagineProdotto.setImageResource(R.drawable.carne);
                break;
            case "Pesce":
                holder.immagineProdotto.setImageResource(R.drawable.pesce);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return supermercato.size();
    }
}
