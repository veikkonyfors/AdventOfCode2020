package com.viware.advent2020day4validatepassports

import android.util.Range

class  Passport constructor(val credentials:String) {

    private val birthYear: Int? = getCredential("byr").toIntOrNull()
    val issueYear: Int? // Property may be initialized in init block as well
    val expirationYear: Int? = getCredential("eyr").toIntOrNull()
    val height: String = getCredential("hgt")
    val hairColor: String = getCredential("hcl")
    val eyeColor: String = getCredential("ecl")
    val passportID: Int? = getCredential("pid").toIntOrNull()
    val countryID: Int? = getCredential("cid").toIntOrNull()

    val listOfTypes=listOf("byr","iyr","eyr","hgt","hcl","ecl","pid")//,"cid")

    val byrRange=(1920..2002)
    val iyrRange=(2010..2020)
    val eyrRange=(2020..2030)
    val heightInRange=(59..76)
    val heightCmRange=(150..193)
    val validEyeColors=listOf("amb","blu","brn","gry","grn","hzl","oth")

    init{
        issueYear=getCredential("iyr").toIntOrNull()
    }

    fun getCredential(type: String): String {
        val fields = credentials.split(' ')
        fields.forEach { s ->
            if (s.startsWith(type)) {
                val list = s.split(":")
                return list[1]
            }
        }
        return ""
    }

    fun isValid():Boolean{
        listOfTypes.forEach{
            when(it){
                "byr" -> if( ! byrRange.contains(birthYear)) return false.also{println("byr failed")}
                "iyr" -> if( ! iyrRange.contains(issueYear)) return false.also{println("iyr failed")}
                "eyr" -> if( ! eyrRange.contains(expirationYear)) return false.also{println("eyr failed")}
                "hgt" -> if( ! Height(height,heightInRange,heightCmRange).isValid()) return false.also{println("hgt failed")}
                "hcl" -> if( ! HairColor(hairColor).isValid()) return false.also{println("hcl failed")}
                "ecl" -> if( ! validEyeColors.contains(eyeColor)) return false.also{println("ecl failed")}
                "pid" -> if(it.filter({it -> !it.isDigit()}).isNotEmpty() || it.length!=9) return false.also{println("pid failed")}
            }
        }
        return true
    }

    fun isValid(type:String):Boolean{
        when(type){
            "byr" -> {return byrRange.contains(getCredential(type).toInt())}
        }
        return false
    }

    fun isOk():Boolean{
        return birthYear!=null && issueYear!=null && expirationYear!=null && height!="" && hairColor!="" && eyeColor!="" &&
        passportID!=null
    }
}

class Height (val credential:String, val validInRange:IntRange, val validCmRange:IntRange){

    val height:Int=credential.takeWhile { !it.isLetter() }.toInt()

    fun isIn():Boolean {return credential.contains("in") }
    fun isCm():Boolean {return credential.contains("cm") }

    fun isValid():Boolean {
        if(!isIn() && !isCm()) return false
        return if(isIn()) validInRange.contains(height)
        else validCmRange.contains(height)
    }
}

class HairColor(val credential:String){

    fun isValid():Boolean {
        if(!credential.startsWith("#")) return false.also{println("hcl failed, missing ^#")}
        if(credential.length!=7) return false.also{println("hcl failed with length")}
        if(credential.filter({it -> !it.isDigit()}).filter({!"abcdef#".contains(it)}).isNotEmpty()) return false.also{println("hcl failed with improper chars")}
        return true
    }
}
