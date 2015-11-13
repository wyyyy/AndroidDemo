package cn.utils.sqlhelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper
{
  private static final int VERSION = 1;
  public static String TABLE_CHANNEL = "CHANNEL";
  public static String TABLE_NAME = "Record";
  public static final String DB_NAME = "database.db";

  public static final String ID = "id";//
  public static final String UserName = "UserName";
  public static final String Sex = "Sex";
  public static final String CardNO = "CardNO";
  public static final String AddRess = "AddRess";

  public static final String NAME = "name";//
  public static final String SELECTED = "selected";
  public static final String ORDERID = "orderId";

  private Context context;

  public SqlHelper(Context context, String name, CursorFactory factory, int version)
  {

    super(context, name, factory, version);
  }

  public SqlHelper(Context context, String name, int version)
  {
    this(context, name, null, version);
  }

  public SqlHelper(Context context, String name)
  {
    this(context, name, VERSION);
  }

  public SqlHelper(Context context)
  {

    super(context, DB_NAME, null, VERSION);
    this.context = context;
  }

  public Context getContext()
  {
    return context;
  }

  @Override
  public void onCreate(SQLiteDatabase db)
  {
    StringBuilder sb = new StringBuilder();
    sb.append(" CREATE TABLE [Record] (").append(" [ID]  integer PRIMARY KEY autoincrement, ")
        .append("[UserName] CHAR, ").append("  [Sex] varchar(20), ")
        .append("  [CardNO] varchar(20), ").append("  [AddRess] varchar(20)").append(")");
    db.execSQL(sb.toString());
    String sql = "create table if not exists " + TABLE_CHANNEL
        + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + ID + " INTEGER , " + NAME + " TEXT , "
        + ORDERID + " INTEGER , " + SELECTED + " SELECTED)";
    db.execSQL(sql);
  }

  @Override
  public void onOpen(SQLiteDatabase db)
  {
    super.onOpen(db);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
  {
    db.execSQL(" DROP TABLE IF EXISTS Record "); // 往表中增加一列
    // DROP TABLE IF EXISTS Record 删除表
  }

  public Cursor select()
  {
    SQLiteDatabase db = this.getReadableDatabase();
    // Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
    Cursor cursor = db.rawQuery("select * from Record", null);
    return cursor;
  }

  // 增加操作
  public long insert(String bookname, String author)
  {
    SQLiteDatabase db = this.getWritableDatabase();
    /* ContentValues */
    ContentValues values = new ContentValues();
    values.put("UserName", "UserName");
    values.put("CardNO", "CardNO");
    values.put("Sex", "Sex");
    values.put("AddRess", "AddRess");
    long row = db.insert(TABLE_NAME, null, values);
    return row;
  }

  // 删除操作
  public void delete(int id)
  {
    SQLiteDatabase db = this.getWritableDatabase();
    String where = ID + " = ?";
    String[] whereValue =
    { Integer.toString(id) };
    db.delete(TABLE_NAME, where, whereValue);
  }

  // 修改操作
  public void update(int id, String bookname, String author)
  {
    SQLiteDatabase db = this.getWritableDatabase();
    String where = ID + " = ?";
    String[] whereValue =
    { Integer.toString(id) };

    ContentValues cv = new ContentValues();
    cv.put(UserName, bookname);
    cv.put(AddRess, author);
    db.update(TABLE_NAME, cv, where, whereValue);
  }
}
