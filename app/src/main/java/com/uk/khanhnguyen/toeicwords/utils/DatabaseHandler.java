package com.uk.khanhnguyen.toeicwords.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import com.uk.khanhnguyen.toeicwords.models.BookModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanhnt on 7/14/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler INSTANCE = null;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BookDatabase.db";
    private static final String BOOKS_TABLE_NAME = "books";
    private static final String BOOKS_COLUMN_ID = "id";
    private static final String BOOKS_COLUMN_TITLE = "title";
    private static final String BOOKS_COLUMN_DESCRIPTION = "description";
    private static final String BOOKS_COLUMN_AUTHOR = "author";
    private static final String BOOKS_COLUMN_IMAGEURL = "imageUrl";

    private static final String SQL_CREATE_BOOK_TABLE =
            "CREATE TABLE " + BOOKS_TABLE_NAME + " (" +
                    BOOKS_COLUMN_ID + " INTEGER PRIMARY KEY," +
                    BOOKS_COLUMN_TITLE + " TEXT," +
                    BOOKS_COLUMN_DESCRIPTION + " TEXT," +
                    BOOKS_COLUMN_AUTHOR + " TEXT," +
                    BOOKS_COLUMN_IMAGEURL + " TEXT" + ")";

    private static final String SQL_DELETE_BOOK_TABLE =
            "DROP TABLE IF EXISTS " + BOOKS_TABLE_NAME;

    /**
     * Singleton pattern: Gets instance.
     *
     * @return the instance
     */
    public static DatabaseHandler getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized(DatabaseHandler.class) {
                INSTANCE = new DatabaseHandler(context);
            }
        }
        return INSTANCE;
    }
    /**
     * Instantiates a new Database handler.
     *
     * @param context the context
     */
    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_BOOK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_BOOK_TABLE);
        onCreate(sqLiteDatabase);
    }

    /**
     * Add new book item to database.
     *
     * @param bookModel the book model
     */
    public boolean addBook(BookModel bookModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOKS_COLUMN_TITLE, bookModel.getTitle());
        contentValues.put(BOOKS_COLUMN_DESCRIPTION, bookModel.getDescription());
        contentValues.put(BOOKS_COLUMN_AUTHOR, bookModel.getAuthor());
        contentValues.put(BOOKS_COLUMN_IMAGEURL, bookModel.getImageUrl());
        db.insert(BOOKS_TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    private BookModel getBook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        // The columns to return
        String[] projection = {
                BOOKS_COLUMN_ID,
                BOOKS_COLUMN_TITLE,
                BOOKS_COLUMN_DESCRIPTION,
                BOOKS_COLUMN_AUTHOR,
                BOOKS_COLUMN_IMAGEURL
        };
        //The columns for the WHERE clause
        String selection = BOOKS_COLUMN_ID + "=?";
        // The values for the WHERE clause
        String[] selectionArgs = {
                String.valueOf(id)
        };

        Cursor cursor = db.query(
                BOOKS_TABLE_NAME,   // The table to query
                projection,         // The columns to return
                selection,          // The columns for the WHERE clause
                selectionArgs,      // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                null                // don't sort order
        );
//        Cursor cursor = db.rawQuery("SELECT * " +
//                                    "FROM "+ BOOKS_TABLE_NAME +
//                                    " WHERE " + BOOKS_COLUMN_ID + " =" + id, null);
        if (cursor!= null) {
            cursor.moveToFirst();
        }
        BookModel bookModel = new BookModel(
                Integer.parseInt(cursor.getString(cursor.getColumnIndex(BOOKS_COLUMN_ID))),
                cursor.getString(cursor.getColumnIndex(BOOKS_COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(BOOKS_COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(BOOKS_COLUMN_AUTHOR)),
                cursor.getString(cursor.getColumnIndex(BOOKS_COLUMN_IMAGEURL)));
        db.close();
        return bookModel;
    }

    public List<BookModel> getAllBooks() {
        List<BookModel> bookList = new ArrayList<BookModel>();
        String sqlQuery = "SELECT * FROM " + BOOKS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                BookModel bookModel = new BookModel(
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(BOOKS_COLUMN_ID))),
                        cursor.getString(cursor.getColumnIndex(BOOKS_COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(BOOKS_COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(BOOKS_COLUMN_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(BOOKS_COLUMN_IMAGEURL)));
                bookList.add(bookModel);
            } while (cursor.moveToNext());
        }
        db.close();
        return bookList;
    }

    private boolean updateBook(BookModel bookModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOKS_COLUMN_TITLE, bookModel.getTitle());
        contentValues.put(BOOKS_COLUMN_DESCRIPTION, bookModel.getDescription());
        contentValues.put(BOOKS_COLUMN_AUTHOR, bookModel.getAuthor());
        contentValues.put(BOOKS_COLUMN_IMAGEURL, bookModel.getImageUrl());
        db.update(BOOKS_TABLE_NAME,
                    contentValues,
                    BOOKS_COLUMN_ID + "=?",
                    new String[] { String.valueOf(bookModel.getId())});
        db.close();
        return true;
    }

    private void deleteBook(BookModel bookModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BOOKS_TABLE_NAME, BOOKS_COLUMN_ID + "=?", new String[] {String.valueOf(bookModel.getId())} );
        db.close();
    }
}
