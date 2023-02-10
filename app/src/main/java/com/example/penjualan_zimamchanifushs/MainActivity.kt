package com.example.penjualan_zimamchanifushs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.penjualan_zimamchanifushs.Room.Constant
import com.example.penjualan_zimamchanifushs.Room.Penjualan
import com.example.penjualan_zimamchanifushs.Room.tb_barang
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var BarangAdapter:BarangAdapter
    val db by lazy { Penjualan(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setupRecyclerView()
    }
    fun halEdit(){
        btnInput.setOnClickListener {
           intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
          val brg = db.PenjualanDAO(). tampilsemua()
          Log.d("MainActivity", "dbResponse:$brg")
          withContext(Dispatchers.Main) {
              BarangAdapter.setData(brg)
          }
        }
    }

    fun setupRecyclerView(){
        BarangAdapter= BarangAdapter(arrayListOf(), object :
            BarangAdapter.OnAdapterListener{

            override fun onClick(tbBrg: tb_barang) {
                intentEdit(tbBrg.id_brg ,Constant.TYPE_READ)
            }

            override fun onUpdate(tbBrg: tb_barang) {
                intentEdit(tbBrg.id_brg, Constant.TYPE_UPDATE)

            }

            override fun onDelete(tbBrg: tb_barang) {
                deletAlert(tbBrg)
            }
        })
        //idRecyclerView
        ListBrg.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = BarangAdapter
        }
    }

    fun deletAlert(tbBrg:tb_barang){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin Mau Hapus ${tbBrg.nm_brg}?")
            setNegativeButton("Batal") {dialogeInterface, i->
                dialogeInterface.dismiss()
            }
            setPositiveButton("Hapus") {dialogInterface, i->
                CoroutineScope(Dispatchers.IO).launch {
                    db.PenjualanDAO().delttbbarang(tbBrg)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }
        dialog.show()
    }

    fun intentEdit (tbBarangid:Int, intentType: Int) {
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id", tbBarangid)
                .putExtra("intent_type",intentType)
        )
    }

    }
