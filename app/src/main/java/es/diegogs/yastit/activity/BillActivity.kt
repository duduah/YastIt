package es.diegogs.yastit.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import es.diegogs.yastit.R
import es.diegogs.yastit.model.Tables
import kotlinx.android.synthetic.main.activity_bill.*

class BillActivity : AppCompatActivity() {

    companion object {
        val EXTRA_BILL = "EXTRA_BILL"

        fun intent(context: Context, tableIndex: Int): Intent {
            val billIntent = Intent(context, BillActivity::class.java)
            billIntent.putExtra(EXTRA_BILL, tableIndex)

            return billIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)

        val totalBill = Tables[intent.getIntExtra(EXTRA_BILL, 0)].getBillFromTable()
        label_bill_total.text = getString(R.string.price_format, totalBill)
    }
}
