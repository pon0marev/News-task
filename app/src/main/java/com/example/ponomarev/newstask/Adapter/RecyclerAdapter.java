package com.example.ponomarev.newstask.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ponomarev.newstask.ArticleHelper.Article;
import com.example.ponomarev.newstask.DateConvertor;
import com.example.ponomarev.newstask.R;
import com.squareup.picasso.Picasso;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{

    private static RecyclerAdapterListener clickListener;
    private  List<Article> articleList;
    Context context;
    int layout=R.layout.recycler_layout;



    public RecyclerAdapter(Context context, List<Article> articleList){
        this.context=context;
        this.articleList = articleList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ItemViewHolder pvh = new ItemViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        holder.textTitle.setText(String.valueOf(articleList.get(position).getTitle()));
        holder.textDescription.setText(String.valueOf(articleList.get(position).getDescription()));
        holder.textAuthor.setText(articleList.get(position).getAuthor());

        holder.textDate.setText(DateConvertor.convert(articleList.get(position).getPublishedAt()));




        Picasso.get().load(articleList.get(position).getUrlToImage()).into(holder.itemImage);

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textTitle;
        private TextView textDescription;
        private TextView textAuthor;
        private TextView textDate;
        CardView cv;

        ImageView itemImage;
        ItemViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card);
            cv.setOnClickListener(this);
            textTitle = (TextView)itemView.findViewById(R.id.titleTextView);
            textDescription = (TextView)itemView.findViewById(R.id.descriptionTextView);
            textAuthor = (TextView)itemView.findViewById(R.id.authorTextView);
            textDate=(TextView)itemView.findViewById(R.id.dateTextView);
            itemImage = (ImageView)itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);

        }

    }
    public void setOnItemClickListener(RecyclerAdapterListener clickListener) {
        RecyclerAdapter.clickListener =  clickListener;
    }

    public interface RecyclerAdapterListener {
        void onItemClick(int position, View v);
    }

}
