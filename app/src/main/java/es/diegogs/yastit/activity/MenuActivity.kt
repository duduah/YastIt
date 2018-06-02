package es.diegogs.yastit.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import es.diegogs.yastit.R
import es.diegogs.yastit.model.Dish
import es.diegogs.yastit.model.Dishes
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_menu.view.*

class MenuActivity : AppCompatActivity() {

    companion object {

        val EXTRA_MENU = "EXTRA_MENU"

        fun intent(context: Context, tableIndex: Int): Intent {

            val menuIntent = Intent(context, MenuActivity::class.java)
            menuIntent.putExtra(EXTRA_MENU, tableIndex)

            return menuIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        menu_list.adapter = ArrayAdapter<Dish>(
                this,
                android.R.layout.simple_list_item_1,
                Dishes.toArray()
        )

        menu_list.setOnItemClickListener { _, _, position, _ ->
            val intent = DishActivity.intent(this, position)
            startActivity(intent)
        }
    }
}
