package com.demos.simplexml.rss;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Calendar;

@Root(name = "item", strict = false)
public class RssItem {
    @Element(name="title")
    private String title;

    @Element(name="pubDate")
    private Calendar date;
}
