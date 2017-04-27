package in.prathameshkesarkar.eyb.model;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by prathameshkesarkar on 27/04/17.
 */

public class Social extends RealmObject{
    private String twitter;

    private String linkedin;

    private String stackoverflow;

    private String github;

    public String getTwitter ()
    {
        return twitter;
    }

    public void setTwitter (String twitter)
    {
        this.twitter = twitter;
    }

    public String getLinkedin ()
    {
        return linkedin;
    }

    public void setLinkedin (String linkedin)
    {
        this.linkedin = linkedin;
    }

    public String getStackoverflow ()
    {
        return stackoverflow;
    }

    public void setStackoverflow (String stackoverflow)
    {
        this.stackoverflow = stackoverflow;
    }

    public String getGithub ()
    {
        return github;
    }

    public void setGithub (String github)
    {
        this.github = github;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [twitter = "+twitter+", linkedin = "+linkedin+", stackoverflow = "+stackoverflow+", github = "+github+"]";
    }
}
