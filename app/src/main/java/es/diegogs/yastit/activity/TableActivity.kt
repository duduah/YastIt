package es.diegogs.yastit.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import es.diegogs.yastit.R
import es.diegogs.yastit.adapter.DishRecyclerViewAdapter
import es.diegogs.yastit.fragment.TableListFragment
import es.diegogs.yastit.model.Dish
import es.diegogs.yastit.model.Tables
import kotlinx.android.synthetic.main.activity_table.*

class TableActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TABLE = "EXTRA_TABLE"

        fun intent(context: Context, tableIndex: Int): Intent {
            val tableIntent = Intent(context, TableActivity::class.java)
            tableIntent.putExtra(EXTRA_TABLE, tableIndex)

            return tableIntent
        }
    }

    private var dishes: MutableList<Dish> = mutableListOf<Dish>()
    set(value) {
        field = value
        if (value != null) {

            val adapter = DishRecyclerViewAdapter(value)
            table_dishes_list.adapter = adapter
//            adapter.onClickListener = View.OnClickListener {
//
//            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        // TODO: parametrizar el 1 en integers
        table_dishes_list.layoutManager = GridLayoutManager(this, 1)
        table_dishes_list.itemAnimator = DefaultItemAnimator()

        val tableIndex = intent.getIntExtra(EXTRA_TABLE, 0)
        dishes = Tables.getDishesFromTable(tableIndex)
        if (dishes.size == 0) {
            label_message_no_dishes.visibility = View.VISIBLE
            table_dishes_list.visibility = View.GONE
        }
        else {
            label_message_no_dishes.visibility = View.GONE
            table_dishes_list.visibility = View.VISIBLE
        }

        add_button.setOnClickListener {
            val intent = MenuActivity.intent(this, tableIndex)

            startActivity(intent)
        }
    }
}
