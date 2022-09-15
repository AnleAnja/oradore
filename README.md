# oradore

## Beschreibung

In diesem Repository sind der Source Code und die [Folien](https://github.com/AnleAnja/oradore/blob/main/slides.pdf) zu dem Vortrag **"Fullstack Mobile Development mit Kotlin Multiplatform"** von Anja Bertels und Alex Dobrynin auf der [Digital XChange 2022](https://digital-xchange.de/programm-2022/) zu finden.

In dem Vortrag wurde die Technologie [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) als ein Ansatz zur Cross-Platform Entwicklung vorgestellt. Dies wurde anhand einer Anwendung gezeigt, die das gesamte Koferenzprogramm, alle Speaker und alle Räume der Digital XChange mit allen relevanten Informationen darstellt. Außerdem wurden einige zusätzliche Features umgesetzt.

Entwickelt wurden ein Backend, eine Android App und eine iOS App. Alle drei Komponenten teilen sich einen gemeinsamen Kern (Shared Code), während die UI nativ bleibt und unabhängig voneinander entwickelt wurde.

Der Entwicklungsprozess lässt sich über die Commits, aber auch über die [Issues](https://github.com/AnleAnja/oradore/issues) nachvollziehen. Dort sind auch geplante Features vorzufinden, die nicht implementiert wurden.

## Verwendung

Das Backend wird als Fat Jar gebaut und als Docker Image bereitgestellt:

```shell
./gradlew :backend:buildFatJar
docker build -t oradore-backend .
```

Die beiden mobilen Anwendungen werden in Android Studio über die jeweilige Run Configuration gebaut. Standardmäßig werden die Apps im Emulator (Android) und Simulator (iOS) ausgeführt. Allerdings können sie auch auf realen Geräten ausgeführt werden:

Unter iOS muss dazu Xcode gestartet werden und unter "Signing and Capabilities" ein Developer Team eingetragen werden (Apple ID). Das Smartphone muss dann mit dem Gerät verbunden und unter "Targets" ausgewählt werden. Dann kann man die App ausführen.

Unter Android muss zunächst auf dem Smartphone unter "Developer Options" das "USB Debugging" aktiviert werden. Dann kann das Smartphone mit dem Gerät verbunden werden und in Android Studio als Gerät ausgewählt werden. Dann kann man die App ausführen. 
