package com.demos.simplexml;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.demos.simplexml.model.OperatingSystem;
import com.demos.simplexml.model.OsReference;
import com.demos.simplexml.model.Version;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private static final String TAG = "SimpleXmlDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //deserializeObject(getResources().openRawResource(R.raw.os));
        serializeObject();
    }

    private void deserializeObject(InputStream is) {
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
}
