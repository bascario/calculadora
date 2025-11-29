package com.example.calculadora.calculadora

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora.calculadora.ui.theme.CalculadoraTheme

class EditarOperacionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditarOperacionBinding
    private lateinit var dao: OperacionDao
    private var idOperacion=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarOperacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = OperacionDAO(this)
        IdOperacion = intent.getIntExtra("id", 0)
        cargarDatos()
        binding.btnGuardar.setOnClickListener {
            guardar()
        }

    }

    private fun cargarDatos() {
        val helper = CalculadoraDbHelper(this)
        val db: SQLiteDatabase=helper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT descripcion, fecha FROM operaciones WHERE id=?",
            arrayOf(idOperacion.toString())
        )
        cursor.close()
        db.close()
    }
    private fun guardarCambios(){
        val descripcion = binding.txtDescripcion.text.toString()
        val fecha = binding.txtFecha.text.toString()
        if(descripcion.isBlank() || fecha.isBlank()) {
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        dao.actualizarOperacion(idOperacion, descripcion, fecha)
        Toast.makeText(this, "Operación actualizada", Toast.LENGTH_SHORT).show()
        finish()
    }
}