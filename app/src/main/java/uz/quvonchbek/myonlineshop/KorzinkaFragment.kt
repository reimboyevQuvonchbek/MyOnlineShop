package uz.quvonchbek.myonlineshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.*
import com.realpacific.clickshrinkeffect.applyClickShrink
import uz.quvonchbek.myonlineshop.adapters.KarzinkaAdapter
import uz.quvonchbek.myonlineshop.databinding.FragmentKorzinkaBinding
import uz.quvonchbek.myonlineshop.models.Order

class KorzinkaFragment : Fragment() {
    private lateinit var binding: FragmentKorzinkaBinding
    private lateinit var karzinkaAdapter: KarzinkaAdapter
    private lateinit var orderList: ArrayList<Order>
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var reference2: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKorzinkaBinding.inflate(layoutInflater)
        binding.card.applyClickShrink()
        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference("korzinka")
        reference2=firebaseDatabase.getReference("orders")
        var userA=MainActivity.getUserA(requireContext())
        orderList=ArrayList()
        reference.child(userA.id.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                orderList.clear()
                for (child in children) {
                    var order = child.getValue(Order::class.java)
                    orderList.add(order!!)
                }
                if(orderList.isNotEmpty()) {
                    binding.text1.visibility = View.INVISIBLE
                    binding.progressCircular.visibility = View.INVISIBLE
                    binding.image.visibility = View.INVISIBLE
                    binding.card.visibility = View.INVISIBLE
                    binding.rv.visibility = View.VISIBLE

                    karzinkaAdapter = KarzinkaAdapter(
                        orderList,
                        object : KarzinkaAdapter.OnClickItem {
                            override fun onClickConform(
                                order: Order,
                                position: Int
                            ) {
                                order.isExcept=true
                                reference.child("${userA.id}/${order.id}/except").setValue(true)
                                reference2.child(order.id.toString()).setValue(order)
                                Toast.makeText(requireContext(), "On Click", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        })

                    binding.rv.adapter = karzinkaAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                Toast.makeText(requireContext(), "Internet bilan muommo bor", Toast.LENGTH_SHORT).show()
            }

        })
        //       orderList.removeAll(orderList)

        if (orderList.isEmpty()) {
            binding.progressCircular.visibility = View.INVISIBLE
            binding.rv.visibility = View.INVISIBLE
        }

        return binding.root
    }
}