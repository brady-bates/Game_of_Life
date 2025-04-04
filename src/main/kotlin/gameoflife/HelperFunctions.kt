package gameoflife

import java.io.BufferedReader

object HelperFunctions {

  fun syscall(cmd: String, vararg args: String?): String {
    val process = ProcessBuilder(cmd, *args)
//      .redirectOutput(ProcessBuilder.Redirect.INHERIT)
//      .redirectErrorStream(true)
      .start()
    process.waitFor()
    val stringResult = process.inputStream.bufferedReader()
        .use(BufferedReader::readText)
        .trim()
    return stringResult
  }

}
