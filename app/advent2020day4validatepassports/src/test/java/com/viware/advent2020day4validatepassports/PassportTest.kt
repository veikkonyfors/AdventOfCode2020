package com.viware.advent2020day4validatepassports

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class PassportTest {

    lateinit var listOfCredentials:List<String>
    lateinit var listOfCredentialLines:List<String>
    lateinit var listOfInValidCredentialLines:List<String>
    lateinit var listOfValidCredentialLines:List<String>

    @Before
    fun setUp() {
        listOfCredentials = listOf(
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm",
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929",
            "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm",
            "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"
        )

        listOfCredentialLines = listOf(
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
            "byr:1937 iyr:2017 cid:147 hgt:183cm",
            "",
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
            "hcl:#cfa07d byr:1929",
            "",
            "hcl:#ae17e1 iyr:2013",
            "eyr:2024",
            "ecl:brn pid:760753108 byr:1931",
            "hgt:179cm",
            "",
            "hcl:#cfa07d eyr:2025 pid:166559648",
            "iyr:2011 ecl:brn hgt:59in"
        )

        listOfInValidCredentialLines = listOf(
            "eyr:1972 cid:100",
            "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926",
            "",
            "iyr:2019",
            "hcl:#602927 eyr:1967 hgt:170cm",
            "ecl:grn pid:012533040 byr:1946",
            "",
            "hcl:dab227 iyr:2012",
            "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277",
            "",
            "hgt:59cm ecl:zzz",
            "eyr:2038 hcl:74454a iyr:2023",
            "pid:3556412378 byr:2007"
        )

        listOfValidCredentialLines = listOf(
            "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980",
            "hcl:#623a2f",
            "",
            "eyr:2029 ecl:blu cid:129 byr:1989",
            "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm",
            "",
            "hcl:#888785",
            "hgt:164cm byr:2001 iyr:2015 cid:88",
            "pid:545766238 ecl:hzl",
            "eyr:2022",
            "",
            "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"
        )
    }


    @After
    fun tearDown() {
    }

    @Test
    fun testGetBYR(){
        assert(Passport(listOfCredentials[0]).getCredential("byr") != "")
        assert(Passport(listOfCredentials[0]).getCredential("byr") == "1937")
        assert(Passport(listOfCredentials[3]).getCredential("byr") == "")
    }

    @Test
    fun testGetIYR(){
        assert(Passport(listOfCredentials[0]).getCredential("iyr") != "")
        assert(Passport(listOfCredentials[0]).getCredential("iyr") == "2017")
        assert(Passport(listOfCredentials[3]).getCredential("iyr") == "2011")
    }

    @Test
    fun testIsOk(){
        assert(Passport(listOfCredentials[0]).isOk())
        assertFalse(Passport(listOfCredentials[1]).isOk())
        assert(Passport(listOfCredentials[2]).isOk())
        assertFalse(Passport(listOfCredentials[3]).isOk())
    }

    @Test
    fun testCredentialBuilder(){
        val listOfCredentials=
            CredentialBuilder(listOfCredentialLines).buildCredentials()

        assert(Passport(listOfCredentials[0]).isOk())
        assertFalse(Passport(listOfCredentials[1]).isOk())
        assert(Passport(listOfCredentials[2]).isOk())
        assertFalse(Passport(listOfCredentials[3]).isOk())
    }

    @Test
    fun testIsNonValidAll(){
        val listOfCredentials=
            CredentialBuilder(listOfInValidCredentialLines).buildCredentials().forEach{
                println(it)
                assertFalse(Passport(it).isValid())
            }
    }

    @Test
    fun testIsNonValid(){
        val listOfCredentials=
            CredentialBuilder(listOfInValidCredentialLines).buildCredentials()
                println(listOfCredentials[2])
                assertFalse(Passport(listOfCredentials[2]).isValid())
    }

    @Test
    fun testIsValidType(){
        val listOfCredentials=
            CredentialBuilder(listOfInValidCredentialLines).buildCredentials()
                assertTrue(Passport(listOfCredentials[0]).isValid("byr"))
    }

    @Test
    fun testHairColor(){
        assertFalse(HairColor("dab227").isValid())
        assert(HairColor("#dab227").isValid())
        assertFalse(HairColor("#dab22799").isValid())
        assertFalse(HairColor("#dab22g").isValid())
    }

    @Test
    fun testEyeColor(){
        assertFalse(Passport("hgt:150cm ecl:zzz eyr:2030 hcl:#74454a iyr:2020 pid:3556412378 byr:2002").isValid())
    }

    @Test
    fun testPid(){
        assertFalse(Passport("hgt:150cm ecl:brn eyr:2030 hcl:#74454a iyr:2020 pid:3556412378 byr:2002").isValid())
        assertFalse(Passport("hgt:150cm ecl:brn eyr:2030 hcl:#74454a iyr:2020 pid:35564123a byr:2002").isValid())

    }
}