// IGNORE_BACKEND: JS
// WITH_REFLECT

import kotlin.reflect.KProperty
import kotlin.test.*

object Delegate {
    operator fun getValue(instance: Any?, property: KProperty<*>) = true
}

class Foo {
    val isOK: Boolean by Delegate
}

fun box(): String {
    val foo = Foo()
    assertEquals(Delegate, Foo::isOK.getDelegate(foo))
    assertEquals(Delegate, foo::isOK.getDelegate())
    return if (foo.isOK) "OK" else "Fail"
}
