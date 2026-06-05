plugins {
    `kotlin-dsl`
}

group = "space.zghoba.kotcore.build-logic"

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.kotlin.multiplatform.library)
    implementation(libs.vanniktech.maven.publish)
}

gradlePlugin {
    plugins {
        register("KmpLibraryTargets") {
            id = libs.plugins.kotcore.kmp.library.targets.get().pluginId
            implementationClass = "KmpLibraryTargetsConventionPlugin"
        }
        register("MavenPublishing") {
            id = libs.plugins.kotcore.maven.publishing.get().pluginId
            implementationClass = "MavenPublishingConventionPlugin"
        }
    }
}