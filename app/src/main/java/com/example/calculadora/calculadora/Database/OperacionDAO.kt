package com.example.calculadora.calculadora.Database
import android.content.ContentValues
import android.content.Context
/* DAO (Data Access Object) encapsular toda la logica del CRUD
permite trabajar las operaciones sin exponer detalles de SQLIte
* */
class OperacionDAO(context: Context) {
    private val dbHelper = CalculadoraDBHelper(context)
    //insertar
    fun insertarOperacion(descripcion: String, fecha: String){
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("descripcion", descripcion)
            put("fecha", fecha)
        }
        db.insert("operaciones", null, values)
        db.close()
        }
    //listar
    fun obtenerOperaciones(): List<Operacion> {
        val lista = mutableListOf<Operacion>()
        val db = dbHelper.readableDatabase
        val cursor=db.rawQuery("SELECT * FROM operaciones ORDER BY id DESC", null)
        if(cursor.moveToFirst()){
            do{
                lista.add(
                    Operacion(
                        id=cursor.getInt(0),
                        descripcion=cursor.getString(1),
                        fecha=cursor.getString(2)
                    )
                )
            }while(cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return lista
    }
    //actualizar
    fun actualizarOperacion(id:Int, descripcion: String, fecha: String):Int{
     val db = dbHelper.writableDatabase
     val values = ContentValues()
     values.put("descripcion", descripcion)
     values.put("fecha", fecha)
     //update de la cantidad de registros afectados
        val row = db.update(
            "operaciones",
            values,
            "id=?", arrayOf(id.toString()))
        db.close()
        return row
    }
    //eliminar
    fun eliminarOperacion(id:Int):Int{
        val db = dbHelper.writableDatabase

        //delete retorna la cantidad de filas afectadas
        val row = db.delete(
            "operaciones",
            "id=?",
            arrayOf(id.toString()))
        db.close()
        return row
    }
}