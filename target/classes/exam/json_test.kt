package exam


class json_test {
}


class O_person(
    n_id: Int,
    s_name: String
){
    // val n_id = n_id //not needed
    // val s_name = s_name // not needed

}
fun main(args: Array<String>) {

    var a_o_person: MutableList<O_person> = mutableListOf();

    for(n_i in 0..10){
        a_o_person.add(
            O_person(
                n_i,
                "Person-${n_i}"
            )
        )
    }

    println(a_o_person)

//    print(JSONArray(a_o_person))
    // println(o)
}