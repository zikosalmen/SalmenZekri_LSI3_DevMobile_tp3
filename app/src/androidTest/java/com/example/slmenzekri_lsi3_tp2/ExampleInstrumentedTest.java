package com.example.slmenzekri_lsi3_tp2;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        android.content.Context appContext = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.slmenzekri_lsi3_tp2", appContext.getPackageName());
    }
}

