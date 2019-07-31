@file:DependsOn("com.yg.kotlin.inquirer:core:1.0")
@file:DependsOn("com.yg.kotlin.inquirer:components:1.0")

import com.yg.kotlin.inquirer.components.ConfirmComponent
import com.yg.kotlin.inquirer.core.interact

val component = ConfirmComponent("Are You OK ?")
interact(component)
println(component.value())