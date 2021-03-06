package com.artsam.app.tools;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.*;

public class MyBundleControl extends ResourceBundle.Control {

    static final Logger logger = Logger.getLogger(MyBundleControl.class);
    @Override
    public ResourceBundle newBundle(String baseName, Locale locale,
                                    String format, ClassLoader loader, boolean reload)
            throws IllegalAccessException, InstantiationException, IOException {

        String bundleName = toBundleName(baseName, locale);
        ResourceBundle bundle = null;
        if (format.equals("java.properties")) {
            final String resourceName = toResourceName(bundleName, "properties");
            final ClassLoader classLoader = loader;
            final boolean reloadFlag = reload;
            InputStream stream = null;
            try {
                stream = AccessController.doPrivileged(
                        (PrivilegedExceptionAction<InputStream>) () -> {
                            InputStream is = null;
                            if (reloadFlag) {
                                URL url = classLoader.getResource(resourceName);
                                if (url != null) {
                                    URLConnection connection = url.openConnection();
                                    if (connection != null) {
                                        // Disable caches to get fresh data for
                                        // reloading.
                                        connection.setUseCaches(false);
                                        is = connection.getInputStream();
                                    }
                                }
                            } else {
                                is = classLoader.getResourceAsStream(resourceName);
                            }
                            return is;
                        });
            } catch (PrivilegedActionException e) {
                throw (IOException) e.getException();
            }
            if (stream != null) {
                InputStreamReader reader = null;
                try {
                    reader = new InputStreamReader(stream, "UTF-8");
                    bundle = new PropertyResourceBundle(reader);
                    Enumeration<String> e = bundle.getKeys();
//                    while (e.hasMoreElements()){
//                        String key = e.nextElement();
//                        logger.debug("newBundle() Key:"+ key+" value:"+bundle.getString(key));
//                    }
                } finally {
                    stream.close();
                    if (reader != null) {
                        reader.close();
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("unknown format: " + format);
        }
        return bundle;
    }

    @Override
    public List<String> getFormats(String baseName) {
        return FORMAT_PROPERTIES;
    }
}