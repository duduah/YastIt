package es.diegogs.yastit.fragment

import android.app.Activity
import android.content.Context
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

    private var onTableCliskListener: OnTableCliskListener? = null


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

        table_list.setOnItemClickListener { _, _, position, _ ->
            onTableCliskListener?.onTableSelected(Tables[position], position)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        runOnAttach(context as Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)

        runOnAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()

        onTableCliskListener = null
    }

    private fun runOnAttach(activity: Activity?) {
        if (activity is OnTableCliskListener) {
            onTableCliskListener = activity
        }
        else {
            onTableCliskListener = null
        }
    }

    interface OnTableCliskListener {
        // TODO: ver si es necesario el rimer parámetro
        fun onTableSelected(table: Table, position: Int)
    }
}