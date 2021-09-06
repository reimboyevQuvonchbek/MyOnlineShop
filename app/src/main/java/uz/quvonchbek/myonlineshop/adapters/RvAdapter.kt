package uz.quvonchbek.myonlineshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.quvonchbek.myonlineshop.databinding.RvItemBinding
import uz.quvonchbek.myonlineshop.models.Product


class RvAdapter(private val list: List<Product>, var listener: OnClicListener) :
    RecyclerView.Adapter<RvAdapter.MV>() {

    inner class MV(private val itemBinding: RvItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(product: Product, position: Int) {
            itemBinding.type.text = product.type
            itemBinding.brand.text = product.name
            itemBinding.perYear.text = product.cost
            Picasso.get().load(product.imgUrl!![0]).into(itemBinding.img)

            itemBinding.root.setOnClickListener {
                listener.onItemClic(product, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MV {
        return MV(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MV, position: Int) {

        holder.onBind(list[position], position)
    }

    interface OnClicListener {
        fun onItemClic(product: Product, position: Int)
    }
}