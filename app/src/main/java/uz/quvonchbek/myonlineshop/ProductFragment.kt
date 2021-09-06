package uz.quvonchbek.myonlineshop

import android.annotation.SuppressLint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.realpacific.clickshrinkeffect.applyClickShrink
import com.shashank.sony.fancygifdialoglib.FancyGifDialog
import uz.quvonchbek.myonlineshop.adapters.MyViewPagerAdapter
import uz.quvonchbek.myonlineshop.databinding.FragmentProductBinding
import uz.quvonchbek.myonlineshop.models.Order
import uz.quvonchbek.myonlineshop.models.Product
import uz.quvonchbek.myonlineshop.models.User


class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private lateinit var myViewPagerAdapter: MyViewPagerAdapter
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    private var z = true
    lateinit var user:User
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(layoutInflater)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("korzinka")
        user = MainActivity.getUserA(requireContext())
        var product = arguments?.getSerializable("product") as Product
        reference.child(user.id.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                for (child in children) {
                    var order=child.getValue(Order::class.java)
                    if(order?.productName == product.name){
                        z=false
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        binding.addCardToBasket.applyClickShrink()
        binding.deleteOrder.applyClickShrink()
        binding.data.applyClickShrink()
        myViewPagerAdapter = MyViewPagerAdapter(product, childFragmentManager)
        (requireActivity() as MainActivity).actionBar?.setTitle(product.name)
        setHasOptionsMenu(true)
        setBinding(product)
        setClicks(product)
        return binding.root
    }

    @SuppressLint("ResourceType")
    private fun setClicks(product: Product) {
        binding.data.setOnClickListener {

            var s =
                "Narxi: " + product?.cost + "\n" + "Modeli: " + product.name + "\n" + "Turi: " + product.type + "\n" + "Vaqt: " + product.date + "\n" + product.description
            FancyGifDialog.Builder(requireContext())
                .setTitle(product.name)
                .setMessage(s)
                .setNegativeBtnText("Yopish")
                .setPositiveBtnBackground(R.color.purple_200)
                .setPositiveBtnText("Ok")
                .setNegativeBtnBackground(R.color.red)
                .isCancellable(true)
                .OnPositiveClicked {
                    Toast.makeText(requireContext(), "Ok", Toast.LENGTH_SHORT).show()
                }
                .OnNegativeClicked {
                    Toast.makeText(requireContext(), "Yopish", Toast.LENGTH_SHORT).show()
                }
                .build()
        }
        binding.addCardToBasket.setOnClickListener {
            if (z) {
                z = true
                var id = reference.push().key.toString()

                var order = Order(
                    product.name,
                    id,
                    product.cost,
                    false,
                    user.id,
                    false,
                    user.name,
                    product.imgUrl,
                    user.adress,
                    user.phoneNumber
                )
                reference.child("${user.id.toString()}/${order.id.toString()}").setValue(order)

            } else {
                Toast.makeText(
                    requireContext(),
                    "Bu mahsulot savatchaga qo`shilgan",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }

    private fun setBinding(product: Product) {

        binding.viewPager.adapter = myViewPagerAdapter
        binding.name.text = product.name

        binding.price.text = product.cost
        binding.pageIndicatorView.setViewPager(binding.viewPager)

    }


}