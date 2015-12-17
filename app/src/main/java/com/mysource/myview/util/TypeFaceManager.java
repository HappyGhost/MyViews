package com.mysource.myview.util;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

public class TypeFaceManager {
    public static final String FONT_MYRIADPRO_REGULAR = "MyriadPro-Regular.ttf";
    public static final String FONT_MYRIADPRO_SEMIBOLD = "MyriadPro-Semibold.ttf";

    public static final String FONT_OCR_A_STD = "OCR A Std.ttf";

    private static HashMap<String, Typeface> mFontMap = new HashMap<>();

    public static Typeface getTypeFace(Context context, String name) {
        Typeface typeface = mFontMap.get(name);

        if(typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(),"fonts/" + name);
            mFontMap.put(name, typeface);
        }

        return typeface;
    }
}
