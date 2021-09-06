package uz.quvonchbek.myonlineshop.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.quvonchbek.myonlineshop.ImageFragment
import uz.quvonchbek.myonlineshop.models.Product

class MyViewPagerAdapter(var product: Product, var fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager){


    override fun getCount(): Int  = product.imgUrl!!.size

    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(product.imgUrl!![position], "")
    }
}