class Foo<T1, T2, T3, T4, T5, T6>
class Bar<T1, T2, T3, T4, T5, T6>(
    private val getFoo: () -> Foo<T1, T2, T3, T4, T5, T6>,
)

fun test() {
    val bar = Bar {
        when (1) {
            1 -> Foo(1)
            else -> Foo(1)
        }
    }
}
