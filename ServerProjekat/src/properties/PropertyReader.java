/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class PropertyReader {
    private static PropertyReader instanca;
    Properties prop;

    private PropertyReader() {
        readProperties();
    }

    public static PropertyReader getInstanca() {
        if (instanca == null) {
            instanca = new PropertyReader();
        }
        return instanca;
    }

    private void readProperties() {
        FileInputStream fis = null;
        try {
            prop = new Properties();
            fis = new FileInputStream("db.properties");
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getDriver() {
        return prop.getProperty("driver");
    }

    public String getURL() {
        return prop.getProperty("url");
    }

    public String getUsername() {
        return prop.getProperty("username");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }

    public void reinicijalizuj() {
        instanca = null;
    }
}
