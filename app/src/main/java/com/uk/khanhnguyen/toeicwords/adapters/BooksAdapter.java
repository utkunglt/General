package com.uk.khanhnguyen.toeicwords.adapters;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uk.khanhnguyen.toeicwords.R;
import com.uk.khanhnguyen.toeicwords.models.BookModel;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by khanhnt on 7/26/2016.
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    List<BookModel> booksList;
    WeakReference<Context> context = null;
    public BooksAdapter(List<BookModel> booksList, Context context) {
        this.booksList = booksList;
        this.context = new WeakReference<Context>(context);
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView txtTitle;
        public TextView txtAuthor;
        public TextView txtDescription;
        public ImageView imgBackground;

        public BookViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_books);
            txtTitle = (TextView) itemView.findViewById(R.id.text_books_title);
            txtAuthor = (TextView) itemView.findViewById(R.id.text_books_author);
            txtDescription = (TextView) itemView.findViewById(R.id.text_books_description);
            imgBackground = (ImageView) itemView.findViewById(R.id.image_background);
        }
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        BookModel bookModel = booksList.get(position);
        holder.txtTitle.setText(bookModel.getTitle());
        holder.txtAuthor.setText(bookModel.getAuthor());
        holder.txtDescription.setText(bookModel.getDescription());

        if (bookModel.getImageUrl() != null && context.get() != null) {
            Glide.with(context.get())
                    .load(bookModel.getImageUrl().replace("https","http"))
                    .asBitmap()
                    .fitCenter()
                    .into(holder.imgBackground);
        }
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }


}
