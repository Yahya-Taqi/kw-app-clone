package com.example.kw_clone;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

// 1. extends SQLiteOpenHelper
public class DatabaseHelper extends SQLiteOpenHelper
{
    // 2. Database Name
    private static final String databaseName = "users.db";

    // 3. Database Version
    private static final int databaseVersion = 1;

    // 4. Class Constructor
    public DatabaseHelper(Context context)
    {
        super(context, databaseName, null, databaseVersion);
    }

    // 5. Database Creation
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // 6. Query to make a table
        String createTableQuery = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, email TEXT, passwd TEXT);";
        // 7. Execute the query
        db.execSQL(createTableQuery);
    }

    // 8. Update Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // 9. Drop the existing
        db.execSQL("DROP TABLE IF EXISTS users");
        // 10. Create a new one
        onCreate(db);
    }

    // 11. Method to insert data into the database
    public boolean InsertUser(String firstName, String lastName, String email, String passwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("firstname", firstName);
        values.put("lastname", lastName);
        values.put("email", email);
        values.put("passwd", passwd);

        long result = -1;
        try
        {
            result = db.insert("users", null, values);
            if (result == -1)
            {
                System.out.println("Error: Insert failed. Check constraints.");
            } else
            {
                System.out.println("Insert successful! User ID: " + result);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Database Error: " + e.getMessage());
        } finally
        {
            db.close();
        }
        return result != -1;
    }

    // 12. Method to retrieve data from the database
    public List<String> GetUsers()
    {
        // Creating a list to store the users
        List<String> userList = new ArrayList<String>();
        // Open Database for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor to retrieve query results
        Cursor cursor = db.rawQuery("Select * FROM users", null);
        // moveToFirst and moveToNext: Iterates through results
        if (cursor.moveToFirst())
        {
            do
            {
                String user = cursor.getString(1) + " - " + cursor.getInt(2) + "years old";
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    // 13. Updating the record in the database
    public void UpdateUser(int id, String newName, int newAge)
    {
        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Set new values
        ContentValues values = new ContentValues();
        values.put("name", newName);
        values.put("age", newAge);
        // Update the row
        db.update("users", values , "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // 14. Deleting a record from the database
    public void DeleteUser(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete the row
        db.delete("users", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void ResetDatabase()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db); // Recreate the table
    }
}
