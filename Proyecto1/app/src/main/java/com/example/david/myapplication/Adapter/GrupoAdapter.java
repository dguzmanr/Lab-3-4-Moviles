package com.example.david.myapplication.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.david.myapplication.LogicaNegocio.Curso;
import com.example.david.myapplication.LogicaNegocio.Grupo;
import com.example.david.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GrupoAdapter extends RecyclerView.Adapter<GrupoAdapter.MyViewHolder> implements Filterable {
    private GrupoAdapterListener listener;
    private Grupo deletedItem;
    private List<Grupo> grupoListFiltered;
    private List<Grupo> grupoList;

    public GrupoAdapter(List<Grupo> grupolist, GrupoAdapterListener listener){
        this.grupoList = grupolist;
        this.listener = listener;
        this.grupoListFiltered = grupolist;
    }

    @Override
    public GrupoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GrupoAdapter.MyViewHolder holder, int position) {
        final Grupo grupo = grupoListFiltered.get(position);
        holder.titulo1.setText(grupo.getCodigo());
        holder.titulo2.setText(grupo.getProfesor().toString());
        holder.description.setText(grupo.getHorario());
    }

    @Override
    public int getItemCount() {
        return grupoListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    grupoListFiltered = grupoList;
                } else {
                    List<Grupo> filteredList = new ArrayList<>();
                    for (Grupo row : grupoList) {
                        // filter use two parameters
                        if (row.getCodigo().toLowerCase().contains(charString.toLowerCase()) || row.getHorario().toLowerCase().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    grupoListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = grupoListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                grupoListFiltered = (ArrayList<Grupo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void removeItem(int position) {
        deletedItem = grupoListFiltered.remove(position);
        Iterator<Grupo> iter = grupoList.iterator();
        while (iter.hasNext()) {
            Grupo aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (grupoListFiltered.size() == grupoList.size()) {
            grupoListFiltered.add(position, deletedItem);
        } else {
            grupoListFiltered.add(position, deletedItem);
            grupoList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Grupo getSwipedItem(int index) {
        if (this.grupoList.size() == this.grupoListFiltered.size()) { //not filtered yet
            return grupoList.get(index);
        } else {
            return grupoListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (grupoList.size() == grupoListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(grupoList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(grupoList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(grupoListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(grupoListFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo1, titulo2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;

        public MyViewHolder(View itemView) {
            super(itemView);
            titulo1 = itemView.findViewById(R.id.titleFirstLbl);
            titulo2 = itemView.findViewById(R.id.titleSecLbl);
            description = itemView.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = itemView.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = itemView.findViewById(R.id.view_background_edit);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(grupoListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface GrupoAdapterListener {
        void onContactSelected(Grupo grupo);
    }
}
