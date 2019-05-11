package com.example.david.myapplication.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.david.myapplication.LogicaNegocio.Ciclo;
import com.example.david.myapplication.LogicaNegocio.Profesor;
import com.example.david.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CicloAdapter extends RecyclerView.Adapter<CicloAdapter.MyViewHolder> implements Filterable {
    private List<Ciclo> cicloList;
    private List<Ciclo> cicloListFiltered;
    private CicloAdapterListener listener;
    private Ciclo deletedItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo1, titulo2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;


        public MyViewHolder(View view) {
            super(view);
            titulo1 = view.findViewById(R.id.titleFirstLbl);
            titulo2 = view.findViewById(R.id.titleSecLbl);
            description = view.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(cicloListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public CicloAdapter(List<Ciclo> cicloList, CicloAdapterListener listener) {
        this.cicloList = cicloList;
        this.listener = listener;
        //init filter
        this.cicloListFiltered = cicloList;
    }

    @Override
    public CicloAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CicloAdapter.MyViewHolder holder, int position) {
        // rendering view
        final Ciclo ciclo = cicloListFiltered.get(position);
        holder.titulo1.setText(ciclo.getCodigo() + "");
        holder.titulo2.setText(ciclo.getAno());
        holder.description.setText(ciclo.getFecha_inicio() + " - " + ciclo.getFecha_finalizacion());
    }
    @Override
    public int getItemCount() {
        return cicloListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = cicloListFiltered.remove(position);
        Iterator<Ciclo> iter = cicloList.iterator();
        while (iter.hasNext()) {
            Ciclo aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (cicloListFiltered.size() == cicloList.size()) {
            cicloListFiltered.add(position, deletedItem);
        } else {
            cicloListFiltered.add(position, deletedItem);
            cicloList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Ciclo getSwipedItem(int index) {
        if (this.cicloList.size() == this.cicloListFiltered.size()) { //not filtered yet
            return cicloList.get(index);
        } else {
            return cicloListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (cicloList.size() == cicloListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(cicloList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(cicloList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(cicloListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(cicloListFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    cicloListFiltered = cicloList;
                } else {
                    List<Ciclo> filteredList = new ArrayList<>();
                    for (Ciclo row : cicloList) {
                        // filter use two parameters
                        if (row.getCodigo().contains(charString)) {
                            filteredList.add(row);
                        }
                    }
                    cicloListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cicloListFiltered;
                return filterResults;
            }


            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cicloListFiltered = (ArrayList<Ciclo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface CicloAdapterListener {
        void onContactSelected(Ciclo ciclo);
    }
}
