package com.example.penjualan_zimamchanifushs

import android.os.Build.ID
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.penjualan_zimamchanifushs.Room.Constant
import com.example.penjualan_zimamchanifushs.Room.Penjualan
import com.example.penjualan_zimamchanifushs.Room.tb_barang
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private val db by lazy { Penjualan(this) }
    private var tbbarang: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tombolperintah()
        setupView()
        tbbarang = intent.getIntExtra("intent_id", tbbarang)
        Toast.makeText(this, tbbarang.toString(), Toast.LENGTH_SHORT).show()

}
    fun setupView(){
        val intentType = intent.getIntExtra("intent_type",0)
        when (intentType){
            Constant.TYPE_CREATE -> {
                btnUpdate.visibility = View.GONE

            }
            Constant.TYPE_READ -> {
                btnSimpan.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                tampildatanis()
            }
            Constant.TYPE_UPDATE-> {
                btnSimpan.visibility = View.GONE
                tampildatanis()
            }
        }
    }

    fun tombolperintah(){
        btnSimpan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.PenjualanDAO().addtbbarang(
                    tb_barang(tbbarang,
                        etHargabrg.text.toString().toInt(),
                        etstok.text.toString().toInt(),
                        etNamabrg.text.toString()
                    )
                )
                finish()
            }
        }
        btnUpdate.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.PenjualanDAO().updatetbbarang(
                    tb_barang(tbbarang,
                        etHargabrg.text.toString().toInt(),
                        etstok.text.toString().toInt(),
                        etNamabrg.text.toString()
                    )
                )
                finish()
            }
        }
    }

     fun tampildatanis(){
        tbbarang = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val brng = db.PenjualanDAO().tampilid(tbbarang) [0]
            val barangid : String = brng.id_brg.toString()
            val harga : String = brng.hrg_brg.toString()
            val Stok : String = brng.stok.toString()
            etIDbrg.setText(barangid)
            etNamabrg.setText(brng.nm_brg)
            etHargabrg.setText(harga)
            etstok.setText(Stok)
        }
    }

}
