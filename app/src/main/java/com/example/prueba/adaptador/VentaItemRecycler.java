package com.example.prueba.adaptador;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba.R;
import com.example.prueba.data.modelo.ProductoVenta;

import java.util.List;

public class VentaItemRecycler extends RecyclerView.Adapter<VentaItemRecycler.ViewHolderVenta>{
    List<ProductoVenta> listaProductoVenta;
    EditText totalVenta;

    public VentaItemRecycler(List<ProductoVenta> listaProductoVenta, EditText totalVenta) {
        this.listaProductoVenta = listaProductoVenta;
        this.totalVenta = totalVenta;
    }

    class ViewHolderVenta extends RecyclerView.ViewHolder{
        EditText cantidad, precio;
        TextView nombre, totalProducto;
        ImageView imagen;
        Button quitar;
        public ViewHolderVenta(View itemView) {
            super(itemView);
            cantidad = itemView.findViewById(R.id.rivEtCantidad);
            precio = itemView.findViewById(R.id.rivEtPrecio);
            nombre = itemView.findViewById(R.id.rivTvNombre);
            totalProducto = itemView.findViewById(R.id.rivTvTotal);
            imagen = itemView.findViewById(R.id.rivCivImagen);
            quitar = itemView.findViewById(R.id.rivBQuitar);
            cantidad.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    modicarValores(cantidad, precio, totalProducto, getAdapterPosition());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            precio.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    modicarValores(cantidad, precio, totalProducto, getAdapterPosition());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            quitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quitar(getAdapterPosition());
                }
            });
        }

        void bind(final ProductoVenta productoVenta){

            cantidad.setText(String.valueOf(productoVenta.getProd_cantidad()));
            precio.setText(String.valueOf(productoVenta.getProd_precio()));
            totalProducto.setText(String.valueOf(productoVenta.getProd_precio()));
            nombre.setText(productoVenta.getProd_nombre());

        }
    }

    @NonNull
    @Override
    public VentaItemRecycler.ViewHolderVenta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_venta, parent, false);
        return new ViewHolderVenta(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VentaItemRecycler.ViewHolderVenta holder, int position) {
        holder.bind(listaProductoVenta.get(position));
    }

    @Override
    public int getItemCount() {
        return listaProductoVenta.size();
    }

    private void quitar(int adapterPosition) {
        listaProductoVenta.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        sumarLista();
    }

    public void sumarLista() {

        Double temp = 0.0;
        for (ProductoVenta item : listaProductoVenta){
            temp += item.getProd_total();
        }
        totalVenta.setText(String.valueOf(temp));
    }

    private void modicarValores(EditText cantidad, EditText precio, TextView totalProducto, int adapterPosition) {

        if (cantidad.getText().length() > 0){
            if (precio.getText().length() > 0){

                int cant = Integer.parseInt(cantidad.getText().toString());
                Double pre = Double.parseDouble(precio.getText().toString());

                totalProducto.setText(String.valueOf( cant * pre));
                listaProductoVenta.get(adapterPosition).setProd_cantidad(cant);
                listaProductoVenta.get(adapterPosition).setProd_precio_venta(pre);
                listaProductoVenta.get(adapterPosition).setProd_total(cant * pre);
                sumarLista();
            }
        }

    }
}
