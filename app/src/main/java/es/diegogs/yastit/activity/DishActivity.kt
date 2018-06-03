package es.diegogs.yastit.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import es.diegogs.yastit.R
import es.diegogs.yastit.model.*
import kotlinx.android.synthetic.main.activity_dish.*
import kotlinx.android.synthetic.main.content_dish.*

class DishActivity : AppCompatActivity() {

    companion object {

        val EXTRA_NEW_DISH_ID = "EXTRA_NEW_DISH_ID"
        val EXTRA_DISH = "EXTRA_DISH"
        val EXTRA_TABLE = "EXTRA_TABLE"
        val EXTRA_IS_NEW_DISH = "EXTRA_IS_NEW_DISH"
        val EXTRA_CHANGE_VARIANTS = "EXTRA_CHANGE_VARIANTS"

        fun intent(context: Context, dishIndex: Int, tableIndex: Int? = null, newDishForTable: Boolean? = null): Intent {
            val dishIntent = Intent(context, DishActivity::class.java)
            dishIntent.putExtra(EXTRA_DISH, dishIndex)

            if (tableIndex != null) {
                dishIntent.putExtra(EXTRA_TABLE, tableIndex)
            }

            if (newDishForTable != null) {
                dishIntent.putExtra(EXTRA_IS_NEW_DISH, newDishForTable)
            }

            return dishIntent
        }
    }

    /**
     * En caso de que se abra un plato asociado a una mesa hay que indicar si está en modo edición (cambiar sus variantes).
     */
    var editMode: Boolean? = false

    var dish: Dish? = null
    set(value) {
        field = value

        if (value != null) {
            photo?.setImageResource(value.photo)
            label_name?.text = value.name
            description.text = value.description
            label_price.text = getString(R.string.price_format, value.price)
            if (editMode!!) {

                text_variantes.setText(value.variantes)
            }

            displayAllergetIcons(value.getAllergens())
        }
    }

    var table: Table? = null

    var tableIndex: Int? = 0
    set(value) {
        field = value

        if (value != null) {
            table = Tables[value]
            if (editMode!!) {
                dish = table?.getDish(intent.getIntExtra(EXTRA_DISH, 0))
            }
            else {
                dish = Dishes[intent.getIntExtra(EXTRA_DISH, 0)]
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dish)

        if (intent.extras.containsKey(EXTRA_TABLE)) {
            editMode = (intent.extras.containsKey(EXTRA_IS_NEW_DISH)
                    && !intent.getBooleanExtra(EXTRA_IS_NEW_DISH, false))

            // Si llegamos a esta actividad desde la actividad de pedidos de mesa TableActivity
            // mostraremos solo los platos de dicha mesa.
            tableIndex = intent.getIntExtra(EXTRA_TABLE, 0)


            ok_button.visibility = View.VISIBLE
            cancel_button.visibility = View.VISIBLE
            text_variantes.visibility = View.VISIBLE

            ok_button.setOnClickListener {

                val result = Intent()
                if (editMode!!) {

                    result.putExtra(EXTRA_TABLE, intent.getIntExtra(EXTRA_TABLE, 0))
                    result.putExtra(EXTRA_DISH, intent.getIntExtra(EXTRA_DISH, 0))
                    result.putExtra(EXTRA_CHANGE_VARIANTS, text_variantes.text.toString())
                }
                else {
                    result.putExtra(EXTRA_NEW_DISH_ID, dish?.dishId)
                    result.putExtra(EXTRA_CHANGE_VARIANTS, text_variantes.text.toString())
                }
                setResult(Activity.RESULT_OK, result)
                finish()
            }

            cancel_button.setOnClickListener {

                setResult(Activity.RESULT_CANCELED)
                finish()
            }

        }
        else {
            // En cambio, si llegamos desde otra vista donde no se indica la mesa, entendemos que
            // se está pidiendo el detalle de un plato del menú (Dishes).

            ok_button.visibility = View.GONE
            cancel_button.visibility = View.GONE
            text_variantes.visibility = View.GONE

            dish = Dishes[intent.getIntExtra(EXTRA_DISH, 0)]
        }

    }

    // TODO: Ver cómo meter esta función en Utils para usarla también en el recycler
    fun displayAllergetIcons(allergens: List<AllergenTypes>) {

        if (allergens.isEmpty()) {

            linear_layout_allergens?.visibility = View.GONE
        }
        else {

            linear_layout_allergens?.visibility = View.VISIBLE

            allergens.forEach {
                findViewById<ImageView?>(it.allergenId)?.visibility = View.VISIBLE
            }
        }
    }

}
