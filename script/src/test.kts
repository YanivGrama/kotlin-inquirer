@file:DependsOn("com.yg.kotlin.inquirer:core:1.0")
@file:DependsOn("com.yg.kotlin.inquirer:components:1.0")

import com.yg.kotlin.inquirer.components.promptInput
import com.yg.kotlin.inquirer.core.KInquirer

val name = KInquirer.promptInput("Enter Your Name:", hint = "(Full Name)")
//val age = KInquirer.promptInputNumber("Enter Your Age:")
//val password = KInquirer.promptInputPassword("Enter Your Password:")

println("Name: $name")
//println("Age: $age")
//println("Password: $password")

//val list = KInquirer.promptList("Chhooooddd:", listOf("A", "B", "C"))
//println(list)

