package com.rodrigojbarrera.restaurante;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    public static final int VERSION_BDD = 1;
    public static final String NOMBRE_BDD = "restaurante.db";
    public static final String TABLA_PRODUCTOS = "CREATE TABLE productos(id INTEGER PRIMARY KEY " +
            "autoincrement, descripcion TEXT, precio REAL, cantidad INTEGER, total INTEGER)";
    public static final String TABLA_PEDIDOS = "CREATE TABLE pedidos (idPedido INTEGER PRIMARY KEY autoincrement, mesa TEXT, " +
            "mozo TEXT, descripcionPedido TEXT," +
            "estadoPedido INTEGER)";

    public DataBase(Context context) {
        super(context, NOMBRE_BDD, null, VERSION_BDD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_PRODUCTOS);
        db.execSQL(TABLA_PEDIDOS);

        ContentValues Comida1 = new ContentValues();

        Comida1.put("descripcion", "Pollo Frito");
        Comida1.put("precio", 150);
        Comida1.put("cantidad", 0);

        db.insert("productos", null, Comida1);

        ContentValues Comida2 = new ContentValues();

        Comida2.put("descripcion", "Pollo Clasico");
        Comida2.put("precio", 150);
        Comida2.put("cantidad", 0);

        db.insert("productos", null, Comida2);

        ContentValues Comida3 = new ContentValues();

        Comida3.put("descripcion", "Pollo Chileno");
        Comida3.put("precio", 150);
        Comida3.put("cantidad", 0);

        db.insert("productos", null, Comida3);
        ContentValues Bebida1 = new ContentValues();

        Bebida1.put("descripcion", "Coca Cola");
        Bebida1.put("precio", 80);
        Bebida1.put("cantidad", 0);

        db.insert("productos", null, Bebida1);

        ContentValues Bebida2 = new ContentValues();

        Bebida2.put("descripcion", "Sprite");
        Bebida2.put("precio", 80);
        Bebida2.put("cantidad", 0);

        db.insert("productos", null, Bebida2);

        ContentValues Bebida3 = new ContentValues();

        Bebida3.put("descripcion", "Fanta");
        Bebida3.put("precio", 80);
        Bebida3.put("cantidad", 0);

        db.insert("productos", null, Bebida3);

        ContentValues Guarnicion1 = new ContentValues();

        Guarnicion1.put("descripcion","Papas Fritas");
        Guarnicion1.put("precio",120);
        Guarnicion1.put("cantidad",0);

        db.insert("productos",null, Guarnicion1);

        ContentValues Guarnicion2 = new ContentValues();

        Guarnicion2.put("descripcion","Ensalada");
        Guarnicion2.put("precio",120);
        Guarnicion2.put("cantidad",0);

        db.insert("productos",null, Guarnicion2);

        ContentValues Guarnicion3 = new ContentValues();

        Guarnicion3.put("descripcion","Mixto");
        Guarnicion3.put("precio",120);
        Guarnicion3.put("cantidad",0);


        db.insert("productos",null, Guarnicion3);


    }

   public void insertPedido(Pedido pedido){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("mesa", pedido.getMesa());
        values.put("mozo", pedido.getMozo());
        values.put("descripcionPedido", pedido.getDescripcionPedido());
        values.put("estadoPedido", pedido.getEstadoPedido());

        sqLiteDatabase.insert("pedidos", null, values);

    }

    public void updatePedido(Pedido pedido, int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("estadoPedido", pedido.getEstadoPedido());

        sqLiteDatabase.update("pedidos", values, "idPedido=" + id, null);

    }

    public ArrayList<Producto> listaProductos() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select descripcion, precio, cantidad, total From productos", null);
        ArrayList<Producto> productosList = new ArrayList<Producto>();
        while (cursor.moveToNext()) {
            productosList.add(new Producto(cursor.getString(0),
                    cursor.getInt(1), cursor.getInt(2),
                    cursor.getInt(3)));
        }
        cursor.close();
        db.close();
        return productosList;
    }

    public ArrayList<Pedido> listaPedidos() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select idPedido, mesa, mozo, descripcionPedido, estadoPedido From pedidos Order by mesa Asc", null);
        ArrayList<Pedido> pedidosList = new ArrayList<Pedido>();
        while (cursor.moveToNext()) {
            pedidosList.add(new Pedido(cursor.getInt(0) , cursor.getString(1),
                    cursor.getString(2)
                    , cursor.getString(3), cursor.getInt(4)));
        }
        cursor.close();
        db.close();
        return pedidosList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
