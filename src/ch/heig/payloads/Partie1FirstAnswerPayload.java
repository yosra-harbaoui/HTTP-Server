package ch.heig.payloads;

import java.util.Date;

public class Partie1FirstAnswerPayload
{
    private String token;
    private String createdAt;

    public Partie1FirstAnswerPayload()
    {

    }

    public Partie1FirstAnswerPayload(String token, String createdAt)
    {
        this.token = token;
        this.createdAt = createdAt;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String tokenId)
    {
        this.token = tokenId;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }
}
