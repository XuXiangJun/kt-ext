package com.github.xuxiangjun.ext.io

enum class PrintColor(val value: Int) {
    BLACK(0),
    RED(1),
    GREEN(2),
    YELLOW(3),
    BLUE(4),
    PURPLE(5),
    CYAN(6),
    GRAY(7),
    DEFAULT(8)
}

enum class PrintTwinkle(val value: Int) {
    SLOW(5),
    FAST(6)
}

class PrintConfig(
    val foreColor: PrintColor = PrintColor.DEFAULT,
    val backColor: PrintColor? = null,
    val bold: Boolean = false,
    val blur: Boolean = false,
    val italic: Boolean = false,
    val underLine: Boolean = false,
    val twinkle: PrintTwinkle? = null,
    val hidden: Boolean = false
)

private fun genPrettyCharSequence(any: Any, config: PrintConfig = PrintConfig()): CharSequence {
    val sb = StringBuilder()
    sb.append("\u001B[")
    sb.append(30 + config.foreColor.value)
    if (config.backColor != null) {
        sb.append(";").append(40 + config.backColor.value)
    }
    if (config.bold) {
        sb.append(";").append("1")
    }
    if (config.blur) {
        sb.append(";").append("2")
    }
    if (config.italic) {
        sb.append(";").append("3")
    }
    if (config.underLine) {
        sb.append(";").append("4")
    }
    if (config.twinkle != null) {
        sb.append(";").append(config.twinkle.value)
    }
    if (config.hidden) {
        sb.append(";").append("7")
    }
    sb.append("m")
    sb.append(any)
    sb.append("\u001B[m")
    return sb
}

fun prettyPrint(any: Any, config: PrintConfig = PrintConfig()) {
    print(genPrettyCharSequence(any, config))
}

fun prettyPrintln(any: Any, config: PrintConfig = PrintConfig()) {
    println(genPrettyCharSequence(any, config))
}
