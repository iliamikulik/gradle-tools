# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- html builders for readme

### Changed
- Kotlin 1.6.0
- Use indy lambdas by default #32 

### Deprecated

### Removed

### Fixed
- remove `nativeMain` dependency from `nativeTest`

### Security

## [0.10.4]
### Added

### Changed
- Kotlin 1.6


### Deprecated

### Removed

### Fixed
- Some issues with opt-ins


### Security

## [0.10.2]
### Added
- Experimental automatic JS project bundling in MPP


### Changed
- Remove vcs requirement for Space publication


### Fixed
-Release task (#19)

## [0.10.0]
### Added
- Lazy readme properties
- BOM for kotlin-wrappers on JS
- Jupyter loader


### Changed
- API validation disabled for dev versions
- Kotlin plugins are propagated downstream


### Removed
- bson support

## [0.9.5]
### Added
- Disable API validation for snapshots
- `-Xjvm-default=all` on JVM


### Changed
- `publication.platform` changed to `publishing.platform`
- Dokka version to `1.4.30`
- `useDateTime` in extension
- Kotlin 1.5


### Removed
- Publish plugin. Use MavenPublish instead


### Fixed
- Removed unnecessary `afterEvaluate` for compatibility with gradle 7.0

## [0.9.0]
### Added
- Skip sonatype publishing for dev versions


### Changed
- Publishing repositories are explicit and defined in the top level project
- Paths to publishing properties now use dot notation like `publishing.github.user`


### Deprecated
- Publishing plugin


### Removed
- Bintray publishing

## [0.8.4]
### Added
- Adaptive support for host OS in native
- CSS support for JS targets


### Changed
- Kotlin 1.4.31
- Coroutines 1.4.3


### Fixed
- Plugin loading order for publishing
- Release task
- Readme generation for multi-module project

## [0.8.1]
### Added
- Ktor version to versions
- Add sonatype publishing
- Per-platform release publishing


### Changed
- Kotlin to 1.4.30 stable.
- Added intermediate jsCommon main/test sourcesSet for node plugin.
- Plugin names changed to `ru.mipt.npm` package.
- Common plugin id changed to `common`
- Plugins group changed to `ru.mipt.npm` with `gradle` prefix


### Removed
- kaml


### Fixed
- Fix publishing load order for sonatype
- Fix root project readme

## [0.7.4]
### Added
- Changelog plugin automatically applied to `project`.
- Feature matrix and Readme generation task for a `project` plugin.
- Add `binary-compatibility-validator` to the `project` plugin.
- Separate `yamlKt` serialization target
- Moved all logic to a common plugin, leaving only proxies for platform plugins
- Suppress API validation for modules with maturity below DEVELOPMENT


### Changed
- Remove node plugin. Node binaries should be turned on manually.
- Use default webpack distribution path.
- `ru.mipt.npm.base` -> `ru.mipt.npm.project`.
- Move publishing out of general extension and apply it to project plugin instead.
- Platform plugins are now simple references to common plugin
- FX configuration moved to extension
- Moved internals to internals
- Kotlin 1.4.30-RC


### Deprecated
- Support of `kaml` and `snake-yaml` in favor of `yamlKt`
- Publish plugin


### Removed
- `useDokka` method. Documentation jar should be added manually if needed.

## [0.6.0]
### Added
- Migrate to kotlin 1.4.0
- Separate Native (current platform) and nodeJs plugins.
- Add `application()` toggle in plugin configuration to produce binaries on JS and applicaion plugin on jvm.
- Add `publish` to expose publishing configuration.


### Changed
-Publishing in bintray now is automatic.

## [0.5.2]
### Added
- Copy resources for jvm modules and jvm source sets in mpp.