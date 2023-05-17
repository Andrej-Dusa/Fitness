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

        sensorManager =  getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepTV = findViewById(com.example.myapplication.R.id.idTVSteps)
        fab = findViewById(com.example.myapplication.R.id.idFAB)
        stepTV.text = steps.toString()


        fab.setOnClickListener {
            if(running){
                running = false
                fab.setImageResource(R.drawable.ic_play)
                Toast.makeText(this@StepCounterAktivity, "Counter Paused", Toast.LENGTH_SHORT).show()
            } else {
                running = true
                fab.setImageResource(R.drawable.ic_pause)
                Toast.makeText(this@StepCounterAktivity, "Counter Started", Toast.LENGTH_SHORT).show()
                startCounting()
            }

        }
    }

    private fun startCounting() {
        running = true
        var sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if(sensor != null){
            sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI)
        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if(running){

            if (p0 != null) {
                stepTV.setText("" + (p0.values[0]))
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}