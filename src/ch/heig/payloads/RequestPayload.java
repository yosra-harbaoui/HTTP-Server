package ch.heig.payloads;

public class RequestPayload
{
    private String email;

    public RequestPayload(String email)
    {
        this.email = email;
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