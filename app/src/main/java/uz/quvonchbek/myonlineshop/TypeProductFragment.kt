package uz.quvonchbek.myonlineshop
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.*
import uz.quvonchbek.myonlineshop.adapters.RvAdapter
import uz.quvonchbek.myonlineshop.databinding.FragmentTypeProductBinding
import uz.quvonchbek.myonlineshop.models.Product

class TypeProductFragment : Fragment() {
    lateinit var binding: FragmentTypeProductBinding
    lateinit var rvAdapter: RvAdapter
    lateinit var list: ArrayList<Product>
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentTypeProductBinding.inflate(layoutInflater, container, false)
        var typname=arguments?.getString("key_type")
        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference("product")
        reference.child(typname.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var children = snapshot.children
                list= ArrayList()
                for (child in children) {
                    var p=child.getValue(Product::class.java)
                    list.add(p!!)
                }
                rvAdapter= RvAdapter(list,object : RvAdapter.OnClicListener{
                    override fun onItemClic(product: Product, position: Int) {
                        var bundle=Bundle()
                        bundle.putSerializable("product",product)
                        findNavController().navigate(R.id.productFragment,bundle)
                    }

                })
                binding.rv.adapter=rvAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



        return binding.root
    }
}