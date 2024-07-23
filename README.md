# Cufyorg: Serialization [![](https://jitpack.io/v/org.cufy/serialization.svg)](https://jitpack.io/#org.cufy/serialization)

Serialization Extensions and Serializers for enhancing partial encoding and decoding
that uses no object mapping.

> NOTE: `PED` is deprecated and will be moved to its own project in the future
> JSON and BSON extensions are enough for straightforward partial encoding/decoding experience

> NOTE: `crypto` is under construction

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
