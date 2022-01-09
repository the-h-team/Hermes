# Hermes
###### A multiplatform message service library
[![Maven Central](https://img.shields.io/maven-central/v/com.github.the-h-team/hermes)](https://search.maven.org/search?q=g:com.github.the-h-team%20a:hermes)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/com.github.the-h-team/hermes?server=https%3A%2F%2Fs01.oss.sonatype.org)](https://s01.oss.sonatype.org/#nexus-search;gav~com.github.the-h-team~hermes~~~)

### Relocation (Please READ THIS SECTION)
It is important to include a valid `maven-shade-plugin` configuration to avoid
class collision with other plugin jars that also use and provide this resource.
Take moment to look over this example:
```xml
    <!-- In your pom.xml -->
    <build>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-shade-plugin</artifactId>
          <version>3.1.0</version>
          <configuration>
            <relocations>
              <relocation>
                <!-- Matches main hermes package -->
                <pattern>com.github.sanctum.hermes</pattern>
                <!-- Replace this with your package! -->
                <shadedPattern>com.github.ms5984.plugin2.lib.hermes</shadedPattern>
              </relocation>
            </relocations>
          </configuration>
          <executions>
            <execution>
              <phase>package</phase>
              <goals>
                <goal>shade</goal>
              </goals>
              <configuration>
                <createDependencyReducedPom>false</createDependencyReducedPom>
              </configuration>
            </execution>
          </executions>
        </plugin>
      </plugins>
    </build>
```

### Importing
#### Full release
```xml
<project>
    <dependencies>
        <!-- Import for Spigot support -->
        <dependency>
            <groupId>com.github.the-h-team</groupId>
            <artifactId>hermes-spigot</artifactId>
            <version><!--maven central version here--></version>
        </dependency>
        <!-- Import for Bungeecord support -->
        <dependency>
            <groupId>com.github.the-h-team</groupId>
            <artifactId>hermes-bungeecord</artifactId>
            <version><!--maven central version here--></version>
        </dependency>
    </dependencies>
</project>
```
#### Snapshots (requires sonatype repository)
```xml
<project>
    <!-- For Sonatype Nexus snapshots (primary development here) -->
    <repositories>
        <repository>
            <id>sonatype-snapshots</id>
            <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>
    <dependencies>
        <!-- Import for core library (Included with -spigot or -bungeecord;
        use this only for platform-agnostic development!) -->
        <dependency>
            <groupId>com.github.the-h-team</groupId>
            <artifactId>hermes-common</artifactId>
            <version><!--nexus snapshot version here--></version>
            <scope>provided</scope>
        </dependency>
        <!-- Import for Spigot support -->
        <dependency>
            <groupId>com.github.the-h-team</groupId>
            <artifactId>hermes-spigot</artifactId>
            <version><!--nexus snapshot version here--></version>
        </dependency>
        <!-- Import for Bungeecord support -->
        <dependency>
            <groupId>com.github.the-h-team</groupId>
            <artifactId>hermes-bungeecord</artifactId>
            <version><!--nexus snapshot version here--></version>
        </dependency>
    </dependencies>
</project>
```
#### GitHub+Jitpack (requires jitpack repository)
```xml
<project>
    <!-- For Jitpack pre-release, custom commit builds -->
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io/</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>com.github.the-h-team.Hermes</groupId>
            <artifactId>hermes-spigot</artifactId>
            <!--commit hash; example below-->
            <version>d33ccb0</version>
        </dependency>
    </dependencies>
</project>
```
