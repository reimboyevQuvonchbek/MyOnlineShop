package uz.quvonchbek.myonlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.google.gson.Gson
import uz.quvonchbek.myonlineshop.databinding.ActivityLoginBinding
import uz.quvonchbek.myonlineshop.models.User
import uz.quvonchbek.myonlineshop.utils.MyShared

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference("users")
        binding.btnRegister.setOnClickListener {
            var intent= Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            var username=binding.name.text.toString()
            var password=binding.password.text.toString()
            if(username.isNotEmpty() && password.isNotEmpty()){
                reference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val children = snapshot.children
                        for (child in children) {
                            var u=child.getValue(User::class.java)
                            if(username == u?.name && password == u.password){
                                var intent=Intent(this@LoginActivity,MainActivity::class.java)
                                startActivity(intent)
                                val myShared1: MyShared = MyShared.getInstance(this@LoginActivity)
                                myShared1.activeUser = Gson().toJson(u)
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })
            }else{
                Toast.makeText(this, "username yoki parol kiritilmagan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}