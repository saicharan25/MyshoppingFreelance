package info.devexchanges.navvp.model;

/**
 * Created by acer on 9/12/2017.
 */

public class Subcategories {

    private String cat_id;

    private String id;

    private String subcategory_name;

    private String icon_image;

    public String getCat_id ()
    {
        return cat_id;
    }

    public void setCat_id (String cat_id)
    {
        this.cat_id = cat_id;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getSubcategory_name ()
    {
        return subcategory_name;
    }

    public void setSubcategory_name (String subcategory_name)
    {
        this.subcategory_name = subcategory_name;
    }

    public String getIcon_image ()
    {
        return icon_image;
    }

    public void setIcon_image (String icon_image)
    {
        this.icon_image = icon_image;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cat_id = "+cat_id+", id = "+id+", subcategory_name = "+subcategory_name+", icon_image = "+icon_image+"]";
    }
}
