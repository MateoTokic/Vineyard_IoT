package com.example.vineyard_iot

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class VineyardFragment : Fragment() {

    private lateinit var db : FirebaseFirestore
    private lateinit var temperature : TextView
    private lateinit var humidity : TextView
    private lateinit var pressure : TextView
    private lateinit var sunlight : TextView
    private lateinit var wind : TextView
    private lateinit var rainfall : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vineyard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()

        settextview(view)
        val docRef = db.collection("Vinarija1").document("Vineyard")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    temperature.text = document.get("Temperature").toString() + "°C"
                    rainfall.text = document.get("Rainfall").toString() + "mm/m^2"
                    humidity.text = document.get("Humidity").toString() + "kg/m^3"
                    pressure.text = document.get("Pressure").toString() + "bar"
                    sunlight.text = document.get("Sunlight").toString() + "h"
                    wind.text = document.get("Wind").toString() + "km/h"
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }

    private fun settextview(view: View) {
        temperature = view.findViewById(R.id.temperature1)
        humidity = view.findViewById(R.id.humidity1)
        pressure = view.findViewById(R.id.pressure1)
        sunlight = view.findViewById(R.id.sunlight1)
        wind = view.findViewById(R.id.wind1)
        rainfall = view.findViewById(R.id.rainfall1)
    }



}