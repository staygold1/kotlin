// IGNORE_BACKEND: JS
// WITH_REFLECT

import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0
import kotlin.test.*

var ref: KProperty<*>? = null

class Delegate {
    var storage = ""
    operator fun getValue(instance: Any?, property: KProperty<*>): String {
        ref = property
        return storage
    }
    operator fun setValue(instance: Any?, property: KProperty<*>, value: String) { storage = value }
}

var result: String by Delegate()

fun box(): String {
    result
    val prop = ref as KProperty0<*>

    result = "Fail"
    val d = prop.getDelegate() as Delegate
    result = "OK"
    assertEquals(d, prop.getDelegate())
    return result
}
