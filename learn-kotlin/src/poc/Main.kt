package poc

fun main(args: Array<String>) {
	println("Welcome To Kotlin")
	var user = User()
	user.name = "Rahul"
	user.salary = 50000

	println("Users: ${user.name}")
	doNullCheck("name")
	
	var userJava = UserJava()
	
//	Stream.of(1,2,3,4,5)
//		.filter({value -> value%2==0})
//		.forEach({value -> println(value)})
}

private fun doNullCheck(name:String) {
	var nullCheck: String? = null
	println(nullCheck?.length)
}