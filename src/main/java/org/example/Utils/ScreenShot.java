package org.example.Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ScreenShot {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenShot.class);

    private ScreenShot() {
    }

    public static String captureScreenshot(WebDriver driver, String folder, String screenName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenShotPath = rutaEnProyecto(folder) + "/evidencia_" + getCurrentDateTime() + "_" + screenName + ".png";

        try {
            Files.copy(src.toPath(), Path.of(screenShotPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LOGGER.error("No se pudo guardar la captura", e);
        }
        return screenShotPath;
    }

    public static String getCurrentDateTime() {
        return new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
    }

    public static void createFolder(String folderName) {
        try {
            Files.createDirectories(Path.of(rutaEnProyecto(folderName)));
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo crear la carpeta de evidencias", e);
        }
    }

    private static String rutaEnProyecto(String path) {
        return System.getProperty("user.dir") + path;
    }
}
