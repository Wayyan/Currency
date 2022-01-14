import org.gradle.api.artifacts.dsl.DependencyHandler

/**
Created By Aunt Htoo Aung on 02/07/2021.
 **/

fun DependencyHandler.koinAndroid() {
  implementation(KoinDep.android)
  //implementation(KoinDep.scope)
 // implementation(KoinDep.viewmodel)
  implementation(KoinDep.workmanager)
  //implementation(KoinDep.androidx_ext)
}

fun DependencyHandler.koinCore() {
  implementation(KoinDep.core)
 // implementation(KoinDep.core_ext)
  testImplementation(KoinDep.test)
}

object KoinDep {

  private const val version = "3.1.2"

  const val gradle_plugin = "io.insert-koin:koin-gradle-plugin:$version"

  const val core = "io.insert-koin:koin-core:$version"
  const val test = "io.insert-koin:koin-test:$version"

  const val android = "io.insert-koin:koin-android:$version"
 // const val scope = "io.insert-koin:koin-androidx-scope:$version"
  //const val viewmodel = "io.insert-koin:koin-androidx-viewmodel:$version"
  const val workmanager = "io.insert-koin:koin-androidx-workmanager:$version"
//  const val androidx_ext = "io.insert-koin:koin-androidx-ext:$version"

}