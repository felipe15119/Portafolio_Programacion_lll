package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.prueba.adaptador.ClienteItemRecycler;
import com.example.prueba.data.modelo.VentaCabecera;
import com.example.prueba.esquemaSqlite.crud.Select;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends AppCompatActivity {
    //region variables con la instancia de controles
  @BindView(R.id.acRvCliente)
    RecyclerView recyclerView;

 @BindView(R.id.acEtBuscarCliente)
    EditText buscador;

//objetos utiles para la carga del recycler
    RecyclerView.LayoutManager layoutManager;
    //objeto para la personalizacion del item del recycler
    ClienteItemRecycler adaptador;
    //lista de objetos clientes
    List<com.example.prueba.data.modelo.Cliente> listaCliente = new ArrayList<com.example.prueba.data.modelo.Cliente>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        cargarLista();
        //interaccion con el buscador
        buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cargarLista();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void clickNuevoCliente(){
        irActivity(true, new Cliente());
    }

    private void cargarLista() {

        Select.seleccionarClientes(getApplicationContext(), listaCliente, buscador.getText().toString());

        cargarRecycler(listaCliente);
    }

    private void cargarRecycler(List<com.example.prueba.data.modelo.Cliente> listaCliente) {

        adaptador = new ClienteItemRecycler(listaCliente, new ClienteItemRecycler.OnItemClickListener() {
            @Override
            public void onItemClick(VentaCabecera cliente, int position) {
               irActivity(false, cliente);
            }
        });


    }

    private void irActivity(boolean bNuevo, Cliente cliente) {
        Intent intent = new Intent(getApplicationContext(), DetalleCliente.class);

        intent.putExtra("bNuevo", bNuevo);
        intent.putExtra("itemCliente", Cliente);

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            cargarLista();
        }
    }


    
}