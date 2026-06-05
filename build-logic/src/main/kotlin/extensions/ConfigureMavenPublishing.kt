package extensions

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureMavenPublishing() {
    val pomArtifactIdKey = "POM_ARTIFACT_ID"
    val pomArtifactId = requireNotNull(findProperty(pomArtifactIdKey)?.toString()) {
        "Module '$path' must declare '$pomArtifactIdKey' in its 'gradle.properties' file."
    }
    val pomDescriptionKey = "POM_DESCRIPTION"
    val pomDescription = requireNotNull(findProperty(pomDescriptionKey)?.toString()) {
        "Module '$path' must declare '$pomDescriptionKey' in its 'gradle.properties' file."
    }

    extensions.configure<MavenPublishBaseExtension> {
        publishToMavenCentral()
        // signAllPublications()

        pom {
            name.set(pomArtifactId)
            description.set(pomDescription)
            inceptionYear.set("2026")
            url.set("https://github.com/yaroslavzghoba/KotCore/")
            licenses {
                license {
                    name.set("Apache License 2.0")
                    url.set("https://raw.githubusercontent.com/yaroslavzghoba/KotCore/refs/heads/main/LICENSE")
                    distribution.set("repo")
                }
            }
            developers {
                developer {
                    id.set("yaroslavzghoba")
                    name.set("Yaroslav Zghoba")
                    email.set("yaroslavzghoba@gmail.com")
                    url.set("https://github.com/yaroslavzghoba")
                    organization.set("yaroslavzghoba")
                    organizationUrl.set("https://github.com/yaroslavzghoba")
                }
            }
            scm {
                url.set("https://github.com/yaroslavzghoba/KotCore/")
                connection.set("scm:git:git://github.com/yaroslavzghoba/KotCore.git")
                developerConnection.set("scm:git:ssh://github.com/yaroslavzghoba/KotCore.git")
            }
        }
    }
}