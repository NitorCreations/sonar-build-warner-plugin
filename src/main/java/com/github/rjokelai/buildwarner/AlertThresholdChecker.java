/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2009 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * Sonar is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Sonar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sonar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */

package com.github.rjokelai.buildwarner;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.BuildBreaker;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.MeasuresFilters;
import org.sonar.api.measures.Metric;
import org.sonar.api.resources.Project;

public class AlertThresholdChecker extends BuildBreaker {

    public static final String WARNING = "WARNING_THRESHOLD_EXCEEDED";
    public static final String ERROR = "ERROR_THRESHOLD_EXCEEDED";

    public void executeOn(Project project, SensorContext context) {
        analyseMeasures(context, LoggerFactory.getLogger(getClass()));
    }

    protected void analyseMeasures(SensorContext context, Logger logger) {
        int[] count = countErrors(context, logger);
        if (count[1] > 0) {
            logger.error(String.format("%s: number of warnings: %d", WARNING, count[1]));
        }
        if (count[0] > 0) {
            logger.error(String.format("%s: number of errors: %d", ERROR, count[0]));
        }
    }

    private int[] countErrors(SensorContext context, Logger logger) {
        Collection<Measure> measures = context.getMeasures(MeasuresFilters.all());
        int[] count = new int[] {0,0};
        for (Measure measure : measures) {
            if (isErrorAlert(measure)) {
                logger.error(measure.getAlertText());
                count[0]++;
            } else if (isWarningAlert(measure)) {
                logger.warn(measure.getAlertText());
                count[1]++;
            }
        }
        return count;
    }

    private boolean isWarningAlert(Measure measure) {
        return !measure.getMetric().equals(CoreMetrics.ALERT_STATUS) &&
                Metric.Level.WARN.equals(measure.getAlertStatus());
    }

    private boolean isErrorAlert(Measure measure) {
        return !measure.getMetric().equals(CoreMetrics.ALERT_STATUS) &&
                Metric.Level.ERROR.equals(measure.getAlertStatus());
    }
}
