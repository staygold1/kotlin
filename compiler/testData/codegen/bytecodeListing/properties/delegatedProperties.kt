// WITH_RUNTIME

object Delegate {
    operator fun getValue(a: Any?, b: Any?): String = ""
    operator fun setValue(a: Any?, b: Any?, c: String) {}
}

class MyClass {
    var x by Delegate
    val String.x by Delegate
    var Int.x by Delegate

    companion object {
        val companion by Delegate

        @JvmStatic
        val jvmStatic by Delegate
    }
}

object MyObject {
    var x by Delegate
    val String.x by Delegate
    var Int.x by Delegate

    @JvmStatic
    val jvmStatic by Delegate
    @JvmStatic
    val String.jvmStatic by Delegate
    @JvmStatic
    val Int.jvmStatic by Delegate
}

var y by Delegate
var Double.y by Delegate
val Unit.y by Delegate
