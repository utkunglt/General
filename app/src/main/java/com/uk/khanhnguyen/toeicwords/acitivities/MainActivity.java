package com.uk.khanhnguyen.toeicwords.acitivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.uk.khanhnguyen.toeicwords.R;
import com.uk.khanhnguyen.toeicwords.adapters.BooksAdapter;
import com.uk.khanhnguyen.toeicwords.models.BookModel;
import com.uk.khanhnguyen.toeicwords.utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BooksAdapter mBookAdapter;
    private DatabaseHandler mDatabaseHandler;
    private List<BookModel> booksList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHandler = DatabaseHandler.getInstance(this);
        createData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mBookAdapter = new BooksAdapter(booksList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mBookAdapter);

    }

    private void createData() {
        booksList = mDatabaseHandler.getAllBooks();
        if (booksList.size() == 0) {
            booksList = new ArrayList<>();
            BookModel book = new BookModel();
            book.setId((int) (1 + System.currentTimeMillis()));
            book.setAuthor("Reto Meier");
            book.setTitle("Android 4 Application Development");
            book.setImageUrl("http://api.androidhive.info/images/realm/1.png");
            booksList.add(book);

            book = new BookModel();
            book.setId((int) (2 + System.currentTimeMillis()));
            book.setAuthor("Itzik Ben-Gan");
            book.setTitle("Microsoft SQL Server 2012 T-SQL Fundamentals");
            book.setImageUrl("http://api.androidhive.info/images/realm/2.png");
            booksList.add(book);

            book = new BookModel();
            book.setId((int) (3 + System.currentTimeMillis()));
            book.setAuthor("Magnus Lie Hetland");
            book.setTitle("Beginning Python: From Novice To Professional Paperback");
            book.setImageUrl("http://api.androidhive.info/images/realm/3.png");
            booksList.add(book);

            book = new BookModel();
            book.setId((int) (4 + System.currentTimeMillis()));
            book.setAuthor("Chad Fowler");
            book.setTitle("The Passionate Programmer: Creating a Remarkable Career in Software Development");
            book.setImageUrl("http://api.androidhive.info/images/realm/4.png");
            booksList.add(book);

            book = new BookModel();
            book.setId((int) (5 + System.currentTimeMillis()));
            book.setAuthor("Yashavant Kanetkar");
            book.setTitle("Written Test Questions In C Programming");
            book.setImageUrl("http://api.androidhive.info/images/realm/5.png");
            booksList.add(book);

            for (BookModel item: booksList) {
                mDatabaseHandler.addBook(item);
            }
        }
    }
}
