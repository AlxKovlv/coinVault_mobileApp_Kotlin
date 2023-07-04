import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coinvault_app.AddCoinActivity
import com.example.coinvault_app.R
import com.example.coinvault_app.UserPreferencesManager

data class Coin(
    val id: Int,
    val coinName: String,
    val issuer: String,
    val currency: String,
    val edgeType: String,
    val year: Int
)

class CoinAdapter(private val coinList: List<Coin>) :
    RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewCoinName: TextView = itemView.findViewById(R.id.textViewCoinName)
        private val textViewCoinIssuer: TextView = itemView.findViewById(R.id.textViewCoinIssuer)
        private val textViewCoinYear: TextView = itemView.findViewById(R.id.textViewCoinYear)

        fun bind(coin: Coin) {
            textViewCoinName.text = coin.coinName
            textViewCoinIssuer.text = coin.issuer
            textViewCoinYear.text = coin.year.toString()

            // Set click listener for each coin item
            itemView.setOnClickListener {
                navigateToCoinDetailScreen(coin)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin, parent, false)
        return CoinViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinList[position]
        holder.bind(coin)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var userPreferencesManager: UserPreferencesManager
    private lateinit var recyclerViewCoins: RecyclerView
    private lateinit var buttonAddCoin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userPreferencesManager = UserPreferencesManager(this)

        recyclerViewCoins = findViewById(R.id.recyclerViewCoins)
        buttonAddCoin = findViewById(R.id.buttonAddCoin)

        // Set up RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerViewCoins.layoutManager = layoutManager

        val coinAdapter = CoinAdapter(getDummyCoinList()) // Replace with your actual coin list
        recyclerViewCoins.adapter = coinAdapter

        buttonAddCoin.setOnClickListener {
            // Handle add coin button click
            navigateToAddCoinScreen()
        }

        if (userPreferencesManager.shouldShowTutorial()) {
            // Show the tutorial screen
            showTutorial()
            // After showing the tutorial, update the preference to hide it next time
            userPreferencesManager.setShowTutorial(false)
        }
    }

    private fun getDummyCoinList(): List<Coin> {
        // Return a dummy list of coins for testing
        // Replace this with your actual coin data retrieval logic
        return listOf(
            Coin(1, "Coin 1", "Issuer 1", "Currency 1", "Edge Type 1", 2021),
            Coin(2, "Coin 2", "Issuer 2", "Currency 2", "Edge Type 2", 2022),
            Coin(3, "Coin 3", "Issuer 3", "Currency 3", "Edge Type 3", 2023)
        )
    }

    private fun navigateToAddCoinScreen() {
        // Navigate to the add coin screen activity
        // Replace AddCoinActivity::class.java with the actual add coin screen activity
        startActivity(Intent(this, AddCoinActivity::class.java))
    }

    private fun navigateToCoinDetailScreen(coin: Coin) {
        // Navigate to the coin detail screen activity and pass the selected coin's information
        // Replace CoinDetailActivity::class.java with the actual coin detail screen activity
        val intent = Intent(this, CoinDetailActivity::class.java)
        intent.putExtra("coinId", coin.id) // Pass the selected coin's ID or other relevant information
        startActivity(intent)
    }

    private fun showTutorial() {
        // TODO: Implement the logic to show the tutorial screen
    }
}
