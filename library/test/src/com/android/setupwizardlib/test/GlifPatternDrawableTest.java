/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.setupwizardlib.test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.android.setupwizardlib.GlifPatternDrawable;

public class GlifPatternDrawableTest extends AndroidTestCase {

    @SmallTest
    public void testScaleToCanvasSquare() {
        final Canvas canvas = new Canvas();
        Matrix expected = new Matrix(canvas.getMatrix());

        final GlifPatternDrawable drawable = new GlifPatternDrawable(Color.RED);
        drawable.setBounds(0, 0, 683, 384);  // half each side of the view box
        drawable.scaleCanvasToBounds(canvas);

        expected.postScale(0.5f, 0.5f);

        assertEquals("Matrices should match", expected, canvas.getMatrix());
    }

    @SmallTest
    public void testScaleToCanvasTall() {
        final Canvas canvas = new Canvas();
        final Matrix expected = new Matrix(canvas.getMatrix());

        final GlifPatternDrawable drawable = new GlifPatternDrawable(Color.RED);
        drawable.setBounds(0, 0, 683, 768);  // half the width only
        drawable.scaleCanvasToBounds(canvas);

        expected.postScale(1f, 1f);
        expected.postTranslate(-100f, 0f);

        assertEquals("Matrices should match", expected, canvas.getMatrix());
    }

    @SmallTest
    public void testScaleToCanvasWide() {
        final Canvas canvas = new Canvas();
        final Matrix expected = new Matrix(canvas.getMatrix());

        final GlifPatternDrawable drawable = new GlifPatternDrawable(Color.RED);
        drawable.setBounds(0, 0, 1366, 384);  // half the height only
        drawable.scaleCanvasToBounds(canvas);

        expected.postScale(1f, 1f);
        expected.postTranslate(0f, -87.5f);

        assertEquals("Matrices should match", expected, canvas.getMatrix());
    }
}
