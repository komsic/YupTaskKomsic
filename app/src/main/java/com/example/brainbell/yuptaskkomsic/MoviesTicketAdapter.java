package com.example.brainbell.yuptaskkomsic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BRAINBELL on 7/16/2017.
 */
public class MoviesTicketAdapter extends RecyclerView.Adapter<MoviesTicketAdapter.MyViewHolder> {
    private List<MoviesTicket> moviesTicketList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTxt;
        public TextView ratingTxt;
        public TextView durationTxt;
        public TextView directorTxt;
        public ImageView posterImg;

        public MyViewHolder(View view) {
            super(view);

            titleTxt = (TextView) view.findViewById(R.id.category_title_txt);
            ratingTxt = (TextView) view.findViewById(R.id.category_rating_txt);
            durationTxt = (TextView) view.findViewById(R.id.category_duration_txt);
            directorTxt = (TextView) view.findViewById(R.id.category_director_txt);
            posterImg = (ImageView) view.findViewById(R.id.category_poster_img);
        }
    }

    public MoviesTicketAdapter(List<MoviesTicket> moviesTicketList) {
        this.moviesTicketList = moviesTicketList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MoviesTicket moviesTicket = moviesTicketList.get(position);

        holder.titleTxt.setText(moviesTicket.getTitle());
        holder.ratingTxt.setText(String.valueOf(moviesTicket.getRating()));
        holder.durationTxt.setText(String.valueOf(moviesTicket.getDuration()));
        holder.directorTxt.setText(moviesTicket.getDirector());
        holder.posterImg.setImageResource(moviesTicket.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return moviesTicketList.size();
    }
}
