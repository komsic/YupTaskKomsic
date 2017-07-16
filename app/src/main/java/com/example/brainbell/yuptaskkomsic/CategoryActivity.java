package com.example.brainbell.yuptaskkomsic;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private List<MoviesTicket> moviesTicketList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesTicketAdapter moviesTicketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        moviesTicketAdapter = new MoviesTicketAdapter(moviesTicketList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(moviesTicketAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoviesTicket moviesTicket = moviesTicketList.get(position);
                Intent intent = new Intent(CategoryActivity.this, BookingActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMoviesTicketData();
    }

    private void prepareMoviesTicketData() {
        MoviesTicket moviesTicket = new MoviesTicket(R.drawable.wonder_woman,"Wonder Woman",
                "Patty Jenkins", 9.2, 141, getString(R.string.movie_about));
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.wonder_woman,"Wonder Woman",
                "Patty Jenkins", 9.2, 141, getString(R.string.movie_about));
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.wonder_woman,"Wonder Woman",
                "Patty Jenkins", 9.2, 141, getString(R.string.movie_about));
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.wonder_woman,"Wonder Woman",
                "Patty Jenkins", 9.2, 141, getString(R.string.movie_about));
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.wonder_woman,"Wonder Woman",
                "Patty Jenkins", 9.2, 141, getString(R.string.movie_about));
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.wonder_woman,"Wonder Woman",
                "Patty Jenkins", 9.2, 141, getString(R.string.movie_about));
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.wonder_woman,"Wonder Woman",
                "Patty Jenkins", 9.2, 141, getString(R.string.movie_about));
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.wonder_woman,"Wonder Woman",
                "Patty Jenkins", 9.2, 141, getString(R.string.movie_about));
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.wonder_woman,"Wonder Woman",
                "Patty Jenkins", 9.2, 141, getString(R.string.movie_about));
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.wonder_woman,"Wonder Woman",
                "Patty Jenkins", 9.2, 141, getString(R.string.movie_about));
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.wonder_woman,"Wonder Woman",
                "Patty Jenkins", 9.2, 141, getString(R.string.movie_about));
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.wonder_woman,"Wonder Woman",
                "Patty Jenkins", 9.2, 141, getString(R.string.movie_about));
        moviesTicketList.add(moviesTicket);
    }
}
