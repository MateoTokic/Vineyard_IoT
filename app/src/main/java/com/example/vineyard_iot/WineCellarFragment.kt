package com.example.vineyard_iot

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class WineCellarFragment : Fragment() {
    private lateinit var db : FirebaseFirestore
    private lateinit var temperature : TextView
    private lateinit var humidity : TextView
    private lateinit var light_level : TextView
    private lateinit var carbon_dioxide : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wine_cellar, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()
        temperature = view.findViewById(R.id.temperature2)
        humidity = view.findViewById(R.id.humidity2)
        light_level = view.findViewById(R.id.light2)
        carbon_dioxide = view.findViewById(R.id.carbon_dioxide2)


        val docRef = db.collection("Vinarija1").document("Wine_Cellar")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    temperature.text = document.get("Temperature").toString() + "°C"
                    humidity.text = document.get("Humidity").toString() + "kg/m^3"
                    light_level.text = document.get("Light level").toString() + "lxs"
                    carbon_dioxide.text = document.get("Carbon Dioxide").toString() + "%"
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }



    }

}