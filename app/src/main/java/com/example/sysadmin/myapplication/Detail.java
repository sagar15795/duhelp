package com.example.sysadmin.myapplication;

public class Detail
{
    private String result;

    private String last_update;

    private Du_details_response[] du_details_response;

    public String getResult ()
    {
        return result;
    }

    public void setResult (String result)
    {
        this.result = result;
    }

    public String getLast_update ()
    {
        return last_update;
    }

    public void setLast_update (String last_update)
    {
        this.last_update = last_update;
    }

    public Du_details_response[] getDu_details_response ()
    {
        return du_details_response;
    }

    public void setDu_details_response (Du_details_response[] du_details_response)
    {
        this.du_details_response = du_details_response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", last_update = "+last_update+", du_details_response = "+du_details_response[0].toString()+"]";
    }
}
