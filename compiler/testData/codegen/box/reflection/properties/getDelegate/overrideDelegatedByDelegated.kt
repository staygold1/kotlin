// IGNORE_BACKEND: JS
// WITH_REFLECT

import kotlin.reflect.KProperty
import kotlin.test.*

class Delegate(val value: String) {
    operator fun getValue(instance: Any?, property: KProperty<*>) = value
}

open class Base {
    open val x: String by Delegate("Base")
}

class Derived : Base() {
    override val x: String by Delegate("Derived")
}

fun check(expected: String, delegate: Any?) {
    if (delegate == null) throw AssertionError("getDelegate returned null")
    assertEquals(expected, (delegate as Delegate).value)
}

fun box(): String {
    val base = Base()
    val derived = Derived()

    check("Base", Base::x.getDelegate(base))
    check("Base", base::x.getDelegate())
    check("Derived", Derived::x.getDelegate(derived))
    check("Derived", derived::x.getDelegate())

    // Note that Base.x is inaccessible through an instance of Derived, so its delegate is Derived.x's delegate
    check("Derived", Base::x.getDelegate(derived))

    return "OK"
}
