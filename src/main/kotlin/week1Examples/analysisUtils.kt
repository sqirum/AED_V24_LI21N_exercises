package week1Examples

import kotlin.math.*
import kotlin.random.*

/**
 * Generate an array of random numbers
 * @param n the size of the array
 */
fun randomArray(n:Int):Array<Double> = Array<Double>(n){Random.nextDouble(0.0, 9.0)}

/**
 * Generate an array of negative numbers in decreasing order
 * @param n the size of the array
 */
fun negativeArrayDecreasing(n: Int): Array<Double> =
    Array(n) { -abs(Random.nextDouble(0.0, 9.0)) }
        .sortedDescending()
        .toTypedArray()

/**
 * Generate an array of positive numbers in increasing order
 * @param n the size of the array
 */
fun positiveArrayIncreasing(n: Int): Array<Double> =
    Array(n) { abs(Random.nextDouble(0.0, 9.0)) }
        .sorted()
        .toTypedArray()

/**
 * Generate an array of multiples of 2
 * @param n the size of the array
 * @param initial the initial value
 */
fun getMultiples(n: Int, initial: Int): Array<Int> =
    generateSequence(initial) { it + 2 }.take(n).toList().toTypedArray()

/**
 * Evaluate the time of an algorithm.
 * Use the System.currentTimeMillis() to get the initial time and the final time.
 * Evaluate the time of an algorithm by running it 13 times.
 * @param functionTest the function to be evaluated
 * @return the average time of the 11 values, removing the maximum and minimum values.
 */
fun algorithmEvaluation(functionTest: ()->Unit) :Long {
    var sum = 0L; var tMax= 0L; var tMin = Long.MAX_VALUE

    for( j in 1 .. 13) {
        val ti = System.currentTimeMillis()
        functionTest()
        val t= System.currentTimeMillis()-ti
        if ( t > tMax ) tMax= t
        if ( t < tMin ) tMin = t
        sum+= t
    }
    return ( sum-tMin-tMax )/11
}
