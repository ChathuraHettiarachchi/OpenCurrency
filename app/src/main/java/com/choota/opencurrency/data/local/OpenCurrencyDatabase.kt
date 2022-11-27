package com.choota.opencurrency.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.choota.opencurrency.data.local.dao.CurrencyDao
import com.choota.opencurrency.domain.model.Currency

@Database(
    entities = [Currency::class],
    version = 2,
    exportSchema = false
)
abstract class OpenCurrencyDatabase: RoomDatabase() {
    abstract val currencyDao: CurrencyDao
}