package com.hefengbao.yuzhu.common.ext

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.util.Patterns
import com.hefengbao.yuzhu.R
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.math.abs
import kotlin.math.round


private const val MINUTE_IN_SECONDS = 60
private const val HOUR_IN_SECONDS = 60 * MINUTE_IN_SECONDS
private const val DAY_IN_SECONDS = 24 * HOUR_IN_SECONDS
private const val WEEK_IN_SECONDS = 7 * DAY_IN_SECONDS
private const val MONTH_IN_SECONDS = 30 * DAY_IN_SECONDS
private const val YEAR_IN_SECONDS = 365 * DAY_IN_SECONDS

//Email Validation
fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isLetterOrDigit(): Boolean {
    this.forEach {
        if (!Character.isLetterOrDigit(it)) {
            return false
        }
    }
    return true
}

/**
 * CJB 文字、标点，Emoji 占两个字符
 */
fun String.count(): Int {
    var count = 0
    val chars = this.toCharArray()
    chars.forEach { char ->
        val u = Character.UnicodeBlock.of(char)
        if (u == Character.UnicodeBlock.CJK_COMPATIBILITY ||
            u == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS ||
            u == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS ||
            u == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT ||
            u == Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT ||
            u == Character.UnicodeBlock.CJK_STROKES ||
            u == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ||
            u == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ||
            u == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A ||
            u == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B ||
            u == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C ||
            u == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D ||
            u == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS ||
            u == Character.UnicodeBlock.EMOTICONS
        ) {
            count += 2
        } else {
            count++
        }
    }

    return count
}

fun String?.asBearerToken(): String? = if (this.isNullOrBlank()) null else "Bearer $this"

//Phone number format
/*fun String.formatPhoneNumber(context: Context, region: String): String? {
    val phoneNumberKit = PhoneNumberUtil.createInstance(context)
    val number = phoneNumberKit.parse(this, region)
    if (!phoneNumberKit.isValidNumber(number)) {
        return null
    }

    return phoneNumberKit.format(number, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
}*/

fun String?.diffForHumans(context: Context): String {
    if (this.isNullOrEmpty()) return ""
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
    val date: Date = simpleDateFormat.parse(this)
    val currentTimeStamp = Date().time
    val diff = abs(currentTimeStamp - date.time)

    when {
        diff < MINUTE_IN_SECONDS -> {
            return context.getString(R.string.formatting_human_time_diff_just_now)
        }

        diff in MINUTE_IN_SECONDS until HOUR_IN_SECONDS -> {
            val mins = round(diff.toDouble() / MINUTE_IN_SECONDS).toInt().toString()
            return context.getString(R.string.formatting_human_time_diff_mins_ago, mins)
        }

        diff in HOUR_IN_SECONDS until DAY_IN_SECONDS -> {
            val hours = round(diff.toDouble() / HOUR_IN_SECONDS).toInt().toString()
            return context.getString(R.string.formatting_human_time_diff_hours_ago, hours)
        }

        else -> {
            val calendar = Calendar.getInstance() // 获取当前日期

            calendar.timeZone = TimeZone.getDefault()
            calendar.timeInMillis = currentTimeStamp
            calendar.add(Calendar.YEAR, 0)
            calendar.add(Calendar.DATE, 0)
            calendar.add(Calendar.MONTH, 0)
            calendar[Calendar.DAY_OF_YEAR] = 1
            calendar[Calendar.HOUR_OF_DAY] = 0
            calendar[Calendar.MINUTE] = 0
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

            return if (calendar.timeInMillis < date.time) {
                val simpleDateFormat2 = SimpleDateFormat(
                    context.getString(R.string.formatting_human_time_diff_md),
                    Locale.getDefault()
                )
                simpleDateFormat2.format(date)
            } else {
                val simpleDateFormat3 = SimpleDateFormat(
                    context.getString(R.string.formatting_human_time_diff_ymd),
                    Locale.getDefault()
                )
                simpleDateFormat3.format(date)
            }
        }
    }
}

/**
 * 字符串转为秒
 */
fun String.toSeconds(): Long {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
    val date: Date = simpleDateFormat.parse(this)

    return date.time
}