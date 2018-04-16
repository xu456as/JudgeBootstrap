package ele.me.hackathon.tank;

/**
 * Created by lanjiangang on 15/11/2017.
 */
public class GameResult {

    private String metaInfo;
    private String result;
    private String win;
    private String state = "";
    private String reason = "";

    public String getMetaInfo(){
        return metaInfo;
    }

    public void setMetaInfo(long timestamp, long aId, int aPort, long bId, int bPort){
        this.metaInfo = String.format("%d|%d|%d|%d|%d", timestamp, aId, aPort, bId, bPort);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}
