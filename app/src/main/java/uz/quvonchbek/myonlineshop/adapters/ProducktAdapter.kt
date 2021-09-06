package uz.quvonchbek.myonlineshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.quvonchbek.myonlineshop.R
import uz.quvonchbek.myonlineshop.databinding.ItemOrder1Binding
import uz.quvonchbek.myonlineshop.models.Product

class ProducktAdapter(var productList:ArrayList<Product>, var onclickOrder: OnclickOrder):RecyclerView.Adapter<ProducktAdapter.VhOrder1>() {
    inner class VhOrder1(var itemOrder1Binding: ItemOrder1Binding)
        :RecyclerView.ViewHolder(itemOrder1Binding.root){
        fun onBind(product: Product){
            itemOrder1Binding.orderCard.setOnClickListener {
                onclickOrder.onclickItem(product)
            }
            itemOrder1Binding.orderCost.text=product.cost
            itemOrder1Binding.orderName.text=product.name
            itemOrder1Binding.orderType.text=product.type
            Picasso.get().load(product.imgUrl?.get(0)).placeholder(R.drawable.image).into(itemOrder1Binding.orderImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhOrder1 {
        return VhOrder1(ItemOrder1Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VhOrder1, position: Int) {
        holder.onBind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}