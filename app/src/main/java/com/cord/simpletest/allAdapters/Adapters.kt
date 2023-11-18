package com.cord.simpletest.allAdapters

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cord.simpletest.R
import com.cord.simpletest.databinding.RowMainPageBinding
import com.cord.simpletest.ui.model.DataItem

class adapters(
    var context: Activity?, var mList: ArrayList<DataItem>,
    var onclicks: (Int) -> Unit
) : RecyclerView.Adapter<adapters.myView>() {

    class myView(var binding: RowMainPageBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myView {
        var bindings = RowMainPageBinding.inflate(context!!.layoutInflater, parent, false)
        return myView(bindings)
    }

    override fun onBindViewHolder(holder: myView, position: Int) {
        var data = mList[position]
        holder.binding.apply {
            cryptoTitleTV.setText(data.symbol)
            txtPriceOne.setText("$"+data.quote.usd!!.price.toString().substringBefore('.') + " USD")
            cryptoSubtitleTV.setText(data.name)

            if (data.quote.usd!!.percentChangeD.toInt() > 0) {
                cryptoGraphIV.setImageResource(R.drawable.up_tren_ic)
                percentChangeTV.setText("+"+
                    data.quote.usd!!.percentChangeD.toString().substringBefore('.') + "%"
                )
                percentChangeTV.setTextColor(context!!.resources.getColor(R.color.green_A200))

            } else {
                cryptoGraphIV.setImageResource(R.drawable.img_vector)
                percentChangeTV.setTextColor(context!!.resources.getColor(android.R.color.holo_red_dark))
                percentChangeTV.setText("-"+
                    data.quote.usd!!.percentChangeD.toString().substringBefore('.') + "%"
                )
            }
        }
        holder.itemView.setOnClickListener {
            onclicks.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateList(data: DataItem) {
        mList.add(data)
        notifyDataSetChanged()
    }
    fun sortBy(value:Int){
        if (value==0) {
            mList.sortBy { it.quote.usd!!.price }
        }else{
            mList.sortBy { it.quote.usd!!.percentChangeD }
        }
        notifyDataSetChanged()
    }
}