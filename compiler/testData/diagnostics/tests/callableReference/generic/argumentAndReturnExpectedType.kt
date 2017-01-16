// !DIAGNOSTICS: -UNUSED_VARIABLE, -UNUSED_PARAMETER

fun <T, R> foo(x: T): R = TODO()

fun <T> fooReturnInt(x: T): Int = 1
fun <T> fooTakeString(x: String): T = TODO()

fun <T, R> bar(x: T, y: R, f: (T) -> R): R = TODO()
fun <T, R> baz(f: (T) -> R, g: (T) -> R) {}

fun test1() {
    bar("", 1, ::foo)
    bar("", 1, ::fooReturnInt)
    bar("", 1, ::fooTakeString)
    bar("", "", ::fooReturnInt)

    val x1: Any = bar("", "", ::fooReturnInt)
    val x2: String = <!TYPE_INFERENCE_EXPECTED_TYPE_MISMATCH!>bar("", "", ::fooReturnInt)<!>

    baz(Int::toString, ::foo)
}

fun <T> listOf(): List<T> = TODO()
fun <T> setOf(): Set<T> = TODO()

fun <T> test2(x: T) {
    bar(x, x, ::foo)
    bar(x, 1, ::foo)
    bar(1, x, ::foo)

    bar(listOf<T>(), setOf<T>(), ::foo)
    bar(listOf<T>(), 1, ::foo)
    bar(1, listOf<T>(), ::foo)
}