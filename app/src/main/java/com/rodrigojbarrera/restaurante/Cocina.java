package com.rodrigojbarrera.restaurante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cocina extends AppCompatActivity {

    DataBase db;
    ArrayList<Pedido> lv_pedidos;
    PedidosAdapter pedidosAdapter;
    ListView listView;
    Button btnActualizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocina);

        db = new DataBase(Cocina.this);

        listView = findViewById(R.id.list_pedidos);
        btnActualizar = findViewById(R.id.btnActualizar);


        lv_pedidos = db.listaPedidos();
        pedidosAdapter = new PedidosAdapter(this, R.layout.item_list_pedidos, lv_pedidos);
        listView.setAdapter(pedidosAdapter);


        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValores();
                Toast.makeText(Cocina.this, "Cambios Realizados", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getValores() {
        View parentView = null;
        int checkestadoPedido = 0;
        String getId;
        String getMesaPedido;
        String getEstadoPedido;

        for (int i = 0; i < lv_pedidos.size(); i++) {
            parentView = getPosicionVista(i, listView);

            getMesaPedido = ((TextView) parentView.findViewById(R.id.txtMesaPedido)).getText().toString();
            getId = ((TextView) parentView.findViewById(R.id.txtIdPedido)).getText().toString();
            getEstadoPedido = ((TextView) parentView.findViewById(R.id.txtEstadoPedido)).getText().toString();


            if (getEstadoPedido.contains("En Preparacion")) {
                checkestadoPedido = 0;

            }
            if (getEstadoPedido.contains("Listo")) {
                checkestadoPedido = 1;

                mostrarNotificacion(getMesaPedido, Integer.parseInt(getId));

            }
            if (getEstadoPedido.contains("Entregado")) {

                checkestadoPedido = 2;
            }


            Pedido pedido = new Pedido(checkestadoPedido);
            pedido.setIdPedido(Integer.parseInt(getId));
            db.updatePedido(pedido, pedido.getIdPedido());
        }


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

    private void mostrarNotificacion(String mesa, int idNotificacion) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setContentTitle("Pedido Listo")
                .setContentText("La " + mesa + " tiene listo su pedido") // body message
                .setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(idNotificacion, mBuilder.build());
    }


}
