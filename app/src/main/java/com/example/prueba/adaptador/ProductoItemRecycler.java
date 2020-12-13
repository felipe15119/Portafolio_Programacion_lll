package com.example.prueba.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba.R;
import com.example.prueba.data.modelo.Producto;

import java.util.List;

public class ProductoItemRecycler extends RecyclerView.Adapter<ProductoItemRecycler.ViewHolderProducto> {
    private List<Producto> listaProducto;
    private ClienteItemRecycler.OnItemClickListener itemClickListener;
    private Context context;

    public ProductoItemRecycler(List<Producto> listaProducto, ClienteItemRecycler.OnItemClickListener itemClickListener) {
        this.listaProducto = listaProducto;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_producto, parent, false);
        return new ViewHolderProducto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        holder.bind(listaProducto.get(position), (OnItemClickListener) itemClickListener);
    }

    public interface OnItemClickListener{
        void OnClickItem(Producto producto, int posicion);
    }

    @Override
    public int getItemCount() {
        return listaProducto.size();
    }

    class ViewHolderProducto extends RecyclerView.ViewHolder{
        TextView nombre, precio;
        LinearLayout llSeleccion;

        public ViewHolderProducto(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.ripTvNombre);
            precio = itemView.findViewById(R.id.ripTvPrecio);
            llSeleccion = itemView.findViewById(R.id.ripLLItemSeleccionado);
        }

        void bind(final Producto producto, final OnItemClickListener listener){
            nombre.setText(producto.getProd_nombre());
            precio.setText(String.valueOf(producto.getProd_precio()));


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnClickItem(producto, getAdapterPosition());
                }
            });
        }
    }
}
