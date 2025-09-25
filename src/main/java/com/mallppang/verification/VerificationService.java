//package com.mallppang.verification;
//
//import java.time.Duration;
//
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class VerificationService {
//
//    private final RedisTemplate<String, String> redisTemplate;
//    private final SolapiMessageService messageService;
//
//    public void sendCode(String telNum, String code) {
//        redisTemplate.opsForValue().set("auth:" + telNum, code, Duration.ofMinutes(5));
//        messageService.send(new Message() {{
//            setFrom("01012345678");
//            setTo(telNum);
//            setText("인증번호: " + code);
//        }});
//    }
//
//    public boolean verifyCode(String telNum, String inputCode) {
//        String savedCode = redisTemplate.opsForValue().get("auth:" + telNum);
//        return inputCode != null && inputCode.equals(savedCode);
//    }
//
//    public boolean isVerified(String telNum) {
//        return redisTemplate.hasKey("auth:" + telNum);
//    }
//
//    public void clearVerification(String telNum) {
//        redisTemplate.delete("auth:" + telNum);
//    }
//}