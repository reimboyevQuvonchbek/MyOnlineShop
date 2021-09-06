package uz.quvonchbek.myonlineshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.quvonchbek.myonlineshop.adapters.CategoryAdapter
import uz.quvonchbek.myonlineshop.databinding.FragmentCategoryBinding
import uz.quvonchbek.myonlineshop.models.Category
import uz.quvonchbek.myonlineshop.models.Product

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryAdapter: CategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        var category=arguments?.getSerializable("key1") as Category

//        var list = ArrayList<String>()
//        list.add("https://storage.kun.uz/source/7/R_jGM8XSliwiveVImeW7aI9bA7W5Gl_5.jpg")
//        list.add("https://storage.kun.uz/source/7/ZBBN9N6vxCu9gJ-icYEflrwim0YiHZZW.jpg")
//        list.add("https://storage.kun.uz/source/7/ZBBN9N6vxCu9gJ-icYEflrwim0YiHZZW.jpg")
//        list.add("https://storage.kun.uz/source/7/M-t5CStOJDcs1lbT92rclpSZvOusom9D.jpg")
//        list.add("https://storage.kun.uz/source/7/Dd2Iwkyu76B3lDb-0JXfvgdGzdXCZSA7.jpg")




        categoryAdapter = CategoryAdapter(category.productList, object : CategoryAdapter.OnItemProductClick{
            override fun onProductItemClick(product: Product) {
                val bundle=Bundle()
                bundle.putSerializable("product",product)
                findNavController().navigate(R.id.productFragment,bundle)
            }

        })
        binding.rv.adapter = categoryAdapter
        return binding.root
    }

}