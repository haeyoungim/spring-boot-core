package hello.core.lifecycle;

public class NetworkClient {

    private String url;

    //디폴트 생성자
    public NetworkClient() {
        System.out.println("생성자 호출, url " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    //연결이 된 상태에서
    public void call(String message){
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료 시
    public void disconnect() {
        System.out.println("close: " + url);
    }
}
