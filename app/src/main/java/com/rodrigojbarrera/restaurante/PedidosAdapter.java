package com.rodrigojbarrera.restaurante;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class PedidosAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Pedido> pedidos;
    private int layout;
    static int contador;
    private boolean click;

    public PedidosAdapter(Context context, int layout, ArrayList<Pedido> pedidos) {
        super();
        this.context = context;
        this.pedidos = pedidos;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return this.pedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.pedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private class ViewHolder {
        TextView txtDescPedido, txtEstadoPedido, txtMesa, txtMozo, txtIdPedido;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = new ViewHolder();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtDescPedido = view.findViewById(R.id.txtDescripcionPedido);
            holder.txtEstadoPedido = view.findViewById(R.id.txtEstadoPedido);
            holder.txtMesa = view.findViewById(R.id.txtMesaPedido);
            holder.txtMozo = view.findViewById(R.id.txtMozoPedido);
            holder.txtIdPedido = view.findViewById(R.id.txtIdPedido);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final Pedido pedido = pedidos.get(position);


        holder.txtIdPedido.setText(String.valueOf(pedido.getIdPedido()));
        holder.txtDescPedido.setText(pedido.getDescripcionPedido());
        holder.txtMesa.setText("Mesa nº: " + pedido.getMesa());
        holder.txtMozo.setText("Mozo: "+pedido.getMozo());


        int estadoPedido = pedido.getEstadoPedido();

        switch (estadoPedido){
            case 0:
                holder.txtEstadoPedido.setText("En Preparacion");
                holder.txtEstadoPedido.setBackgroundColor(Color.RED);
                break;
            case 1:
                holder.txtEstadoPedido.setText("Listo");
                holder.txtEstadoPedido.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                holder.txtEstadoPedido.setText("Entregado");
                holder.txtEstadoPedido.setBackgroundColor(Color.BLUE);
                break;
        }

        final ViewHolder finalHolder = holder;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int estadoPedido = pedido.getEstadoPedido();

                switch (estadoPedido){
                    case 0:
                        finalHolder.txtEstadoPedido.setText("Listo");
                        finalHolder.txtEstadoPedido.setBackgroundColor(Color.GREEN);
                        pedido.setEstadoPedido(1);
                        break;
                    case 1:
                        finalHolder.txtEstadoPedido.setText("Entregado");
                        finalHolder.txtEstadoPedido.setBackgroundColor(Color.BLUE);
                                                pedido.setEstadoPedido(2);
                        break;
                    case 2:
                        finalHolder.txtEstadoPedido.setText("En Preparacion");
                        finalHolder.txtEstadoPedido.setBackgroundColor(Color.RED);
                        pedido.setEstadoPedido(0);
                        break;
                }

            }
        });



        return view;
    }
}
