// IGNORE_BACKEND: JS
// WITH_REFLECT

import kotlin.reflect.KProperty
import kotlin.test.*

object Delegate {
    var storage = ""
    operator fun getValue(instance: Any?, property: KProperty<*>) = storage
    operator fun setValue(instance: Any?, property: KProperty<*>, value: String) { storage = value }
}

class Foo {
    var result: String by Delegate
}

fun box(): String {
    val foo = Foo()
    foo.result = "Fail"
    val d = Foo::result.getDelegate(foo) as Delegate
    foo.result = "OK"
    assertEquals(d, Foo::result.getDelegate(foo))
    return d.getValue(foo, Foo::result)
}
