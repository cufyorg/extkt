# Partial Encoding / Decoding (PED) [![](https://jitpack.io/v/org.cufy/ped.svg)](https://jitpack.io/#org.cufy/ped)

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
    implementation("org.cufy.ped:bson:TAG")
    implementation("org.cufy.ped:json:TAG")
    implementation("org.cufy.ped:ped-core:TAG")
    implementation("org.cufy.ped:ped-bson:TAG")
}
```
