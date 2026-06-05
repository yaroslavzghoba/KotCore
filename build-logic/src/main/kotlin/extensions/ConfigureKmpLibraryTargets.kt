import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKmpLibraryTargets() {
    val androidNamespaceKey = "ANDROID_NAMESPACE"
    val androidNamespace = requireNotNull(findProperty(androidNamespaceKey)?.toString()) {
        "Module '$path' must declare '$androidNamespaceKey' in its 'gradle.properties' file."
    }

    val catalog = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
    val androidCompileSdk = catalog.findVersion("android.compileSdk").get().requiredVersion.toInt()
    val androidMinSdk = catalog.findVersion("android.minSdk").get().requiredVersion.toInt()

    extensions.configure<KotlinMultiplatformExtension> {
        jvm()
        configureAndroidTarget(androidNamespace, androidCompileSdk, androidMinSdk)
        iosArm64()
        iosSimulatorArm64()
        linuxX64()
        configureWasmJs()
        configureSourceSets()
    }
}

private fun KotlinMultiplatformExtension.configureAndroidTarget(
    namespace: String,
    compileSdk: Int,
    minSdk: Int,
) {
    extensions.configure<KotlinMultiplatformAndroidLibraryTarget> {
        this.namespace = namespace
        this.compileSdk = compileSdk
        this.minSdk = minSdk

        withJava()  // enable java compilation support
        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }

        compilerOptions.jvmTarget.set(JvmTarget.JVM_11)
    }
}

@OptIn(ExperimentalWasmDsl::class)
private fun KotlinMultiplatformExtension.configureWasmJs() {
    wasmJs {
        browser()
        binaries.executable()
    }
}

private fun KotlinMultiplatformExtension.configureSourceSets() {
    sourceSets.commonTest.dependencies {
        implementation(kotlin("test"))
    }
}