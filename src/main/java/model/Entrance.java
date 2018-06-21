package model;

import java.util.Date;

public class Entrance {
    
    protected long id;
    protected Date date;
    protected EntranceType type;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public EntranceType getType()
    {
        return type;
    }

    public void setType(EntranceType type)
    {
        this.type = type;
    }
    
}
