package in.prathameshkesarkar.eyb.model;

/**
 * Created by prathameshkesarkar on 27/04/17.
 */

public class Profile {
    private String resume;

    private String[] prefered_city;

    private Social social;

    private String description;

    private long mobile_no;

    public String getResume ()
    {
        return resume;
    }

    public void setResume (String resume)
    {
        this.resume = resume;
    }

    public String[] getPreferedCity ()
    {
        return prefered_city;
    }

    public void setPreferedCity (String[] prefered_city)
    {
        this.prefered_city = prefered_city;
    }

    public Social getSocial ()
    {
        return social;
    }

    public void setSocial (Social social)
    {
        this.social = social;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public long getMobileNo ()
    {
        return mobile_no;
    }

    public void setMobileNo (long mobileNo)
    {
        this.mobile_no = mobile_no;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [resume = "+resume+", prefered_city = "+prefered_city+", social = "+social+", description = "+description+", mobile_no = "+mobile_no+"]";
    }
}
