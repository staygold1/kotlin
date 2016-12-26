// IGNORE_BACKEND: JS
// WITH_REFLECT

import kotlin.test.*

class Foo {
    companion object {
        @JvmStatic
        lateinit var x: String
    }
}

object Bar {
    @JvmStatic
    lateinit var x: String
}

fun box(): String {
    assertNotNull(Foo.Companion::x.getLateInit())
    assertNotNull(Bar::x.getLateInit())

    assertEquals(false, Foo.Companion::x.getLateInit()!!.isSet)
    Foo.x = ""
    assertEquals(true, Foo.Companion::x.getLateInit()!!.isSet)
    Foo.Companion::x.getLateInit()!!.reset()
    assertEquals(false, Foo.Companion::x.getLateInit()!!.isSet)

    assertEquals(false, Bar::x.getLateInit()!!.isSet)
    Bar.x = ""
    assertEquals(true, Bar::x.getLateInit()!!.isSet)
    Bar::x.getLateInit()!!.reset()
    assertEquals(false, Bar::x.getLateInit()!!.isSet)

    return "OK"
}
