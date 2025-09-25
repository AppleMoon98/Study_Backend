//package com.mallppang.verification;
//
//import java.util.Map;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mallppang.member.MemberService;
//import com.mallppang.member.RegisterDTO;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/verification")
//@RequiredArgsConstructor
//public class VerificationController {
//
//    private final VerificationService verificationService;
//    private final MemberService memberService;
//
//    @PostMapping("/send-code")
//    public Map<String, Object> sendCode(@RequestBody Map<String,String> body){
//        String telNum = body.get("telNum");
//        String code = String.valueOf((int)(Math.random()*900000)+100000);
//        verificationService.sendCode(telNum, code);
//        return Map.of("ok", true);
//    }
//
//    @PostMapping("/verify-code")
//    public Map<String, Object> verifyCode(@RequestBody Map<String, String> body){
//        String telNum = body.get("telNum");
//        String code = body.get("code");
//        boolean ok = verificationService.verifyCode(telNum, code);
//        return Map.of("ok", ok);
//    }
//
//    @PostMapping("/register")
//    public Map<String, Object> register(@RequestBody RegisterDTO dto){
//        if(!verificationService.isVerified(dto.getTelNum())){
//            return Map.of("ok", false, "msg", "인증 필요");
//        }
//
//        memberService.register(dto, false);
//        verificationService.clearVerification(dto.getTelNum());
//        return Map.of("ok", true);
//    }
//}
