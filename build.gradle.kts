plugins {
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.kotlin.multiplatform) apply  false
    alias(libs.plugins.vanniktech.maven.publish) apply false
}

// Set a group and a version of the project.
allprojects {
    val catalog = rootProject.extensions
        .getByType<VersionCatalogsExtension>()
        .named("libs")

    group = "space.zghoba"
    version = catalog.findVersion("project").get().requiredVersion
}

// Print the current version of the project.
tasks.register("printVersion") {
    description = "Print the current version of the project."
    doLast {
        println(project.version)
    }
}