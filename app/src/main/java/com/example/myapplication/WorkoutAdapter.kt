package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkoutAdapter(private val workoutList: ArrayList<Workout>)
    : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    var onItemClick: ((Workout)-> Unit)? = null
    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.idIVItem)
        val textNameView: TextView = itemView.findViewById(R.id.idTVActivity)
        val textTimeView: TextView = itemView.findViewById(R.id.idTVWorkoutTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun getItemCount(): Int {
        return workoutList.size;
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout = workoutList[position]
        holder.imageView.setImageResource(workout.image)
        holder.textNameView.text = workout.name
        holder.textTimeView.text = "Time: " + workout.time.toString() + " min"

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(workout)
        }

    }
}