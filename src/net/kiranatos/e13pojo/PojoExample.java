package net.kiranatos.e13pojo;

import java.io.Serializable;
import java.util.Objects;

/* Java Bean (POJO - Plain Old Java Object):
1. Public class
2. Default constructor
3. Private non-static fields
4. Correct set & get methods for incapsulation
5. Serializable
6. equals, hashcode, toString
*/

public class PojoExample implements Serializable, Cloneable {
    private int id;
    private String str;

    public PojoExample() { }
    public PojoExample(int id, String str) {
        this.id = id;
        this.str = str;
    }

    public int getId()              { return id; }
    public String getStr()          { return str; }
    public void setId(int id)       { this.id = id; }
    public void setStr(String str)  { this.str = str; }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.str);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PojoExample other = (PojoExample) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.str, other.str)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PojoExample{" + "id=" + id + ", str=" + str + '}';
    }    
}