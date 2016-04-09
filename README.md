# Interleaver 

[![Release](https://jitpack.io/v/kgilmer/interleaver.svg)](https://jitpack.io/#kggilmer/interleaver)

Interleave elements from a collection of iterables in Java.

## Example

The following snippit will yield `interleavedElements = ["a1", "c1", "a2", "a3"]`.

```Java
List<String> l1 = Arrays.asList("a1", "a2", "a3");
List<String> l2 = Arrays.asList();
List<String> l3 = Arrays.asList("c1");

List<String> interleavedElements = Interleaver.fromIterables(Arrays.asList(l1, l2, l3))
```

## See Also

- https://code.google.com/p/guava-libraries/issues/detail?id=677


# Get `interleaver` into your Gradle project

Add it to your build.gradle with:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and:

```gradle
dependencies {
    compile 'com.github.kgilmer:interleaver:1.0'
}
```
