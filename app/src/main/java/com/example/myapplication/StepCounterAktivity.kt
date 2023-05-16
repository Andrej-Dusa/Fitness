package com.example.myapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StepCounterAktivity : AppCompatActivity(), SensorEventListener {
    val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    var running = false
    val stepTV: TextView = findViewById(R.id.idTVSteps)
    val fab: FloatingActionButton = findViewById(R.id.idFAB)
    var steps: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_counter_aktivity)

        stepTV.text = steps.toString();

        fab.setOnClickListener {view->
            if(running){
                running = false
                fab.setImageResource(R.drawable.ic_play)
                Toast.makeText(this@StepCounterAktivity, "Counter Paused", Toast.LENGTH_SHORT)
            } else {
                running = true
                fab.setImageResource(R.drawable.ic_pause)
                Toast.makeText(this@StepCounterAktivity, "Counter Started", Toast.LENGTH_SHORT)
                startCounting()
            }

        }
    }

    private fun startCounting() {
        running = true
        val sensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(sensor != null){
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT)
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if(running){
            if (p0 != null) {
                steps = steps + p0.values[0]
                stepTV.text = steps.toString()
            }

        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }
}