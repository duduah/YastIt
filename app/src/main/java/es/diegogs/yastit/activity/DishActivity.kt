package es.diegogs.yastit.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import es.diegogs.yastit.R
import es.diegogs.yastit.model.AllergenTypes
import es.diegogs.yastit.model.Dish
import es.diegogs.yastit.model.Dishes
import es.diegogs.yastit.model.Tables
import kotlinx.android.synthetic.main.activity_dish.*
import kotlinx.android.synthetic.main.content_dish.*

class DishActivity : AppCompatActivity() {

    companion object {

        val EXTRA_DISH_ID = "EXTRA_DISH"
        val EXTRA_DISH = "EXTRA_DISH"
        //val EXTRA_TABLE = "EXTRA_TABLE"

        fun intent(context: Context, dishIndex: Int): Intent {
            val dishIntent = Intent(context, DishActivity::class.java)
            dishIntent.putExtra(EXTRA_DISH, dishIndex)
            //dishIntent.putExtra(EXTRA_TABLE, tableIndex)

            return dishIntent
        }
    }

    var dish: Dish? = null
    set(value) {
        field = value

        if (value != null) {
            photo?.setImageResource(value.photo)
            label_name?.text = value.name
            description.text = value.description
            label_price.text = getString(R.string.price_format, value.price)

            displayAllergetIcons(value.getAllergens())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dish)


        //val table = Tables[intent.getIntExtra(EXTRA_TABLE, 0)]
        val dishIndex = intent.getIntExtra(EXTRA_DISH, 0)
        dish = Dishes[dishIndex]

        ok_button.setOnClickListener {

            val result = Intent()
            result.putExtra(EXTRA_DISH_ID, dish?.dishId)
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        cancel_button.setOnClickListener {

            setResult(Activity.RESULT_CANCELED)
            finish()
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
