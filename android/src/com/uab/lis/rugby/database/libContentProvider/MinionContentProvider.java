package com.uab.lis.rugby.database.libContentProvider;

/**
 * Created by Manuel on 29/03/2014.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * This class manage a specific provider
 */
public abstract class MinionContentProvider {

    private Context context;


    /**
     * Returns the impl path of this entity. For example "sports". Will be added to the getAuthority of the DespicableContentProvider
     * in the shape content://{DespicableContentProvider.getAuthority()}/{getBasePath()}
     * @return The impl path
     */
    public abstract String getBasePath();

    /**
     * Performs a query on the entity managed by this minion
     * @param db the current db
     * @param uri	The URI to query. This will be the full URI sent by the client; if the client is requesting a specific record, the URI will end in a record number that the implementation should parse and add to a WHERE or HAVING clause, specifying that _id value.
     * @param projection	The list of columns to put into the cursor. If null all columns are included.
     * @param selection	A selection criteria to apply when filtering rows. If null then all rows are included.
     * @param selectionArgs	You may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the selection. The values will be bound as Strings.
     * @paramsortOrder	How the rows in the cursor should be sorted. If null then the provider is free to define the sort order.
     * @return a Cursor or null.
     */
    public abstract Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder);

    /**
     * Inserts value in the entity managed by this minion
     * @param db the current db
     * @param uri The content:// URI of the insertion request.
     * @param contentValues A set of column_name/value pairs to add to the database.
     * @return  The URI for the newly inserted item.
     */
    public abstract long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues);

    /**
     * Deletes one or more rows for the  entity managed by this minion
     * @param db
     * @param uri The full URI to query, including a row ID (if a specific record is requested).
     * @param where An optional restriction to apply to rows when deleting.
     * @param selectionArgs the values for the arguments
     * @return The number of rows affected.
     */
    public abstract int delete(SQLiteDatabase db, Uri uri, String where, String[] selectionArgs);

    /**
     *  Updates one or more rows
     * @param uri The URI to query. This can potentially have a record ID if this is an update request for a specific record.
     * @param values A Bundle mapping from column names to new column values (NULL is a
     *               valid value).
     * @param where An optional filter to match rows to update.
     * @return the number of rows affected.
     * @return
     */
    public abstract int update(SQLiteDatabase db, Uri uri, ContentValues values, String where, String[] selectionArgs);

    public abstract String getType();

    public void setContext(Context context) {
        this.context=context;
    }

    public Context getContext() {
        return context;
    }

}