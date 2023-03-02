package com.lyfebloc.binancechainkit.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

@Entity
class Balance(@PrimaryKey val symbol: String, @SerializedName("free") var amount: BigDecimal)
