package es.diegogs.yastit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import es.diegogs.yastit.R
import es.diegogs.yastit.fragment.TableListFragment
import es.diegogs.yastit.model.Table
import kotlinx.android.synthetic.main.activity_tables.view.*

class TablesActivity : AppCompatActivity(), TableListFragment.OnTableCliskListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        if (supportFragmentManager.findFragmentById(R.id.table_list_fragment) == null) {
            val fragment = TableListFragment.newInstance()

            supportFragmentManager.beginTransaction()
                    .add(R.id.table_list_fragment, fragment)
                    .commit()
        }
    }

    override fun onTableSelected(table: Table, position: Int) {

        startActivity(TableActivity.intent(this, position))
    }

}
