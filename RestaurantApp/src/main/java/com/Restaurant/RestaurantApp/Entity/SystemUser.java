package com.Restaurant.RestaurantApp.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "systemUsers")
public class SystemUser // extends User // yapılabilir
{
    @Id
    private Long id;
    @Column(name = "userName", length = 50)
    private String username = "";
    @Column(name = "password", length = 100)

    private String password = "";
    @Column(name = "order_no", length = 50)

    private boolean enabled = true;

    public SystemUser()
    {
    }

    public SystemUser(String username, String password, boolean enabled)
    {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
}