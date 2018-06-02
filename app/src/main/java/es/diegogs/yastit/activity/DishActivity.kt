package es.diegogs.yastit.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import es.diegogs.yastit.R
import es.diegogs.yastit.model.AllergenTypes
import es.diegogs.yastit.model.Dish
import es.diegogs.yastit.model.Dishes
import kotlinx.android.synthetic.main.activity_dish.*

class DishActivity : AppCompatActivity() {

    companion object {

        val EXTRA_DISH = "EXTRA_DISH"

        fun intent(context: Context, dishIndex: Int): Intent {
            val dishIntent = Intent(context, DishActivity::class.java)
            dishIntent.putExtra(EXTRA_DISH, dishIndex)

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

            displayAllergetIcons()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dish)

        val dishIndex = intent.getIntExtra(EXTRA_DISH, 0)
        dish = Dishes[dishIndex]

//        dish = Dish(name = "Sartén de guisantes, trigueros y champiñones",
//                description = "Quisantes frescos salteados en mantequilla junto con unos espárragos trigueros de la huerta y unos champiñones de monte. Se deja al comensal salpimentar al gusto.",
//                price = 15.50f,
//                photo = R.drawable.sarten_guisantes_trigueros)

    }

    fun displayAllergetIcons() {
        val allergens = dish?.getAllergens()

        if (allergens?.size == 0) {
            linear_layout_allergens.visibility = View.GONE
        }
        else {
            linear_layout_allergens.visibility = View.VISIBLE

            icon_allergen_cacahuete.visibility = View.GONE
            icon_allergen_frutos_cascara.visibility = View.GONE
            icon_allergen_gluten.visibility = View.GONE
            icon_allergen_huevo.visibility = View.GONE
            icon_allergen_pescado.visibility = View.GONE
            icon_allergen_sesamo.visibility = View.GONE

            allergens?.forEach {
                when(it) {
                    AllergenTypes.PEANUT -> icon_allergen_cacahuete.visibility = View.VISIBLE
                    AllergenTypes.SHELLFRUIT -> icon_allergen_frutos_cascara.visibility = View.VISIBLE
                    AllergenTypes.GLUTEN -> icon_allergen_gluten.visibility = View.VISIBLE
                    AllergenTypes.EGG -> icon_allergen_huevo.visibility = View.VISIBLE
                    AllergenTypes.FISH -> icon_allergen_pescado.visibility = View.VISIBLE
                    AllergenTypes.SESAME -> icon_allergen_sesamo.visibility = View.VISIBLE
                }
            }
        }
    }
}
