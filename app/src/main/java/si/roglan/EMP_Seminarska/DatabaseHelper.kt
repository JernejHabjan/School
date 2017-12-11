package si.roglan.EMP_Seminarska

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context1: Context, DATABASE_NAME: String, context: Context, DATABASE_VERSION: Int) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    //private var sInstance: DatabaseHelper? = null

    /*@Synchronized // TODO ---- FOR MULTITHREAD
    fun getInstance(context: Context): DatabaseHelper {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = DatabaseHelper(context.applicationContext)
        }
        return sInstance as DatabaseHelper
    }
    */

    private var database: SQLiteDatabase? = null // database object
    private val databaseOpenHelper = this // database helper

    @Throws(SQLException::class)
    fun getOneContact(id: Any): Cursor {
        open()
        return database!!.query("Persons", null, "PersonID=" + id, null, null, null, null)
    }

    @Throws(SQLException::class)
    fun getAllContacts(): Cursor {
        open()
        return database!!.query("Persons", arrayOf("PersonID", "LastName", "FirstName", "Address", "City"), null, null, null, null, "LastName")
    }


    @Throws(SQLException::class)
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("create table $TABLE_NAME (PersonID INTEGER PRIMARY KEY AUTOINCREMENT, LastName TEXT, FirstName TEXT, Address TEXT, City TEXT) ")
    }

    @Throws(SQLException::class)
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    @Throws(SQLException::class)
    fun open() {
        // create or open a database for reading/writing
        database = databaseOpenHelper.writableDatabase
        //database = sInstance!!.writableDatabase
    }

    override fun close() {
        if (database != null) database!!.close() // close the database connection
    }


    fun insertContact(lastName: String, firstName: String, address: String, city: String) {
        val newContact = ContentValues()
        newContact.put("LastName", lastName)
        newContact.put("FirstName", firstName)
        newContact.put("Address", address)
        newContact.put("City", city)
        open() // open the database
        database!!.insert("Persons", null, newContact)
        close() // close the database
    }

    fun updateContact(id: Long, lastName: String, firstName: String, address: String, city: String) {
        val editContact = ContentValues()
        editContact.put("PersonID", id)
        editContact.put("LastName", lastName)
        editContact.put("FirstName", firstName)
        editContact.put("Address", address)
        editContact.put("City", city)

        open() // open the database
        database!!.update("Persons", editContact, "PersonID=" + id, null)
        close() // close the database
    }

    fun deleteContact(id: Long) {
        open() // open the database
        database!!.delete("Persons", "PersonID=" + id, null)
        close() // close the database
    }

    companion object {
        val TABLE_NAME = "Persons"
    }



}
