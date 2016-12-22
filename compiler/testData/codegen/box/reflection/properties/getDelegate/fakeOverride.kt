// IGNORE_BACKEND: JS
// WITH_REFLECT

import kotlin.reflect.KProperty
import kotlin.test.*

object Delegate {
    operator fun getValue(instance: Any?, property: KProperty<*>) = "OK"
}

open class Base {
    val x: String by Delegate
}

class Derived : Base()

fun box(): String {
    val d = Derived()
    assertEquals(Base::x.getDelegate(d), Derived::x.getDelegate(d))
    return d.x
}
