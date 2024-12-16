package com.driverInstance;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import java.io.File;

public class AppiumServer{
	static AppiumDriverLocalService service;
	static String osName=System.getProperty("os.name").toLowerCase();
	//0-Param Constructor
	public AppiumServer(){
	}
	private synchronized static void setInstance(){
		if(osName.contains("mac")) {
			AppiumServiceBuilder builder = new AppiumServiceBuilder();
			builder
					.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
					.usingDriverExecutable(new File("/usr/local/bin/node"))
					.withArgument(GeneralServerFlag.LOG_LEVEL, "error")
					.usingAnyFreePort()
					.withArgument(()->"--base-path", "/wd/hub")
					.withLogFile(new File("Appiumlog.txt"))
					.withIPAddress("127.0.0.1");
			service = AppiumDriverLocalService.buildService(builder);
		} else if(osName.contains("linux")){
			System.out.println("To be implemented for linux");
		} else{
			AppiumServiceBuilder builder = new AppiumServiceBuilder();
			builder
//					.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
//					.withAppiumJS(new File("C:\\Users\\" + System.getProperty("user.name")
//							+ "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
					.withArgument(GeneralServerFlag.LOG_LEVEL, "error")
					.usingAnyFreePort()
					.withArgument(()->"--base-path", "/wd/hub")
//					.withLogFile(new File("Appiumlog.txt"))
					.withIPAddress("127.0.0.1");
			service = AppiumDriverLocalService.buildService(builder);
		}

	}
	private static AppiumDriverLocalService getInstance(){
		if(service == null){
			setInstance();
		} else {
			service.stop();
			setInstance();
		}
		return service;
	}

	public static String start(){
		getInstance().start();
		int port = service.getUrl().getPort();
		System.out.println("Port: " +port);
		System.out.println("Appium Server Started = " +service.isRunning());
		return String.valueOf(port);
	}

	//Stop Server
	public void stopServer(){
		service.stop();
	}
}
