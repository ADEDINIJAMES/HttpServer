package org.example.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
        server.createContext("/", new Handler("/"));
        server.createContext("/about-us", new Handler("/about-us"));
        server.createContext("/services", new Handler("/services"));
        server.createContext("/contact-us", new Handler("/contact-us"));

        server.setExecutor(null);
        server.start();

    }

    static class Handler implements HttpHandler {
        private String path;

        public Handler(String path) {
            this.path = path;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response;
            System.out.println("This is the current path "+path);
            if (Objects.equals(path.length(), 1)){
                response = "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>JAMTECH</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        //"<img src="+"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAABblBMVEXy8vLy8vHx8vT18e77bQHz8fLx8/IAIz/x8vX9bADz8u/w8/T08fDx8fEAJD719fUAACMAAB70bAAAABkAACUAACDu8/jy8fYADywAIjqJlaAAAAD2aQD0///sagAAGTTpllgAABAAFDT79On+7+EAHjUAFjEAI0ORnqcAJEPc5uzO2uEAABUACyb/7dMAHTsAFSn84sm7yND98NzjcBVyfor+3bPlbQAAACr61LJIWGcAJTg7TV3l5eXU1NS3t7eNkJNwcW9lbHHDxscrLzKvt7wuPERES0+Xm50IFh4NIi1gZWY+REiqqqsADRsQJC6Dg4Wbp7cjOEzm0cLOnHtBVWnem2jabhr1wZj1vYf3yJ7ahUTbdynrsIj4zqOJnKrnomLfgz7vtpXuqnWsusXwm1b80JvuhDAuRVnMdSnek1dYbH/hv6DFhU/WubPy4NbIrIbNqJH2gCb5ql/3+eX/5sDhpHb1lEfGkGjez8FhrYynAAARJUlEQVR4nO2bjV/bRprHJY314hnZYwQev8QShJENyGCQLMfYQNJNFtKm223jbmNeDMELKTnSa67dTXr3398zktP07rPbdi9wxHzm+wFjhID56Zl53kZSFIlEIpFIJBKJRCKRSCQSiUQikUgkEolEIpFIJBKJRCKRSCQSiUQikUgkEolEIpFIJBKJRCKRfPzkbnoA18vODr3pIVwjoI3ef0Bzym0VqT5QVfXRJ5TQRznjpgdzLaj3/5CjDx4y8tWDW2pEgz76Y7C7Fzz+9HYKpEqOuF989vDJZx2XaTc9muuBsKD5+M9PnlR3o53cbYwZ97/6/E+fffnkyZP+w86nX+wkx7RbZUxKKXEf/vnL1Y57n4FDxRgbjJHb5FWx4X76eHlv9+4X1ifLjBAVYUMzbpfCR5+zR4/dnT/ddx99vsuYCmZU1Zse1hVi3P+EGvf/4Fr6V4Swr/4YucRQb5fCHbDZzqdU1ylChhtt9APXSBXeFpkG6Mk9AIU5jClh7OGdpwwjW2NWovO2yNTfv3WX7/YDqmoWuU0K6ftQr5dYtFE/d2HO3hJxifX09zZUddttDmar5NYofE+q0sBf+zjYvtNnFN8OiWlshyWnESKyNYN//ZeQs29WXwYUpz+ZcohVKjFCkc2wYVDL0jF/NtznrD9nNhHVVYRueoQfilBICOGt3sHBSQsZuoJ7cXzsk/5avmnZCOm//Tc+biyi6Rj7h0dDz4uPDtuGTvhCrXvgs71Fs0mRwm56hB+KZRkY+YfDWsbJOE58xm3GDrvZ+K88+HJ+K7A066ZH+EGAFykRUBiOnCwozGSy8SFnWjjK1uJ9HhXmB4E13bNUFTZUET/2hAmzjpOtjdsMtRZqYM4T93mh3CdTX/frJR35R042m4EP+Oz2Sjo/dTK1zKhH++ZaxxUdqmku+yFLQ+3RRCF88U5sxk9r2WzWGYfBYmX2KcU2VqZYITgW3BrBFE0U1rLdE8TAmWZhVXrPeLVcmW9SjG96lB8CNgjmC4kNhadxhiEmrQsHyGTifbxVaWwzbboVYshlDoTCTLIML33Me91Ubm3U2p1bmnvu3vQgPwgV8murNRbRAqZqbdxDrPXCSfRmst5bPlhfKgdkmo2YJNYoHHc90OeNe5jxM09M0WTOjlrLi0uNV64x7fk3oeHbo+Hwcj/kBgmHtdrEhtnu2Y5p5u9G1vQGxbR40hTE262wTYlhoN4w9TpCIRjx1bxZh2Lxhsf5ASSBDttQYDDdRu1Wyw/HtVo6S0Fj9+TRnLlejMhNj/P/gqhtddsGT0M0CxIZhPz98Wh87PeGqQ1FquqdocHS0nxfuFNjisK+LhozCBFGsG3bOZuqKmq/OR53a5By9/z9GKIHJKkQQmqjdmdxqT7TJCWNTVshZQuFhmbB5EQ83H8Re4km57LtH8diDYrvsnH4aG6pstFxSzop3fSQfz8GoOSENIS53wr/ujDyIBpmayImxr2SfzqJ+bAQ93fqZqX+ZVDSp2mWCoF6qYT8duve/unIq4lMOytSNfhSO/Jp67KblBqZmnPGt9fNSvHcwniKSkXDgJXIw8Ozo1FXhPekbpq8OFnvgJMwLagytdoL3lk0841XFE1BAg7OU7UIzExCkN97Noo9sJGTKEyyGCES3kAQZKg3mpSMo/buprlibjF7ChQCtq5ZFuYh+E3P8+L4YvTTxUUsDOnUasKGMFkz3X3OMGTjyfdxGM3ll5Y2IzodfUUDKwyFsPS84cLBYS9sA62wd/h24aIrfGlGrEhv/IYYJ3FiVFDICvmllfIymYIeuKrksIHDyzgenR6GnMN0hXhhYHCpImnbXxh20yjoveiF3yYKwYY9auZNyNzcaWh/5xTUOh7GC/utvxtajqigTRcaFQIvFCbvwbjbFRqdeHThpbM2vkdf1vNmfeBOQw2l8t7RcKHnI6h6sQ0lkabp8BbyNnjBotxv9466iTARQBJPE5/gb+Yr+cpGQD5eG4IcDBmoZbT3/+0S9NmQk+Kc+n4vOwl0oJFYYOSD2EldTlpgwCyF8sLMFyL6McbDtDjSVTAUQa/5d/9+2EKWhn5tRan8ZPiutHivMJ+fO/94m8OQvSiYY/87/4fQZhYkor92NmP8MJ5IzP5CYWP5o7Rhiq7p6PvX4dvWd6+tko2UdCviH49XI0gRfYxaZlIGx6HbF7O0XKUf7xaGrWjc/8vp+Nl3CJJtcCi/dsuT4edYOBY5Tlrqg8JBfQXyts7HZ0NVdOMh1TJA1+veWexdHH/3GiHFeLfvqypWybIsRcWTA2BX8KeEoUPPSRqMoPOixcoV82eFuX9RZ07J5dTr6vKoiqqpCKkQx9DXJ6Aw3g85zoFnTRwNOFgFFqVuwYHkfEPVdVFAQF7evnBEf1GUGy/8ZhEE5hc7Jd1ihFDd0hT8e29jyFGL0X+yIK4EEeMA8B7t/zhYOPihbcD7UvoP4TjU+ci22KR4hzdEsTmySxasxKTgACue8fM5UGgKhSUWnEeMaIr6exVq7Gmn07ymPpYYA1YNA3GslfTvX7/+of3195zjUimt1bEC70qixMeToALVg1YS294q7sUiBYc0tbuPq4umWckvVkFhtFaY7bB/QSE7v1Mub1xT+0OMgYkbf0S9hDn3v8Y2AhtaNlKFJIwtZoFJESJpJ83SEeTlhEFpjNqjrKiqnMwwpFAB50Hhckmn1bKZ32Ca+rtnKVtey5uzwfUoFPi9w54PQ9aS/JqLb0N/MjrwKX7YA0KcKFTTb8M2J5bePko6io4zbgczprDh5jko7CyumBuwsH5WSAW/+Ieifzc5pqu6+k4hTc68anWqQvlZ1+ue+ulkZMa9o67jxQecgf/RQDTfH0JpGI/2uc6wqrWOhnB6vNAyiMVPRePbcbxj+rSYhwrYhKyNucKGdea6FNkG5OqUNaOo2WQus3TwyVjNIeqypjjIqLjfGAuF+WJA3aAZNQP3SusTzdZI+whGOfYt8H66ztovPLHlAkkKEQpz2F8QdqrBGSWGCDr2Egfa7YF7AoVJNByGbLteMZegQAxYZ28L4kZlMNiOShTm+/nDrXKhUGgMXkWsxCwV5QiJ+oO1YnGuvNXfJVQFheV8ZS44364XCjNPts+v0o6arhB/olBTcnqJ7nue0/WytXGLEFiElp0oBFXdQ1ibrD3OZGsgERQqYEMnab4985uFSiUPEvNucGd13lwyzbninSpTjOZ2obG+ubm4Wa43Zh4GTLMxaX4zt77emCtsLq4vzm1HLqGgMF//prC5uVleN+ur1St0Opqem9iwrYl7SNVwWMvGBxdQwp/5kJtpFkoU/tjNOgs+x/gQxP/oJQr1EthQKAR7dxqgMG+u9xltnr9aX1qpPz2PrJwRfNkw1/eeNpvNaqVSKb9kGiLNLXC75c55dN6pz5vza0GisLK1NngeRVX43cpqdHVW1NSfFYriT20vQPi+bB92M058whnEwURh7cVPDkxcbrQXatnu37oZUIh0nS+IxNR5xoM6jHArX1l87iLiVhdXVhpMeGj3YQMmbAD+w3IjsFNhmSpuv5GvlCNKNeI298r1O7s0tWFfZAru08WllbXlq1CYLmYrsWEtVYgV3OvWQInNLx041AKFWrIOndOzrgNhnbwZZmqjb2PHiYVCf5x2oWh1ERSCkYqRioQvNcGXEhvRYCO/Mt8JEli/kp8HFc2CSNBdDMFJZUGnX2U4sWEhEjft4GZxZQXy9w+Vp4PXh/im5SwI0P7YSRRCvQTJtJiepVI4TPZ2NVtXfXFHwtt7Qyd70RL1hHd2L05tiMMhRHzv4O9BfWsL3EveHDCKFZq0ThmkBjQqmCv5xurqLLAK5pzru2y3sAIul0GwtW2NEde1hA1NcybQ4LrToLxkXoFCYT3MfU6hYtKFwsy4je0SxI1MdvSGc+SfwXob9qBQ1IXC2lnr1Mt2D96MwMT37sE69HqUof0uJKWXLbdTzguFsLhcLVGYrzRYySJWNAMh5OX2O/qdJmFP55bMYgReDJJBA0IIY9pEYXJPTgBvr0Sh7h8+e9vjUMrnfLE/P26pyOjFtWz2x9Nnp2dnPzngMo/ajKmpQn4Cvuanv4HuhTYozHo9S9xR4zijkEYzlRXhaPKFJjhB7Z1C3bACiOTl564rkl7XdcVzNjQqrixNZik22KutvWbqS2evWCHklF48fkOYStpgw9pRG/PWUU3MOq8GAUPsf4rOPZvYELfGmYwHV6B7SHtdoVDTW6OsNwJb7j3ZMkXKBquMQgmUKCzDLKU6lMWVyhZ4Gli0NKhube1ShQ0gINSbLiXMDfrleqHjWstr72wIs3TlihS+6cJUO/Ap4r0Y/OElV0WpkOmenp2CEeEV6luYpzhdh2ecH4i5mamN21Qk3LAOOUzSUY+7D8tblRWofsFX0BwGG1Y3QeFyM9oNaHOjkZ8fRDAVg6eDcmV1uaTQaHY+P1+vRs1oeQBudTUCG66t/EJh/koUGv4CzLjRca+3LyZp91uMwxFIWGjzNkec8/ZJ7NWcSx9PZqn+Zgh2hQSNiz18UMhbYxCI3GoR6sIVUf32GYKiUqPNmTrk4AXwLhAdXxbn1zcrLweLm+uLM1VmKQY73yqalfJcYaZQny/Uz92SsGH+bpJ50wDMuXYlnkYJjzwYsteFV2941jL4s8S5aIwbSY6dVH9xD/uXwr8iqw0/z0KU0JP9mPgemHAMApeLEOlB34o5O9nHp2R3awbGP7PRYQZj5/1GoTgLOdrgXQlI2PJeuTgzMzO79nI5cCHnj9aKd/vpb7P+3ZnZK6kVLRQej+MuEI+f9SBb4cdxd7TvM42LShA+/IW4exRa/HgYg60YCo/ghGNeSjr+R2F4tBAitjyTF+18YK7K0hiLVZFrn4u82rIsQlwWREAgUoBJnwAygPuRyLzB+4gOAoWaeXKbg8Hg1Ku45UEV2xCTuqjlI41ApRD2Qm6x3GQYhMKPfWSpbTiFE6iFfHECVsTt3r1W+J/HLcQ6s5VUnzm/x2iqUOwZ65RoVLf1kgZRIa2UxLNg+N01UMUxIyksRFfENgidtIGQTRX6q53M3wvk1XqpRJMGhSJ2C0WjAmoLXZ+kO5jr2CpBaQxnqgwKDV0z4HSOmXD6/g8hp83tmXo+VbjeaNJJK0nFWFfFFrmBDdGsSdGJBf8g7RMYRNMsRU32z9XJ5Z48m6KLQhxdyQaWRjRIKojoT4AITbdtW9c10GqByKS3ARmAXoL/x5hiE4JUJgYr+uKaQvB/+dRdbiyZldSC5uI5XPh0C98STR8iOlaWaNhNBqtp8JHeYwuXwFahVLREZWNZYjfd/vl5BrgmV7S3Y+saSf4WzBG4qCKRg0Pw5zVrUoBaFlhZUVUNBmKJDZqk7wYGxOI6u9H25ooIEokN53bFvTS5iZb0uRP9n95R+4unM9+dcg37Of+zd/ePu78/nwOXW7XhymPbFksHU7b7uDgPWYwpPs35RkSn8Z62XzxAaRHRY4M1a9tUdCCqlWI9b1ZE1QuJZ/nldXUC///Y2YEaQMCC5tPOYG5x3RTSKqJkmp/psKtxfjfJzid7jx92Oq+2B/W5zYaQl6w/0Fif2Y5cOg1b27+O5gbVwWqhsb6eek5RSFQq5tJicW+XCX2pw5hqnRCxH3UG5U0xP/NpACwXtjpQyd6ip/LBxQTnnVeDtZnZmcLG9qvnogVKpug2tt8gB4HagEo2cTeB+OISbOS0KboV8TdRDQLxXlOTLUWiqSK7wlP+1Nr/Qqy4JAlQxYYU5Hkg9TYtQ9EbUyF5VSYKlfRRrlulUCKRSCQSiUQikUgkEolEIpFIJBKJRCKRSCQSiUQikUgkEolEIpFIJBKJRCKRSCQSiUQiuX7+G418RftNJfMbAAAAAElFTkSuQmCC"+">\n"+

                        "<h1 style="+"color: purple>"+ "JAMETECH \n\t"+"</h1>\n" +
                        "  <h2>"+"(a subsidiary of TunmiseGroup)"+"</h2>"+
                        "<span style="+"color: purple;font-size:50px\">"+
                        "<a href = "+"/>HOME PAGE</a>\n"+
                        "<a href = "+"/about-us>ABOUT US</a>\n"+
                        "<a href = "+"/contact-us>CONTACT US</a>\n"+
                        "<a href = "+"/services>OUR SERVICES</a>\n"+
                        "</span>"+
                        "<br>"+
                        "<br>"+

                        "<img src="+"https://i.pinimg.com/originals/c7/16/b1/c716b153427a8ac1c9e4dbab1cd41d21.png"+">"+

                        "</body>\n" +
                        "</html>";
            } else if (path.contains("/about-us")) {
                response = "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>JAMTECH</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<a href = "+"/>HOME PAGE</a>\n"+
                        "<a href = "+"/about-us>ABOUT US</a>\n"+
                        "<a href = "+"/contact-us>CONTACT US</a>\n"+
                        "<a href = "+"/services>OUR SERVICES</a>\n"+
                        "<h1>ABOUT US</h1>\n" +
                        "<br>"+
                        "<img src="+"https://cms.araiindia.com/MediaFiles/aboutus_1095.png"+">"+
                        "<div style=\"size:100px;font-weight:100; color: purple; width: 1015px;\n" +
                        "  height: 600px;\n" +
                        "  top: 900px;\n" +
                        "  left: 165px;\">"+
                        "<p style=\"font-size:40px;font-weight:100; text-align: justify; color: purple\"> JameTech Institute works with companies to make sure their dreams come to pass. This is a TECHNOLOGY Institute that has the passion of developing world class SOLUTIONS that have excellent user experience. We base our services on :"+"<br>"+" Integrity, Diligence, Discipline."+"<br>"+"YOU CAN TRUST US WITH JOBS, WE WILL DELIVER TO YOUR TASTE!!!"+" </p>"+
                        "</div>"+
                        "</body>\n" +
                        "</html>";
            } else if (path.contains("/services")) {
                response = "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>JAMTECH</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<a href = "+"/>HOME PAGE</a>\n"+
                        "<a href = "+"/about-us>ABOUT US</a>\n"+
                        "<a href = "+"/contact-us>CONTACT US</a>\n"+
                        "<a href = "+"/services>OUR SERVICES</a>\n"+
                        "<h1> OUR SERVICES</h1>\n" +
                        "<br>"+
                        "<img src="+"https://www.zimegats.com/wp-content/uploads/2018/03/IT-Managed-For-Your-Business.jpg>"+
                        "<p style=\"font-size:40px;font-weight:50; color: purple\">WE ARE OPEN FOR BUSINESS AND PARTNERSHIP:  "+"<ul style=\"font-size:25px;font-weight:50px; text-align: justify; color: purple\">"+"<li>"+"SOFTWARE/WEB ENGINEERING(FULL STACK: JAVA, PHP, JAVASCRIPT, REACTJS, SPRING BOOT)" +"</li>"+"<li>"+"DATA SCIENCE" +"</li>"+"<li>"+" COMPUTER NETWORKING" +"</li>"+"<li>"+"COMPUTER ENGINEERING " +"</li>"+"<li>"+"ARTIFICIAL INTELLIGENCE" +"<li>"+"IT SUPPORT" +"</li>"+"<li>"+"TRAININGS AND WORKSHOPS" +"</li>"+"<li>"+"FREELANCING" +"<li>"+"DIGITAL MARKETING " +"</ul>"+"</p>"+

                        "</body>\n" +
                        "</html>";


            } else if (path.contains("/contact-us")) {
                response = "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>JAMTECH</title>\n" +
                        "</head>\n" +
                        "<body>\n" +


                        "<a href = "+"/>HOME PAGE</a>\n"+
                        "<a href = "+"/about-us>ABOUT US</a>\n"+
                        "<a href = "+"/contact-us>CONTACT US</a>\n"+
                        "<a href = "+"/services>OUR SERVICES</a>\n"+
                        "<div>"+
                        "<h2> CONTACT US</h2>" +
                        "<p style=\"font-size:30px;font-weight:50; color: purple\">YOU CAN CONTACT US ON:  "+"<ul style=\"font-size:25px;font-weight:50px; color: purple\">"+"<li>"+"Email: adedinijames28@gmail.com" +"</li>"+"<li>"+"Phone: 08166370378" +"</li>"+"<li>"+"Twitter: @adedini_james" +"</li>"+ "</ul>"+"</p>"+

                        "<img src="+"https://thumbs.dreamstime.com/z/contact-us-word-writing-banner-55915379.jpg"+">"+
                        "</div>"+
                        "</body>\n" +
                        "</html>";

            } else{
                response =  "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>JAMTECH</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<h1> 404 Page Not Found</h1>\n" +
                        "<a href = "+"localhost"+":8085/about-us>About Us</a>\n"+
                        "<a href = "+"localhost"+":8085/>Home page</a>\n"+
                        "</body>\n" +
                        "</html>";
            }


            exchange.getResponseHeaders().set("Content-type","text/html");
            exchange.sendResponseHeaders(200, response.length());
            try(OutputStream outputStream = exchange.getResponseBody()){
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
