package com.demos.simplexml.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name="version")
public class Version {
    @Attribute(required = false)
    private String codename;

    @Attribute
    private int year;

    @Text
    private String title;

    public Version(String codename, int year, String title) {
        this.codename = codename;
        this.year = year;
        this.title = title;
    }
}
