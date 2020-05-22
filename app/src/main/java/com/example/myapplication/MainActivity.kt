package com.example.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.model.Consult
import com.example.myapplication.repository.rest.BaseObserver
import com.example.myapplication.viewmodel.ConsultViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() , BaseObserver<Consult> {

    private var date: Calendar? = null
    private var fechInicial : Date? = null
    private var fechFinal : Date? = null
    private var sdf : SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    private var inicial = true



    private lateinit var consultViewModel: ConsultViewModel

    override fun onStart() {
        super.onStart()
        this.runOnUiThread{ consultViewModel = ViewModelProviders.of(this).get(ConsultViewModel::class.java)}
        consultViewModel.mutableData.observe(this, this)



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

    }

    private fun initViews() {

        textimp_fechinicial.setOnClickListener { showDatePickerDialog(edit_fec_inical,true) }
        textimp_fechinfinal.setOnClickListener { showDatePickerDialog(edit_fec_final,false) }
        edit_fec_inical.setOnClickListener { showDatePickerDialog(edit_fec_inical,true) }
        edit_fec_final.setOnClickListener { showDatePickerDialog(edit_fec_final,false) }
        btnconsultar.setOnClickListener { generateR() }








    }

    private fun generateR() {
        if (fechInicial != null && fechFinal !=null){

            var i =  fechInicial!!.time
            var f =  fechFinal!!.time


            if (i  > f){
                var messa = "La Fecha inicial es mayor a la Final"
                showerrorDialog(messa,"Error")
            }else if ( f< System.currentTimeMillis()){
                var messa = "La Fecha Final debe ser mayor"
                showerrorDialog(messa,"Error")
            }else{
                succes()

            }



        }else{

            var messa = "Falta alguna fecha"
            showerrorDialog(messa,"Error")
        }
    }

    private fun succes() {
        consultViewModel.getConstul( edit_fec_inical.text.toString().replace(" ",""),edit_fec_final.text.toString().replace(" ",""))

    }

    private fun showerrorDialog(messa: String,title: String) {


        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle(title)
        builder.setMessage(messa)
        builder.setPositiveButton("Aceptar"){dialog, which ->
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar"){dialog,which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
        }

    private fun showDatePickerDialog(edt : EditText , inicial :Boolean) {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val selectedDate :String
            if (month<9)
                selectedDate= year.toString() + " - 0" + (month + 1) + " - " + day.toString()
            else
                selectedDate = year.toString() + " - " + (month + 1) + " - " + day.toString()

            edt.setText(selectedDate)
            if (inicial)
                fechInicial = sdf.parse(day.toString()+"/"+ (month + 1) +"/"+year.toString())
            else
                fechFinal = sdf.parse(day.toString()+"/"+ (month + 1) +"/"+year.toString())


        })
        newFragment.show(supportFragmentManager, "datePicker")
    }

    override fun inProgress(info: String) {

    }

    override fun onError(error: String) {

    }

    override fun onChanged(t: Consult?) {

    }


}
