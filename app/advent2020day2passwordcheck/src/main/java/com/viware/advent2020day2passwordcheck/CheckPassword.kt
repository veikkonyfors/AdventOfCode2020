class CheckPassword(var password:String) {
    var  c:Char=' '
    var  min:Int=-1
    var  max:Int=-1

    init{
        val listOfCheckAndPassword=password.split(':')
        val listOfMinMaxChar=listOfCheckAndPassword[0].split(' ')
        c=listOfMinMaxChar[1].toCharArray()[0]
        val listOfMinMax=listOfMinMaxChar[0].split('-')
        min=listOfMinMax[0].toInt()
        max=listOfMinMax[1].toInt()
        password=listOfCheckAndPassword[1]
    }

    fun isOk():Boolean{
        var occurrences=password.count { it==c }

        when (occurrences) {
            in min .. max -> return true
            else -> return false
        }

    }

    override fun toString():String {
        return "c=$c, min=$min, max=$max, password=$password"
    }
}