package week12Trees

import week7ADT.queue.LinkedQueue
import week7ADT.queue.Queue
import kotlin.Comparator
import kotlin.NoSuchElementException
import kotlin.math.max
import kotlin.math.min

/**
 * Implementation of a MutableSet using a binary search tree.
 */
class AedTreeSet<K>( val comparator: Comparator<K> ) : MutableSet<K> {
    private class TreeNode<K> (
        var key: K,
        var left: TreeNode<K>?=null,
        var right: TreeNode<K>?=null,
        var parent: TreeNode<K>?=null ) {
    }

    // << Instance Variables >>
    private var root: TreeNode<K>? = null // root of the tree

    private var count = 0                 // number of elements in the tree
    override val size: Int = count

    /******************************
     * AUXILIARY METHODS
     */

    /**
     * Count the number of keys.
     * The number of keys is the sum of the number of keys of the
     * subtree left and right plus one.
     * @param root root of the tree
     * @param <K> type of the key
     * @return number of keys
     */
    private fun count(r: TreeNode<K>? = root): Int {
        if (r== null) return 0
        val nl = count(r.left)
        val nr = count(r.right)
        return nl + nr +1
    }

    /**
     * Calculate the height of the tree.
     * The height is the maximum of the heights of the subtrees plus one.
     * @param root root of the tree
     * @param <K> type of the key
     * @return height of the tree
    */
    private fun height( r: TreeNode<K>? = root): Int {
        if ( r == null ) return -1
        val hl = height(r.left)
        val hr = height(r.right)
        return 1 + max(hl, hr)
    }

    /**
     * Search for a node with key is key.
     * @param root root of the tree
     * @param key value to search
     * @param <K> tipo da chave
     * @return the node whose key is key
     */
    private fun getNode(r: TreeNode<K>?, key: K): TreeNode<K>? {
        var root = r
        while (root != null ) {
            val cmp = comparator.compare( root.key, key )
            if (cmp == 0) return root
            root = if( cmp < 0 ) root.right
                   else root.left
        }
        return null
    }

    /**
     * Add a node with key key if there is no node with the same key
     * @param key  value to add
     * @param <K> type of the key
     * @return true if the key is added, false otherwise
     */
    private fun addNode( key: K ): Boolean {
        var p: TreeNode<K>?= null
        var r = root
        var cmp= 0
        while ( r != null ){
            cmp = comparator.compare(r.key, key)
            if ( cmp == 0 ) return false
            p = r
            r = if(cmp < 0 ) r.right else r.left
        }
        val newNode = TreeNode( key, null, null, p)
        if ( p == null ) root= newNode
        else {
            if ( cmp < 0 ) p.right = newNode
            else p.left = newNode
        }
        ++count
        return true
    }

    /**
     * Get the node with the smallest key
     * @param root root of the tree
     * @param <K> type of the key
     * @return node whose key is the smallest
     */
    private fun minimum( root: TreeNode<K> ): TreeNode<K> {
        var min= root
        var r = min.left
        while ( r != null ) {
           min = r
           r = r.left
        }
        return min
    }

    /**
     * Get the node with the largest key
     * @param r root of the tree
     * @param <K> type of the key
     * @return node whose key is the largest
     */
    private fun maximum(root: TreeNode<K>): TreeNode<K> {
        var max= root
        var r = max.right
        while ( r != null ) {
            max = r
            r = r.right
        }
        return max
    }

    /**
     * Get the node whose key is immediately greater
     * @param r root of the tree
     * @param <K> type of the key
     * @return node whose key is immediately greater
     */
    private fun sucessor(root: TreeNode<K>): TreeNode<K>? {
        TODO()
    }

    /**
     * Remove the specified node
     * @param z node to remove
     * @param <K> type of the key
     */
    private fun removeNode( z: TreeNode<K> ) {
        TODO()
        --count
    }

    /******************************
     * Methods of the MultSet interface
     */
    override fun isEmpty(): Boolean = size == 0
    override fun add(element: K): Boolean = addNode( element )
    override fun remove(element: K): Boolean {
       TODO()
    }
    override fun contains(element: K): Boolean = getNode( root, element )!= null
    override fun clear() {
        TODO()
    }
    override fun addAll(elements: Collection<K>): Boolean {
        var b = false
        elements.forEach { if( add(it) ) b = true }
        return b
    }
    override fun containsAll(elements: Collection<K>): Boolean =
        elements.all { contains( it ) }
    override fun removeAll(elements: Collection<K>): Boolean =
        removeIf {!elements.contains( it) }
    override fun retainAll(elements: Collection<K>): Boolean =
        removeIf {!elements.contains( it) }
    override fun iterator() : MutableIterator<K> =
        object : MutableIterator<K> {
           override fun hasNext() = TODO()
           override fun next(): K {
            TODO()
           }
           override fun remove() {
             TODO()
           }
        }

    override fun toString() =
        this.joinToString(prefix="[", postfix = "]")

    /******************************
     * Methods added to the TreeSet
     ******************************/

    /**
     * Get the smallest key.
     * @return smallest key
     * @throws NoSuchElementException if the tree is empty
     */
    fun first(): K =
        (root?: throw NoSuchElementException("tree empty")).let{ minimum( it )}.key

    /**
     * Get the largest key.
     * @return largest key
     * @throws NoSuchElementException if the tree is empty
     */
    fun last(): K =
        (root?: throw NoSuchElementException("tree empty")).let{ maximum( it )}.key

    /** Get the height of the tree. */
    fun height() = height( root )

     /**
     * Methods for balancing the tree
     */

    /**
     * Build ordered single linked list with the elements of the tree.
     * The elements of the tree are added in reverse order at the beginning of
     * the specified single linked list.
     * The nodes of the list are TreeNode and the right field of the tree node
     * is the next of the single linked list.
     * @param root root of the tree
     * @param head reference to the first node of the single linked list
     * @param <K> type of the key
     * @return the first node of the single linked list after adding all
     *         the elements of the tree
     */
    private fun treeToList(root: TreeNode<K>?, head: TreeNode<K>? = null): TreeNode<K>? {
        TODO()
    }

    /**
     * Build a balanced tree with the first size elements of the
     * specified single linked list with sentinel. The nodes of the list are
     * TreeNode and the right field of the tree node is the next field of the
     * linked list. The single linked list is ordered in ascending order.
     * The method is recursive and uses the divide and conquer strategy.
     * Build the left subtree with the first n/2 elements of the list,
     * the root with the first element of the remaining list and
     * the right subtree with the remaining elements wihout the
     * first element.
     * @param sentinel sentinel node whose next is the right field of the tree node
     * @param size number of elements of the single linked list
     *             to build the tree
     * @return the root of the balanced tree
     */
    private fun listToTree(sentinel: TreeNode<K>, size: Int ): TreeNode<K>? {
        TODO()
    }

    /**
     * Get the root of a balanced tree with the elements
     * of the specified tree.
     * @param oldRoot root of the tree not balanced
     * @param sz number of elements of the tree
     * @param <K> type of the key
     * @return root of the balanced tree
     */
    private fun balance(oldRoot: TreeNode<K>, sz: Int= size): TreeNode<K>? {
        TODO()
    }

    /** Balance the tree. */
    fun balance() {
        root = root?.let{ balance(it) }
    }

    /**
     * Iterate over the elements of the tree in breadth-first order.
     * Use a queue as an auxiliary (FIFO ordering). Use the methods:
     *  offer - add to the end
     *  poll  - remove to the begin
     **/
    fun transverseBreadthFirst( action: (k:K) -> Unit ) {
        root?.let {
            val q: Queue<TreeNode<K>> = LinkedQueue() // Do java.util
            TODO()
        }
    }

 }