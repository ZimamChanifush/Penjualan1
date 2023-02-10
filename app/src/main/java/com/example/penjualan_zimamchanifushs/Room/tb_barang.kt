package com.example.penjualan_zimamchanifushs.Room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class tb_barang (
    @PrimaryKey
    val id_brg : Int,
    val hrg_brg : Int,
    val stok : Int,
    val nm_brg : String
    )