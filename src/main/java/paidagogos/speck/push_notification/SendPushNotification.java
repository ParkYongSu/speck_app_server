package paidagogos.speck.push_notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import paidagogos.util.Util;

import javax.sql.DataSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class SendPushNotification {

    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final JdbcTemplate jdbcTemplate;
    private Date current;
    private final Calendar calendar = Calendar.getInstance();

    @Autowired
    public SendPushNotification(FirebaseCloudMessageService firebaseCloudMessageService, DataSource dataSource) {
        this.firebaseCloudMessageService = firebaseCloudMessageService;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /** 인증 1시간 전에 List 갱신 후 알림 전송 **/
    @Scheduled(cron = "0 0 * * * *")
    public void sendBefore1Hour() {
        current = new Date();
        calendar.setTime(current);
        calendar.add(Calendar.HOUR, 1);
        String authDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        int timeNum = Util.getTimeNum(new SimpleDateFormat("HH:00").format(calendar.getTime()));
        String query = "select bookingdate.useremail, bookingdate.dateinfo, bookinginfo.timenum, bookingdate.attendvalue,fcm_token.token " +
                "from ((payment left join bookingdate on payment.bookinfo = bookingdate.bookinfo) left join bookinginfo on bookingdate.bookinfo = bookinginfo.id)" +
                " left join fcm_token on bookingdate.useremail = fcm_token.email where bookingdate.attendvalue = 0 and bookingdate.dateinfo = ? and bookinginfo.timenum = ?";
        String title = "테스트";
        String body = "테스트죵";
        sendNotification(query, authDay, timeNum, title, body);
    }
    /** 인증 10분 전에 List 갱신 후 알림 전송 **/
    @Scheduled(cron = "0 15 * * * *")
    public void sendBefore15Minutes() {
        current = new Date();
        calendar.setTime(current);
        calendar.add(Calendar.MINUTE, 10);
        String authDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        int timeNum = Util.getTimeNum(new SimpleDateFormat("HH:00").format(calendar.getTime()));
        String query = "select bookingdate.useremail, bookingdate.dateinfo, bookinginfo.timenum, bookingdate.attendvalue,fcm_token.token " +
                "from ((payment left join bookingdate on payment.bookinfo = bookingdate.bookinfo) left join bookinginfo on bookingdate.bookinfo = bookinginfo.id)" +
                " left join fcm_token on bookingdate.useremail = fcm_token.email where bookingdate.attendvalue = 0 and bookingdate.dateinfo = ? and bookinginfo.timenum = ?";
        String title = "테스트";
        String body = "테스트죵";
        sendNotification(query, authDay, timeNum, title, body);
    }

    public void sendNotification(String query, String today, int timeNum, String title, String body) {
        List<TodoAuthDate> todoAuthDates = jdbcTemplate.query(query, todoAuthDateRowMapper(), today, timeNum);
        for (TodoAuthDate todoAuthDate : todoAuthDates) {
            try {
                firebaseCloudMessageService.sendMessageTo(todoAuthDate.getDeviceToken(), title, body);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public RowMapper<TodoAuthDate> todoAuthDateRowMapper() {
        return (rs, rowNum) -> {
            TodoAuthDate todoAuthDate = new TodoAuthDate();
            todoAuthDate.setDateTime(rs.getString("dateinfo") + Util.getAuthTime(rs.getInt("timenum")));
            todoAuthDate.setDeviceToken(rs.getString("token"));
            return todoAuthDate;
        };
    }
}
