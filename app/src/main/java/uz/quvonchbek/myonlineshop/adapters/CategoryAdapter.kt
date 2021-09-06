package uz.quvonchbek.myonlineshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realpacific.clickshrinkeffect.applyClickShrink
import com.squareup.picasso.Picasso
import uz.quvonchbek.myonlineshop.R
import uz.quvonchbek.myonlineshop.databinding.ItemProductBinding
import uz.quvonchbek.myonlineshop.models.Product
import java.lang.Exception

class CategoryAdapter(var list: List<Product>, var onItemProductClick: OnItemProductClick): RecyclerView.Adapter<CategoryAdapter.MyCategoryHolder>() {

    inner class MyCategoryHolder(var binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root){

        fun onBind(product: Product, position: Int){

            binding.root.applyClickShrink()
            binding.type.text = product.type
            binding.name.text = product.name
            binding.price.text = product.cost
            binding.root.setOnClickListener {
                onItemProductClick.onProductItemClick(product)
            }

            Picasso.get().load(product.imgUrl!![0])
                .placeholder(R.drawable.placeholder)
                .into(binding.image, object : com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        binding.progressCircular.visibility = View.INVISIBLE
                    }

                    override fun onError(e: Exception?) {

                    }

                })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCategoryHolder {
     return MyCategoryHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyCategoryHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemProductClick{
        fun onProductItemClick(product: Product)
    }
}