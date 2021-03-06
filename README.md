sonar-build-warner-plugin
=========================

# DEPRECATED

This plugin was developed for a now legacy version of Sonar. Use the [oliverbk/sonar-build-breaker](https://github.com/olivervbk/sonar-build-breaker) instead, or feel free to PR for newer Sonar support.

----------

# OLD


Sonar Build Warner Plugin

Plugin for Sonar that outputs all alerts to console with the following syntax:

* WARNING_THRESHOLD_EXCEEDED: number of warnings: 123
* ERROR_THRESHOLD_EXCEEDED: number of errors: 313


#### Download


[Download](https://github.com/downloads/rjokelai/sonar-build-warner-plugin/sonar-build-warner-plugin-1.0.jar) the precompiled jar.


#### Example usage with Jenkins

1. Add the plugin to sonar by copying it to `$SONAR_INSTALL_DIR$/extensions/plugins`
2. Install [Jenkins Console log parser plugin] (https://wiki.jenkins-ci.org/display/JENKINS/Log+Parser+Plugin)
3. Restart Jenkins
4. Configure Console log parser with the following ruleset
  - save it, e.g., under $JENKINS_USER_HOME/ruleset.conf
  - specify the ruleset location in Jenkins system configuration -> Console Output Parsing
5. Add console log parser to the desired Jenkins build (see image)
  - be sure to add it after the Sonar plugin

###### Sample configuration file for Console log parser plugin

```
error /ERROR_THRESHOLD_EXCEEDED/
warn /WARNING_THRESHOLD_EXCEEDED/
```

![Console log parser configuration example](https://github.com/downloads/rjokelai/sonar-build-warner-plugin/console_log_config.png)
