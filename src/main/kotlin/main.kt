class Foo<T1, T2, T3, T4, T5, T6>
class Bar<T1, T2, T3, T4, T5, T6>(
    private val getFoo: () -> Foo<T1, T2, T3, T4, T5, T6>,
)

fun <T1, T2, T3, T4, T5, T6> test(): Bar<T1, T2, T3, T4, T5, T6> {
    return Bar {
        // when (1) {
        //     1 -> Foo()
        //     else -> Foo()
        // }
        if (true) Foo() else Foo()
    }
}
