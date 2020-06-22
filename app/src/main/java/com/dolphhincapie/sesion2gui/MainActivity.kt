package com.dolphhincapie.sesion2gui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var fecha: String
    private var cal = Calendar.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("OnCreate", "ok")

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                val format = "dd/MM/yy"
                val simpleDateFormat = SimpleDateFormat(format, Locale.US)
                fecha = simpleDateFormat.format(cal.time).toString()
                tv_calendar.text = fecha
            }

        ib_calendar.setOnClickListener {
            DatePickerDialog( this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        bt_guardar.setOnClickListener {
            val nombre = et_nombre.text.toString()
            val correo = et_correo.text.toString()
            val contrasena = et_contrasena.text.toString()
            val repContra = et_repitacontrasena.text.toString()
            val genero = if(rb_masculino.isChecked) "Masculino" else "Femenino"
            var pasatiempos = ""
            val ciudadNacimiento = s_ciudades.selectedItem.toString()
            val fechanaci = tv_calendar.text.toString()


            if (cb_cine.isChecked) pasatiempos = "$pasatiempos Cine"
            if (cb_videojuegos.isChecked) pasatiempos = "$pasatiempos VideoJuegos"
            if (cb_series.isChecked) pasatiempos = "$pasatiempos Series"
            if (cb_deportes.isChecked) pasatiempos = "$pasatiempos Deportes"


            if(nombre.isEmpty()){
                Toast.makeText(this, "Campo Nombre Vacio", Toast.LENGTH_LONG).show()
            }
            else if (correo.isEmpty() || "@" !in correo){
                Toast.makeText(this, "Campo Correo Invalido", Toast.LENGTH_LONG).show()
            }
            else if (contrasena.isEmpty()){
                Toast.makeText(this, "Campo Contraseña Vacio", Toast.LENGTH_LONG).show()
            }
            else if (repContra.isEmpty()){
                Toast.makeText(this, "Campo Rep Contraseña Vacio", Toast.LENGTH_LONG).show()
            }
            else if (fechanaci == "dd/MM/yy"){
                Toast.makeText(this, "Campo Fecha Vacio", Toast.LENGTH_LONG).show()
            }
            else {
                if (contrasena == repContra){
                    tv_result.text = "\nNombre: $nombre " +
                            "\nCorreo: $correo " +
                            "\nFecha de Nacimiento: $fecha " +
                            "\nCiudad de Nacimiento: $ciudadNacimiento " +
                            "\nGenero: $genero " +
                            "\nPasatiempos:$pasatiempos \n"
                }
                else{
                    Toast.makeText(this, "Contraseñas diferentes", Toast.LENGTH_LONG).show()
                }
            }

        }

    }
}