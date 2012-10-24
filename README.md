sonar-build-warner-plugin
=========================

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
5. Add console log parser after Sonar to the desired Jenkins build (see image)

###### Sample configuration file for Console log parser plugin

```
error /ERROR_THRESHOLD_EXCEEDED/
warn /WARNING_THRESHOLD_EXCEEDED/
```