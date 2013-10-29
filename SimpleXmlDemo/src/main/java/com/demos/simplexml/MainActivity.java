package com.demos.simplexml;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.demos.simplexml.model.OperatingSystem;
import com.demos.simplexml.model.OsReference;
import com.demos.simplexml.model.Version;
import com.demos.simplexml.rss.Channel;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity {
    private static final String TAG = "SimpleXmlDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        deserializeRss(getResources().openRawResource(R.raw.rss));
        //deserializeOsReference(getResources().openRawResource(R.raw.os));
        //serializeObject();
    }

    private void deserializeRss(InputStream is) {
        Matcher matcher = new Matcher() {
            @Override
            public Transform match(Class aClass) throws Exception {
                if (aClass == Calendar.class) {
                    return new CalendarTransform();
                }
                return null;
            }
        };

        Serializer serializer = new Persister(matcher);

        try {
            Channel result = serializer.read(Channel.class, is);
            Log.d(TAG, "Deserializing OK");
        } catch (Exception e) {
            Log.e(TAG, "Error while parsing RSS", e);
        }
    }

    private void deserializeOsReference(InputStream is) {
        Serializer serializer = new Persister();

        try {
            OsReference result = serializer.read(OsReference.class, is);
            Log.d(TAG, "Deserializing OK");
        } catch (Exception e) {
            Log.e(TAG, "Error while parsing XML", e);
        }
    }

    private void serializeObject() {
        OperatingSystem debian = new OperatingSystem("Debian Linux", "Debian", "http://debian.org", new ArrayList<Version>() {{
            add(new Version("Lenny", 2009, "5.0"));
            add(new Version("Squeeze", 2011, "6.0"));
            add(new Version("Wheezy", 2013, "7.0"));
        }});

        Serializer serializer = new Persister();
        StringWriter writer = new StringWriter();
        try {
            serializer.write(debian, writer);
            Log.d(TAG, writer.toString());
        } catch (Exception e) {
            Log.e(TAG, "Error while serializing", e);
        }
    }

    public class CalendarTransform implements Transform<Calendar> {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyy HH:mm:ss zzz", Locale.ENGLISH);

        @Override
        public Calendar read(String s) throws Exception {
            Calendar c = Calendar.getInstance();
            c.setTime(dateFormat.parse(s));
            return c;
        }
        @Override
        public String write(Calendar dateTime) throws Exception {
            return dateFormat.format(dateTime.getTime());
        }
    }
}
