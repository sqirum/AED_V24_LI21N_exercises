package week9Collection

import kotlin.test.*

class TestCollection {
   // private fun emptyCollection():MutableCollection<Int> = ArrayCollection<Int>()
    private fun emptyCollection():MutableCollection<Int> = LinkedCollection<Int>()
    private fun emptyIterator() = emptyCollection().iterator()
    private fun addSequenceOf(start:Int, end: Int) :MutableCollection<Int>{
        val c= emptyCollection()
        for (i in 0 until end-start )
          c.add(start+i)
        return c
    }
    @Test
    fun test_emptyCollection() {
        val c: Collection<Int> = emptyCollection()
        assertTrue(c.isEmpty())
        assertEquals(0, c.size)
        assertFalse(c.iterator().hasNext())
    }

    @Test
    fun test_removeFirst() {
        val testList = listOf(1, 2, 3, 4, 5)
        val c: Collection<Int> = emptyCollection()
        if ( c is LinkedCollection) {
            c.addAll( testList )
            assertEquals(testList[0], c.getFirst())
            c.removeFirst()
            assertEquals(testList[1], c.getFirst())
            assertEquals(testList.size-1, c.size)
        }
    }

    @Test
    fun test_removeLast() {
        val testList = listOf(1, 2, 3, 4, 5)
        val c: Collection<Int> = emptyCollection()
        if ( c is LinkedCollection) {
            c.addAll( testList )
            assertEquals(testList[testList.lastIndex], c.getLast())
            c.removeLast()
            assertEquals(testList[testList.lastIndex-1], c.getLast())
            assertEquals(testList.size-1, c.size)
        }
    }


    @Test
    fun test_emptyIterator() {
        val it: Iterator<Int> = emptyIterator()
        assertFalse(it.hasNext())
    }

    @Test
    fun test_singleton() {
        val c: Collection<Int> = addSequenceOf(2, 3)
        assertFalse(c.isEmpty())
        assertEquals(1, c.size)
        val it = c.iterator()
        assertTrue(it.hasNext())
        assertEquals(2, it.next())
        assertFalse(it.hasNext())
    }

    @Test
    fun test_add() {
        val c: Collection<Int> = addSequenceOf(1, 6)
        assertFalse(c.isEmpty())
        assertEquals(5, c.size)
        val it = c.iterator()
        for (i in 1 .. 5 ) {
            assertEquals(5, c.size)
            assertTrue(it.hasNext())
            assertEquals(i, it.next())
        }
        assertFalse(it.hasNext())
    }


    @Test
    fun test_addAll() {
        val a = listOf(1, 2, 3, 4, 5, 6)
        val c: MutableCollection<Int> = emptyCollection()
        c.addAll( a )
        assertEquals(a.size, c.size)
        var expected = 0
        for (v in c) assertEquals(++expected, v)
        assertEquals(a.size, expected)
    }

    @Test
    fun test_contains() {
        val a = mutableListOf(1, 2, 3, 4, 5, 6)
        val c: MutableCollection<Int> = emptyCollection()
        c.addAll( a )
        for (e in a)
            assertTrue(c.contains(e))
        assertTrue( c.containsAll(a))
        assertFalse(c.contains(7))
        a+=7
        assertFalse( c.containsAll(a) )
    }
    @Test
    fun test_sequence_two_iteration() {
        var vE = -3
        val c = addSequenceOf(vE, 15)
        assertEquals(c.size, 18)
        val it = c.iterator()
        while (vE < 14) {
            assertTrue(it.hasNext())
            assertEquals(vE++, it.next())
        }
        assertFalse(!it.hasNext())
        vE = -3
        for (v in c) {
            assertEquals(vE++, v)
        }
        assertEquals(15, vE)
    }

    @Test
    fun test_remove() {
        val c: MutableCollection<Int> = addSequenceOf(1, 6)
        assertEquals(5, c.size)
        val len = c.size
        for (i in 0 until len) {
            assertEquals(len-i, c.size)
            assertTrue(c.remove(i+1))
        }
        assertTrue(c.isEmpty())
    }

    @Test
    fun test_removeAll() {
        val a = arrayOf(1, 2, 3, 4, 5, 6)
        val c: MutableCollection<Int> = emptyCollection()
        c.addAll( a )
        assertEquals(a.size, c.size)
        assertFalse(c.removeAll(arrayOf(7, 8)))
        assertTrue (c.removeAll(addSequenceOf(1,4)))
        assertEquals(a.size-3, c.size)
        var ve = 4
        for ( v in c )
            assertEquals(ve++, v)
    }

    @Test
    fun test_retainAll() {
        val a = listOf(1, 2, 3, 4, 5, 6)
        val c: MutableCollection<Int> = emptyCollection()
        c.addAll( a )
        assertEquals(a.size, c.size)
        assertFalse(c.retainAll(a))
        assertTrue (c.retainAll(listOf(1, 3, 5, 10)))
        assertEquals(3, c.size)
        var ve = 1
        for ( v in c ) {
            assertEquals(ve, v)
            ve += 2
        }
    }

    @Test
    fun test_getAndSet() {
        val col = emptyCollection()
        if ( col is ArrayCollection) {
                col.addAll( listOf(1, 2, 3, 4) )
                assertEquals(1, col[0])
                assertEquals(4, col[3])
                col[0]= 0
                for (i in 1 until col.size)
                    col[i]-= 1
                assertEquals(0, col[0])
                assertEquals(3, col[3])
        }
    }
}