package foo

// NOTE THIS FILE IS AUTO-GENERATED by the generateTestDataForReservedWords.kt. DO NOT EDIT!

enum class Foo {
    `public`
}

fun box(): String {
    testNotRenamed("public", { Foo.`public` })

    return "OK"
}