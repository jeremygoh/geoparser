geoparser
=========

Java library for geocoding using the Google Maps API

NOTICE: It is a violation of the terms of the Google Maps API to geocode for purposes other than to display a locaton on a map.

## Dependencies

#### JSON in Java - Douglas Crockford
Get the latest JAR at http://mvnrepository.com/artifact/org.json/json

## Getting Started

1) Include the GeoParser class in your project

2) Download the JSON in Java dependency. Add downloaded JAR to project library 

3) Create an instance of GeoParser with your Google Geocoding API key 

```java
GeoParser myGeoParser = new GeoParser("MYAPIKEY_124123102320A230ds");
```

4) Get the latitude and longitude for a given address

```java
myGeoParser.getLatitude("19 Brick Hill Road, Denmark");
myGeoParser.getLongitude("19 Brick Hill Road, Denmark");
```
