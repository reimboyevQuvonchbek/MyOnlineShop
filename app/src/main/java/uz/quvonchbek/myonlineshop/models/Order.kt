package uz.quvonchbek.myonlineshop.models

class Order {
    var productName: String? = null
    var id: String? = null
    var productPrice: String? = null
    var isExcept: Boolean? = null
    var clientId: String? = null
    var x:Boolean?=null
    var clientName: String? = null
    var imageUrls: List<String>? = null
    var clientAddress: String? = null
    var clientPhoneNumber: String? = null

    constructor()
    constructor(
        productName: String?,
        id: String?,
        productPrice: String?,
        isExcept: Boolean?,
        clientId: String?,
        x: Boolean?,
        clientName: String?,
        imageUrls: List<String>?,
        clientAddress: String?,
        clientPhoneNumber: String?
    ) {
        this.productName = productName
        this.id = id
        this.productPrice = productPrice
        this.isExcept = isExcept
        this.clientId = clientId
        this.x = x
        this.clientName = clientName
        this.imageUrls = imageUrls
        this.clientAddress = clientAddress
        this.clientPhoneNumber = clientPhoneNumber
    }


}