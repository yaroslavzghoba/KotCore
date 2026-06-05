import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.apply

/**
 * Encapsulates the shared KMP target configuration for all library modules.
 * Call [Project.configureKmpLibraryTargets] from a convention plugin.
 */
@Suppress("unused")
class KmpLibraryTargetsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.multiplatform")
            apply(plugin = "com.android.kotlin.multiplatform.library")

            configureKmpLibraryTargets()
        }
    }
}