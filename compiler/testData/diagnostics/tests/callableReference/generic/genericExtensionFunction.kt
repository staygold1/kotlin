// !DIAGNOSTICS: -UNUSED_PARAMETER, -UNUSED_VARIABLE

class Wrapper

fun <R, S> Wrapper.foo(x: R): S = TODO()
fun Wrapper.fooIntString(x: Int): String = ""
fun <T> Wrapper.fooReturnString(x: T): String = ""
fun <T> Wrapper.fooTakeInt(x: Int): T = TODO()

fun <T, R, S> bar(f: T.(R) -> S) {}
fun <T, R, S> baz(x: T, y: R, z: S, f: T.(R) -> S) {}

fun test1() {
    val x: Wrapper.(String) -> Boolean = Wrapper::foo
    bar<Wrapper, Double, Float>(Wrapper::foo)
    bar(Wrapper::fooIntString)
}

fun <T> test2() {
    bar<Wrapper, Int, String>(Wrapper::fooReturnString)
    bar<Wrapper, T, String>(Wrapper::fooReturnString)
    bar<Wrapper, T, T>(Wrapper::<!TYPE_INFERENCE_EXPECTED_TYPE_MISMATCH!>fooReturnString<!>)
    bar<Wrapper, Int, Int>(Wrapper::<!TYPE_INFERENCE_EXPECTED_TYPE_MISMATCH!>fooReturnString<!>)

    bar<Wrapper, Int, T>(Wrapper::fooTakeInt)
    bar<Wrapper, Int, String>(Wrapper::fooTakeInt)
}