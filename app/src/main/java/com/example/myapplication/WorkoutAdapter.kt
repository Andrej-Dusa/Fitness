package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBinding
import com.ezatpanah.roomdatabase_youtube.db.Workout

class WorkoutAdapter : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>(){
    private lateinit var binding: ItemBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: WorkoutAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    //vnutorna pomocna trieda na naplnenie RecyclerView
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        //ztlmenie vynimky
        @SuppressLint("SetTextI18n")
        fun bind(item: Workout) {
            binding.apply {
                //nastavenie hodnot v layoute
                idTVActivity.text = item.name
                idTVWorkoutTime.text = item.time.toString() + " min"
                idIVItem.setImageResource(item.img)

                //nastavenie co sa ma stat po kliknuti na cvik zo zoznamu
                root.setOnClickListener {
                    val intent=Intent(context,WorkoutDetailActivity::class.java)
                    intent.putExtra("bundle_workout_id", item.id)
                    context.startActivity(intent)
                }

            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Workout>() {
        override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}