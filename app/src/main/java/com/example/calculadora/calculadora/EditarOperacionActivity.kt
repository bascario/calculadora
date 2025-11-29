package com.example.calculadora.calculadora

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.calculadora.Database.CalculadoraDBHelper
import com.example.calculadora.calculadora.Database.OperacionDAO
import com.example.calculadora.calculadora.databinding.ActivityEditarOperacionBinding

class EditarOperacionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditarOperacionBinding
    private lateinit var dao: OperacionDAO
    private var idOperacion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditarOperacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = OperacionDAO(this)
        idOperacion = intent.getIntExtra("id", 0)
        cargarDatos()
        binding.btnGuardar.setOnClickListener {
            guardarCambios()
        }
    }

    private fun cargarDatos() {
        val helper = CalculadoraDBHelper(this)
        val db = helper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT descripcion, fecha FROM operaciones WHERE id=?",
            arrayOf(idOperacion.toString())
        )
        if (cursor.moveToFirst()) {
            binding.txtDescripcion.setText(cursor.getString(0))
            binding.txtFecha.setText(cursor.getString(1))
        }
        cursor.close()
        db.close()
    }

    private fun guardarCambios() {
        val descripcion = binding.txtDescripcion.text.toString()
        val fecha = binding.txtFecha.text.toString()
        if (descripcion.isBlank() || fecha.isBlank()) {
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        dao.actualizarOperacion(idOperacion, descripcion, fecha)
        Toast.makeText(this, "Operación actualizada", Toast.LENGTH_SHORT).show()
        finish()
    }
}