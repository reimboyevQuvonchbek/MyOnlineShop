package uz.quvonchbek.myonlineshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import uz.quvonchbek.myonlineshop.databinding.ActivityRegisterBinding
import uz.quvonchbek.myonlineshop.models.User

class RegisterActivity : AppCompatActivity() {
    lateinit var binding:ActivityRegisterBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference("users")
        binding.registerBtn.setOnClickListener {
            var newName=binding.newName.text.toString()
            var newPassword=binding.newPassword.text.toString()
            var newAdres=binding.newAdress.text.toString()
            var newNumber=binding.newNumber.text.toString()
            if(newName.isNotEmpty() && newPassword.isNotEmpty() && newNumber.isNotEmpty() && newAdres.isNotEmpty()){
                val id = reference.push().key.toString()
                var newUser=User(id,newName,newNumber,newAdres,newPassword)
                var z=true
                reference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val children = snapshot.children
                        for (child in children) {
                            var u = child.getValue(User::class.java)
                            if (newUser.name == u?.name) {
                                z = false
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@RegisterActivity, "Xatolik 1", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
                if(z){
                    reference.child(id).setValue(newUser)
                    finish()
                }else{
                    Toast.makeText(this, "Bunday username royhatdan otgan", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "Barcha bo`limlar to`ldirilishi kerak", Toast.LENGTH_SHORT).show()
            }
        }

    }
}