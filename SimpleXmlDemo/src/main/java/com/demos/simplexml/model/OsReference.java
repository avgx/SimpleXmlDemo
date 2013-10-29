package com.demos.simplexml.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="operation_systems")
public class OsReference {
    @ElementList(name="desktop", inline = false)
    private List<OperatingSystem> desktopSystems;

    @ElementList(name="mobile", inline = false)
    private List<OperatingSystem> mobileSystems;
}
