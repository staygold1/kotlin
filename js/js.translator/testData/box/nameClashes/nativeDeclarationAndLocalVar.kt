package test

@native fun foo(): dynamic

@native fun bar(): dynamic

fun box(): String {
    val foo = "local foo;"
    val bar = "local bar;"
    val result = foo + test.bar() + test.foo() + bar
    if (result != "local foo;global bar;global foo;local bar;") return "fail: $result"

    return "OK"
}