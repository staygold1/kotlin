typealias TString = String
fun f1() = TString::class

typealias TNullableString = String?
fun <!KCLASS_WITH_NULLABLE_ARGUMENT_IN_SIGNATURE!>f2<!>() = <!NULLABLE_TYPE_IN_CLASS_LITERAL_LHS!>TNullableString::class<!>

/*
// TODO: uncomment once KT-15734 is fixed
typealias TNullableTString = TString?
typealias TTNullableTString = TNullableTString
fun f3() = TTNullableTString::class
*/

inline fun <reified T> f4(): Any {
    <!TOPLEVEL_TYPEALIASES_ONLY!>typealias X = <!TYPEALIAS_SHOULD_EXPAND_TO_CLASS!>T<!><!>
    return X::class
}
