package com.choota.opencurrency.data.local.dao

import androidx.room.*
import com.choota.opencurrency.domain.model.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM Currency")
    fun getCurrencies(): Flow<List<Currency>>

    @Query("SELECT * FROM Currency WHERE id = :id")
    suspend fun getCurrencyById(id: Long): Currency

    @Query("SELECT * FROM Currency WHERE code = :code")
    suspend fun getCurrencyByTag(code: String): Currency

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: Currency): Long

    @Update
    suspend fun updateCurrency(currency: Currency)

    @Delete
    suspend fun deleteCurrency(currency: Currency)

    @Query("SELECT COUNT(*) FROM Currency WHERE code = :code")
    suspend fun countByCode(code: String): Long
}