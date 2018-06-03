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
import es.diegogs.yastit.R
import es.diegogs.yastit.adapter.DishRecyclerViewAdapter
import es.diegogs.yastit.model.Dish
import es.diegogs.yastit.model.Dishes
import es.diegogs.yastit.model.Table
import es.diegogs.yastit.model.Tables
import kotlinx.android.synthetic.main.activity_table.*
import kotlinx.android.synthetic.main.dishes_recycler_view.*

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
    val REQUEST_EDIT_DISH = 2

    private var dishes: MutableList<Dish> = mutableListOf<Dish>()
    set(value) {
        field = value

        setDishAdapter(value)

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

        // Indicamos la vista que actuará de toolbar. En nuestro caso: toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
                if (resultCode == Activity.RESULT_OK && data != null) {

                    val dishId = data.getSerializableExtra(DishActivity.EXTRA_NEW_DISH_ID).toString()
                    val newVariants = data.getSerializableExtra(DishActivity.EXTRA_CHANGE_VARIANTS).toString()

                    table?.addDish(Dishes.getDish(dishId))
                    table?.setDishVariants(dishId, newVariants)
                    dishes = table?.getDishes()!!
                    updateViews()
                    /*this.onRestart()*/

                }
            }
            REQUEST_EDIT_DISH -> {

                if (resultCode == Activity.RESULT_OK && data != null) {

                    val dishIndex = data.getIntExtra(DishActivity.EXTRA_DISH, 0)
                    val newVariants = data.getSerializableExtra(DishActivity.EXTRA_CHANGE_VARIANTS).toString()

                    table?.setDishVariants(dishIndex, newVariants)
                    /*this.onRestart()*/
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
            android.R.id.home -> {

                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun updateViews() {
        toolbar.title = getString(R.string.table_name_format, table?.tableNumber)
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

            startActivityForResult(DishActivity.intent(this, dishIndex, tableIndex, false), REQUEST_EDIT_DISH)
        }

    }
}
