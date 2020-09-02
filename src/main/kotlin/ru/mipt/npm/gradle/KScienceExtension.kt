package ru.mipt.npm.gradle

import org.gradle.api.Project
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinJsProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

class KScienceExtension(val project: Project) {

    fun useCoroutines(
        version: String = KScienceVersions.coroutinesVersion,
        sourceSet: DependencySourceSet = DependencySourceSet.MAIN,
        configuration: DependencyConfiguration = DependencyConfiguration.API
    ): Unit = project.useCommonDependency(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version",
        dependencySourceSet = sourceSet,
        dependencyConfiguration = configuration
    )

    fun useAtomic(version: String = KScienceVersions.atomicVersion): Unit = project.run {
        plugins.apply("kotlinx-atomicfu")
        useCommonDependency(
            "org.jetbrains.kotlinx:atomicfu:$version"
        )
    }

    fun useSerialization(
        version: String = KScienceVersions.serializationVersion,
        sourceSet: DependencySourceSet = DependencySourceSet.MAIN,
        configuration: DependencyConfiguration = DependencyConfiguration.API,
        block: SerializationTargets.() -> Unit = {}
    ): Unit = project.run {
        plugins.apply("org.jetbrains.kotlin.plugin.serialization")
        val artifactName = if (version.startsWith("0")) {
            "kotlinx-serialization-runtime"
        } else {
            "kotlinx-serialization-core"
        }
        useCommonDependency(
            "org.jetbrains.kotlinx:$artifactName:$version",
            dependencySourceSet = sourceSet,
            dependencyConfiguration = configuration
        )
        SerializationTargets(sourceSet, configuration).apply(block)
    }

    /**
     * Mark this module as an application module. JVM application should be enabled separately
     */
    fun application() {
        project.extensions.findByType<KotlinProjectExtension>()?.apply {
            explicitApi = null
        }
        project.extensions.findByType<KotlinJsProjectExtension>()?.apply {
            js {
                binaries.executable()
            }
        }
        project.extensions.findByType<KotlinMultiplatformExtension>()?.apply {
            js{
                binaries.executable()
            }
        }
    }

    /**
     * Activate publishing and configure it
     */
    fun publishing(block: Publishing.() -> Unit) = Publishing().apply(block)

    inner class Publishing {
        init {
            if (project.plugins.findPlugin(KSciencePublishPlugin::class) == null) {
                project.plugins.apply(KSciencePublishPlugin::class)
            }
        }

        var githubOrg: String? by project.extra
        var githubProject: String? by project.extra
        var spaceRepo: String? by project.extra
        var spaceUser: String? by project.extra
        var spaceToken: String? by project.extra
        var bintrayOrg: String? by project.extra
        var bintrayUser: String? by project.extra
        var bintrayApiKey: String? by project.extra
        var bintrayRepo: String? by project.extra
    }
}