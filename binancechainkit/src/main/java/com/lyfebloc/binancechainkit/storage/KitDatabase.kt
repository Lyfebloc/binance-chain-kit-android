package com.lyfebloc.binancechainkit.storage

import android.content.Context
import androidx.room.*
import com.lyfebloc.binancechainkit.models.Balance
import com.lyfebloc.binancechainkit.models.LatestBlock
import com.lyfebloc.binancechainkit.models.SyncState
import com.lyfebloc.binancechainkit.models.Transaction
import java.math.BigDecimal
import java.util.*

@Database(version = 1, exportSchema = false, entities = [
    Balance::class,
    Transaction::class,
    LatestBlock::class,
    SyncState::class
])

@TypeConverters(Converters::class)
abstract class KitDatabase : RoomDatabase() {
    abstract val balance: BalanceDao
    abstract val transactions: TransactionDao
    abstract val latestBlock: LatestBlockDao
    abstract val syncState: SyncStateDao

    companion object {
        fun create(context: Context, dbName: String): KitDatabase {
            return Room.databaseBuilder(context, KitDatabase::class.java, dbName)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
        }
    }
}

class Converters {
    @TypeConverter
    fun bigDecimalFromString(string: String): BigDecimal {
        return BigDecimal(string)
    }

    @TypeConverter
    fun bigDecimalToString(bigDecimal: BigDecimal): String {
        return bigDecimal.toString()
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
