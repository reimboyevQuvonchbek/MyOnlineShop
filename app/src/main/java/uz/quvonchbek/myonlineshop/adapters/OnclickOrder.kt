package uz.quvonchbek.myonlineshop.adapters

import uz.quvonchbek.myonlineshop.models.Product

interface OnclickOrder {
    fun onclickItem(product: Product)
}