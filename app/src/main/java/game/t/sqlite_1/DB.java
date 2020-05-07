package game.t.sqlite_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {
    int count=0;

    private String name="NAME",content="CONTENT";

    public DB(@Nullable Context context) {
        super(context,"Note", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("Abhishek","Successfully crated table");
        sqLiteDatabase.execSQL("CREATE TABLE CONTACT (ID INTEGER PRIMARY KEY,"+name+" TEXT, "+content+" TEXT);");
        //Toast.makeText(,"Succesfully compiled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addcontact(getter_setter gs){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(name,gs.getName());
        cv.put(content,gs.getContent());

        db.insert("CONTACT",null,cv);
        Log.d("msgh","Succesfully inserted");
    }

    public List<getter_setter> getAllContacts(){
        List<getter_setter> contactsList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        String select="SELECT * FROM CONTACT";
        Cursor cursor=db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do {
                getter_setter gs=new getter_setter();
                gs.setId(Integer.parseInt(cursor.getString(0)));
                gs.setName(cursor.getString(1));
                gs.setContent(cursor.getString(2));
                count++;
                contactsList.add(gs);
            }while (cursor.moveToNext());
        }
        return contactsList;
    }

    public int count(){
        return count;
    }

    public int updateContact(getter_setter gs){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(name,gs.getName());
        values.put(content,gs.getContent());
        return db.update("CONTACT",values,"ID =? ",new String[]{String.valueOf(gs.getId())});
    }

    public void deleteContact(getter_setter gs){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("CONTACT","ID =?",new String[]{String.valueOf(gs.getId())});

    }
}
