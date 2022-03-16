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

plugins {
    `cpp-library`
    xcode
    `visual-studio`
}

repositories {
    mavenCentral()
}

library {
    linkage.set(listOf(Linkage.STATIC))
    binaries.configureEach {
        val compileTask = compileTask.get()
        compileTask.source.from(fileTree("src/main/c") {
            include("**/*.c")
        })
        when(toolChain) {
            is VisualCpp -> compileTask.compilerArgs.addAll(listOf("/TC"))
            is Clang, is GccCompatibleToolChain -> compileTask.compilerArgs.addAll(listOf("-x", "c", "-std=c11"))
        }
    }
}