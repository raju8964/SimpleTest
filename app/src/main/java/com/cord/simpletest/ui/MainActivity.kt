package com.cord.simpletest.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cord.simpletest.allAdapters.adapters
import com.cord.simpletest.databinding.ActivityMainBinding
import com.cord.simpletest.repo.MyResponse
import com.cord.simpletest.ui.model.DataItem
import com.cord.simpletest.utils.Common
import com.cord.simpletest.utils.ImageApplication
import com.cord.simpletest.utils.ShowMessage
import com.cord.simpletest.viewModel.MainViewModel
import com.cord.simpletest.viewModel.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    var count = 0;
    var adapter: adapters? = null
    var mlist = ArrayList<DataItem>()
    var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = Common.progressDialog(this)
        val repository = (application as ImageApplication).quoteRepository
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        binding.apply {
            etGroupNinetyOne.setOnClickListener {
                showPopupMenu(etGroupNinetyOne)
            }

            recyclerMainPage.setHasFixedSize(true)
            recyclerMainPage.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapters(this@MainActivity, mlist) {
                Toast.makeText(this@MainActivity, "Coming soon...", Toast.LENGTH_SHORT).show()
            }
            recyclerMainPage.adapter = adapter
            mainViewModel.postData()
            obsorvers()

        }
    }

    private fun obsorvers() {
        //// getcripto
        mainViewModel.allCrypto.observe(this@MainActivity) {
            when (it) {
                is MyResponse.Error -> {
                    dialog?.dismiss()
                    ShowMessage.getInstance().Show(this@MainActivity,
                        "Oops! something went wrong. Please try again...",500)
                }
                is MyResponse.Success -> {
                    it.mData?.let {
                        try {
                            it.data!!.filter {it.symbol!="BTC"}.map { adapter!!.updateList(it) }
                            CoroutineScope(Dispatchers.Main).launch {
                                binding.apply {
                                    it.data.find { it.symbol == "BTC" }.apply {
                                        txtBTC.setText(this!!.symbol)
                                        txtPrice.setText(
                                            "$" + this.quote.usd!!.price.toString()
                                                .substringBefore('.') + " USD"
                                        )
                                        txtBitcoin.setText(this.name.toString())
                                        txtTwentyFive.setText(
                                            this.quote.usd!!.percentChangeD.toString()
                                                .substringBefore('.') + "%"
                                        )
                                    }
                                }
                            }
                        } catch (e: Exception) {
                        }
                    }
                    dialog?.dismiss()
                }
                is MyResponse.Loading -> {
                    dialog?.show()
                }
            }
        }
        //// getImages
        mainViewModel.allImage.observe(this@MainActivity) {
            when (it) {
                is MyResponse.Error -> {
                    dialog?.dismiss()
                    ShowMessage.getInstance().Show(this@MainActivity,
                        "Oops! something went wrong. Please try again...",500)
                }
                is MyResponse.Success -> {
                    it.mData?.let {
                        try {
                            it.data.values.map {
                                it.urls
                            }

                        } catch (e: Exception) {
                        }
                    }
                    dialog?.dismiss()
                }
                is MyResponse.Loading -> {
                    dialog?.show()
                }
            }
        }

    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menu.add(Menu.NONE, 0, Menu.NONE, "Sort by Price")
        popupMenu.menu.add(Menu.NONE, 1, Menu.NONE, "Sort by 24h Volume")
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                0 -> {
                    sortBy(0)
                    true
                }
                1 -> {
                    sortBy(1)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun sortBy(value:Int) {
        adapter!!.sortBy(0)
    }



    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this);

    }


}
