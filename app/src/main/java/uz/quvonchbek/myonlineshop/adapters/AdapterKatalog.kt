package uz.quvonchbek.myonlineshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.quvonchbek.myonlineshop.R
import uz.quvonchbek.myonlineshop.databinding.ItemKatalogBinding
import uz.quvonchbek.myonlineshop.models.Katalog

class AdapterKatalog(var list:ArrayList<Katalog>, var onclickKatalog: OnclickKatalog):RecyclerView.Adapter<AdapterKatalog.VhTYpe>() {
    inner class VhTYpe(var itemKatalogBinding: ItemKatalogBinding):RecyclerView.ViewHolder(itemKatalogBinding.root){
        fun onBind(katalog: Katalog){
            itemKatalogBinding.name.text=katalog.name
            Picasso.get().load(katalog.imgUrl).placeholder(R.drawable.placeholder).into(itemKatalogBinding.image1)
            itemKatalogBinding.cardKatalog.setOnClickListener {
                onclickKatalog.onclickItem(katalog.name.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhTYpe {
        return VhTYpe(ItemKatalogBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VhTYpe, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
interface OnclickKatalog{
    fun onclickItem(typename:String)
}