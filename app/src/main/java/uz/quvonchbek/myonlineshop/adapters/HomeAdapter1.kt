package uz.quvonchbek.myonlineshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.quvonchbek.myonlineshop.databinding.ItemRv2Binding
import uz.quvonchbek.myonlineshop.models.Category
import uz.quvonchbek.myonlineshop.models.Product

class HomeAdapter1(var categoryList: ArrayList<Category>,var onclickRvItem: OnclickRvItem):RecyclerView.Adapter<HomeAdapter1.VhRv1>() {
    inner class VhRv1(var itemRv2Binding: ItemRv2Binding):RecyclerView.ViewHolder(itemRv2Binding.root){
        fun onBind(category: Category){
            var orderAdapter1=ProducktAdapter(category.productList,object :OnclickOrder{
                override fun onclickItem(product: Product) {
                    onclickRvItem.onclickItemOrder(product)
                }
            })
            itemRv2Binding.nameRv.text=category.name
            itemRv2Binding.rv2.adapter=orderAdapter1
            itemRv2Binding.btnAll.setOnClickListener {
                onclickRvItem.onclickAll(category)
            }
        }
    }


    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhRv1 {
        return VhRv1(ItemRv2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VhRv1, position: Int) {
        holder.onBind(categoryList[position])
    }
}
interface OnclickRvItem{
    fun onclickAll(category: Category)
    fun onclickItemOrder(product: Product)
}