package com.example.penjualan_zimamchanifushs.Room

import androidx.room.*

@Dao

interface PenjualanDAO {
    @Insert
    fun addtbbarang (tbBrg:tb_barang)

    @Update
    fun updatetbbarang (tbBrg:tb_barang)

    @Delete
    fun delttbbarang (tbBrg:tb_barang)

    @Query ("SELECT*FROM tb_barang")
    fun tampilsemua () : List<tb_barang>

    @Query ("SELECT*FROM tb_barang WHERE id_brg=:barang_id")
    fun tampilid (barang_id: Int) : List<tb_barang>
}