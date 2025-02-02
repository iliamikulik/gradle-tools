package ru.mipt.npm.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Create a separate target for node
 */
public class KScienceNodePlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = target.run {
        //Apply multiplatform plugin is not applied, apply it
        if (plugins.findPlugin("org.jetbrains.kotlin.multiplatform") == null) {
            logger.info("Kotlin multiplatform plugin is not resolved. Adding it automatically")
            plugins.apply("org.jetbrains.kotlin.multiplatform")
        }

        if (plugins.findPlugin(KScienceCommonPlugin::class) == null) {
            logger.info("KScience plugin is not resolved. Adding it automatically")
            apply<KScienceCommonPlugin>()
        }

        configure<KotlinMultiplatformExtension> {
            js(name = "node", compiler = IR) {
                nodejs()
            }

            sourceSets {
                val commonMain = findByName("commonMain")!!
                val commonTest = findByName("commonTest")!!

                val jsCommonMain = create("jsCommonMain").apply {
                    dependsOn(commonMain)
                }

                val jsCommonTest = create("jsCommonTest").apply {
                    dependsOn(commonTest)
                }

                findByName("jsMain")?.dependsOn(jsCommonMain)
                findByName("jsTest")?.dependsOn(jsCommonTest)

                findByName("nodeMain")?.apply {
                    dependsOn(jsCommonMain)
                    dependencies {
                        api("org.jetbrains.kotlinx:kotlinx-nodejs:${KScienceVersions.kotlinxNodeVersion}")
                    }
                }
                findByName("nodeTest")?.dependsOn(jsCommonMain)
            }
        }
    }
}
