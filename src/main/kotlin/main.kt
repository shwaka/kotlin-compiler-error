class Foo<T1, T2, T3, T4, T5, T6>
class Bar<T1, T2, T3, T4, T5, T6>(
    private val foo: Foo<T1, T2, T3, T4, T5, T6>,
)

fun <T1, T2, T3, T4, T5, T6> test(): Bar<T1, T2, T3, T4, T5, T6> {
    return Bar(if (true) Foo() else Foo())
}
