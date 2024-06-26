package week12Trees

interface NodeExp {
    val left: NodeExp?
    val right: NodeExp?
}

class Operation(val oper: Char,
                override val left: NodeExp,
                override val right: NodeExp
) : NodeExp {
    override fun toString() = "$oper "
}

class Value(val value: Int): NodeExp {
    override val left =null
    override val right = null
    override fun toString() = "$value "
}

fun printPosfix(n: NodeExp?){
    if( n==null ) return
    printPosfix( n.left )
    printPosfix( n.right )
    print( n )
}

fun printPrefix(n: NodeExp?){
    if( n==null ) return
    print( n )
    printPrefix( n.left )
    printPrefix( n.right )
}

fun printInfix(n: NodeExp) {
    n.left?.let {
        print('(')
        printInfix(it)
    }
    print(n)
    n.right?.let {
        printInfix(it)
        print(')')
    }
}

fun main() {
    val plus = Operation( '+', Value(3), Value(1))
    val exp = Operation( 'x', Value(2), plus)
    print( "in-order: ")
    printInfix( exp )
    print( "\npre-order: ")
    printPrefix( exp )
    print( "\npos-order: ")
    printPosfix( exp )
}