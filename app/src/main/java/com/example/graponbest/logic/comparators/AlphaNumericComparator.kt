package com.example.graponbest.logic.comparators

import java.math.BigInteger
import java.text.Collator
import java.util.Locale
import java.util.Objects

@Suppress("unused")
class AlphaNumericComparator : Comparator<String?> {
    private val collator: Collator

    /**
     * Default constructor, uses the default Locale and default collator strength
     *
     * @see AlphaNumericComparator
     */
    constructor() {
        collator = Collator.getInstance()
    }

    /**
     * Constructor using the provided `Locale` and default collator strength
     *
     * @param locale Desired `Locale`
     */
    constructor(locale: Locale) {
        collator = Collator.getInstance(Objects.requireNonNull(locale))
    }

    /**
     * Constructor with given `Locale` and collator strength value
     *
     * @param locale   Desired `Locale`
     * @param strength Collator strength value, any of collator values from: [java.text.Collator.PRIMARY],
     * [java.text.Collator.SECONDARY], [java.text.Collator.TERTIARY],
     * or [java.text.Collator.IDENTICAL]
     * @see java.text.Collator
     */
    constructor(locale: Locale, strength: Int) {
        collator = Collator.getInstance(Objects.requireNonNull(locale))
        collator.strength = strength
    }

    /**
     * Compares two given `String` parameters. Both string parameters will be trimmed before comparison.
     *
     * @param s1 the first string to be compared
     * @param s2 the second string to be compared
     * @return If any of the given parameters is `null` or is an empty string like
     * `""`, `-1` or `1` will be returned based on the order:
     * `-1` will be returned if the first parameter is `null` or empty,
     * `1` will be returned if the second parameter is  `null` or empty.
     * When both are either `null` or empty or any combination of those, a
     * `0` will be returned.
     */
    override fun compare(s1: String?, s2: String?): Int {
        var ss1 = s1
        var ss2 = s2
        if ((ss1 == null || ss1.trim { it <= ' ' }
                .isEmpty()) && ss2 != null && ss2.trim { it <= ' ' }.isNotEmpty()) {
            return -1
        }
        if ((ss2 == null || ss2.trim { it <= ' ' }
                .isEmpty()) && ss1 != null && ss1.trim { it <= ' ' }.isNotEmpty()) {
            return 1
        }
        if ((ss1 == null || ss1.trim { it <= ' ' }
                .isEmpty()) && (ss2 == null || ss2.trim { it <= ' ' }
                .isEmpty())) {
            return 0
        }
        assert(ss1 != null)
        ss1 = ss1!!.trim { it <= ' ' }
        assert(ss2 != null)
        ss2 = ss2!!.trim { it <= ' ' }
        var s1Index = 0
        var s2Index = 0
        while (s1Index < ss1.length && s2Index < ss2.length) {
            var result: Int
            val s1Slice = this.slice(ss1, s1Index)
            val s2Slice = this.slice(ss2, s2Index)
            s1Index += s1Slice.length
            s2Index += s2Slice.length
            result =
                if (Character.isDigit(s1Slice[0]) && Character.isDigit(s2Slice[0])) {
                    compareDigits(s1Slice, s2Slice)
                } else {
                    compareCollatedStrings(s1Slice, s2Slice)
                }
            if (result != 0) {
                return result
            }
        }
        return Integer.signum(ss1.length - ss2.length)
    }

    private fun slice(s: String, index: Int): String {
        var index1 = index
        val result = StringBuilder()
        if (Character.isDigit(s[index1])) {
            while (index1 < s.length && Character.isDigit(s[index1])) {
                result.append(s[index1])
                index1++
            }
        } else {
            result.append(s[index1])
        }
        return result.toString()
    }

    private fun compareDigits(s1: String, s2: String): Int {
        return BigInteger(s1).compareTo(BigInteger(s2))
    }

    private fun compareCollatedStrings(s1: String, s2: String): Int {
        return collator.compare(s1, s2)
    }
}