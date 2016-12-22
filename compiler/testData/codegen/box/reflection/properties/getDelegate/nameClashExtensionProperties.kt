// IGNORE_BACKEND: JS
// WITH_REFLECT

import kotlin.reflect.*
import kotlin.test.*

class Delegate(val value: String) {
    operator fun getValue(instance: Any?, property: KProperty<*>) = value
}

class Foo

val Foo.bar: String by Delegate("Foo")
val String.bar: String by Delegate("String")
val Unit.bar: String by Delegate("Unit")

class MemberExtensions {
    val Foo.bar: String by Delegate("Foo")
    val String.bar: String by Delegate("String")
    val Unit.bar: String by Delegate("Unit")
}

fun box(): String {
    val foo = Foo()

    assertEquals("Foo", (foo::bar.getDelegate() as Delegate).value)
    assertEquals("Foo", (Foo::bar.getDelegate(foo) as Delegate).value)
    assertEquals("String", (""::bar.getDelegate() as Delegate).value)
    assertEquals("String", (String::bar.getDelegate("") as Delegate).value)
    assertEquals("Unit", (Unit::bar.getDelegate() as Delegate).value)

    val me = MemberExtensions::class.members.filter { it.name == "bar" } as List<KProperty2<MemberExtensions, *, String>>
    assertEquals(listOf("Foo", "String", "Unit"), me.map { (it.getDelegate(MemberExtensions()) as Delegate).value }.sorted())

    return "OK"
}
