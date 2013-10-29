package com.demos.simplexml.rss;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="rss", strict = false)
public class Channel {
    @Path("channel")
    @ElementList(entry = "item", inline = true)
    private List<RssItem> rssItems;
}
