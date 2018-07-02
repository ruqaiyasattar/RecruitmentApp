package app.bootcamp.com.recruitapp.models

open class AppUser{
    var id = ""
    var name = ""
    var email = ""
    var password = ""
    var type = 0

    constructor()

    constructor(id:String, name: String, email: String, password: String,type:Int) : this() {
        this.id = id
        this.name = name
        this.email = email
        this.password = password
        this.type = type
    }
}

class CompanyUser : AppUser{
    var about = ""
    var contact  = ""
    var location = ""
    var image = ""

    constructor()

    constructor(id:String, name: String, email: String, password: String,
                about:String,contact:String,location:String,image:String) : super(id,name,email,password,1) {
        this.about = about
        this.contact = contact
        this.location = location
        this.image = image
    }
}

class StudentUser : AppUser {

    var age = 0
    var qualification = ""
    var city = ""


    constructor()


    constructor(id:String, name: String, email: String, password: String,
                age:Int, qualification:String, city:String) : super(id,name,email,password,2) {
        this.age = age
        this.qualification = qualification
        this.city = city
    }
}


