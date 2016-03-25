/*
 *    Copyright (C) 2015 - 2016 VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.vrem.wifianalyzer.wifi.graph;

import android.support.annotation.NonNull;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.Viewport;

class GraphViewBuilder {
    static final int MIN_Y = -100;
    static final int MAX_Y = -20;
    static final int CNT_X = 16;
    private static final int CNT_Y = (MAX_Y - MIN_Y) / 10 + 1;
    private final View view;
    private final int graphViewId;
    private LabelFormatter labelFormatter;
    private String verticalTitle;
    private String horizontalTitle;

    GraphViewBuilder(@NonNull View view, int graphViewId) {
        this.view = view;
        this.graphViewId = graphViewId;
    }

    GraphViewBuilder setLabelFormatter(@NonNull LabelFormatter labelFormatter) {
        this.labelFormatter = labelFormatter;
        return this;
    }

    GraphViewBuilder setVerticalTitle(@NonNull String verticalTitle) {
        this.verticalTitle = verticalTitle;
        return this;
    }

    GraphViewBuilder setHorizontalTitle(@NonNull String horizontalTitle) {
        this.horizontalTitle = horizontalTitle;
        return this;
    }

    GraphView build() {
        GraphView graphView = (GraphView) view.findViewById(graphViewId);

        setGridLabelRenderer(graphView.getGridLabelRenderer());
        setViewPortY(graphView.getViewport());

        return graphView;
    }

    private void setViewPortY(@NonNull Viewport viewport) {
        viewport.setScrollable(true);

        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(MIN_Y);
        viewport.setMaxY(MAX_Y);
    }

    private void setGridLabelRenderer(@NonNull GridLabelRenderer gridLabelRenderer) {
        gridLabelRenderer.setHighlightZeroLines(false);
        gridLabelRenderer.setNumVerticalLabels(CNT_Y);
        gridLabelRenderer.setNumHorizontalLabels(CNT_X);

        if (labelFormatter != null) {
            gridLabelRenderer.setLabelFormatter(labelFormatter);
        }

        if (verticalTitle != null) {
            gridLabelRenderer.setVerticalAxisTitle(verticalTitle);
            gridLabelRenderer.setVerticalLabelsVisible(true);
        } else {
            gridLabelRenderer.setVerticalLabelsVisible(false);
        }

        if (horizontalTitle != null) {
            gridLabelRenderer.setHorizontalAxisTitle(horizontalTitle);
            gridLabelRenderer.setHorizontalLabelsVisible(true);
        } else {
            gridLabelRenderer.setHorizontalLabelsVisible(false);
        }
    }

}
