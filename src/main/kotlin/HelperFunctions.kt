package main.kotlin

import java.io.BufferedReader

object HelperFunctions {
  fun syscall(cmd: String, vararg args: String?): String {
    val process = ProcessBuilder(cmd, *args)
      .apply { environment()["TERM"] = "xterm" }
//      .redirectOutput(ProcessBuilder.Redirect.INHERIT)
//      .redirectErrorStream(true)
      .start()
    process.waitFor()
    val stringResult =
      process.inputStream.bufferedReader()
        .use(BufferedReader::readText)
        .trim()
    println("stringResult: $stringResult")
    return if ( stringResult.startsWith("tput")  ) "45" else stringResult
  }

  fun getTermSize(): Pair<Int, Int> {
    return Pair (
      syscall("tput", "cols").toInt(),
      syscall("tput", "lines").toInt()
    )
  }
}
