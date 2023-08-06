# NewsApps

This Application for Open news by news api

Requirement to build this app
minimum Android Studio Flamingo | 2022.2.1 Patch 2 and JDK 17 because this project using AGP 8.0

Note if error api pop up you can just try change api key at module build.gradle(:app) at NEWS_API_KEY property
 //build config deprecated as AGP 9.0
        buildConfigField "String", "NEWS_API_URL", "\"https://newsapi.org/\""
        buildConfigField "String", "NEWS_API_KEY", "\"**********\""

