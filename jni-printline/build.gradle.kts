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

import org.gradle.internal.jvm.Jvm

plugins {
    base
    `cpp-library`
    `visual-studio`
}

repositories {
    mavenCentral()
}

library {
    binaries.configureEach{
        val compileTask = compileTask.get()
        val javaHome = Jvm.current().javaHome.canonicalPath
        compileTask.compilerArgs.addAll(compileTask.targetPlatform.map {
            listOf("-I", "$javaHome/include") + when {
                it.operatingSystem.isMacOsX -> listOf("-I", "$javaHome/include/darwin")
                it.operatingSystem.isLinux -> listOf("-I", "$javaHome/include/linux")
                it.operatingSystem.isWindows -> listOf("-I", "$javaHome/include/win32")
                else -> emptyList()
            }
        })

        when(toolChain) {
            is VisualCpp -> compileTask.compilerArgs.addAll(listOf())
            is Clang, is GccCompatibleToolChain -> compileTask.compilerArgs.addAll(listOf("-x", "c", "-std=c11"))
        }
    }
}

dependencies {
    implementation(project(":printline"))
}