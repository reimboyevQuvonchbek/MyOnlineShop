package uz.quvonchbek.myonlineshop

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import uz.quvonchbek.myonlineshop.models.User
import uz.quvonchbek.myonlineshop.utils.MyShared

class MainActivity : AppCompatActivity() {
    companion object{
        fun getUserA(context: Context): User {
            val myShared= MyShared.getInstance(context)
            return  Gson().fromJson(myShared.activeUser.toString(), User::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottomBar)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment2, R.id.korzinkaFragment, R.id.korzinkaFragment,R.id.acauntFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}