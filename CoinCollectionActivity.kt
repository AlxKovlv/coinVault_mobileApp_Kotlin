import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coinvault_app.CoinCollectionAdapter
import com.example.coinvault_app.DatabaseHelper
import com.example.coinvault_app.R

class CoinCollectionActivity : AppCompatActivity() {
    private lateinit var recyclerViewCoinCollection: RecyclerView
    private lateinit var coinCollectionAdapter: CoinCollectionAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_collection)

        // Initialize UI components
        recyclerViewCoinCollection = findViewById(R.id.recyclerViewCoinCollection)

        // Set up RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerViewCoinCollection.layoutManager = layoutManager

        // Initialize DatabaseHelper
        databaseHelper = DatabaseHelper(this)

        // Retrieve the actual coin collection from the database
        val coinCollection = databaseHelper.getAllCoins()

        // Create the adapter with the actual coin collection
        coinCollectionAdapter = CoinCollectionAdapter(coinCollection)
        recyclerViewCoinCollection.adapter = coinCollectionAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        // Close the database connection
        databaseHelper.close()
    }
}
