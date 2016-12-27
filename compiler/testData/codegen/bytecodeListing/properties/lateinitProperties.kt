// WITH_RUNTIME

class MyClass {
    lateinit var x: String

    @get:JvmName("bar")
    lateinit var foo: String

    companion object {
        lateinit var companion: String

        @JvmStatic
        lateinit var jvmStatic: String
    }
}

object MyObject {
    lateinit var x: String

    @JvmStatic
    lateinit var jvmStatic: String
}
