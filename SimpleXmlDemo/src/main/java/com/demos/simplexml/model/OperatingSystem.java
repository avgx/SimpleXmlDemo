package com.demos.simplexml.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "os")
public class OperatingSystem {
    @Element
    private String title;

    @Path("company")
    @Element(name="name")
    private String companyName;

    @Path("company")
    @Element(name="website")
    private String companySite;

    @ElementList(inline = false)
    private List<Version> versions;

    public OperatingSystem(String title, String companyName, String companySite, List<Version> versions) {
        this.title = title;
        this.companyName = companyName;
        this.companySite = companySite;
        this.versions = versions;
    }
}
