package com.example.brainbell.yuptaskkomsic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private List<MoviesTicket> moviesTicketList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesTicketAdapter moviesTicketAdapter;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            finish();
        } else {
            username = bundle.getString("username");
        }

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
                intent.putExtra("username", username);
                intent.putExtra("title", moviesTicket.getTitle());
                intent.putExtra("rating", moviesTicket.getRating());
                intent.putExtra("plot", moviesTicket.getPlot());
                intent.putExtra("imageResourceId", moviesTicket.getImageResourceId());
                intent.putExtra("duration", moviesTicket.getDuration());
                intent.putExtra("director", moviesTicket.getDirector());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMoviesTicketData();
    }

    private void prepareMoviesTicketData() {
        MoviesTicket moviesTicket = new MoviesTicket(R.drawable.wonder_woman,
                getString(R.string.wonder_woman_title),
                getString(R.string.wonder_woman_director),
                9.2, 141, getString(R.string.wonder_woman_plot),
                R.raw.wonder_woman);
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.the_matrix,
                getString(R.string.the_matrix_title),
                getString(R.string.the_matrix_director),
                8.7, 136, getString(R.string.the_matrix_plot),
                R.raw.matrix);
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.terminator,
                getString(R.string.the_terminator_title),
                getString(R.string.harry_potter_director),
                8.0, 107, getString(R.string.the_terminator_plot),
                R.raw.terminator);
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.mr_and_mrs_smith,
                getString(R.string.mr_and_mrs_smith_title),
                getString(R.string.mr_and_mrs_smith_director),
                6.5, 120, getString(R.string.mr_and_mrs_smith_plot),
                R.raw.mr_mrs_smith);
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.twilight,
                getString(R.string.twilight_title),
                getString(R.string.twilight__director),
                5.2, 121, getString(R.string.twilight__plot),
                R.raw.twillight);
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.the_hunger_games,
                getString(R.string.the_hunger_games_title),
                getString(R.string.the_hunger_games_director),
                7.2, 142, getString(R.string.the_hunger_games_plot),
                R.raw.hunger_games);
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.harry_potter,
                getString(R.string.harry_potter_title),
                getString(R.string.harry_potter_director),
                7.5, 152, getString(R.string.harry_potter_plot),
                R.raw.harry_potter);
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.the_avengers,
                getString(R.string.the_avengers_title),
                getString(R.string.the_avengers_director),
                8.5, 143, getString(R.string.the_avengers_plot),
                R.raw.avengers);
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.iron_man,
                getString(R.string.iron_man_title),
                getString(R.string.iron_man_director),
                7.9, 126, getString(R.string.iron_man_plot),
                R.raw.iron_man);
        moviesTicketList.add(moviesTicket);

        moviesTicket = new MoviesTicket(R.drawable.spider_man,
                getString(R.string.spider_man_title),
                getString(R.string.spider_man_director),
                8.1, 133, getString(R.string.spider_man_plot),
                R.raw.spider_man);
        moviesTicketList.add(moviesTicket);
    }
}
