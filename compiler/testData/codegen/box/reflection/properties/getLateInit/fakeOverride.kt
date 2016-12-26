// IGNORE_BACKEND: JS
// WITH_REFLECT

import kotlin.test.*

open class Base {
    lateinit var x: String
}

class Derived : Base()

fun box(): String {
    val d = Derived()
    val bl = Base::x.getLateInit(d) ?: return "Fail: no LateInit for Base.x"
    val dl = Derived::x.getLateInit(d) ?: return "Fail: no LateInit for Derived.x"

    assertEquals(false, bl.isSet)
    assertEquals(false, dl.isSet)
    d.x = ""
    assertEquals(true, bl.isSet)
    assertEquals(true, dl.isSet)
    bl.reset()
    assertEquals(false, bl.isSet)
    assertEquals(false, dl.isSet)
    d.x = ""
    dl.reset()
    assertEquals(false, bl.isSet)
    assertEquals(false, dl.isSet)

    assertEquals(d::x.getLateInit(), (d as Base)::x.getLateInit())

    return "OK"
}
