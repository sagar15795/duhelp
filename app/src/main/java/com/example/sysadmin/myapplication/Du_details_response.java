package com.example.sysadmin.myapplication;

public class Du_details_response
{
    private String id;

    private String status;

    private String photo_url;

    private String static_text;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getPhoto_url ()
    {
        return photo_url;
    }

    public void setPhoto_url (String photo_url)
    {
        this.photo_url = photo_url;
    }

    public String getStatic_text ()
    {
        return static_text;
    }

    public void setStatic_text (String static_text)
    {
        this.static_text = static_text;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", status = "+status+", photo_url = "+photo_url+", static_text = "+static_text+"]";
    }
}