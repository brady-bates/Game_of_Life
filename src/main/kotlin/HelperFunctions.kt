package main.kotlin

import java.io.BufferedReader

object HelperFunctions {
  fun syscall(cmd: String, vararg args: String?): String {
    val process = ProcessBuilder(cmd, *args)
      .apply { environment()["TERM"] = "xterm" }
      .redirectOutput(ProcessBuilder.Redirect.INHERIT)
      .redirectErrorStream(true)
      .start()
    process.waitFor()
    val stringResult =
      process.inputStream.bufferedReader()
        .use(BufferedReader::readText)
        .trim()
    print(stringResult)
    return if ( stringResult.startsWith("tput") || stringResult == """""" ) "45" else stringResult
  }

  fun getTermSize(): Pair<Int, Int> {
    print("${Pair (
      syscall("tput", "cols").toInt(),
      syscall("tput", "lines").toInt()
    )}")

    return Pair (
      syscall("tput", "cols").toInt(),
      syscall("tput", "lines").toInt()
    )
  }
}
