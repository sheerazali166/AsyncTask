package com.example.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button pokemonButton;

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<String> pokemonList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(pokemonList);
        recyclerView.setAdapter(recyclerViewAdapter);

        pokemonButton = findViewById(R.id.pokemonButton);
        pokemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadPokemonTask(MainActivity.this).execute();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public class DownloadPokemonTask extends AsyncTask<Void, Integer, ArrayList<String>> {

        ProgressDialog progressDialog;

        public DownloadPokemonTask(Context context) {
            this.progressDialog = new ProgressDialog(context);
        }

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {

            ArrayList<String> newPokemonList = new ArrayList<String>();

            for (int i = 0; i <= 3; i++) {
                try {
                    Thread.sleep(1000);
                    newPokemonList.add(simulateCallToAPI(i));

                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }

            return newPokemonList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            this.progressDialog.setMessage("Downloading data...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<String> arrayList) {
            super.onPostExecute(arrayList);
            pokemonList.clear();
            pokemonList.addAll(arrayList);
            recyclerViewAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
        }


        // TODO: This method simulate a call to API
        private String simulateCallToAPI(int i) {
            switch (i) {
                case 1:
                    return "Bulbasaur";
                case 2:
                    return "Ivysaur";
                case 3:
                    return "Venusaur";
                default:
                    return "Invalid Number";

            }
        }
    }
}