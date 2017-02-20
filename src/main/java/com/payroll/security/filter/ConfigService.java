/**
 * Program Name: ConfigService
 * 
 * Program Description / functionality: This class is for configuration service to read properties
 *            from properties files.
 * 
 * Modules Impacted: My Company
 * 
 * Developer    Created             /Modified Date       Purpose
  *******************************************************************************
 * Bhargava   17/08/2015
 *  
 */

package com.payroll.security.filter;

import javax.annotation.PostConstruct;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service(value = "configService")
@Scope(value = "singleton")
@PropertySources({@PropertySource("classpath:errormessages.properties"),
    @PropertySource("classpath:mycompany.properties")})
public class ConfigService {

  private static final Logger LOGGER = LogManager.getLogger(ConfigService.class);
  
  @Autowired
  private Environment environment;

  private static Environment env;

  @PostConstruct
  public void init() {
    env = environment;
  }

  public static String getProperty(String key) {

    LOGGER.debug("Getting property  :" + key);

    return env.getProperty(key);

  }

}
