package uz.quvonchbek.myonlineshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.realpacific.clickshrinkeffect.applyClickShrink
import com.squareup.picasso.Picasso
import uz.quvonchbek.myonlineshop.databinding.ItemKarzinkaBinding
import uz.quvonchbek.myonlineshop.models.Order


class KarzinkaAdapter(
    var list: List<Order>,
    var onClickItem: OnClickItem
) : RecyclerView.Adapter<KarzinkaAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: ItemKarzinkaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(order: Order, position: Int) {
            binding.productName.text = order.productName
            binding.productPrice.text = order.productPrice.toString()
            if (order.x!!)
                binding.isRequestOrder.visibility = View.VISIBLE
            else
                binding.isRequestOrder.visibility = View.INVISIBLE
            if(order.isExcept!!){
                binding.textbtn.text="Buyurtma berildi"
            }else{
                binding.textbtn.text="Sotib olish"
            }

            Picasso.get().load(order.imageUrls?.get(0))
                .into(binding.imageView)

            binding.conform.applyClickShrink()

            binding.conform.setOnClickListener {

                onClickItem.onClickConform(order, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemKarzinkaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnClickItem {
        fun onClickConform(order: Order, position: Int)
    }
}