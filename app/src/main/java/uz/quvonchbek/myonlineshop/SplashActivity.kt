package uz.quvonchbek.myonlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.gson.Gson
import uz.quvonchbek.myonlineshop.models.User
import uz.quvonchbek.myonlineshop.utils.MyShared

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable { /* Create an Intent that will start the Menu-Activity. */
            val my: MyShared = MyShared.getInstance(this)
            try {
                val str: String = my.activeUser.toString()
                if(str.isNotEmpty()){
                    var activeUser: User = Gson().fromJson(str,User::class.java)
                    val mainIntent = Intent(this, MainActivity::class.java)
                    this.startActivity(mainIntent)
                    this.finish()
                }else{
                    val loginIntent = Intent(this, LoginActivity::class.java)
                    this.startActivity(loginIntent)
                    this.finish()
                }
            } catch (e: Exception) {
                e.message
            }

        }, 1000)
    }
}