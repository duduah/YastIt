package es.diegogs.yastit.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
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

        val EXTRA_TABLE_MENU = "EXTRA_MENU"

        fun intent(context: Context, tableIndex: Int): Intent {

            val menuIntent = Intent(context, MenuActivity::class.java)
            menuIntent.putExtra(EXTRA_TABLE_MENU, tableIndex)

            return menuIntent
        }
    }

    private var dishes = listOf<Dish>()
        set(value) {
            field = value

            setDishAdapter(value)

            updateViews()

        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Indicamos la vista que actuará de toolbar. En nuestro caso: toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            android.R.id.home -> {

                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
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

    fun setDishAdapter(dishes: List<Dish>) {
        val adapter = DishRecyclerViewAdapter(dishes)
        table_dishes_list.adapter = adapter

        adapter.onClickListener = View.OnClickListener {


            val dishIndex = table_dishes_list.getChildAdapterPosition(it)
            val tableIndex = intent.getIntExtra(EXTRA_TABLE_MENU, 0)

            if (intent.extras.containsKey(EXTRA_TABLE_MENU)) {
                startActivity(DishActivity.intent(this, dishIndex, tableIndex, true)
                        .addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT))
                finish()
            }
            else {
                startActivity(DishActivity.intent(this, dishIndex))
            }
        }

    }
}
