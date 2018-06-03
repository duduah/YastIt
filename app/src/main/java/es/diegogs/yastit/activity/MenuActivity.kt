package es.diegogs.yastit.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.ArrayAdapter
import es.diegogs.yastit.R
import es.diegogs.yastit.adapter.DishRecyclerViewAdapter
import es.diegogs.yastit.model.Dish
import es.diegogs.yastit.model.Dishes
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.dishes_recycler_view.*

class MenuActivity : AppCompatActivity() {

    companion object {

        val EXTRA_MENU = "EXTRA_MENU"

        fun intent(context: Context, tableIndex: Int): Intent {

            val menuIntent = Intent(context, MenuActivity::class.java)
            menuIntent.putExtra(EXTRA_MENU, tableIndex)

            return menuIntent
        }
    }

    private var dishes = listOf<Dish>()
        set(value) {
            field = value
            if (value != null) {

                val adapter = DishRecyclerViewAdapter(value)
                table_dishes_list.adapter = adapter

                updateViews()
//            adapter.onClickListener = View.OnClickListener {
//
//            }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // TODO: parametrizar el 1 en integers
        table_dishes_list.layoutManager = GridLayoutManager(this, 1)
        table_dishes_list.itemAnimator = DefaultItemAnimator()

        dishes = Dishes.getDishes()


        /*menu_list.adapter = ArrayAdapter<Dish>(
                this,
                android.R.layout.simple_list_item_1,
                Dishes.toArray()
        )*/

        //val tableIndex = intent.getIntExtra(EXTRA_MENU, 0)
/*        menu_list.setOnItemClickListener { _, _, position, _ ->

//            val intent = DishActivity.intent(this, position, tableIndex)
            val intent = DishActivity.intent(this, position)
            intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
            startActivity(intent)
            finish()
        }*/
    }

    fun updateViews() {
        if (dishes.size == 0) {
            label_message_no_dishes.visibility = View.VISIBLE
            table_dishes_list.visibility = View.GONE
        }
        else {
            label_message_no_dishes.visibility = View.GONE
            table_dishes_list.visibility = View.VISIBLE
        }
    }
}
