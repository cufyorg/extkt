# Cufyorg: Serialization (including: Partial Encoding / Decoding "PED") [![](https://jitpack.io/v/org.cufy/serialization.svg)](https://jitpack.io/#org.cufy/serialization)

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
    implementation("org.cufy.serialization:cufyorg-bson:TAG")
    implementation("org.cufy.serialization:cufyorg-json:TAG")
    implementation("org.cufy.serialization:cufyorg-PED-core:TAG")
    implementation("org.cufy.serialization:cufyorg-PED-bson:TAG")
}
```
