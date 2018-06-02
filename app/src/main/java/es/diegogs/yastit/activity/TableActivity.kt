package es.diegogs.yastit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import es.diegogs.yastit.R
import es.diegogs.yastit.fragment.TableListFragment

class TableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        val fragment = TableListFragment.newInstance()

        supportFragmentManager.beginTransaction()
                .add(R.id.table_list_fragment, fragment)
                .commit()
    }
}
