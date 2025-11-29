package com.example.calculadora.calculadora.Database
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
/* Clase se encarga de crear y actualizacion de la base de datos SQLite
se ejecuta automaticamente al inicial la primera vez
* */
class CalculadoraDBHelper (context: Context) : SQLiteOpenHelper(context, "calculadora.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Tabla inicial con ID, descripción y fecha
        db?.execSQL("""
            CREATE TABLE operaciones(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                descripcion TEXT NOT NULL,
                fecha TEXT NOT NULL
            )
        """)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //aun no preparado
    }
}