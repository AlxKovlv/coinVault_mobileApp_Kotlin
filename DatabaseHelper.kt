package com.example.coinvault_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "coin_collection.db"
        private const val DATABASE_VERSION = 1

        // Define the table name and column names
        private const val TABLE_COINS = "coins"
        private const val COLUMN_ID = "id"
        private const val COLUMN_COIN_NAME = "coin_name"
        private const val COLUMN_ISSUER = "issuer"
        private const val COLUMN_CURRENCY = "currency"
        private const val COLUMN_EDGE_TYPE = "edge_type"
        private const val COLUMN_YEAR = "year"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create the table
        val createCoinsTable = """
            CREATE TABLE $TABLE_COINS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_COIN_NAME TEXT,
                $COLUMN_ISSUER TEXT,
                $COLUMN_CURRENCY TEXT,
                $COLUMN_EDGE_TYPE TEXT,
                $COLUMN_YEAR INTEGER
            )
        """.trimIndent()

        db.execSQL(createCoinsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This implementation is a placeholder and does nothing
    }

    fun addCoin(coin: Coin) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_COIN_NAME, coin.coinName)
            put(COLUMN_ISSUER, coin.issuer)
            put(COLUMN_CURRENCY, coin.currency)
            put(COLUMN_EDGE_TYPE, coin.edgeType)
            put(COLUMN_YEAR, coin.year)
        }
        db.insert(TABLE_COINS, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getAllCoins(): List<Coin> {
        val coins = mutableListOf<Coin>()
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_COINS"
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val coinName = cursor.getString(cursor.getColumnIndex(COLUMN_COIN_NAME))
                val issuer = cursor.getString(cursor.getColumnIndex(COLUMN_ISSUER))
                val currency = cursor.getString(cursor.getColumnIndex(COLUMN_CURRENCY))
                val edgeType = cursor.getString(cursor.getColumnIndex(COLUMN_EDGE_TYPE))
                val year = cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR))

                val coin = Coin(id, coinName, issuer, currency, edgeType, year)
                coins.add(coin)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return coins
    }

    fun updateCoin(coin: Coin) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_COIN_NAME, coin.coinName)
            put(COLUMN_ISSUER, coin.issuer)
            put(COLUMN_CURRENCY, coin.currency)
            put(COLUMN_EDGE_TYPE, coin.edgeType)
            put(COLUMN_YEAR, coin.year)
        }
        db.update(
            TABLE_COINS,
            values,
            "$COLUMN_ID = ?",
            arrayOf(coin.id.toString())
        )
        db.close()
    }

    fun deleteCoin(coin: Coin) {
        val db = writableDatabase
        db.delete(
            TABLE_COINS,
            "$COLUMN_ID = ?",
            arrayOf(coin.id.toString())
        )
        db.close()
    }
}
