package com.example.myapplication

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StepCounterAktivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var running = false
    private lateinit var stepTV: TextView
    private lateinit var fab: FloatingActionButton
    private var steps: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_counter_aktivity)

        //inicializacia premmenych
        sensorManager =  getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepTV = findViewById(com.example.myapplication.R.id.idTVSteps)
        fab = findViewById(com.example.myapplication.R.id.idFAB)
        stepTV.text = steps.toString()

        //nastavenie tlaidla pre spustenie a zastavenie merania
        fab.setOnClickListener {
            //ak chceme zastavit meranie
            if(running){
                running = false
                fab.setImageResource(R.drawable.ic_play)
                Toast.makeText(this@StepCounterAktivity, "Counter Paused", Toast.LENGTH_SHORT).show()
            }
            //ak chceme spustit meranie
            else {
                running = true
                fab.setImageResource(R.drawable.ic_pause)
                Toast.makeText(this@StepCounterAktivity, "Counter Started", Toast.LENGTH_SHORT).show()
                startCounting()
            }

        }
    }

    //spustenie merania
    private fun startCounting() {
        running = true
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            Toast.makeText(this, "Step detector sensor not found", Toast.LENGTH_SHORT).show()
        }
    }

    //co sa ma vykonat ak senzor zaznamena zmenu
    override fun onSensorChanged(event: SensorEvent) {
        if (running && event.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
            steps++
            stepTV.text = steps.toString()
        }
    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}