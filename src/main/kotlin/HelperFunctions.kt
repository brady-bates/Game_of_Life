object HelperFunctions {
  fun syscall(cmd: String, args: String = "") {
    val result = ProcessBuilder(cmd, args)
      .redirectOutput(ProcessBuilder.Redirect.INHERIT)
      .redirectError(ProcessBuilder.Redirect.INHERIT)
      .start()
      .waitFor()
    println(result)
  }
}


fun main() {
  print(HelperFunctions.syscall("echo", "'test'"))
  print(HelperFunctions.syscall("clear"))
}