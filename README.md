# Домашнее задание к занятию «4.1. Репортинг»

***
## Задача №1 - "Проснулись" (Allure)
## Настройка allure
Смотрите [данный коммит](https://github.com/Kingwood15/aqajava-homework-9-report/commit/6efeafdf7cb4432447b54caa25522a451651c438)
***
## Задача №2 - Report Portal (необязательная)
## Настройка Report Portal
**Краткая инструкция по интеграции Report Portal в проекте**
1. Должен быть установлен [Docker](https://www.docker.com/get-started/) на компьютере.
2. Скачать и вставить в проект [docker-compose.yml](https://github.com/reportportal/reportportal/blob/master/docker-compose.yml) файл.
3. В  файле **docker-compose.yml**  для машин с ОС Windows нужно разкоммитить строки с пометкой `For windows host` в строках [144](https://github.com/reportportal/reportportal/blob/d6bbc4bf58d56b63ab5454b4a4a420098c4adda7/docker-compose.yml#L144), [331](https://github.com/reportportal/reportportal/blob/d6bbc4bf58d56b63ab5454b4a4a420098c4adda7/docker-compose.yml#L331), [344](https://github.com/reportportal/reportportal/blob/d6bbc4bf58d56b63ab5454b4a4a420098c4adda7/docker-compose.yml#L344).
4. **Далее необходимVPN**. Скачаем и запустим контейнер командой `docker-compose -p reportportal up`. Либо из команды на [строке 6](https://github.com/reportportal/reportportal/blob/d6bbc4bf58d56b63ab5454b4a4a420098c4adda7/docker-compose.yml#L6). После скачивания необходимых снимков, VPN можно выключить.
5. В браузере перейти по адресу контейнера http://localhost:8080/ и авторизоваться используя логин/пароль: superadmin/erebus.
6. Далее перейти в настройки профиля и скопировать из раздела CONFIGURATION EXAMPLES во вкладке JAVA все строки REQUIRED и вставить их в проект в файл reportportal.properties. Который должен находиться в каталоге `src/test/resources`. Это необходимо для связи проекта с сайтом запущенным в контейнере.
7. **Далее подключаем зависимости в проект**. В файле `build.gradle` нужно прописать:
	
  <pre><code>
  repositories {
  ...
  mavenLocal()
  ...
  }
  
  dependencies {
  ...
  implementation 'com.epam.reportportal:agent-java-junit5:5.0.0'
  implementation 'com.epam.reportportal:logger-java-logback:5.0.2'
  implementation 'ch.qos.logback:logback-classic:1.2.3'
  ...
  }
  
  test {
  ...
  systemProperty 'junit.jupiter.extensions.autodetection.enabled', true
  testLogging.showStandardStreams = true
  ...
  }
  </code></pre>
    
8. По пути `src/test/resources` добавить каталоги `META-INF/services`, где создать файл `org.junit.jupiter.api.extensions.Extension`. В файле должно быть прописано .`com.epam.reportportal.junit5.ReportPortalExtension`. *Файл необходим для описания подключаемого расширения.*
9. По пути `src/test/resources` добавить *логгер* файл `logback.xml`. 
10. Для создания **скриншотов** по пути `src/test/java/ru/netology/util` добавим класс `ScreenShooterReportPortalExtension.java`. Также добавим класс логгер для записи данных `LoggingUtils.java`.
11. В тестовом классе, перед названием, добавить аннотацию `@ExtendWith({ScreenShooterReportPortalExtension.class})`. В тестовых методах прописать методы `LogInfo(*)` для отображения действий в логах.
12. Приступаем к **запуску тестов**. Контейнер уже запущен. Запускаем SUT командой `java -jar artifacts/app-card-delivery.jar`. Запускаем тесты командой `./gradlew clean test --info --debug`.
13. Смотрим отчет по адресу http://localhost:8080/ в разделе запусков.
