package uz.quvonchbek.myonlineshop.models
class User{
    var id:String?=null
    var name:String?=null
    var phoneNumber:String?=null
    var adress:String?= null
    var password:String?=null
    constructor()
    constructor(
        id: String?,
        name: String?,
        phoneNumber: String?,
        adress: String?,
        password: String?
    ) {
        this.id = id
        this.name = name
        this.phoneNumber = phoneNumber
        this.adress = adress
        this.password = password
    }

}