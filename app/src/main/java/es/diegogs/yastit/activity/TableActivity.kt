package es.diegogs.yastit.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import es.diegogs.yastit.R
import es.diegogs.yastit.adapter.DishRecyclerViewAdapter
import es.diegogs.yastit.fragment.TableListFragment
import es.diegogs.yastit.model.Dish
import es.diegogs.yastit.model.Dishes
import es.diegogs.yastit.model.Table
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

    val REQUEST_NEW_DISH = 1

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

    private var table: Table? = null
    private var tableIndex: Int? = 0
    set(value) {
        field = value

        if (value != null) {
            table = Tables[value]

            dishes = table?.getDishes()!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        // TODO: parametrizar el 1 en integers
        table_dishes_list.layoutManager = GridLayoutManager(this, 1)
        table_dishes_list.itemAnimator = DefaultItemAnimator()

        tableIndex = intent.getIntExtra(EXTRA_TABLE, 0)
        //dishes = Tables.getDishesFromTable(tableIndex)

        updateViews()

        add_button.setOnClickListener {
            val intent = MenuActivity.intent(this, tableIndex!!)

            startActivityForResult(intent, REQUEST_NEW_DISH)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_NEW_DISH -> {
                if (requestCode.equals(REQUEST_NEW_DISH) && resultCode == Activity.RESULT_OK) {

                    val result = data?.getStringExtra(DishActivity.EXTRA_DISH_ID)

                    if (result != null && !result.isEmpty()) {

                        table?.addDish(Dishes.getDish(result))
                        dishes = table?.getDishes()!!
                        updateViews()
                        /*this.onRestart()*/
                    }
                }
            }
        }
    }

/*    override fun onRestart() {
        super.onRestart()

        updateViews()
    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.activity_table, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.menu_item_bill -> {
                //val intent = BillActivity.intent(this, tableIndex!!)
                startActivity(BillActivity.intent(this, tableIndex!!))
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
}
