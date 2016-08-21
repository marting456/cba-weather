## Assumptions
- temperature depends on month of the year, distance from equator, time of the day
- rain depends on distance from equator
- pressure depends on elevation
- humidity depends on distance from equator

## How to build and run
```
martin@martin-HOME-PC /cygdrive/c/dev/workspace/cba-weather
$ gradle clean build
:clean
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

Total time: 8.931 secs

martin@martin-HOME-PC /cygdrive/c/dev/workspace/cba-weather
$ cd bin

martin@martin-HOME-PC /cygdrive/c/dev/workspace/cba-weather/bin
$ java org.marting.weather.WeatherPredictor
SYD|-33.56, 151.1, 21|2015-07-15T01:44:06Z|Rain|2.9|1006.3|58|
BUD|47.26, 19.15, 495|2015-04-16T22:50:56Z|Snow|-3.7|1014.1|44|
JFK|40.38, 73.46, 13|2016-03-10T11:43:22Z|Sunny|11.8|1014.1|50|
SVO|55.58, 37.24, 630|2016-07-20T08:05:42Z|Rain|8.1|1015.0|30|
GIG|-22.48, 43.14, 28|2016-11-22T06:41:35Z|Rain|32.5|1014.2|72|
GCJ|-25.59, 28.08, 5327|2015-04-16T05:49:08Z|Sunny|19.9|1062.1|62|
LHR|51.28, 0.27, 80|2015-09-16T03:54:39Z|Sunny|-3.4|1012.5|43|
SIN|1.21, 103.59, 22|2016-05-28T09:23:44Z|Sunny|56.2|1007.1|83|
LAX|33.56, 118.24, 126|2016-09-16T01:33:28Z|Rain|16.6|1011.1|60|
HND|35.33, 139.46, 35|2015-11-30T02:50:07Z|Rain|5.2|1006.1|57|
```
