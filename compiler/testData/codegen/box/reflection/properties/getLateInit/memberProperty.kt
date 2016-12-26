// IGNORE_BACKEND: JS
// WITH_REFLECT

class Foo {
    lateinit var result: String
}

fun box(): String {
    val foo = Foo()
    val p = foo::result
    val l = p.getLateInit() ?: return "Fail: getLateInit is null"
    if (l !== p.getLateInit()) return "Fail: different instances of LateInit"

    if (l.isSet) return "Fail: isSet should be false before lateinit property is set"
    l.reset()
    if (l.isSet) return "Fail: isSet should be false after reset"
    foo.result = "value"
    if (!l.isSet) return "Fail: isSet should be true after a value is set to the property"
    l.reset()
    if (l.isSet) return "Fail: isSet should be false after reset"

    try {
        foo.result
        return "Fail: explicit access should throw an exception for property that has been reset"
    }
    catch (e: Exception) {
        // OK
    }

    return "OK"
}
