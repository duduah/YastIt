package es.diegogs.yastit.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import es.diegogs.yastit.R
import es.diegogs.yastit.model.Dish

class DishRecyclerViewAdapter(private val dishes: List<Dish>): RecyclerView.Adapter<DishRecyclerViewAdapter.DishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_cell, parent, false)

        return DishViewHolder(view)
    }

    override fun getItemCount() = dishes.size

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bindDish(dishes[position], position)
    }

    inner class DishViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val context = itemView.context

        val dishId = itemView.findViewById<TextView?>(R.id.dish_id)
        val dishName = itemView.findViewById<TextView?>(R.id.label_name)
        val dishPhoto = itemView.findViewById<ImageView?>(R.id.photo)
        val dishDescription = itemView.findViewById<TextView?>(R.id.description)
        val dishPrice = itemView.findViewById<TextView?>(R.id.label_price)
        // TODO: ver cómo hacemos los alérgenos

        fun bindDish(dish: Dish, position: Int) {

            // update view with model
            dishId?.text = dish.dishId
            dishName?.text = dish.name
            dishPhoto?.setImageResource(dish.photo)
            dishDescription?.text = dish.description
            dishPrice?.text = context.getString(R.string.price_format)
        }

    }
}