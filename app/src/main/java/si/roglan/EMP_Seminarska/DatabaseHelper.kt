package si.roglan.EMP_Seminarska

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    private var database: SQLiteDatabase? = null // database object
    private val databaseOpenHelper = this // database helper
    val allContacts: Cursor
        get() = database!!.query("contacts", arrayOf("_id", "name"), null, null, null, null, "name")

    override fun onCreate(db: SQLiteDatabase) {
        database!!.execSQL("create table $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT) ")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    //@Throws(SQLException::class)
    fun open() {
        // create or open a database for reading/writing
        database = databaseOpenHelper.writableDatabase
    }

    override fun close() {
        if (database != null) database!!.close() // close the database connection
    }


    fun insertContact(name: String, email: String, phone: String, state: String, city: String) {
        val newContact = ContentValues()
        newContact.put("name", name)
        newContact.put("email", email)
        newContact.put("phone", phone)
        newContact.put("street", state)
        newContact.put("city", city)
        open() // open the database
        database!!.insert("contacts", null, newContact)
        close() // close the database
    }

    fun updateContact(id: Long, name: String, email: String, phone: String, state: String, city: String) {
        val editContact = ContentValues()
        editContact.put("name", name)
        editContact.put("email", email)
        editContact.put("phone", phone)
        editContact.put("street", state)
        editContact.put("city", city)
        open() // open the database
        database!!.update("contacts", editContact, "_id=" + id, null)
        close() // close the database
    }

    fun deleteContact(id: Long) {
        open() // open the database
        database!!.delete("contacts", "_id=" + id, null)
        close() // close the database
    }

    companion object {
        val DATABASE_NAME = "Example.db"
        val TABLE_NAME = "Example_table"
        val COL1 = "ID"
        val COL2 = "NAME"
    }
    // get a Cursor containing all information about the contact specified by the given id
    // public Cursor getOneContact(long id) {
    //    return database.query("contacts", null, "_id"= + id, null, null, null, null);
    // }


}
