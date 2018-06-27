package emp.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnector extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserContacts";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;

    public DatabaseConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE contacts ("
                + "_id integer primary key autoincrement,"
                + "name TEXT, email TEXT, phone TEXT, street TEXT, city TEXT, newText TEXT);";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    void open() throws SQLException {
        database = this.getWritableDatabase();
    }

    public void close() {
        if (database != null)
            database.close();
    }

    void insertContact(String name, String email, String phone, String state, String city, String newText) {
        ContentValues newContact = new ContentValues();
        newContact.put("name", name);
        newContact.put("email", email);
        newContact.put("phone", phone);
        newContact.put("street", state);
        newContact.put("city", city);
        newContact.put("newText", newText);

        open();
        database.insert("contacts", null, newContact);
        close();
    }

    void updateContact(long id, String name, String email, String phone, String state,
                       String city, String newText) {
        ContentValues editContact = new ContentValues();
        editContact.put("name", name);
        editContact.put("email", email);
        editContact.put("phone", phone);
        editContact.put("street", state);
        editContact.put("city", city);
        editContact.put("newText", newText);
        open();
        database.update("contacts", editContact, "_id=" + id, null);
        close();
    }

    Cursor getAllContacts() {
        return database.query("contacts", new String[]{"_id", "name", "phone"}, null, null, null, null,
                "name" + " DESC");
    }

    Cursor getOneContact(long id) {
        return database.query("contacts", null, "_id=" + id, null, null, null, null);
    }

    void deleteContact(long id) {
        open();
        database.delete("contacts", "_id=" + id, null);
        close();
    }
}
