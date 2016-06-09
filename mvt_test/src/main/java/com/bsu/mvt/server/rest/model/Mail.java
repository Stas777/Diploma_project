package com.bsu.mvt.server.rest.model;

public class Mail {
    private String to;
    private String subj;
    private String body;

    public Mail() {}

    public Mail(String to, String subj, String body) {
        this.to = to;
        this.subj = subj;
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Mail{");
        sb.append("to='").append(to).append('\'');
        sb.append(", subj='").append(subj).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
