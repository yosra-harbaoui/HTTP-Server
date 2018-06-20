package ch.heig.payloads;

public class Partie1FirstPayload
{
    private String oldToken;
    private String newToken;
    private String email;

    public Partie1FirstPayload(String oldToken, String newToken, String email)
    {
        this.oldToken = oldToken;
        this.newToken = newToken;
        this.email = email;
    }

    public String getOldToken()
    {
        return oldToken;
    }

    public void setOldToken(String oldToken)
    {
        this.oldToken = oldToken;
    }

    public String getNewToken()
    {
        return newToken;
    }

    public void setNewToken(String newToken)
    {
        this.newToken = newToken;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
