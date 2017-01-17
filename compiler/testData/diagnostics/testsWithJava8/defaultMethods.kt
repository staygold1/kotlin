// FILE: JavaInterface.java

public interface JavaInterface {
    static String testStatic() {
        return "OK";
    }

    default String test() {
        return "OK";
    }

    default String testOverride() {
        return "OK";
    }
}

// FILE: 1.kt
import JavaInterface.testStatic

interface KotlinInterface : JavaInterface {
    fun fooo() {
        <!DEFAULT_METHOD_CALL_FROM_JAVA6_TARGET!>testStatic<!>()
        super.<!INTERFACE_CANT_CALL_DEFAULT_METHOD_VIA_SUPER!>test<!>()
    }

    override fun testOverride(): String {
        return "OK";
    }
}

interface KotlinInterfaceInderectInheritance : KotlinInterface {
    fun foooo() {
        <!DEFAULT_METHOD_CALL_FROM_JAVA6_TARGET!>testStatic<!>()
        super.<!INTERFACE_CANT_CALL_DEFAULT_METHOD_VIA_SUPER!>test<!>()
    }
}

open class KotlinClass : JavaInterface {
    fun foo(){
        <!DEFAULT_METHOD_CALL_FROM_JAVA6_TARGET!>testStatic<!>()
        super.<!DEFAULT_METHOD_CALL_FROM_JAVA6_TARGET!>test<!>()
        super.<!DEFAULT_METHOD_CALL_FROM_JAVA6_TARGET!>testOverride<!>()
    }
}

class KotlinClassInderectInheritance : KotlinClass() {
    fun foo2(){
        <!DEFAULT_METHOD_CALL_FROM_JAVA6_TARGET!>testStatic<!>()
        super.test()
        super.testOverride()
    }
}

class KotlinClassInderectInheritance2 : KotlinInterfaceInderectInheritance {
    fun foo(){
        <!DEFAULT_METHOD_CALL_FROM_JAVA6_TARGET!>testStatic<!>()
        super.<!DEFAULT_METHOD_CALL_FROM_JAVA6_TARGET!>test<!>()
        super.testOverride()
    }
}

fun test() {
    JavaInterface.<!DEFAULT_METHOD_CALL_FROM_JAVA6_TARGET!>testStatic<!>()
    KotlinClass().foo()
    KotlinClassInderectInheritance2().foo()

    KotlinClass().test()
    KotlinClass().testOverride()
    KotlinClassInderectInheritance().testOverride()
}
