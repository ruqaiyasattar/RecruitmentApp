package app.bootcamp.com.recruitapp.models

class Post {
    var jobCom: String = ""
    var postTyp: String = ""
    var xperienc: String = ""
    var comSalary: String = ""
    var comType: String = ""
    var jobDesc: String = ""
    var comIndustry: String = ""
    var compId: String = ""
    var key:String = ""

    constructor() {}
    constructor(jobCom: String, postTyp: String, xperienc: String, comSalary: String, comType: String, jobDesc: String, comIndustry: String, compId: String) {
        this.jobCom = jobCom
        this.postTyp = postTyp
        this.xperienc = xperienc
        this.comSalary = comSalary
        this.comType = comType
        this.jobDesc = jobDesc
        this.comIndustry = comIndustry
        this.compId = compId
    }


}