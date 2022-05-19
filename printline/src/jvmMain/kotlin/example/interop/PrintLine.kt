/**
 * Copyright (c) 2022 by Kristoffer Paulsson <kristoffer.paulsson@talenten.se>.
 *
 * This software is available under the terms of the MIT license. Parts are licensed
 * under different terms if stated. The legal terms are attached to the LICENSE file
 * and are made available on:
 *
 *      https://opensource.org/licenses/MIT
 *
 * SPDX-License-Identifier: MIT
 *
 * Contributors:
 *      Kristoffer Paulsson - initial implementation
 */
package example.interop

internal actual class PrintLine {
    actual companion object {

        actual fun print(message: String) {
            posix_print(message)
        }

        init {
            System.loadLibrary("jni-printline")
        }

        @JvmStatic
        private external fun posix_print(message: String): Boolean
    }
}