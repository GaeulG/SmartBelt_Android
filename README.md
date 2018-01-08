# Project Title

SmartShoes & SmartBelt for Posture correction

## Front-End

Front-End provides functions about tutorials, Sign-up, Sign-in, Bluetooth Connection Page and Daily statistics of bad posture.

### Source Code

 ** Java Class **
 => SmartBelt_Android/SmartBelt_Android_FrontEnd/Application/src/main/java/com/example/android/bluetoothlegatt/

	* Bluetooth Connection *
	BluetoothLeService.java
	SampleGattAttributes.java
	CustomAdapter.java
	DeviceControlActivity.java
	DeviceScanActivity.java
	ListItem.java

	* Sign up *
	RegisterActivity.java
	
	* Sign in *
	LoginActivity.java
	MyID.java

	* Main Screen *
	MainActivity.java
	
	
	* Splash Screen *
	SplashScreenActivity.java

	* View Statistics *
	StatisticsActivity.java

	* Tutorial *
	tutorialActivity.java

 ** Manifest & Layout **
 => SmartBelt_Android/SmartBelt_Android_FrontEnd/Application/src/main/java/com/example/android/

## Back-End

Back-End provides datas about user's informations and posture data through statistics

### SourceCode

=> SmartBelt_Android/SmartBelt_Android_BackEnd/

	* Sign in *
	config.inc.php
	login.inc.php

	* Sign up *
	insert.php

	* Posture Datas *
	getwalkjson.php
	walkInsert.php
