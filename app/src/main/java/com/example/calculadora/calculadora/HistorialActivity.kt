package com.example.calculadora.calculadora

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculadora.calculadora.Database.Operacion
import com.example.calculadora.calculadora.Database.OperacionDAO
import com.example.calculadora.calculadora.databinding.ActivityHistorialBinding

class HistorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistorialBinding
    private lateinit var dao: OperacionDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        dao = OperacionDAO(this)
        cargarLista()
    }

    private fun cargarLista() {
        val lista = dao.obtenerOperaciones()
        binding.rvHistorialDB.layoutManager = LinearLayoutManager(this)
        binding.rvHistorialDB.adapter = HistorialAdapter(lista,
            onItemClick = { operacion -> abrirEditor(operacion) },
            onItemLongClick = { operacion -> confirmarEliminacion(operacion) }
        )
    }

    private fun abrirEditor(op: Operacion) {
        val intent = Intent(this, EditarOperacionActivity::class.java)
        intent.putExtra("id", op.id)
        startActivity(intent)
    }

    private fun confirmarEliminacion(op: Operacion) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar la eliminación")
            .setMessage("¿Seguro que desea eliminar la operación?")
            .setPositiveButton("Eliminar") { _, _ ->
                dao.eliminarOperacion(op.id)
                cargarLista()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun onResume() {
        super.onResume()
        cargarLista()
    }
}