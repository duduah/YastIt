package es.diegogs.yastit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import es.diegogs.yastit.R
import es.diegogs.yastit.model.AllergenTypes
import es.diegogs.yastit.model.Dish
import kotlinx.android.synthetic.main.content_dish.*

class DishRecyclerViewAdapter(private val dishes: List<Dish>): RecyclerView.Adapter<DishRecyclerViewAdapter.DishViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_cell, parent, false)

        view.setOnClickListener {
            onClickListener?.onClick(it)
        }
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
        val dishAllergensContainer = itemView.findViewById<LinearLayout?>(R.id.linear_layout_allergens)


        // TODO: ver cómo hacemos los alérgenos

        fun bindDish(dish: Dish, position: Int) {

            // update view with model
            dishId?.text = dish.dishId
            dishName?.text = dish.name
            dishPhoto?.setImageResource(dish.photo)
            dishDescription?.text = dish.description
            dishPrice?.text = context.getString(R.string.price_format, dish.price)

            displayAllergetIcons(dish.getAllergens())

        }

        fun displayAllergetIcons(allergens: List<AllergenTypes>) {

            if (allergens.isEmpty()) {

                dishAllergensContainer?.visibility = View.GONE
            }
            else {

                dishAllergensContainer?.visibility = View.VISIBLE

                allergens.forEach {
                    itemView.findViewById<ImageView?>(it.allergenId)?.visibility = View.VISIBLE
                }
            }
        }
    }


}