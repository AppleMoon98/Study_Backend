package com.mallppang.verification;

import com.mallppang.config.RedisConfig;
import com.solapi.sdk.SolapiClient;
import com.solapi.sdk.message.dto.response.MultipleDetailMessageSentResponse;
import com.solapi.sdk.message.model.Message;
import com.solapi.sdk.message.service.DefaultMessageService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    private final RedisConfig redisConfig;

	final DefaultMessageService messageService;
//	final VerificationService verificationService;

	public SmsController(RedisConfig redisConfig) {
		// 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
		this.messageService = SolapiClient.INSTANCE.createInstance("NCSGWPU7EPSVCJUO",
				"PVHLXHYUDCJQRCULACZN2UDKGIBIYHXS");
//		this.verificationService = new VerificationService();
		this.redisConfig = redisConfig;
	}

	// =============================================
	// 기존 Solapi 공식 예제 코드 모두 주석 처리 + 설명 주석 유지
	// =============================================

	/*
	 * // 메시지 조회 예제
	 * 
	 * @GetMapping("/get-message-list") public MessageListResponse getMessageList()
	 * { // 검색 조건이 있는 경우에 MessagListRequest를 초기화 하여 getMessageList 함수에 파라미터로 넣어서 검색할
	 * 수 있습니다. // 수신번호와 발신번호는 반드시 -,* 등의 특수문자를 제거한 01012345678 형식으로 입력해주셔야 합니다!
	 * MessageListRequest request = new MessageListRequest();
	 * 
	 * // 검색할 건 수, 값 미지정 시 20건 조회, 최대 500건 까지 설정 가능 // request.setLimit(1);
	 * 
	 * // 조회 후 다음 페이지로 넘어가려면 조회 당시 마지막의 messageId를 입력해주셔야 합니다! //
	 * request.setStartKey("메시지 ID");
	 * 
	 * // request.setTo("검색할 수신번호"); // request.setFrom("검색할 발신번호");
	 * 
	 * // 메시지 상태 검색, PENDING은 대기 건, SENDING은 발송 중, COMPLETE는 발송완료, FAILED는 발송 실패 건
	 * // request.setStatus(MessageStatusType.COMPLETE);
	 * 
	 * // 검색할 메시지 목록 // ArrayList<String> messageIds = new ArrayList<>(); //
	 * messageIds.add("검색할 메시지 ID"); // request.setMessageIds(messageIds);
	 * 
	 * // 메시지 유형 검색 // SMS: 단문 // LMS: 장문 // MMS: 사진문자 // ATA: 알림톡 //
	 * request.setType("조회 할 메시지 유형");
	 * 
	 * return this.messageService.getMessageList(request); }
	 * 
	 * // 단일 메시지 발송 예제
	 * 
	 * @PostMapping("/send-one") public MultipleDetailMessageSentResponse sendOne()
	 * { try { // 발신번호 및 수신번호는 반드시 01012345678 형태 Message message = new Message();
	 * message.setFrom("발신번호 입력"); message.setTo("수신번호 입력");
	 * 
	 * // 메시지 내용: 한글 45자, 영자 90자 이하 입력 시 자동 SMS 타입
	 * message.setText("한글 45자, 영자 90자 이하 입력되면 자동으로 SMS타입의 메시지가 추가됩니다.");
	 * 
	 * MultipleDetailMessageSentResponse response =
	 * this.messageService.send(message); System.out.println(response);
	 * 
	 * return response; } catch (Exception e) { e.fillInStackTrace(); } return null;
	 * }
	 * 
	 * // MMS 발송 예제
	 * 
	 * @PostMapping("/send-mms") public MultipleDetailMessageSentResponse
	 * sendMmsByResourcePath() throws IOException,
	 * SolapiMessageNotReceivedException, SolapiUnknownException,
	 * SolapiEmptyResponseException { // 파일 업로드 예제 ClassPathResource resource = new
	 * ClassPathResource("static/sample.jpg"); File file = resource.getFile();
	 * String imageId = this.messageService.uploadFile(file, StorageType.MMS);
	 * 
	 * Message message = new Message(); message.setFrom("발신번호 입력");
	 * message.setTo("수신번호 입력");
	 * message.setText("한글 45자, 영자 90자 이하 입력되면 자동 SMS 타입 추가");
	 * message.setImageId(imageId);
	 * 
	 * MultipleDetailMessageSentResponse response =
	 * this.messageService.send(message); System.out.println(response); return
	 * response; }
	 * 
	 * // 여러 메시지 발송 예제
	 * 
	 * @PostMapping("/send-many") public MultipleDetailMessageSentResponse
	 * sendMany() { ArrayList<Message> messageList = new ArrayList<>(); for (int i =
	 * 0; i < 3; i++) { Message message = new Message(); message.setFrom("발신번호 입력");
	 * message.setTo("수신번호 입력");
	 * message.setText("한글 45자, 영자 90자 이하 입력되면 자동 SMS 타입 추가" + i); }
	 * 
	 * try { MultipleDetailMessageSentResponse response =
	 * this.messageService.send(messageList); System.out.println(response); return
	 * response; } catch (SolapiMessageNotReceivedException exception) {
	 * System.out.println(exception.getFailedMessageList());
	 * System.out.println(exception.getMessage()); } catch (Exception exception) {
	 * System.out.println(exception.getMessage()); } return null; }
	 * 
	 * // 잔액 조회 예제
	 * 
	 * @GetMapping("/get-balance") public Balance getBalance() { return
	 * this.messageService.getBalance(); }
	 * 
	 * // 음성 메시지 발송 예제
	 * 
	 * @PostMapping("/send-voice-message") public MultipleDetailMessageSentResponse
	 * sendVoiceMessage() { try { VoiceOption voiceOption = new VoiceOption();
	 * Message message = new Message(); message.setFrom("등록한 발신번호 입력");
	 * message.setTo("보낼 휴대전화 번호 입력"); message.setText("음성 메시지에서 TTS로 나올 내용 입력");
	 * message.setVoiceOptions(voiceOption); return
	 * this.messageService.send(message); } catch (SolapiMessageNotReceivedException
	 * e) { System.out.println(e.getFailedMessageList());
	 * System.out.println(e.getMessage()); } catch (SolapiUnknownException |
	 * SolapiEmptyResponseException e) { System.out.println(e.getMessage()); }
	 * return null; }
	 */

	// =============================================
	// 회원가입 SMS 인증 전용 메서드
	// =============================================

//	// 인증번호 발송
//	@PostMapping("/send-code")
//	public ResponseEntity<String> sendVerificationCode(@RequestParam String phoneNumber)
//			throws SolapiMessageNotReceivedException, SolapiUnknownException, SolapiEmptyResponseException {
//		// 6자리 인증번호 생성 및 저장
//		String code = verificationService.generateAndSaveCode(phoneNumber);
//
//		// 메시지 전송
//		Message message = new Message();
//		message.setFrom("발신번호 입력"); // 실제 등록된 발신번호
//		message.setTo(phoneNumber);
//		message.setText("인증번호: " + code);
//
//		MultipleDetailMessageSentResponse response = messageService.send(message);
//
//		return ResponseEntity.ok("인증번호 발송 완료");
//	}
//
//	// 인증번호 확인
//	@PostMapping("/verify-code")
//	public ResponseEntity<Boolean> verifyCode(@RequestParam String phoneNumber, @RequestParam String code) {
//		boolean result = verificationService.verifyCode(phoneNumber, code);
//		return ResponseEntity.ok(result);
//	}

	@PostMapping("/send-one")
	public MultipleDetailMessageSentResponse sendOne(@RequestBody SmsDTO dto) {
		System.err.println("number : " + dto.getNumber() + " / telNum : " + dto.getTelNum());
		try {
			
			// 발신번호 및 수신번호는 반드시 01012345678 형태
			Message message = new Message();
			message.setFrom("01036750170");
			message.setTo(dto.getTelNum());

			// 메시지 내용: [몰빵] 인증번호 [100000~999999]를 입력해주세요. 사칭/전화사기에 주의하세요.
			message.setText("[몰빵] 인증번호 [" + dto.getNumber() + "]를 입력해주세요. 사칭/전화사기에 주의하세요.");
			MultipleDetailMessageSentResponse response = this.messageService.send(message);
			System.err.println("메세지 전송완료");
			return response;
		} catch (Exception e) {
			e.fillInStackTrace();
			System.err.println(e.getMessage());
		}
		return null;
	}

	// =============================================
	// 인증번호 관리 서비스
	// =============================================
//	@Service
//	class VerificationService {
//		private final Map<String, String> codeMap = new ConcurrentHashMap<>();
//
//		public String generateAndSaveCode(String phoneNumber) {
//			// 6자리 랜덤 인증번호 생성
//			String code = String.format("%06d", new Random().nextInt(999999));
//			codeMap.put(phoneNumber, code);
//			return code;
//		}
//
//		public boolean verifyCode(String phoneNumber, String code) {
//			String savedCode = codeMap.get(phoneNumber);
//			if (savedCode != null && savedCode.equals(code)) {
//				// 인증 완료 후 제거
//				codeMap.remove(phoneNumber);
//				return true;
//			}
//			return false;
//		}
//	}
}