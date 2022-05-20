package com.ncc.mn.schedule;

import com.ncc.mn.repository.UserRepository;
import com.ncc.mn.utils.GenerateString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenerateString generateString;

//    @Scheduled(fixedDelay = 2000)
//    public void AddUserFixedDelay() {
//        LOGGER.info("add user");
//        User user = User.builder()
//                .userId(generateString.generate(48, 57, 4, "185106"))
//                .email(generateString.generate(48, 57, 7, "testmail"))
//                .encpyptPassword("test pass")
//                .firstName("test")
//                .lastName("test")
//                .emailVerificationStatus(false)
//                .build();
//        userRepository.save(user);
//    }

//    @Scheduled(cron = "0 */5 11,15 15 JAN MON")
//    public void testCron(){
//        System.out.println("test cron");
//    }

//    @Scheduled(cron = "0 */2 * * * *")
//    @CacheEvict(cacheNames = "allUserCache",allEntries = true)
//    public void clearCache(){
//        System.out.println("clear cache user");
//    }
}
