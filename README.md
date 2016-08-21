## Assumptions
- temperature depends on month of the year, distance from equator, time of the day
- rain depends on distance from equator
- pressure depends on elevation
- humidity depends on distance from equator

## How to build and run (outside Eclipse)
```
martin@martin-HOME-PC /cygdrive/c/temp
$ git clone https://github.com/marting456/cba-weather.git
Cloning into 'cba-weather'...
remote: Counting objects: 43, done.
remote: Compressing objects: 100% (29/29), done.
remote: Total 43 (delta 8), reused 36 (delta 4), pack-reused 0
Unpacking objects: 100% (43/43), done.
Checking connectivity... done.

martin@martin-HOME-PC /cygdrive/c/temp
$ cd cba-weather/

martin@martin-HOME-PC /cygdrive/c/temp/cba-weather
$ gradle build
:compileJava
:processResources
:classes
:jar
:assemble
:compileTestJava
:processTestResources UP-TO-DATE
:testClasses
:test
:check
:build

BUILD SUCCESSFUL

Total time: 8.521 secs

martin@martin-HOME-PC /cygdrive/c/temp/cba-weather
$ java -jar build/libs/cba-weather-1.0.jar
SYD|-33.56, 151.1, 21|2015-04-19T18:40:47Z|Sunny|13.1|1008.8|59|
BUD|47.26, 19.15, 495|2016-06-27T04:32:29Z|Sunny|11.1|1012.6|45|
JFK|40.38, 73.46, 13|2015-06-04T22:31:18Z|Sunny|11.0|1005.6|50|
SVO|55.58, 37.24, 630|2016-01-02T11:58:30Z|Snow|-4.0|1018.2|35|
GIG|-22.48, 43.14, 28|2016-09-03T14:01:39Z|Rain|29.7|1007.2|68|
GCJ|-25.59, 28.08, 5327|2016-06-07T16:47:00Z|Rain|18.3|1062.4|63|
LHR|51.28, 0.27, 80|2015-04-13T18:17:39Z|Rain|1.5|1006.1|40|
SIN|1.21, 103.59, 22|2015-11-12T19:20:30Z|Sunny|39.2|1007.7|85|
LAX|33.56, 118.24, 126|2015-09-21T12:00:40Z|Rain|21.7|1012.8|60|
HND|35.33, 139.46, 35|2016-02-27T07:34:49Z|Rain|8.4|1015.0|52|

martin@martin-HOME-PC /cygdrive/c/temp/cba-weather
$ java -jar build/libs/cba-weather-1.0.jar SYD LAX
SYD|-33.56, 151.1, 21|2015-01-19T05:54:08Z|Rain|18.2|1009.3|60|
LAX|33.56, 118.24, 126|2016-01-11T03:05:03Z|Sunny|1.9|1010.9|56|

```
