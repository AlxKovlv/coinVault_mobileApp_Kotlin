package com.example.coinvault_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddCoinActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var editTextCoinName: EditText
    private lateinit var editTextCoinIssuer: EditText
    private lateinit var editTextCoinCurrency: EditText
    private lateinit var editTextCoinEdgeType: EditText
    private lateinit var editTextCoinYear: EditText
    private lateinit var buttonSaveCoin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coin)

        databaseHelper = DatabaseHelper(this)

        // Initialize UI components
        editTextCoinName = findViewById(R.id.editTextCoinName)
        editTextCoinIssuer = findViewById(R.id.editTextCoinIssuer)
        editTextCoinCurrency = findViewById(R.id.editTextCoinCurrency)
        editTextCoinEdgeType = findViewById(R.id.editTextCoinEdgeType)
        editTextCoinYear = findViewById(R.id.editTextCoinYear)
        buttonSaveCoin = findViewById(R.id.buttonSaveCoin)

        buttonSaveCoin.setOnClickListener {
            // Handle save coin button click
            saveCoin()
        }
    }

    private fun saveCoin() {
        val coinName = editTextCoinName.text.toString()
        val coinIssuer = editTextCoinIssuer.text.toString()
        val coinCurrency = editTextCoinCurrency.text.toString()
        val coinEdgeType = editTextCoinEdgeType.text.toString()
        val coinYear = editTextCoinYear.text.toString().toInt()

        val coin = Coin(0, coinName, coinIssuer, coinCurrency, coinEdgeType, coinYear)
        databaseHelper.addCoin(coin)

        Toast.makeText(this, "Coin added successfully", Toast.LENGTH_SHORT).show()
        finish() // Close the activity after adding the coin
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseHelper.close()
    }
}
