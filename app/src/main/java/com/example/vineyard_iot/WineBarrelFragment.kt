package com.example.vineyard_iot

import android.content.ContentValues
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore


class WineBarrelFragment : Fragment() {

    private lateinit var db : FirebaseFirestore
    private lateinit var temperature : TextView
    private lateinit var humidity : TextView
    private lateinit var PH : TextView
    private lateinit var pressure : TextView
    private lateinit var sulfur : TextView
    private lateinit var carbon_dioxide : TextView
    private lateinit var density : TextView
    private lateinit var transparency : TextView
    private lateinit var alcohol : TextView
    private lateinit var color : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wine_barrel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()


        settextview(view)

        val docRef = db.collection("Vinarija1").document("Wine_Barrel")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    temperature.text = document.get("Temperature").toString() + "°C"
                    humidity.text = document.get("Humidity").toString() + "kg/m^3"
                    pressure.text = document.get("Pressure").toString() + "bar"
                    density.text = document.get("Density").toString() + "NTU"
                    sulfur.text = document.get("Sulfur").toString() + "%"
                    PH.text = document.get("PH").toString() + ""
                    carbon_dioxide.text = document.get("Carbon Dioxide").toString() + "%"
                    color.text = document.get("Color").toString()
                    transparency.text = document.get("Transparency").toString() + "NTU"
                    alcohol.text = document.get("Alcohol").toString() + "Oe°"
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }





    }

    private fun settextview(view: View) {
        temperature = view.findViewById(R.id.temperature3)
        humidity = view.findViewById(R.id.humidity3)
        pressure = view.findViewById(R.id.pressure3)
        PH = view.findViewById(R.id.ph3)
        sulfur = view.findViewById(R.id.sulfur3)
        carbon_dioxide = view.findViewById(R.id.carbon_dioxide3)
        density = view.findViewById(R.id.density3)
        alcohol = view.findViewById(R.id.alcohol3)
        transparency =  view.findViewById(R.id.transparency3)
        color =  view.findViewById(R.id.color3)
    }


}