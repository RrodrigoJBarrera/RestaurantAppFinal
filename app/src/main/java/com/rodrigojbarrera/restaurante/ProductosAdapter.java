package com.rodrigojbarrera.restaurante;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductosAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> productos;
    private int layout;
    private int contador;
    static int total;
    static int getPrecio;


    public ProductosAdapter(Context context, int layout, ArrayList<Producto> productos) {
        super();
        this.context = context;
        this.productos = productos;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return this.productos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder {
        Button btnPlus, btnMin;
        TextView txtDesc, txtPrecio, txtCant, txtTotal;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = new ViewHolder();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.btnMin = view.findViewById(R.id.btnMin);
            holder.btnPlus = view.findViewById(R.id.btnPlus);
            holder.txtCant = view.findViewById(R.id.txtCant);
            holder.txtDesc = view.findViewById(R.id.txtDesc);
            holder.txtPrecio = view.findViewById(R.id.txtPrecio);
            holder.txtTotal = view.findViewById(R.id.txtTotal);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final Producto producto = productos.get(position);

        holder.txtPrecio.setText(String.valueOf("$" + producto.getPrecio()));
        if (producto.getTotal() == 0) {
            holder.txtTotal.setText(String.valueOf(""));
        } else {
            holder.txtTotal.setText(String.valueOf("$" + producto.getTotal()));
        }

        holder.txtDesc.setText(producto.getDescripcion());
        holder.txtCant.setText(String.valueOf(producto.getCantidad()));

        final ViewHolder finalHolder = holder;
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = producto.getCantidad();
                getPrecio = producto.getPrecio();


                contador = cantidad;
                contador += 1;
                producto.setCantidad(contador);

                total = contador * getPrecio;

                producto.setTotal(total);

                finalHolder.txtCant.setText(String.valueOf(contador));
                finalHolder.txtTotal.setText(String.valueOf("$" + producto.getTotal()));
            }

        });


        holder.btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = producto.getCantidad();
                getPrecio = producto.getPrecio();
                contador = cantidad;
                contador -= 1;
                if (contador < 0) {
                    contador = 0;
                }
                producto.setCantidad(contador);

                total = contador * getPrecio;

                producto.setTotal(total);
                finalHolder.txtCant.setText(String.valueOf(contador));
                if (total == 0) {
                    finalHolder.txtTotal.setText((String.valueOf("")));
                } else {
                    finalHolder.txtTotal.setText((String.valueOf("$" + total)));
                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            private int itemClick;

            @Override
            public void onClick(View v) {
                itemClick += 1;
                if (itemClick == 2) {
                    int cantidad = producto.getCantidad();
                    getPrecio = producto.getPrecio();


                    contador = cantidad;
                    contador += 1;
                    producto.setCantidad(contador);

                    total = contador * getPrecio;

                    producto.setTotal(total);

                    finalHolder.txtCant.setText(String.valueOf(contador));
                    finalHolder.txtTotal.setText(String.valueOf("$" + producto.getTotal()));
                    itemClick = 0;
                }

            }
        });

        return view;
    }
}
