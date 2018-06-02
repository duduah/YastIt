package es.diegogs.yastit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import es.diegogs.yastit.R
import es.diegogs.yastit.model.Table
import es.diegogs.yastit.model.Tables
import kotlinx.android.synthetic.main.fragment_table_list.*


class TableListFragment: Fragment() {

    companion object {
        fun newInstance() = TableListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        table_list.adapter = ArrayAdapter<Table>(
                activity,
                android.R.layout.simple_list_item_1,
                Tables.toArray()
        )
    }
}