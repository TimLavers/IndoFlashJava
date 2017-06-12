package org.grandtestauto.indoflash;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.InstrumentationTestCase;
import android.test.TouchUtils;
import android.view.KeyEvent;
import android.view.View;

public abstract class UIActivity {

    private Instrumentation instrumentation;
    private InstrumentationTestCase testCase;
    private Activity activity;
    private Instrumentation.ActivityMonitor monitor;

    public UIActivity(InstrumentationTestCase testCase) {
        this.testCase = testCase;
        instrumentation = testCase.getInstrumentation();
    }

    public UIActivity(InstrumentationTestCase testCase, Instrumentation.ActivityMonitor monitor) {
        this.testCase = testCase;
        instrumentation = testCase.getInstrumentation();
        this.monitor = monitor;
        activity = instrumentation().waitForMonitorWithTimeout(monitor(), 5);

    }

    protected void doClick(View view, Class classOfDestinationActivity) {
        Activity current = activity();
        instrumentation.removeMonitor(monitor);
        monitor = instrumentation.addMonitor(classOfDestinationActivity.getName(), null, false);
        clickView(view);
        current.finish();
    }

    protected void clickView(View view) {
        TouchUtils.clickView(testCase, view);
    }

    public void clickMenu() {
//        instrumentation.send

        instrumentation.getUiAutomation().injectInputEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MENU), true);
        pause(100);
        instrumentation.getUiAutomation().injectInputEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MENU), true);
    }

    private void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract Class activityClass();

    protected Instrumentation instrumentation() {
        return instrumentation;
    }

    public void finish() {
        activity.finish();
        instrumentation.removeMonitor(monitor);
    }

    protected InstrumentationTestCase testCase() {
        return testCase;
    }

    public Activity activity() {
        return activity;
    }

    protected void setActivity(Activity activity) {
        this.activity = activity;
    }

    protected void setMonitor(Instrumentation.ActivityMonitor monitor) {
        this.monitor = monitor;
    }

    public Instrumentation.ActivityMonitor monitor() {
        return monitor;
    }

}
