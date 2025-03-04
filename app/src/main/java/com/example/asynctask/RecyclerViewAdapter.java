package com.example.asynctask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

  // TODO: Created by Sheeraz on 15/08/2024.

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<String> arrayList;
    TextView pokemonName;

    public RecyclerViewAdapter(ArrayList<String> _arrayList) {
        this.arrayList = _arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);

        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.pokemon.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pokemon;

        public MyViewHolder(@NonNull View view) {
            super(view);
            pokemon = view.findViewById(R.id.pokemonName);
        }
    }

}
