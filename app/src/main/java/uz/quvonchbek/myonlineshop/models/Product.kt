package uz.quvonchbek.myonlineshop.models

import java.io.Serializable

class Product:Serializable{
    var id: String?=null
    var name:String?=null
    var brand:String?=null
    var categoriya:String?=null
    var type:String?=null
    var description:String?=null
    var cost:String?=null
    var imgUrl:ArrayList<String>?=null
    var date:String?=null
    var time:String?=null
    constructor()
    constructor(
        id: String?,
        name: String?,
        brand: String?,
        categoriya: String?,
        type: String?,
        description: String?,
        cost: String?,
        imgUrl: ArrayList<String>?,
        date: String?,
        time: String?
    ) {
        this.id = id
        this.name = name
        this.brand = brand
        this.categoriya = categoriya
        this.type = type
        this.description = description
        this.cost = cost
        this.imgUrl = imgUrl
        this.date = date
        this.time = time
    }
}