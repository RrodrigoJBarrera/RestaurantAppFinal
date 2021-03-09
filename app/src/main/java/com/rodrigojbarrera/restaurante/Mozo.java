package com.rodrigojbarrera.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Mozo extends AppCompatActivity {

    ProductosAdapter productosAdapter;
    Producto producto;
    DataBase db;
    ListView listView;
    ArrayList<Producto> lv_productos;
    Button btnEnviarPedido;
    TextView txtImporteTotal;
    EditText eTxtMozo, eTxtMesa;
    String getMesa, getMozo;
    ArrayList<String> arrayPedidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mozo);
        db = new DataBase(Mozo.this);

        listView = findViewById(R.id.listConsumibles);
        btnEnviarPedido = findViewById(R.id.btnEnviarPedido);
        txtImporteTotal = findViewById(R.id.txtImporteTotal);
        eTxtMesa = findViewById(R.id.etxtMesa);
        eTxtMozo = findViewById(R.id.etxtMozo);

        lv_productos = db.listaProductos();
        productosAdapter = new ProductosAdapter(this, R.layout.item_list_productos, lv_productos);
        listView.setAdapter(productosAdapter);


        btnEnviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValores();

            }
        });

    }


    public void getValores() {
        View parentView = null;
        int resultado = 0;
        int totalPrecio;

        getMesa = eTxtMesa.getText().toString();
        getMozo = eTxtMozo.getText().toString();
        arrayPedidos = new ArrayList<String>();

        if (getMesa.isEmpty()) {
            Toast.makeText(this, "Debe Ingresar el Nº de Mesa", Toast.LENGTH_SHORT).show();
            return;
        }

        if (getMozo.isEmpty()) {
            Toast.makeText(this, "Debe Ingresar el nombre del Mozo", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < lv_productos.size(); i++) {
            parentView = getPosicionVista(i, listView);
            String getCantidad = ((TextView) parentView.findViewById(R.id.txtCant)).getText().toString();
            String getPedido = ((TextView) parentView.findViewById(R.id.txtDesc)).getText().toString();

            if (getCantidad.contains("0")) {

            } else {
                String getPrecio = ((TextView) parentView.findViewById(R.id.txtTotal)).getText().toString();
                totalPrecio = Integer.parseInt(getPrecio.substring(1));
                resultado = resultado + totalPrecio;

                arrayPedidos.add(getCantidad + " " + getPedido + " ");

            }

        }
        if (arrayPedidos.isEmpty()) {
            Toast.makeText(this, "Debe ingresar al menos un producto", Toast.LENGTH_SHORT).show();
            return;
        }

        arrayPedidos.add("Total: $" + resultado);
        Pedido p = new Pedido(getMesa, getMozo, arrayPedidos.toString().trim(), 0);
        db.insertPedido(p);
        Toast.makeText(this, "Pedido Agregado!", Toast.LENGTH_SHORT).show();
        txtImporteTotal.setText(String.valueOf("Importe Total: $" + resultado));

    }


    public View getPosicionVista(int pos, ListView listView) {
        final int primertItem = listView.getFirstVisiblePosition();
        final int ultimoItem = primertItem
                + listView.getChildCount() - 1;

        if (pos < primertItem || pos > ultimoItem) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int itemIndex = pos - primertItem;
            return listView.getChildAt(itemIndex);
        }
    }
}