import java.util.Date;

class Session {
    private User user;
    private Date expireDate;
    final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
    final int minutes = 1;

    public Session(User user) {
        setExpireDate();
        this.user = user;
    }

    public Date getExpireDate(){
        return expireDate;
    }

    private void setExpireDate(){
        this.expireDate = new Date((new Date()).getTime() + (minutes * ONE_MINUTE_IN_MILLIS));
    }
}