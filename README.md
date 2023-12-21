# Kotlin Partial Encoding Decoding (KPED) [![](https://jitpack.io/v/org.cufy/kped.svg)](https://jitpack.io/#org.cufy/kped)

Serialization Extensions and Serializers for enhancing partial encoding and decoding
that uses no object mapping.

### Install

The main way of installing this library is
using `jitpack.io`

```kts
repositories {
    // ...
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    // Replace TAG with the desired version
    implementation("org.cufy.kped:bson:TAG")
    implementation("org.cufy.kped:json:TAG")
    implementation("org.cufy.kped:kped-core:TAG")
    implementation("org.cufy.kped:kped-bson:TAG")
}
```
