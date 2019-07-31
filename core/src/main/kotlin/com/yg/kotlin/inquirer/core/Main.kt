package com.yg.kotlin.inquirer.core

import com.yg.kotlin.inquirer.core.components.IComponent
import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import org.jline.utils.NonBlockingReader

fun <T> interact(component: IComponent<T>) {
    runTerminal { reader ->
        println(component.render())
        while (component.interacting()) {
            val event = waitForInteraction(reader)
            component.onEvent(event)
            renderView(component.render())
        }
    }
}

fun runTerminal(func: (reader: NonBlockingReader) -> Unit) {
    val terminal: Terminal = TerminalBuilder.builder().jna(true).system(true).build()
    terminal.enterRawMode()
    val reader: NonBlockingReader = terminal.reader()

    func(reader)

    reader.close()
    terminal.close()
}

private fun renderView(view: String) {
    print("\u001b[${view.lines().size}A")
    print("\u001b[0J")
    println(view)
}

private fun waitForInteraction(reader: NonBlockingReader): Event {
    return when (val c = reader.read()) {
        13 -> Event.PressEnter
        32 -> Event.PressSpace
        27 -> readEscValues(reader)
        else -> Event.Character(c.toChar())
    }
}

private fun readEscValues(reader: NonBlockingReader): Event {
    if (reader.read() == '['.toInt()) {
        return when (reader.read()) {
            65 -> Event.PressUp     //"↑"
            66 -> Event.PressDown   //"↓"
            67 -> Event.PressRight  //"→"
            68 -> Event.PressLeft   //"←"
            else -> Event.NotSupportedChar
        }
    }
    return Event.NotSupportedChar
}
