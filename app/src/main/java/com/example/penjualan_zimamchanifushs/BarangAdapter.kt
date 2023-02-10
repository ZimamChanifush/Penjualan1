package com.example.penjualan_zimamchanifushs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.penjualan_zimamchanifushs.Room.tb_barang
import kotlinx.android.synthetic.main.activity_barang_adapter.view.*

class BarangAdapter (private val barang:ArrayList<tb_barang>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>(){

    class BarangViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        return BarangViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_barang_adapter,parent,false)
        )
    }
    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val brg = barang[position]
        holder.view.Nama.text = brg.nm_brg
        holder.view.goo.text = brg.id_brg.toString()
        holder.view.Nama.setOnClickListener{
            listener.onClick(brg)
        }
        holder.view.image_Edit.setOnClickListener{
            listener.onUpdate(brg)
        }
        holder.view.ic_delete.setOnClickListener{
            listener.onDelete(brg)
        }

    }

    override fun getItemCount() = barang.size

    fun setData(list: List<tb_barang>){
        barang.clear()
        barang.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener{
        fun onClick(tbBrg:tb_barang)
        fun onUpdate(tbBrg: tb_barang)
        fun onDelete(tbBrg: tb_barang)
    }

}
