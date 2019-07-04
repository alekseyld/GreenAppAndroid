# Мобильное приложение для дипломного проекта

Предназначено для устройств с ОС Android. 
Написано на языке программирования Kotlin с использованием архитектурного стиля MVVM.
Сторонние библиотеки:
- Retrofit 2
- Dagger 2
- RxJava 2
- MPAndroidChart
- Android Jetpack

Классным моментом является - внедрение ViewModel во View с помощью Dagger 2


# Экран управления
  Наблюдать за состоянием системы, а также управлять ей можно с экрана “Управление”. Это первая вкладка нижнем меню. На ней расположены карточки каждого автоматизированного узла.
Если узлом возможно управление, то в карточке есть переключатель. При нажатии на карточку происходит запрос к ESP8266 с переключением состоянием выбранного пункта.
Экран обладает возможностью прокрутки вниз, если все карточки не поме-щаются на дисплее.
Для того чтобы перейти на другой экран достаточно нажать на иконки этого экрана в нижнем меню. При переходе экран будет отображен в главной секции приложения, а снизу изменится выбранный пункт меню и станет видна подпись экрана.
<p align="center">
<img width="353" alt="Screenshot" src="https://user-images.githubusercontent.com/11257626/60682417-bf25cd80-9eac-11e9-9558-08beb40b3ba4.jpg">
</p>

# Экран статистики 
<p>
  Экран статистики используется для ведения наглядных графиков состояний системы. Программа ведет сохранение в базу данных состояний системы при каждом обращении к ней.
На экране по сохраненным в базе данных значениям чертятся графики из-менения температуры, освещенности и влажности.
Также экран возможно прокручивать вниз при нехватке места на дисплее.
У каждого графика состояния установлен свой основной цвет. Для темпера-туры этот цвет – зеленый, для освещенности цвет имеет желтый оттенок, а гра-фик влажности голубой.
</p>
<p align="center">
<img width="353" alt="Screenshot" src="https://user-images.githubusercontent.com/11257626/60682418-bfbe6400-9eac-11e9-8279-205684646d70.png">
</p>

# Экран видеонаблюдения
Для контроля работы всех систем, а также в общем наблюдением за состоя-нием теплицы во вкладке “Наблюдение” имеется возможность видеонаблюдения за ней.
На этом экране расположено два элемента с видео передаваемого камерами, направленными на телицу. 
Также экран возможно прокручивать вниз при нехватке места на дисплее.
Обе камеры пронумерованы и имеют соответствующий заголовок.
Для изменения IP адресов камер необходимо использовать экран настройки.
<p align="center">
<img width="353" alt="Screenshot" src="https://user-images.githubusercontent.com/11257626/60682419-bfbe6400-9eac-11e9-8b6b-ea5d748ff87b.png">
</p>

# Экран настроек
Экран настроек разделен на две секции
- Подключение
- Видеонаблюдение
В первой секции “Подключение” настаивается IP адрес, предназначенный для подключения к ESP8266, а значит и ко всей автоматизированной системы.
Во второй секции “Видеонаблюдение” возможно изменит IP адресы обеих камер видеонаблюдения подключенных к системе.
<p align="center">
<img width="353" alt="Screenshot" src="https://user-images.githubusercontent.com/11257626/60682420-bfbe6400-9eac-11e9-9929-fdcea49da0c5.png">
</p>