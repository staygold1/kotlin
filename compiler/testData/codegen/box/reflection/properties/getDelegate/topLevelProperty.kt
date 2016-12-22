// IGNORE_BACKEND: JS
// WITH_REFLECT

import kotlin.reflect.KProperty
import kotlin.test.*

object Delegate {
    var storage = ""
    operator fun getValue(instance: Any?, property: KProperty<*>) = storage
    operator fun setValue(instance: Any?, property: KProperty<*>, value: String) { storage = value }
}

var result: String by Delegate

fun box(): String {
    result = "Fail"
    val d = ::result.getDelegate() as Delegate
    result = "OK"
    assertEquals(d, ::result.getDelegate())
    return d.getValue(null, ::result)
}
