package com.example.loginf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.preference.PreferenceManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.loginf.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var contentView:ActivityMainBinding
    lateinit var colaPeticion: RequestQueue

    lateinit var userDB:String;
    lateinit var passDB:String;

    lateinit var nUser:String;
    lateinit var lnUser:String;

    lateinit var avatarDB:String;

    var login:Int=1;//2==clave de usuarios

    //var intentosLogin:Int =0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       val prefManager= PreferenceManager.getDefaultSharedPreferences(this)

        if(prefManager.getBoolean("log",false))
        {

            val a= Intent(this,system::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE,prefManager.getString("user","user"))
                putExtra("avatar",prefManager.getString("foto","avatar"))
            }
            startActivity(a)
            finish()

        }

        colaPeticion= Volley.newRequestQueue(this)

        contentView= ActivityMainBinding.inflate(layoutInflater)

        setContentView(contentView.root)

        val btnAcces=contentView.btnIntro

        btnAcces.setOnClickListener { v->

            val nick=contentView.txtusernick.editText?.text.toString()

            if(login.equals(1)) {

                if (nick.isEmpty()) {
                   // Toast.makeText(this, "Ingrese su nombre de usuario", Toast.LENGTH_LONG).show()
                    myDialog("Debe ingresar un nombre de Usuario")

                 return@setOnClickListener
                }

                val url = "https://geniomaticrodas.edu.pe/resources/JsonObjectRequest.php?nick=${nick}"

                val query = JsonObjectRequest(
                    Request.Method.GET,
                    url, null,
                    Response.Listener { resp ->
                        Log.i("result", resp.toString())

                        if (resp.get("registros").equals(0)) {

                            myDialog("El usuario ingresado no existe")

                            return@Listener
                        }

                        contentView.txtpassuser.visibility = View.VISIBLE
                        contentView.txtpassuser.requestFocus()

                        userDB = nick
                        passDB = resp.get("pass").toString()
                        nUser=resp.get("name").toString()
                        lnUser=resp.get("lname").toString()
                        avatarDB = resp.get("profile").toString()

                        login = 2

                        val miImagen: ImageView? = contentView.avatar as ImageView?
                        val resImage = "https://geniomaticrodas.edu.pe/resources/${resp.get("profile")}"

                        miImagen?.setImage(resImage)

                    },
                    Response.ErrorListener { eResp ->
                        Log.i("result", "Error: ${eResp.message.toString()}")
                    }

                )

                colaPeticion.add(query)
            }

            else if(login.equals(2)){

                val pass=contentView.txtpassuser.editText?.text.toString()

                if(pass.isEmpty()){
                    myDialog("Debe ingresar su clave de usuario",1)
                    return@setOnClickListener
                }

                if(pass.equals(passDB)){

                   val manager=prefManager.edit()
                    manager.putBoolean("log",true)//
                    manager.putString("user","${nUser} ${lnUser}")
                    manager.putString("foto",avatarDB)
                    manager.apply()

                    val intent= Intent(this,system::class.java)

                    intent.putExtra(EXTRA_MESSAGE,"${nUser} ${lnUser}")
                    intent.putExtra("avatar",avatarDB)
                    startActivity(intent)
                    finish()

                }else{
                   myDialog("La clave ingresada es incorrecta, vuelva a intentar",1)
                }

            }


        }

    }

    fun ImageView.setImage(url: String){

        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_error_24)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(this)
           // this.setBackgroundResource(R.drawable.backLogin)

    }

    fun myDialog(message:String, activar:Int=0){

        MaterialAlertDialogBuilder(this)
            .setTitle("Error en la Identificación de Usuario")
            .setMessage(message)
            .setPositiveButton("OK"){dialog, which->
                /*code*/
                if(activar.equals(1)){
                    contentView.txtpassuser.requestFocus()
                }
            }
            .show()

    }
}