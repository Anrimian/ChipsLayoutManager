package com.beloo.widget.chipslayoutmanager;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({ChipsLayoutManager.HORIZONTAL, ChipsLayoutManager.VERTICAL})
@Retention(RetentionPolicy.SOURCE)
@interface Orientation {
}
