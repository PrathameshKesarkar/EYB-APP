package in.prathameshkesarkar.eyb.model;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by prathameshkesarkar on 27/04/17.
 */

public class User
{
    private String _id;

    private String email;

    private String age;

    private String name;

    private String last_name;

    private String gender;

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getAge ()
    {
        return age;
    }

    public void setAge (String age)
    {
        this.age = age;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [_id = "+_id+", email = "+email+", age = "+age+", name = "+name+", last_name = "+last_name+", gender = "+gender+"]";
    }
}
