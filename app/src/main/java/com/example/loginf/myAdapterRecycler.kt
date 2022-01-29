package com.example.loginf

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class myAdapterRecycler: RecyclerView.Adapter<myAdapterRecycler.ViewHolder>() {

    val fechas= arrayOf("28/01/2021","30/01/2021","31/01/2021","02/02/2021",)
    val desc= arrayOf("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
            "\n", "El hierro es un elemento químico de número atómico 26 situado en el grupo 8, periodo 4 de la tabla periódica de los elementos. Su símbolo es Fe (del latin ...\n","La pandemia de COVID-19, conocida también como pandemia de coronavirus, es una pandemia actualmente en curso derivada de la enfermedad causada por el virus SARS-CoV-2.7\u200B8\u200B Inicialmente fue llamada «neumonía de Wuhan», puesto que los primeros casos fueron identificados en diciembre de 2019 en la ciudad china de Wuhan,9\u200B10\u200B al reportarse casos de un grupo de personas enfermas con un tipo de neumonía desconocida. La mayoría de los afectados tenía vinculación con trabajadores del Mercado mayorista de mariscos de Huanan.11\u200B La Organización Mundial de la Salud (OMS) la declaró una emergencia de salud pública de importancia internacional el 30 de enero de 2020 y la reconoció como una pandemia el 11 de marzo de 2020, cuando informó que había 4291 muertos y 118 000 casos en 114 países.12\u200B\n" +
            "\n","La erupción volcánica de La Palma de 2021 se inició el 19 de septiembre en el paraje de Cabeza de Vaca, cercano a la localidad de El Paraíso del municipio de El Paso, en la isla española de La Palma.2\u200B Se trata de la primera erupción en la isla desde la del Teneguía en 1971 y la primera producida en Canarias desde la submarina de El Hierro de 2011.4\u200B5\u200B6\u200B7\u200B La erupción se detuvo el 13 de diciembre tras 85 días de actividad, siendo la erupción histórica más larga registrada en la isla.8\u200B9\u200B\n" +
            "\n")
    val autores= arrayOf("Estelo","Victor","Alexander","LAChirana Plat")

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val myCard:CardView
        val imgAvatar:ImageView
        val txtContent:TextView
        val txtAutor:TextView
        val txtFecha:TextView

        init {

            myCard=view.findViewById(R.id.main)
            imgAvatar=view.findViewById(R.id.imgAvatar)
            txtContent=view.findViewById(R.id.txtContent)
            txtAutor=view.findViewById(R.id.txtAutor)
            txtFecha=view.findViewById(R.id.txtFecha)

            myCard.setOnClickListener { v->

                MaterialAlertDialogBuilder(view.context)
                    .setTitle("Detalle de Item")
                    .setMessage("Autor: ${txtAutor.text}")
                    .setPositiveButton("OK"){dialog, which->

                    }
                    .show()
            }

            txtAutor.setOnClickListener { v->

            //var intent:Intent = Intent(this,detallado::class.java)
            //view.context.startActivity(view.context,detallado::class.java)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val vista=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)

        return ViewHolder(vista)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.myCard.animation=AnimationUtils.loadAnimation(holder.myCard.context,R.anim.myefecto)

            holder.txtFecha.text=fechas[position]
            holder.txtAutor.text=autores[position]
            holder.txtContent.text=desc[position]

    }

    override fun getItemCount(): Int {
        return autores.size
    }

}