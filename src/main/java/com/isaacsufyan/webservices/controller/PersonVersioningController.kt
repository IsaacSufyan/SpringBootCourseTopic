package com.isaacsufyan.webservices.controller

import com.isaacsufyan.webservices.beans.PersonV1
import com.isaacsufyan.webservices.beans.PersonV2
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonVersioningController {

//    http://localhost:8080/v1/person
    @GetMapping("v1/person")
    fun personV1(): PersonV1 {
        return PersonV1("Sufyan")
    }

    @GetMapping("v2/person")
    fun personV2(): PersonV2 {
        return PersonV2(firstName = "Sufyan", secondName = "Sattar")
    }


//    http://localhost:8080/person/param?version=1
    @GetMapping(value = ["/person/param"], params = ["version=1"])
    fun personParamV1(): PersonV1 {
        return PersonV1("Sufyan")
    }
//
//    @GetMapping(value = ["/person/param"], params = ["version=2"])
//    fun personParamV2(): PersonV2 {
//        return PersonV2(firstName = "Sufyan", secondName = "Sattar")
//    }

//    http://localhost:8080/person/header
//    Key = X-API-VERSION   and Value =1

    @GetMapping(value = ["/person/header"], headers = ["X-API-VERSION=1"])
    fun peronHeaderV1(): PersonV1 {
        return PersonV1("Sufyan")
    }
//
//    @GetMapping(value = ["/person/header"], headers = ["X-API-VERSION=2"])
//    fun personHeaderV2(): PersonV2 {
//        return PersonV2(firstName = "Sufyan", secondName = "Sattar")
//    }

//    http://localhost:8080/person/produces
//    Key = Accept   and Value = application/vnd.company.app-v1+json
    @GetMapping(value = ["/person/produces"], produces = ["application/vnd.company.app-v1+json"])
    fun peronProducesV1(): PersonV1 {
        return PersonV1("Sufyan")
    }

    @GetMapping(value = ["/person/produces"], produces = ["application/vnd.company.app-v2+json"])
    fun personProducesV2(): PersonV2 {
        return PersonV2(firstName = "Sufyan", secondName = "Sattar")
    }
}