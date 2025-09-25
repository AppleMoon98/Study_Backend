package com.mallppang.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mallppang.bakery.BakeryDTO;
import com.mallppang.bakery.BakeryService;
import com.mallppang.freeboard.FreeComment;
import com.mallppang.freeboard.FreeCommentService;

@SpringBootTest
class MallppangApplicationTests {
	@Autowired
	FreeCommentService commentService;
	@Autowired
	BakeryService bakeryService;

	@Test
	void contextLoads() {
//		List<FreeComment> a = commentService.getList(1L);
//		for(int i = 0; i < a.size(); i++)
//			System.out.println(a.get(i));

		BakeryDTO dto = BakeryDTO.builder().name("러브 베이크 하우스")
				.loadAddress("경기도 김포시 김포한강2로23번길 10, 라베니체마치에비뉴Ⅰ 1층 112호 (장기동)")
				.townAddress("경기도 김포시 장기동 2018-2번지 라베니체마치에비뉴Ⅰ 1층 112호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("스토리오브라망김포풍무점").loadAddress("경기도 김포시 풍무로146번길 18, 102호 (풍무동)")
				.townAddress("경기도 김포시 풍무동 948번지 102호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("19:30:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("고촌캐슬&파밀리에 파리바게뜨").loadAddress("경기도 김포시 고촌읍 수기로 115, 1층 101,102호")
				.townAddress("경기도 김포시 고촌읍 신곡리 880-4번지 1층 101,102호").openDate(java.sql.Time.valueOf("07:30:00"))
				.closeDate(java.sql.Time.valueOf("23:30:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("이상용베이커리휴").loadAddress("경기도 김포시 김포한강11로 143, 비호뉴팰리스Ⅱ 106호 (운양동)")
				.townAddress("경기도 김포시 운양동 1431-2번지 비호뉴팰리스Ⅱ 106호").openDate(null).closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("그린베리 쿠키즈").loadAddress("경기도 김포시 초당로61번길 26, 102호 (장기동)").townAddress("경기도 김포시 장기동 1958-1번지 102호")
				.openDate(java.sql.Time.valueOf("10:00:00")).closeDate(java.sql.Time.valueOf("17:00:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨김포점").loadAddress("경기도 김포시 풍무로146번길 26, 프라미스 1층 105호, 106호 (풍무동)")
				.townAddress("경기도 김포시 풍무동 952번지 프라미스 1층 105호, 106호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("동네식빵(우리동네식빵)").loadAddress("경기도 김포시 김포한강11로 310, 승문리앤포레 108호 (운양동)")
				.townAddress("경기도 김포시 운양동 1297-6번지 승문리앤포레 108호").openDate(java.sql.Time.valueOf("11:00:00"))
				.closeDate(java.sql.Time.valueOf("20:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("카페오늘").loadAddress("경기도 김포시 김포한강7로22번길 22, 근린생활시설동 지1층 B03호 (마산동, 한강 힐스테이트)")
				.townAddress("경기도 김포시 마산동 636-1번지 한강 힐스테이트 근린생활시설동 지1층 B03호")
				.openDate(java.sql.Time.valueOf("09:00:00")).closeDate(java.sql.Time.valueOf("19:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("우리동네식빵").loadAddress("경기도 김포시 감정로 6, 성운코아빌딩 112호 (감정동)")
				.townAddress("경기도 김포시 감정동 551-6번지 성운코아빌딩 112호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("20:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("캔아이 케이크").loadAddress("경기도 김포시 유현로 215, 308동 201호 (풍무동, 풍무 센트럴 푸르지오)")
				.townAddress("경기도 김포시 풍무동 936번지 풍무센트럴푸르지오 308동 201호").openDate(java.sql.Time.valueOf("11:00:00"))
				.closeDate(java.sql.Time.valueOf("19:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("더 바른 베이커리").loadAddress("경기도 김포시 김포한강2로24번길 78-79, 1층 102호 (장기동)")
				.townAddress("경기도 김포시 장기동 1935-3번지 1층 102호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("21:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨(김포시청점)").loadAddress("경기도 김포시 돌문로 49 (사우동)").townAddress("경기도 김포시 사우동 237번지")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("23:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게트 김포솔터마을점").loadAddress("경기도 김포시 김포한강8로 331, 상가2동 1층 101,102호 (마산동, 김포한강엘에이치솔터마을2단지)")
				.townAddress("경기도 김포시 마산동 615-1번지 김포한강엘에이치솔터마을2단지 상가2동 1층 101,102호")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("일빠네(il pane)").loadAddress("경기도 김포시 고촌읍 인향로24번길 66-22, 지1층 B02호 (디아이빌4단지)")
				.townAddress("경기도 김포시 고촌읍 신곡리 577-21번지 디아이빌4단지 지1층 B02호").openDate(java.sql.Time.valueOf("11:00:00"))
				.closeDate(java.sql.Time.valueOf("20:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르 (카페양곡점)").loadAddress("경기도 김포시 양촌읍 양곡2로80번길 70, 102~104호")
				.townAddress("경기도 김포시 양촌읍 양곡리 1294-3번지 102~104호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("판데오로").loadAddress("경기도 김포시 김포한강4로 125, 월드타워 1층 104호 (장기동)")
				.townAddress("경기도 김포시 장기동 1604번지 월드타워 1층 104호").openDate(java.sql.Time.valueOf("08:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("방긋시루").loadAddress("경기도 김포시 김포한강9로12번길 97-20, 102호 (구래동)")
				.townAddress("경기도 김포시 구래동 6889-14번지 102호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("20:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("약암리495카페").loadAddress("경기도 김포시 대곶면 약암로948번길 2-3, 1,2동")
				.townAddress("경기도 김포시 대곶면 약암리 495번지 1,2동").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("19:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("약암리495베이커리").loadAddress("경기도 김포시 대곶면 약암로948번길 3, 1층 일부호")
				.townAddress("경기도 김포시 대곶면 약암리 496-1번지 1층 일부호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("19:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("모담케이크").loadAddress("경기도 김포시 김포한강1로 240, 블루동 108호 (운양동)")
				.townAddress("경기도 김포시 운양동 1340-4번지 블루동 108호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("17:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("청수당").loadAddress("경기도 김포시 김포한강10로133번길 75, 1층 107~116, 119~121호 (구래동)")
				.townAddress("경기도 김포시 구래동 6871-16번지 1층 107~116, 119~121호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("소굴속").loadAddress("경기도 김포시 김포한강4로 521, 한강메디플라자 1층 110호 (구래동)")
				.townAddress("경기도 김포시 구래동 6880-5번지 한강메디플라자 1층 110호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("21:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("102베이커리").loadAddress("경기도 김포시 걸포2로 21, 1층 102호 (걸포동)")
				.townAddress("경기도 김포시 걸포동 335-1번지 1층 102호").openDate(java.sql.Time.valueOf("08:00:00"))
				.closeDate(java.sql.Time.valueOf("21:30:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("나무휴식").loadAddress("경기도 김포시 고촌읍 태리로 121, 6동 1,2층").townAddress("경기도 김포시 고촌읍 태리 219-1번지 6동 1,2층")
				.openDate(java.sql.Time.valueOf("10:30:00")).closeDate(java.sql.Time.valueOf("19:00:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨 김포걸포점").loadAddress("경기도 김포시 걸포로 34, 1층 104호 (걸포동)")
				.townAddress("경기도 김포시 걸포동 294-53번지 1층 104호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:40:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("화이트리에 구래점").loadAddress("경기도 김포시 김포한강8로 382, 고은메디타워 1층 112호 (구래동)")
				.townAddress("경기도 김포시 구래동 6885-2번지 고은메디타워 1층 112호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("20:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("달팽이의꿈카페").loadAddress("경기도 김포시 양촌읍 양곡3로1번길 94, 1층 일부호")
				.townAddress("경기도 김포시 양촌읍 양곡리 1308-12번지 1층 일부호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("18:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("홍종흔베이커리 김포장기점").loadAddress("경기도 김포시 김포한강1로 85, 1층 103호 (장기동)")
				.townAddress("경기도 김포시 장기동 1911-5번지 1층 103호").openDate(java.sql.Time.valueOf("08:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("스카치케이크").loadAddress("경기도 김포시 유현로 215, 309동 216호 (풍무동, 풍무 센트럴 푸르지오)")
				.townAddress("경기도 김포시 풍무동 936번지 풍무센트럴푸르지오 309동 216호").openDate(java.sql.Time.valueOf("11:00:00"))
				.closeDate(java.sql.Time.valueOf("20:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("소굴속베이킹클래스").loadAddress("경기도 김포시 김포한강4로 118, 109호 (장기동)").townAddress("경기도 김포시 장기동 1851번지 109호")
				.openDate(java.sql.Time.valueOf("11:00:00")).closeDate(java.sql.Time.valueOf("21:00:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("비리디언(VIRIDIAN DESSERT)").loadAddress("경기도 김포시 김포한강10로133번길 107, M213호 (구래동)")
				.townAddress("경기도 김포시 구래동 6871-12번지 M213호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("19:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨(김포월드점)").loadAddress("경기도 김포시 김포한강11로 133, 중앙프라자 104,105호 (운양동)")
				.townAddress("경기도 김포시 운양동 1427-1번지 중앙프라자 104,105호").openDate(null).closeDate(null).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("달보드레 베이커리").loadAddress("경기도 김포시 양도로30번길 11, 양도마을서해아파트 상가2동 104호 (풍무동)")
				.townAddress("경기도 김포시 풍무동 149번지 양도마을서해아파트 상가 2동 104호").openDate(java.sql.Time.valueOf("11:00:00"))
				.closeDate(java.sql.Time.valueOf("19:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("우리동네식빵&바게트").loadAddress("경기도 김포시 고촌읍 은행영사정로23번길 23, 지하1층 14 일부호 (고촌행정타운 한양수자인)")
				.townAddress("경기도 김포시 고촌읍 신곡리 1291번지 고촌행정타운 지하1층 14 일부호").openDate(java.sql.Time.valueOf("07:30:00"))
				.closeDate(java.sql.Time.valueOf("21:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("삼송빵집").loadAddress("경기도 김포시 김포한강4로 561, 101, 102호 (구래동)")
				.townAddress("경기도 김포시 구래동 6878-3번지 101, 102호").openDate(null).closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("당신을위한베이커리").loadAddress("경기도 김포시 고촌읍 신곡로29번길 9, 강변마을 동부센트레빌 1층 104호")
				.townAddress("경기도 김포시 고촌읍 신곡리 1110번지 강변마을동부센트레빌 1층 104호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("15:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("아앰브레드").loadAddress("경기도 김포시 통진읍 조강로 43, 2층").townAddress("경기도 김포시 통진읍 마송리 100-5번지 2층")
				.openDate(java.sql.Time.valueOf("06:00:00")).closeDate(java.sql.Time.valueOf("22:30:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("그리고케이크").loadAddress("경기도 김포시 김포한강11로140번길 92, 1층 102호 (장기동)")
				.townAddress("경기도 김포시 운양동 1408-12번지 1층 102호").openDate(java.sql.Time.valueOf("11:00:00"))
				.closeDate(java.sql.Time.valueOf("18:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨 고촌점").loadAddress("경기도 김포시 고촌읍 장차로 13, 1층 일부호")
				.townAddress("경기도 김포시 고촌읍 신곡리 538-5번지 1층 일부호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("아비엥또").loadAddress("경기도 김포시 사우중로73번길 39, 상가동 1층 104,107호 (북변동, 풍년마을동남아파트)")
				.townAddress("경기도 김포시 북변동 816 풍년마을동남아파트 104, 107호 상가동동").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("19:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("비앙그랑").loadAddress("경기도 김포시 풍무2로 2, 1층 일부호 (풍무동)").townAddress("경기도 김포시 풍무동 969번지 1층 일부호")
				.openDate(java.sql.Time.valueOf("09:00:00")).closeDate(java.sql.Time.valueOf("20:30:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("1991아빠빵집").loadAddress("경기도 김포시 봉화로167번길 35-29, 1층 일부호 (감정동)")
				.townAddress("경기도 김포시 감정동 673번지 1층 일부호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("21:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르 마산점").loadAddress("경기도 김포시 김포한강8로 173-59, 1층 113~115호 (마산동, e편한세상 한강신도시2차)")
				.townAddress("경기도 김포시 마산동 672-3번지 e편한세상 한강신도시2차 1층 113~115호")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("23:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨풍무꿈에그린점").loadAddress("경기도 김포시 풍무로68번길 44, 1층 101, 102호 (풍무동)")
				.townAddress("경기도 김포시 풍무동 788번지 1층 101, 102호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("팔도명물도너츠").loadAddress("경기도 김포시 양촌읍 양곡2로 50, 아름터프라자 1층 103호")
				.townAddress("경기도 김포시 양촌읍 양곡리 1305-4번지 아름터프라자 1층 103호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("21:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("아이뚜또(Hai tutto)").loadAddress("경기도 김포시 김포한강7로 93, 월드에비뉴주차빌딩 1층 128호 (구래동)")
				.townAddress("경기도 김포시 구래동 6880-7번지 월드에비뉴주차빌딩 1층 128호").openDate(java.sql.Time.valueOf("12:00:00"))
				.closeDate(java.sql.Time.valueOf("19:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("베이커리프랜즈(BakeryFriends)").loadAddress("경기도 김포시 김포대로 851, 제일메디칼센터 104호 (사우동)")
				.townAddress("경기도 김포시 사우동 931번지 제일메디칼센터 104호").openDate(null).closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("아이든(AYDIN)").loadAddress("경기도 김포시 김포한강4로 543, 지오프라자 121호 (구래동)")
				.townAddress("경기도 김포시 구래동 6879-5번지 지오프라자 121호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("20:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("쉐프부랑제").loadAddress("경기도 김포시 사우중로 82, 사우프라자 108,109호 (사우동)")
				.townAddress("경기도 김포시 사우동 875번지 사우프라자 108,109호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("21:20:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("카페경선비").loadAddress("경기도 김포시 초당로16번길 102-3, 조은타워투 1층 일부호 (장기동)")
				.townAddress("경기도 김포시 장기동 2054-3번지 조은타워투 1층").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("20:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("빵미미").loadAddress("경기도 김포시 초당로61번길 26, 102호 (장기동)").townAddress("경기도 김포시 장기동 1958-1번지 102호")
				.openDate(null).closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("고촌빵집").loadAddress("경기도 김포시 고촌읍 장차로 4, 1층 일부호").townAddress("경기도 김포시 고촌읍 신곡리 1072번지 1층 일부호")
				.openDate(null).closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("베이콜로지 빵학개론 샹그리나점").loadAddress("경기도 김포시 고촌읍 아라육로152번길 210-14, 지영빌딩 1층 일부호")
				.townAddress("경기도 김포시 고촌읍 전호리 640번지 지영빌딩 1층 일부호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("21:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨김포은여울마을점").loadAddress("경기도 김포시 김포한강8로 163, 1층 107호 (마산동, 스퀘어빌딩)")
				.townAddress("경기도 김포시 마산동 648-5번지 스퀘어빌딩 1층 107호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("102베이커리(운양점)").loadAddress("경기도 김포시 김포한강11로 288-19, 운양프라자 115호 (운양동)")
				.townAddress("경기도 김포시 운양동 1297-9번지 운양프라자 115호").openDate(null).closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("하늘콩(SKY BEAN)").loadAddress("경기도 김포시 김포한강1로97번길 10-8, 부용오피스 1동 1층 (장기동)")
				.townAddress("경기도 김포시 장기동 1900-17번지 부용오피스 1동 1층").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("21:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("라페브").loadAddress("경기도 김포시 김포한강10로 190, 121~125,225,227~235호 (구래동)")
				.townAddress("경기도 김포시 구래동 6878-7번지 121~125,225,227~235호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("21:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("빠앙빠앙 김포구래점").loadAddress("경기도 김포시 솔터로 22, 301동 192호 (구래동, 메트로타워 예미지)")
				.townAddress("경기도 김포시 구래동 6886-4번지 메트로타워예미지 301동 192호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("식빵앗간").loadAddress("경기도 김포시 김포한강8로 173-59, 상가동 1층 111호 (마산동, e편한세상 한강신도시2차)")
				.townAddress("경기도 김포시 마산동 672-3번지 e편한세상 한강신도시2차 상가동 1층 111호")
				.openDate(java.sql.Time.valueOf("08:30:00")).closeDate(java.sql.Time.valueOf("21:30:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르(대곶점)").loadAddress("경기도 김포시 대곶면 율생로 82, 102호").townAddress("경기도 김포시 대곶면 율생리 453-6번지 102호")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("23:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게트 고촌힐스테이트점").loadAddress("경기도 김포시 고촌읍 인향로24번길 56, 1층 전체호")
				.townAddress("경기도 김포시 고촌읍 신곡리 615-22번지 1층 전체호").openDate(java.sql.Time.valueOf("07:30:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("어바웃브레드").loadAddress("경기도 김포시 김포한강8로 386, 김포센트럴프라자2 1층 102호 (구래동)")
				.townAddress("경기도 김포시 구래동 6885-1번지 김포센트럴프라자2 1층 102호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("삼송빵집").loadAddress("경기도 김포시 김포한강4로 561, 101, 102호 (구래동)")
				.townAddress("경기도 김포시 구래동 6878-3번지 101, 102호").openDate(null).closeDate(null).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("비앙그랑").loadAddress("경기도 김포시 풍무2로 2, 2층 일부호 (풍무동)").townAddress("경기도 김포시 풍무동 969번지 2층 일부호")
				.openDate(java.sql.Time.valueOf("09:00:00")).closeDate(java.sql.Time.valueOf("20:30:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("빚은김포풍무점").loadAddress("경기도 김포시 양도로19번길 15, 시그니처프라자 1층 104호 (풍무동)")
				.townAddress("경기도 김포시 풍무동 920번지 시그니처프라자 1층 104호").openDate(null).closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("밀보리앤코").loadAddress("경기도 김포시 솔터로 22, 301동 2층 256호 (구래동, 메트로타워 예미지)")
				.townAddress("경기도 김포시 구래동 6886-4번지 메트로타워예미지 301동 2층 256호").openDate(java.sql.Time.valueOf("11:00:00"))
				.closeDate(java.sql.Time.valueOf("19:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르 김포향산점").loadAddress("경기도 김포시 고촌읍 상미1로 7, 근린생활시설나동 B110, B111, B112호 (힐스테이트 리버시티 2단지)")
				.townAddress("경기도 김포시 고촌읍 향산리 83-1번지 힐스테이트리버시티2단지 근린생활시설나동 B110, B111, B112호")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("23:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게트김포운양점").loadAddress("경기도 김포시 김포한강1로 227, 1동 1층 123,124호 (운양동, 광장프라자)")
				.townAddress("경기도 김포시 운양동 1299-1번지 광장프라자 1동 1층 123,124호").openDate(null).closeDate(null).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("빵터졌네 You").loadAddress("경기도 김포시 김포한강2로24번길 78-39, 1층 일부호 (장기동)")
				.townAddress("경기도 김포시 장기동 1935-1번지 1층 일부호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("18:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("쁘니파이").loadAddress("경기도 김포시 고촌읍 김포대로 343, 1층 101호")
				.townAddress("경기도 김포시 고촌읍 신곡리 567-8번지 1층 101호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("18:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("도레도레김포현대몰").loadAddress("경기도 김포시 고촌읍 아라육로152번길 100, 2층 314호 (현대프리미엄아울렛)")
				.townAddress("경기도 김포시 고촌읍 전호리 654번지 현대프리미엄아울렛 2층 314호").openDate(java.sql.Time.valueOf("10:30:00"))
				.closeDate(java.sql.Time.valueOf("21:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨김포구래점").loadAddress("경기도 김포시 김포한강9로 73, 1층 101,102호 (구래동, 센트럴프라자)")
				.townAddress("경기도 김포시 구래동 6884-8번지 센트럴프라자 1층 101,102호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("우리동네식빵").loadAddress("경기도 김포시 유현로 52, 1층 114호 (풍무동, 프라임빌복합상가)")
				.townAddress("경기도 김포시 풍무동 532-3번지 프라임빌복합상가 1층 114호").openDate(null).closeDate(null).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("산토리니의아침김포장기점").loadAddress("경기도 김포시 김포한강2로 113, 1층 103호 (장기동, 초당마을중흥에스-클래스리버티)")
				.townAddress("경기도 김포시 장기동 2058-1번지 초당마을중흥에스-클래스리버티 1층 103호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("21:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("고촌캐슬&파밀리에 파리바게뜨").loadAddress("경기도 김포시 고촌읍 수기로 115, 1층 101,102호")
				.townAddress("경기도 김포시 고촌읍 신곡리 880-4번지 1층 101,102호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("102베이커리").loadAddress("경기도 김포시 걸포2로 21, 1층 102호 (걸포동)")
				.townAddress("경기도 김포시 걸포동 335-1번지 1층 102호").openDate(java.sql.Time.valueOf("08:00:00"))
				.closeDate(java.sql.Time.valueOf("21:30:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("빠냐데로").loadAddress("경기도 김포시 양촌읍 모산로 17, 1층 일부호").townAddress("경기도 김포시 양촌읍 석모리 282-3번지 1층 일부호")
				.openDate(java.sql.Time.valueOf("08:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨 풍무역점").loadAddress("경기도 김포시 유현로238번길 8, 풍무역플라자 101,102,104호 (풍무동)")
				.townAddress("경기도 김포시 풍무동 1033번지 풍무역플라자 101,102,104호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르 김포마송").loadAddress("경기도 김포시 통진읍 김포대로 2231, 서진프라자 104,105호")
				.townAddress("경기도 김포시 통진읍 마송리 515-1번지 서진프라자 104,105호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("베이커리홍쉐프").loadAddress("경기도 김포시 통진읍 김포대로 2229, 111호").townAddress("경기도 김포시 통진읍 마송리 515-2번지 111호")
				.openDate(java.sql.Time.valueOf("09:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르").loadAddress("경기도 김포시 김포한강11로255번길 15, 지105호 (운양동, 김포한강 이랜드 타운힐스)")
				.townAddress("경기도 김포시 운양동 1304-1번지 김포한강이랜드타운힐스 G동 105호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파티피플(party people)").loadAddress("경기도 김포시 김포한강2로23번길 74, 라베니체3차 205호 (장기동)")
				.townAddress("경기도 김포시 장기동 2026-5번지 라베니체3차 205호").openDate(java.sql.Time.valueOf("11:00:00"))
				.closeDate(java.sql.Time.valueOf("19:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨 김포디원시티점").loadAddress("경기도 김포시 김포한강10로133번길 127, 디원시티 B119,B120호 (구래동)")
				.townAddress("경기도 김포시 구래동 6871-7번지 디원시티 B119,B120호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("슬로 김포").loadAddress("경기도 김포시 모담공원로 58, 1동 103호 (운양동)")
				.townAddress("경기도 김포시 운양동 1326-8번지 1동 103호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("20:50:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("호호케이크").loadAddress("경기도 김포시 김포한강1로 240, 라비드퐁네프 블루동 1층 134호 (운양동)")
				.townAddress("경기도 김포시 운양동 1340-4번지 라비드퐁네프 블루동 1층 134호").openDate(null).closeDate(null).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("로마니나").loadAddress("경기도 김포시 걸포2로 9, 1층 전체호 (걸포동)").townAddress("경기도 김포시 걸포동 1563-2번지 1층 전체호")
				.openDate(java.sql.Time.valueOf("07:30:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("베이커리결").loadAddress("경기도 김포시 걸포2로 45, A동 1층 102, 103호 (걸포동)")
				.townAddress("경기도 김포시 걸포동 262-1번지 A동 1층 102, 103호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("몽블랑제(주) 김포점2").loadAddress("경기도 김포시 중봉로 14, 지하1층 일부호 (감정동)")
				.townAddress("경기도 김포시 감정동 692번지 지하1층 일부호").openDate(null).closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("카페큐").loadAddress("경기도 김포시 월곶면 곰바위로17번길 9, 1층 전체호").townAddress("경기도 김포시 월곶면 군하리 398-4번지 1층 전체호")
				.openDate(java.sql.Time.valueOf("11:00:00")).closeDate(java.sql.Time.valueOf("19:00:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨 김포청송마을").loadAddress("경기도 김포시 청송로 26 (장기동,청송현대 쇼핑센타 1층 101호)")
				.townAddress("경기도 김포시 장기동 1324번지 청송현대 쇼핑센타 1층 101호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("빵곳간").loadAddress("경기도 김포시 사우중로 92, 1층 103호 (사우동)").townAddress("경기도 김포시 사우동 872번지 1층 103호")
				.openDate(java.sql.Time.valueOf("08:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("블러썸").loadAddress("경기도 김포시 김포한강2로 41, 지하3층 (장기동, e편한세상 캐널시티 롯데마트내)")
				.townAddress("경기도 김포시 장기동 2031번지 e편한세상 캐널시티 롯데마트내 지하3층").openDate(java.sql.Time.valueOf("06:00:00"))
				.closeDate(java.sql.Time.valueOf("18:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨김포초당마을").loadAddress("경기도 김포시 김포한강2로 103 (장기동,우남아파트상가 528동 103,104,111호)")
				.townAddress("경기도 김포시 장기동 2065-1번지 우남아파트상가 528동 103,104,111호")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게트 김포자이점").loadAddress("경기도 김포시 태장로 845, B106,B107호 (장기동, 한강센트럴자이1단지근린생활시설-3)")
				.townAddress("경기도 김포시 장기동 2193번지 한강센트럴자이1단지근린생활시설-3 B106,B107호")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("Bread77(브레드77)").loadAddress("경기도 김포시 김포대로 703, 인선빌딩 1층 101호 (풍무동)")
				.townAddress("경기도 김포시 풍무동 1006번지 인선빌딩 1층 101호").openDate(java.sql.Time.valueOf("08:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("식빵이다").loadAddress("경기도 김포시 김포한강2로 362, 1층 117호 (장기동, 청송마을중흥에스클래스 상가동)")
				.townAddress("경기도 김포시 장기동 1866-1번지 청송마을중흥에스클래스 상가동 1층 117호").openDate(java.sql.Time.valueOf("10:30:00"))
				.closeDate(java.sql.Time.valueOf("20:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("빚은 김포구래점").loadAddress("경기도 김포시 김포한강8로 382, 고은메디타워 1층 108호 (구래동)")
				.townAddress("경기도 김포시 구래동 6885-2번지 고은메디타워 1층 108호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("21:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("베이크포유 (Bake4u)").loadAddress("경기도 김포시 김포한강11로 37, 1동 109호 (운양동, 김포한강신도시 2차 KCC 스위첸)")
				.townAddress("경기도 김포시 운양동 1331-1번지 김포한강신도시2차KCC스위첸 1동 109호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("18:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("빠앙빠앙 김포사우점").loadAddress("경기 김포시 솔터로 22").townAddress("경기 김포시 구래동 6886-4")
				.openDate(java.sql.Time.valueOf("09:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨(김포시청점)").loadAddress("경기도 김포시 돌문로 49 (사우동)").townAddress("경기도 김포시 사우동 237번지")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르 감정점").loadAddress("경기도 김포시 봉화로181번길 36, 1층 105, 111, 112호 (감정동)")
				.townAddress("경기도 김포시 감정동 659번지 1층 105, 111, 112호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨(김포월드점)").loadAddress("경기도 김포시 김포한강11로 133, 1층 104,105호 (장기동, 중앙프라자)")
				.townAddress("경기도 김포시 운양동 1427-1번지 중앙프라자 1층 104,105호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨김포점").loadAddress("경기도 김포시 풍무로146번길 26, 프라미스 1층 105호, 106호 (풍무동)")
				.townAddress("경기도 김포시 풍무동 952번지 프라미스 1층 105호, 106호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨 김포사우점").loadAddress("경기도 김포시 사우중로 82, 사우프라자 1층 103, 104호 (사우동)")
				.townAddress("경기도 김포시 사우동 875번지 사우프라자 1층 103, 104호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("블랑슈베이커리").loadAddress("경기도 김포시 고촌읍 인향로24번길 16, 1층").townAddress("경기도 김포시 고촌읍 신곡리 591-8번지 1층")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("블랑제리115").loadAddress("경기도 김포시 김포한강6로 115, 1층 (장기동)").townAddress("경기도 김포시 장기동 115-11번지 1층")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("21:00:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("강쉐프제빵소").loadAddress("경기도 김포시 김포한강8로162번길 10, 104, 105호 (마산동)")
				.townAddress("경기도 김포시 마산동 650-7번지 104, 105호").openDate(java.sql.Time.valueOf("08:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르풍무점").loadAddress("경기도 김포시 풍무로 62, 한화프라자 1층 101~103호 (풍무동)")
				.townAddress("경기도 김포시 풍무동 807번지 한화프라자 1층 101~103호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("프레첼제과점").loadAddress("경기도 김포시 통진읍 조강로 71-1, 1층").townAddress("경기도 김포시 통진읍 마송리 109-15번지 1층")
				.openDate(java.sql.Time.valueOf("11:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르 김포청송점").loadAddress("경기도 김포시 김포한강2로 361, 107,108호 (장기동, 호반베르디움 더퍼스트)")
				.townAddress("경기도 김포시 장기동 1865-4번지 호반베르디움 더퍼스트 107,108호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("천안옛날호두과자").loadAddress("경기도 김포시 대곶면 대명항로 273").townAddress("경기도 김포시 대곶면 율생리 351-54번지")
				.openDate(java.sql.Time.valueOf("09:00:00")).closeDate(java.sql.Time.valueOf("20:00:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게트장기신도시점").loadAddress("경기도 김포시 김포한강4로 131 (장기동,정현메디피아 106호)")
				.townAddress("경기도 김포시 장기동 1602번지 정현메디피아 106호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("프레첼제과점").loadAddress("경기도 김포시 하성면 태산로13번길 지하 66").townAddress("경기도 김포시 하성면 마곡리 589-1번지")
				.openDate(null).closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("떡담").loadAddress("경기도 김포시 풍무로 121, 1층 (풍무동)").townAddress("경기도 김포시 풍무동 115-11번지 1층")
				.openDate(null).closeDate(null).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨김포솔터마을").loadAddress("경기도 김포시 김포한강8로 331, 상가2동 1층 101,102호 (마산동, 김포한강엘에이치솔터마을2단지)")
				.townAddress("경기도 김포시 마산동 615-1번지 김포한강엘에이치솔터마을2단지 상가2동 1층 101,102호")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르 김포구래점").loadAddress("경기도 김포시 김포한강9로 80 (구래동, 다온프라자 101, 102호)")
				.townAddress("경기도 김포시 구래동 6883-8번지 다온프라자 101, 102호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르한강신도시점").loadAddress("경기도 김포시 김포한강2로 222, 110호 (장기동,외1필지 대영메디칼아카데미)")
				.townAddress("경기도 김포시 장기동 1886-6번지 외1필지 대영메디칼아카데미 110호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("샤론").loadAddress("경기도 김포시 하성면 애기봉로 860-1, 1층").townAddress("경기도 김포시 하성면 마곡리 615-1번지 1층")
				.openDate(java.sql.Time.valueOf("10:00:00")).closeDate(java.sql.Time.valueOf("20:00:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("브레드홀릭").loadAddress("경기도 김포시 김포한강2로104번길 4, 1층 101호 (장기동)")
				.townAddress("경기도 김포시 장기동 2039-10번지 경서프라자 1층 101호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨 김포한강노블레스점").loadAddress("경기도 김포시 김포한강2로 232, 1층 101호 일부, 102호 (장기동, 한강노블레스)")
				.townAddress("경기도 김포시 장기동 1886-5번지 한강노블레스 1층 101호 일부, 102호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("이상용베이커리휴").loadAddress("경기도 김포시 전원로 32, 전원마을월드1단지아파트 상가동 1층 101호 (운양동)")
				.townAddress("경기도 김포시 운양동 1437번지 전원마을월드1단지아파트 상가동 1층 101호").openDate(java.sql.Time.valueOf("08:30:00"))
				.closeDate(java.sql.Time.valueOf("10:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("천안옛날호두과자통진점").loadAddress("경기도 김포시 통진읍 김포대로 1985, 1층").townAddress("경기도 김포시 통진읍 도사리 843-15번지 1층")
				.openDate(java.sql.Time.valueOf("09:00:00")).closeDate(java.sql.Time.valueOf("20:00:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("우리동네식빵&바게트").loadAddress("경기도 김포시 고촌읍 은행영사정로23번길 23, 1층 14호 (고촌행정타운한양수자인)")
				.townAddress("경기도 김포시 고촌읍 신곡리 1291번지 고촌행정타운한양수자인 1층 14호").openDate(java.sql.Time.valueOf("07:30:00"))
				.closeDate(java.sql.Time.valueOf("21:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("블랑제리115 고촌점").loadAddress("경기도 김포시 고촌읍 장곡로3번길 100, 1층 103, 104호")
				.townAddress("경기도 김포시 고촌읍 태리 296-4번지 1층 103, 104호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("19:30:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("우리동네식빵&베이글").loadAddress("경기도 김포시 봉화로 180, 1층 일부호 (북변동)").townAddress("경기도 김포시 북변동 761번지 1층 일부호")
				.openDate(java.sql.Time.valueOf("09:30:00")).closeDate(java.sql.Time.valueOf("09:30:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("동동케이크 김포풍무점").loadAddress("경기도 김포시 풍무로 168, 넘버원리치안 2층 209호 (풍무동)")
				.townAddress("경기도 김포시 풍무동 897번지 넘버원리치안 2층 209호").openDate(java.sql.Time.valueOf("11:00:00"))
				.closeDate(java.sql.Time.valueOf("18:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("밀퐁 (milfon)").loadAddress("경기도 김포시 솔터로 22, 301동 2층 251호 (구래동, 메트로타워 예미지)")
				.townAddress("경기도 김포시 구래동 6886-4번지 메트로타워예미지 301동 2층 251호").openDate(java.sql.Time.valueOf("12:00:00"))
				.closeDate(java.sql.Time.valueOf("19:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨 김포걸포점").loadAddress("경기도 김포시 걸포로 34, 1층 104호 (걸포동)")
				.townAddress("경기도 김포시 걸포동 294-53번지 1층 104호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("유피케이크").loadAddress("경기도 김포시 김포한강4로212번길 22, 102호 (장기동)")
				.townAddress("경기도 김포시 장기동 1870-30번지 102호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("00:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("우리동네식빵").loadAddress("경기도 김포시 태장로 808, 송호프라자 109호 (장기동)")
				.townAddress("경기도 김포시 장기동 2079-3번지 송호프라자 109호").openDate(java.sql.Time.valueOf("11:00:00"))
				.closeDate(java.sql.Time.valueOf("16:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("베이커리 결").loadAddress("경기도 김포시 김포한강11로 288-5, 한강베네치아 115,116호 (운양동)")
				.townAddress("경기도 김포시 운양동 1297-1번지 한강베네치아 115,116호").openDate(java.sql.Time.valueOf("09:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("라베또디저트").loadAddress("경기도 김포시 고촌읍 은행영사정로23번길 36, 101호")
				.townAddress("경기도 김포시 고촌읍 신곡리 1123번지 101호").openDate(java.sql.Time.valueOf("19:00:00"))
				.closeDate(java.sql.Time.valueOf("18:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨걸포메트로자이점").loadAddress("경기도 김포시 걸포2로 83, 상가동 110, 111호 (걸포동, 한강메트로자이1단지)")
				.townAddress("경기도 김포시 걸포동 1595번지 한강메트로자이1단지 상가동 110, 111호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("폴브라운").loadAddress("경기도 김포시 김포한강11로438번길 123, 1226동 110호 (운양동, 하늘빛마을 한신더휴테라스)")
				.townAddress("경기도 김포시 운양동 1288-1번지 하늘빛마을한신더휴테라스 1226동 110호").openDate(java.sql.Time.valueOf("08:30:00"))
				.closeDate(java.sql.Time.valueOf("16:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("좋은아침페스츄리").loadAddress("경기도 김포시 김포한강8로 396, 지오타워 106~107호 (구래동)")
				.townAddress("경기도 김포시 구래동 6884-7번지 지오타워 106~107호").openDate(java.sql.Time.valueOf("08:00:00"))
				.closeDate(java.sql.Time.valueOf("23:30:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("프레첼제과점").loadAddress("경기도 김포시 대곶면 율생로 95, 1층").townAddress("경기도 김포시 대곶면 율생리 534-3번지 1층")
				.openDate(null).closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("샹제르망 베이커리 커피").loadAddress("경기도 김포시 돌문로 114 (사우동)").townAddress("경기도 김포시 사우동 186-13번지")
				.openDate(java.sql.Time.valueOf("06:30:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("스토리오브라망김포풍무점").loadAddress("경기도 김포시 풍무로146번길 18, 102호 (풍무동)")
				.townAddress("경기도 김포시 풍무동 948번지 102호").openDate(null).closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르 (카페양곡점)").loadAddress("경기도 김포시 양촌읍 양곡2로80번길 70, 102~104호")
				.townAddress("경기도 김포시 양촌읍 양곡리 1294-3번지 102~104호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨 김포예가점").loadAddress("경기도 김포시 김포한강2로 11, 상가2동 106,107호 (장기동, 수정마을 쌍용예가아파트)")
				.townAddress("경기도 김포시 장기동 2004-4 수정마을 쌍용예가아파트").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("똣똣빵집").loadAddress("경기도 김포시 김포대로 709, 퍼스트블루 1층 111호 (풍무동)")
				.townAddress("경기도 김포시 풍무동 1004번지 퍼스트블루 1층 111호").openDate(java.sql.Time.valueOf("10:00:00"))
				.closeDate(java.sql.Time.valueOf("20:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("뚜레쥬르(양곡점)").loadAddress("경기도 김포시 양촌읍 양곡1로 65, 104,105호")
				.townAddress("경기도 김포시 양촌읍 양곡리 443번지 104,105호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("빠냐데로").loadAddress("경기도 김포시 김포한강2로23번길 14, 103호 (장기동, 강변프라자)")
				.townAddress("경기도 김포시 장기동 2019-1번지 강변프라자 103호").openDate(java.sql.Time.valueOf("08:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨통진점").loadAddress("경기도 김포시 통진읍 조강로 84").townAddress("경기도 김포시 통진읍 서암리 761-3번지")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨").loadAddress("경기도 김포시 풍무로 25 (풍무동)").townAddress("경기도 김포시 풍무동 598-4번지")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게트 김포신감정점").loadAddress("경기도 김포시 중봉1로 11, 1층 (감정동)").townAddress("경기도 김포시 감정동 528-1번지 1층")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("23:00:00")).parking(true)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("파리바게뜨").loadAddress("경기도 김포시 양촌읍 양곡2로30번길 7-23, 1층").townAddress("경기도 김포시 양촌읍 양곡리 1306-2번지 1층")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("22:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder()

				.name("쉘브론베이커리").loadAddress("경기도 김포시 통진읍 조강로 50").townAddress("경기도 김포시 통진읍 서암리 717-6번지").openDate(null)
				.closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder().name("hoho브레드").loadAddress("경기도 김포시 김포한강9로76번길 87, 1층 103호 (구래동, 골든타임)")
				.townAddress("경기도 김포시 구래동 6883-1번지 골든타임 1층 103호").openDate(java.sql.Time.valueOf("08:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder().name("던킨도너츠 김포장기점").loadAddress("경기도 김포시 김포한강1로51번길 20, 111호 (장기동,산타마리아 1층)")
				.townAddress("경기도 김포시 장기동 1618번지 산타마리아 1층 111호").openDate(java.sql.Time.valueOf("08:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder().name("던킨 도너츠 김포양촌점").loadAddress("경기도 김포시 양촌읍 황금로 117, 다동 1층 105호 (이젠몰)")
				.townAddress("경기도 김포시 양촌읍 학운리 2979번지 이젠몰 다동 1층 105호").openDate(java.sql.Time.valueOf("08:00:00"))
				.closeDate(java.sql.Time.valueOf("23:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder().name("플리머스9-3").loadAddress("경기도 김포시 통진읍 애기봉로698번길 18, 1동 1층")
				.townAddress("경기도 김포시 통진읍 귀전리 9-3번지 1동 1층").openDate(null).closeDate(null).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder().name("파리바게뜨 김포향산점").loadAddress("경기도 김포시 고촌읍 상미1로 4, H스퀘어 102,103호")
				.townAddress("경기도 김포시 고촌읍 향산리 62번지 H스퀘어 102,103호").openDate(java.sql.Time.valueOf("07:00:00"))
				.closeDate(java.sql.Time.valueOf("22:00:00")).parking(false).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder().name("뚜레쥬르 고촌캐슬파밀리에점")
				.loadAddress("경기도 김포시 고촌읍 태리로 236, 401동 109, 110호 (캐슬앤파밀리에시티 1단지)")
				.townAddress("경기도 김포시 고촌읍 신곡리 923-2번지 캐슬앤파밀리에시티1단지 401동 110호 109, 110호")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("23:00:00")).parking(false)
				.build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder().name("쉐프부랑제").loadAddress("경기도 김포시 모담공원로 155, 1층전체, 2층일부 (운양동)")
				.townAddress("경기도 김포시 운양동 1317-7번지 1층전체, 2층일부").openDate(java.sql.Time.valueOf("10:30:00"))
				.closeDate(java.sql.Time.valueOf("21:30:00")).parking(true).build();
		bakeryService.register(dto);

		dto = BakeryDTO.builder().name("파리바게뜨김포호수마을점")
				.loadAddress("경기도 김포시 김포한강4로420번길 164, 상가동 1층 101,102호 (구래동, 호수마을자연앤이편한세상)")
				.townAddress("경기도 김포시 구래동 6874-6번지 호수마을자연앤이편한세상 상가동 1층 101,102호")
				.openDate(java.sql.Time.valueOf("07:00:00")).closeDate(java.sql.Time.valueOf("23:00:00")).parking(false)
				.build();
		bakeryService.register(dto);
	}
}
