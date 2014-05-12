package com.uab.lis.rugby.database.libContentProvider;

/**
 * Created by Manuel on 29/03/2014.
 */
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.SparseArray;

/**
 * This class is responsible to manage all minions
 */
public abstract class DespicableContentProvider extends ContentProvider {

    protected static final String CONTENT = "content://";
    public static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir/vnd.";
    public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.";

    SparseArray<MinionContentProvider> minions;
    int minionId = 0;
    private UriMatcher uriMatcher;
    private boolean minionsRecruited = false;


    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        minions = new SparseArray<MinionContentProvider>();

        return false;
    }

    /**
     * This method is a placeholder to perform all the addMinion calls in order
     * to setup our army of minions. It will be invoked on the first use of the
     * minions
     */
    public abstract void recruitMinions();

    /**
     * Current Authority for this content provider. Must be the same than the
     * one declared in the manifest
     *
     * @return The Authority value
     */
    public abstract String getAuthority();



    /**
     * Adds a minion to the list of minions that will answer to data requests
     *
     * @param minion
     *            The minion to add
     * @return The identifier of the minion
     */
    public int addMinion(MinionContentProvider minion) {
        minionId++;
        uriMatcher.addURI(getAuthority(), minion.getBasePath(), minionId);
        minions.put(minionId, minion);
        minion.setContext(getContext());

        return minionId;
    }

    /**
     * Retrieves the minion that answers to the provided uri It will also invoke
     * the recruitMinions method if they have not been recruited before
     *
     * @param uri
     *            A data uri
     * @return The minion who requested the type of uri provided
     */
    private MinionContentProvider getMinion(Uri uri) {
        if (!minionsRecruited) {
            recruitMinions();
            minionsRecruited = true;
        }

        int uriType = uriMatcher.match(uri);
        if (uriType == -1) {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        return minions.get(uriType);
    }

    /**
     * Getter for the current database
     *
     * @return returns the database used by the content provider
     */
    public abstract SQLiteDatabase getDb();

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MinionContentProvider minion = getMinion(uri);

        Cursor cursor = minion.query(getDb(), uri, projection, selection, selectionArgs, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        MinionContentProvider minion = getMinion(uri);

        return minion.getType();
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        MinionContentProvider minion = getMinion(uri);
        long id = minion.insert(getDb(), uri, contentValues);
        getContext().getContentResolver().notifyChange(uri, null);

        return Uri.parse(uri + "/" + id);
    }

    @Override
    public int delete(Uri uri, String where, String[] selectionArgs) {
        MinionContentProvider minion = getMinion(uri);
        int rowsDeleted = minion.delete(getDb(), uri, where, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] selectionArgs) {
        MinionContentProvider minion = getMinion(uri);
        int rowsUpdated = minion.update(getDb(), uri, values, where, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return rowsUpdated;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] valuesTable) {
        MinionContentProvider minion = getMinion(uri);
        SQLiteDatabase db = getDb();
        db.beginTransaction();

        for (ContentValues values : valuesTable) {
            minion.insert(db, uri, values);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        getContext().getContentResolver().notifyChange(uri, null);

        return valuesTable.length;
    }


}