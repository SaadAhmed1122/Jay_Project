package com.example.stockmanagment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBName = "Stoke";
    private static final String TableName = "Products";
    private static final String Company_Table= "Company_table";

    private static final String KeyID = "Pro_id";
    private static final String PRO_IMAGE = "Product_img";

    private static final String ProductName = "Product_Name";
    private static final String ProductRP = "Product_Retail_Price";
    private static final String P_Qty = "Product_Quantity";
    private static final String P_Qty_d = "Darzan_Quantity";
    private static final String P_WHOLESALE_P = "WholeSale_p_p";
    private static final String P_WHOLESALE_d = "WholeSale_p_d";

    private static final String Discount = "Discount";
    private static final String Scheme = "Scheme";
    private static final String Coupon = "Coupen";
    private static final String COMPANY_NAME = "Company";
    private static final String COMPANY_ID = "Company_id";

    private static final String NETAmountP = "Net_Amount_piece";
    private static final String NETAmountD = "Net_Amount_derzan";
    private static final String EXTRADIS1 = "Extra_Discount1";
    private static final String EXTRADIS2 = "Extra_Discount2";
    private static final String EXTRADIS3 = "Extra_Discount3";
    private static final String CITY = "City";
    private static final String FINALAMOUNTP = "Final_amount_p";
    private static final String FINALAMOUNTd = "Final_amount_d";
    private Context context;
    private ByteArrayOutputStream objstream;
    private byte[] imageinbytes;





    DBHelper(Context context) {
        super(context, DBName, null, 1);
        this.context = context;
    }

    private static DBHelper singleton;
    //utility method to access single class object
    public static DBHelper getDBHelper(Context context) {
        if (singleton == null) {
            singleton = new DBHelper(context);
        }
        singleton.context = context;
        return singleton;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + TableName + "("
                + KeyID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRO_IMAGE + " BLOB ,"
                + ProductName + " TEXT NOT NULL,"
                + ProductRP + " INTEGER NOT NULL,"
                + P_Qty + "  INTEGER NOT NULL,"
                + P_Qty_d + " FLOAT,"
                + P_WHOLESALE_P + " FLOAT NOT NULL,"
                + P_WHOLESALE_d + " FLOAT NOT NULL,"
                + Discount + " INTEGER NOT NULL,"
                + Scheme + " INTEGER NOT NULL,"
                + Coupon + " TEXT NOT NULL,"
                + NETAmountP + " FLOAT NOT NULL,"
                + NETAmountD + " FLOAT NOT NULL,"
                + EXTRADIS1 + " INTEGER,"
                + EXTRADIS2 + " INTEGER,"
                + EXTRADIS3 + " INTEGER,"
                + CITY + " TEXT,"

                + FINALAMOUNTP + "  FLOAT NOT NULL,"
                + FINALAMOUNTd + "  FLOAT NOT NULL,"
                + COMPANY_NAME + " TEXT NOT NULL"+" )";

        String Company_Table_Query = "CREATE TABLE "+Company_Table+"("
                +COMPANY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COMPANY_NAME + " TEXT NOT NULL"+")";


        db.execSQL(Company_Table_Query);
        db.execSQL(CREATE_TABLE_QUERY);

        Toast.makeText(context, "Table Created Successfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void companyinset(Companymodel companymodel){
        try{
            SQLiteDatabase dbsql = this.getWritableDatabase();
            ContentValues cv= new ContentValues();
            cv.put(COMPANY_NAME,companymodel.getComapny_name());
            long checkqueryrun = dbsql.insertOrThrow(Company_Table, null, cv);
            if(checkqueryrun != -1){
                Toast.makeText(context, "Data Insert Successfully", Toast.LENGTH_SHORT).show();
                dbsql.close();
            }
            else {
                Toast.makeText(context, "Data inserted failed", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ee){
            Toast.makeText(context, ee.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void storedata(ModelAddProduct objectmodel){
        try{
            SQLiteDatabase objsqlite = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            Bitmap imagetostorebitmap = objectmodel.getPro_images();

            objstream = new ByteArrayOutputStream();
            imagetostorebitmap.compress(Bitmap.CompressFormat.JPEG,100,objstream);

            imageinbytes = objstream.toByteArray();

            contentValues.put(ProductName,objectmodel.getPro_name());
            contentValues.put(PRO_IMAGE,imageinbytes);
            contentValues.put(ProductRP,objectmodel.getRetail_price());
            contentValues.put(P_Qty,objectmodel.getQuantity_packet());
            contentValues.put(P_Qty_d,objectmodel.getQuantity_Darzan());
            contentValues.put(P_WHOLESALE_P, objectmodel.getWhole_price_drzan());
            contentValues.put(P_WHOLESALE_d, objectmodel.getWhole_prize_cartoon());
            contentValues.put(Discount , objectmodel.getDiscount_pice());
            contentValues.put(Scheme , objectmodel.getScheme());
            contentValues.put(Coupon,objectmodel.getCoupen());
            contentValues.put(NETAmountP,objectmodel.getNetAmount_p());
            contentValues.put(NETAmountD,objectmodel.getNetAmount_d());
            contentValues.put(EXTRADIS1,objectmodel.getExtra_discount1());
            contentValues.put(EXTRADIS2,objectmodel.getExtra_discount2());
            contentValues.put(EXTRADIS3,objectmodel.getExtra_discount3());
            contentValues.put(CITY, objectmodel.getCity());
            contentValues.put(FINALAMOUNTP,objectmodel.getFinalamount_p());
            contentValues.put(FINALAMOUNTd,objectmodel.getFinalamount_d());
            contentValues.put(COMPANY_NAME,objectmodel.getCompany_name());


            long checkqueryrun = objsqlite.insertOrThrow(TableName, null, contentValues);
             if(checkqueryrun != -1){
              Toast.makeText(context, "Data insert Successfully", Toast.LENGTH_SHORT).show();
              objsqlite.close();
                }
             else {
                 Toast.makeText(context, "Data inserted failed", Toast.LENGTH_SHORT).show();
                }

        }
        catch (Exception uu){
            Toast.makeText(context, uu.getMessage(), Toast.LENGTH_SHORT).show();
        }
        }

        public ArrayList<Companymodel> getAllCompany(){
        ArrayList<Companymodel> clist = new ArrayList<Companymodel>();
            String selectQuery = "SELECT  * FROM " + Company_Table;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do{
                    Companymodel companymodel= new Companymodel(
                            cursor.getString(1)
                    );
                    clist.add(companymodel);
                }while (cursor.moveToNext());
        }
        db.close();
        return clist;
}
public Cursor getCompanyforContext(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM " + Company_Table,null);
        return res;
}

    public ArrayList<ModelAddProduct> getAllUsers()
    {
        ArrayList<ModelAddProduct> mList = new ArrayList<ModelAddProduct>();
        String selectQuery = "SELECT  * FROM " + TableName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                byte[] imagebytes = cursor.getBlob(1);
                Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes,0 ,imagebytes.length);

                ModelAddProduct user = new ModelAddProduct(
                       // Integer.parseInt(cursor.getString(0)),
                        objectbitmap,
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getFloat(5),
                        cursor.getFloat(6),
                        cursor.getFloat(7),
                        cursor.getFloat(8),
                        cursor.getFloat(9),
                        cursor.getString(10),
                        cursor.getFloat(11),
                        cursor.getFloat(12),
                        cursor.getInt(13),
                        cursor.getInt(14),
                        cursor.getInt(15),
                        cursor.getString(16),
                        cursor.getFloat(17),
                        cursor.getFloat(18),
                        cursor.getString(19));

                mList.add(user);
            } while (cursor.moveToNext());
        }
        db.close();
        return mList;

    }

    public ArrayList<ModelAddProduct> search(String productName) {
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqldata = {KeyID,PRO_IMAGE,ProductName,ProductRP,
                P_Qty,P_Qty_d,P_WHOLESALE_P,P_WHOLESALE_d,Discount,
                Scheme,Coupon,NETAmountP, NETAmountD, EXTRADIS1, EXTRADIS2,
                EXTRADIS3, CITY, FINALAMOUNTP, FINALAMOUNTd};

        qb.setTables(TableName);

        Cursor cursor = qb.query(db,sqldata,"Product_Name LIKE ?",new String[]{"%"+productName+"%"},null,null,null);

        ArrayList<ModelAddProduct> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                byte[] imagebytes = cursor.getBlob(1);
                Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.length);

                ModelAddProduct aa = new ModelAddProduct(
                        objectbitmap,
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getFloat(5),
                        cursor.getFloat(6),
                        cursor.getFloat(7),
                        cursor.getFloat(8),
                        cursor.getFloat(9),
                        cursor.getString(10),
                        cursor.getFloat(11),
                        cursor.getFloat(12),
                        cursor.getInt(13),
                        cursor.getInt(14),
                        cursor.getInt(15),
                        cursor.getString(16),
                        cursor.getFloat(17),
                        cursor.getFloat(18),
                        cursor.getString(19));

result.add(aa);

            }while (cursor.moveToNext());
        }return result;

        //String query = "Select * From Products WHERE Pro_id='"+a+"'" ;
//        Cursor cursor =
//                db.rawQuery("SELECT * FROM " + TableName
//                                + " WHERE " + ProductName + "=?",
//                        new String[] {"Dajaj"} );
        //Cursor  cursor = db.rawQuery(query,null);

//        if (cursor != null)
//            cursor.moveToFirst();
//        else{
//            return null;
//        }
//        byte[] imagebytes = cursor.getBlob(1);
//        Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes,0 ,imagebytes.length);
//
//        return new ModelAddProduct(
//
//        // Integer.parseInt(cursor.getString(0)),
//                objectbitmap,
//                cursor.getString(2),
//                cursor.getInt(3),
//                cursor.getInt(4),
//                cursor.getFloat(5),
//                cursor.getFloat(6),
//                cursor.getFloat(7),
//                cursor.getFloat(8),
//                cursor.getFloat(9),
//                cursor.getString(10),
//                cursor.getFloat(11),
//                cursor.getFloat(12),
//                cursor.getInt(13),
//                cursor.getInt(14),
//                cursor.getInt(15),
//                cursor.getString(16),
//                cursor.getFloat(17),
//                cursor.getFloat(18));
//
    }


    public ArrayList<ModelAddProduct> search_pro_b_c(String companyname) {
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqldata = {KeyID,PRO_IMAGE,ProductName,ProductRP,
                P_Qty,P_Qty_d,P_WHOLESALE_P,P_WHOLESALE_d,Discount,
                Scheme,Coupon,NETAmountP, NETAmountD, EXTRADIS1, EXTRADIS2,
                EXTRADIS3, CITY, FINALAMOUNTP, FINALAMOUNTd,COMPANY_NAME};

        qb.setTables(TableName);

        Cursor cursor = qb.query(db,sqldata,"Company LIKE ?",new String[]{"%"+companyname+"%"},null,null,null);

        ArrayList<ModelAddProduct> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                byte[] imagebytes = cursor.getBlob(1);
                Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.length);

                ModelAddProduct aa = new ModelAddProduct(
                        objectbitmap,
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getFloat(5),
                        cursor.getFloat(6),
                        cursor.getFloat(7),
                        cursor.getFloat(8),
                        cursor.getFloat(9),
                        cursor.getString(10),
                        cursor.getFloat(11),
                        cursor.getFloat(12),
                        cursor.getInt(13),
                        cursor.getInt(14),
                        cursor.getInt(15),
                        cursor.getString(16),
                        cursor.getFloat(17),
                        cursor.getFloat(18),
                        cursor.getString(19));

                result.add(aa);

            }while (cursor.moveToNext());
        }return result;

        //String query = "Select * From Products WHERE Pro_id='"+a+"'" ;
//        Cursor cursor =
//                db.rawQuery("SELECT * FROM " + TableName
//                                + " WHERE " + ProductName + "=?",
//                        new String[] {"Dajaj"} );
        //Cursor  cursor = db.rawQuery(query,null);

//        if (cursor != null)
//            cursor.moveToFirst();
//        else{
//            return null;
//        }
//        byte[] imagebytes = cursor.getBlob(1);
//        Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes,0 ,imagebytes.length);
//
//        return new ModelAddProduct(
//
//        // Integer.parseInt(cursor.getString(0)),
//                objectbitmap,
//                cursor.getString(2),
//                cursor.getInt(3),
//                cursor.getInt(4),
//                cursor.getFloat(5),
//                cursor.getFloat(6),
//                cursor.getFloat(7),
//                cursor.getFloat(8),
//                cursor.getFloat(9),
//                cursor.getString(10),
//                cursor.getFloat(11),
//                cursor.getFloat(12),
//                cursor.getInt(13),
//                cursor.getInt(14),
//                cursor.getInt(15),
//                cursor.getString(16),
//                cursor.getFloat(17),
//                cursor.getFloat(18));
//
    }

}
