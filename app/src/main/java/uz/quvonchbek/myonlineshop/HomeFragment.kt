package uz.quvonchbek.myonlineshop
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.*
import uz.quvonchbek.myonlineshop.adapters.HomeAdapter1
import uz.quvonchbek.myonlineshop.adapters.OnclickRvItem
import uz.quvonchbek.myonlineshop.databinding.FragmentHomeBinding
import uz.quvonchbek.myonlineshop.models.Category
import uz.quvonchbek.myonlineshop.models.Product
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var categoryList:ArrayList<Category>
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var allproductlist:ArrayList<Product>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater, container, false)
        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference("product")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                allproductlist= ArrayList()
                for (child in snapshot.children) {
                    var children1 = child.children
                    for (child1 in children1){
                        var p=child1.getValue(Product::class.java)
                        allproductlist.add(p!!)
                    }

                }
                categoryList= ArrayList()
                categoryList.add(Category("Yangi mahsulotlar", allproductlist))
                categoryList.add(Category("Eng kop sotilganlar",allproductlist))
                categoryList.add(Category("Tavsiya qilinganlar",allproductlist))
                val homeAdapter1=HomeAdapter1(categoryList, object : OnclickRvItem{
                    override fun onclickAll(category: Category) {
                        var bundle=Bundle()
                        bundle.putSerializable("key1",category)
                        findNavController().navigate(R.id.categoryFragment,bundle)
                    }
                    override fun onclickItemOrder(product: Product) {
                        val bundle=Bundle()
                        bundle.putSerializable("product",product)
                        findNavController().navigate(R.id.productFragment,bundle)
                    }
                })
                binding.rv1.adapter=homeAdapter1
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return binding.root
    }
}