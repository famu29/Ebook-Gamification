package jp.ac.tokushima_u.is.ll.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.ac.tokushima_u.is.ll.entity.GamificationInfo;
import jp.ac.tokushima_u.is.ll.entity.Users;
import jp.ac.tokushima_u.is.ll.security.SecurityUserHolder;
import jp.ac.tokushima_u.is.ll.service.GamificationService;
import jp.ac.tokushima_u.is.ll.service.UserService;
//kns

@Controller
@RequestMapping("/gamification")
public class GamificationController {

	@Autowired
	private UserService userService;
	@Autowired
	private GamificationService GamificationService;

	@RequestMapping
	public String index(ModelMap model) throws ParseException {

		String YOBI_StartDay="2023-01-10 00:00:00";//予備実験開始日
		String HON_StartDay="2023-01-20 00:00:00";//本実験開始日
		Date today =new Date();//Date型で現在の日付・時刻を取得
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date Day_HON_StartDay=sdf.parse(HON_StartDay);//startdashdayをDay型に変換

		//使っているユーザを判別し、userに格納
		Users user = userService.getById(SecurityUserHolder.getCurrentUser().getId());
		model.addAttribute("user", user);

		//Bグループ（対照群）リストを作成
		List<String> GroupB=Arrays.asList("ff8081818599fa5d0185a698627f0011",
										  "ff8081818599fa5d01859eac577a000e",
										  "ff8081818599fa5d01859b55d431000c",
										  "ff8081818599fa5d01859ae61d2b000b",
										  "ff8081818599fa5d01859acc9e990009",
										  "ff8081818599fa5d01859aa01d150008",
										  "ff8081818599fa5d01859a6de2270002",
										  "ff8081818599fa5d01859a5090760001"
										  );

		if(GroupB.contains(user.getId())||today.before(Day_HON_StartDay)){
			System.out.println("Bグループまたは実験開始以前のため閲覧禁止");return "ban";}
		else {
		//TodayRankingにentityで定義した構造体の配列を格納
		GamificationInfo[] TodayRanking=this.GamificationService.getGamificationUserArray(YOBI_StartDay);

		//今日のポイント数でソート
		Arrays.sort(TodayRanking, new Comparator<GamificationInfo>() {
			public int compare(GamificationInfo a, GamificationInfo b) {
				return b.getTodayAllPoint()-a.getTodayAllPoint();
			}
		});

		//TodayRankingとArrayLengthを渡す
		model.addAttribute("TodayRanking",TodayRanking);
		int ArrayLength =TodayRanking.length;
		model.addAttribute("ArrayLength",ArrayLength);

		//トータルランキング配列も作成＆ソート
		GamificationInfo[] TotalRanking=this.GamificationService.getGamificationUserArray(YOBI_StartDay);
		//累計ポイント数でソート
		Arrays.sort(TotalRanking, new Comparator<GamificationInfo>() {
			public int compare(GamificationInfo a, GamificationInfo b) {
				return (b.getTotalAllPoint()-a.getTotalAllPoint());
			}
		});

		//学習ポイントからユーザレベルを設定
		for(int i=0;i<ArrayLength;i++) {
			int TotalPoint =TotalRanking[i].getTotalEbookPoint()
					+TotalRanking[i].getTotalHighLightPoint()
					+TotalRanking[i].getTotalMemoPoint()
					+TotalRanking[i].getTotalRedSheetPoint();
			if(TotalPoint<50){TotalRanking[i].setLevel(1);}
			else if(TotalPoint<100) {TotalRanking[i].setLevel(2);}
			else if(TotalPoint<150) {TotalRanking[i].setLevel(3);}
			else if(TotalPoint<200) {TotalRanking[i].setLevel(4);}
			else {TotalRanking[i].setLevel(5);
			}
		}


		/*バッジ付与編*/

		//追い上げバッジ獲得者にフラグをたてる＆リストに追加
		List<String> OiageList = new ArrayList<String>();
		for(int i=0;i<ArrayLength;i++) {
			int tmp1[]=TotalRanking[i].getEbookPointArray();
			int tmp2[]=TotalRanking[i].getHighLightPointArray();
			int tmp3[]=TotalRanking[i].getMemoPointArray();
			int tmp4[]=TotalRanking[i].getRedSheetPointArray();
			int Flag=0;
			for(int j=0;j<tmp1.length;j++) {
				if(tmp1[j]+tmp2[j]+tmp3[j]+tmp4[j]>=50) {
					TotalRanking[i].setOiage(true);
					Flag=1;
				}
			}
			if(Flag==1) {
				OiageList.add(TotalRanking[i].getNickName());
			}
		}
		model.addAttribute("OiageList", OiageList);

		//スタートダッシュバッジ獲得者にフラグを立てる＆リストに追加
		List<String> StartDashList = new ArrayList<String>();
		for(int i=0;i<ArrayLength;i++) {
			if(this.GamificationService.getStartDashUserId(HON_StartDay).equals(TotalRanking[i].getUserId())) {
				TotalRanking[i].setStartDash(true);
				StartDashList.add(TotalRanking[i].getNickName());
			}
		}
		model.addAttribute("StartDashList",StartDashList);

		//三日以上連続でポイント獲得者に勤勉バッジを付与
		//獲得者にフラグ＆リストに追加
		List<String> KinbenList = new ArrayList<String>();
		for(int i=0;i<ArrayLength;i++) {
			int judge=0;
			int []EbookArray=TotalRanking[i].getEbookPointArray();
			int []HighLightArray=TotalRanking[i].getHighLightPointArray();
			int []MemoArray=TotalRanking[i].getMemoPointArray();
			int []RedSheetArray=TotalRanking[i].getRedSheetPointArray();
			for(int j=0;j<EbookArray.length;j++) {
				if(EbookArray[j]+HighLightArray[j]+MemoArray[j]+RedSheetArray[j]>0) {judge++;}
				else {judge=0;}
				if(judge>=3) {
					TotalRanking[i].setKinben(true);
					KinbenList.add(TotalRanking[i].getNickName());
				}
			}
		}
		model.addAttribute("KinbenList",KinbenList);

		//ハイライトマスターバッジ獲得者にフラグ＆リストに追加
		int HighLightMaxPoint=0;
		List<String> HighLightMasterList = new ArrayList<String>();
		for(int i=0;i<ArrayLength;i++) {
			if(HighLightMaxPoint<TotalRanking[i].getTotalHighLightPoint()) {
				HighLightMaxPoint=TotalRanking[i].getTotalHighLightPoint();
			}
		}
		for(int i=0;i<ArrayLength;i++) {
			if(HighLightMaxPoint==TotalRanking[i].getTotalHighLightPoint()) {
				TotalRanking[i].setHighLightMaster(true);
				HighLightMasterList.add(TotalRanking[i].getNickName());
			}
		}
		model.addAttribute("HighLightMasterList",HighLightMasterList);

		//教科書マスターバッジ獲得者にフラグ＆リストに追加
		int EbookMaxPoint=0;
		List<String> EbookMasterList = new ArrayList<String>();
		for(int i=0;i<ArrayLength;i++) {
			if(EbookMaxPoint<TotalRanking[i].getTotalEbookPoint()) {
				EbookMaxPoint=TotalRanking[i].getTotalEbookPoint();
			}
		}
		for(int i=0;i<ArrayLength;i++) {
			if(EbookMaxPoint==TotalRanking[i].getTotalEbookPoint()) {
				TotalRanking[i].setEbookMaster(true);
				EbookMasterList.add(TotalRanking[i].getNickName());
			}
		}
		model.addAttribute("EbookMasterList",EbookMasterList);

		//メモマスターバッジ獲得者フラグ＆リストに追加
		int MemoMaxPoint=0;
		List<String> MemoMasterList = new ArrayList<String>();
		for(int i=0;i<ArrayLength;i++) {
			if(MemoMaxPoint<TotalRanking[i].getTotalMemoPoint()) {
				MemoMaxPoint=TotalRanking[i].getTotalMemoPoint();
			}
		}
		for(int i=0;i<ArrayLength;i++) {
			if(MemoMaxPoint==TotalRanking[i].getTotalMemoPoint()) {
				TotalRanking[i].setMemoMaster(true);
				MemoMasterList.add(TotalRanking[i].getNickName());
			}
		}
		model.addAttribute("MemoMasterList",MemoMasterList);

		//赤シートマスターバッジ獲得者フラグ＆リストに追加
		int RedSheetMaxPoint=0;
		List<String> RedSheetMasterList = new ArrayList<String>();
		for(int i=0;i<ArrayLength;i++) {
			if(RedSheetMaxPoint<TotalRanking[i].getTotalRedSheetPoint()) {
				RedSheetMaxPoint=TotalRanking[i].getTotalRedSheetPoint();
			}
		}
		for(int i=0;i<ArrayLength;i++) {
			if(RedSheetMaxPoint==TotalRanking[i].getTotalRedSheetPoint()) {
				TotalRanking[i].setRedSheetMaster(true);
				RedSheetMasterList.add(TotalRanking[i].getNickName());
			}
		}
		model.addAttribute("RedSheetMasterList",RedSheetMasterList);

		//本実験に使う教科書リストの作成
		List<String> EbookList = Arrays.asList("epubid_1b34896c926c422e9e78cd1d714488a8",
												"epubid_50ce8e91e51f4201aee68961d7727efa",
												"epubid_2bf2c73776c74ba7bcec6d64fb58a9e5",
												"epubid_a4435f9b23744dc598cd55570a9da6c7",
												"epubid_a650a061b79242169dadbfbe7d6d6dd6");
		//まずは一周バッジ獲得者にフラグ＆リストに追加
		List<String> IssyuuList = new ArrayList<String>();
		for(int i=0;i<ArrayLength;i++) {
			int count=1;//各教材の最後のページに一度でもアクセスしていなかったらcountが0になる（9ページでNEXT）
			for(int j=0;j<EbookList.size();j++) {
				count*=this.GamificationService.getMyEbookAchievement(TotalRanking[i].getUserId(), EbookList.get(j));
			}
			if(count>0) {
				TotalRanking[i].setIssyuu(true);
				IssyuuList.add(TotalRanking[i].getNickName());}
		}
		model.addAttribute("IssyuuList", IssyuuList);

		//TotalRankingを渡す
		model.addAttribute("TotalRanking",TotalRanking);
		//閲覧しているユーザの情報を渡す
		for(int i=0;i<ArrayLength;i++) {
			if(TotalRanking[i].getUserId().equals(user.getId())) {
				model.addAttribute("MyGamificationInfo", TotalRanking[i]);
				int ToNextLevel =50-(TotalRanking[i].getTotalAllPoint())%50;
				model.addAttribute("ToNextLevel", ToNextLevel);
			}
		}


		/*デバッグ
		System.out.println("---------Today---------");
		for(int i=0; i<TodayRanking.length;i++) {
			System.out.println("UserId:"+TodayRanking[i].getUserId());
			System.out.println("NickName:"+TodayRanking[i].getNickName());
			System.out.println("TodayEbookPoint:"+TodayRanking[i].getTodayEbookPoint());
			System.out.println("TotalEbookPoint:"+TodayRanking[i].getTotalEbookPoint());
			System.out.println("TodayHighLightPoint:"+TodayRanking[i].getTodayHighLightPoint());
			System.out.println("TotalHighLightPoint:"+TodayRanking[i].getTotalHighLightPoint());
			System.out.println();
		}
		System.out.println("---------Total------------");
		for(int i=0; i<TotalRanking.length;i++) {
			System.out.println("UserId:"+TotalRanking[i].getUserId());
			System.out.println("NickName:"+TotalRanking[i].getNickName());
			System.out.println("TodayEbookPoint:"+TotalRanking[i].getTodayEbookPoint());
			System.out.println("TotalEbookPoint:"+TotalRanking[i].getTotalEbookPoint());
			System.out.println("TodayHighLightPoint:"+TotalRanking[i].getTodayHighLightPoint());
			System.out.println("TotalHighLightPoint:"+TotalRanking[i].getTotalHighLightPoint());
			System.out.println("スタートダッシュ:"+TotalRanking[i].getStartDash());
			System.out.println("追い上げ:"+TotalRanking[i].getOiage());
			System.out.println("勤勉："+TotalRanking[i].getKinben());
			System.out.println("ebookマスター:"+TotalRanking[i].getEbookMaster());
			System.out.println("ハイライトマスター:"+TotalRanking[i].getHighLightMaster());
			System.out.println("まずは一周:"+TotalRanking[i].getIssyuu());
			int []EbookPointArray =TotalRanking[i].getEbookPointArray();
			int []HighLightPointArray =TotalRanking[i].getHighLightPointArray();
			for(int j=0;j<EbookPointArray.length;j++) {
				System.out.println("EbookPointArray["+j+"]:"+EbookPointArray[j]);
			}
			for(int j=0;j<EbookPointArray.length;j++) {
				System.out.println("HighLightArray["+j+"]:"+HighLightPointArray[j]);
			}
			System.out.println();
		}
		System.out.println("end");*/

		/*予備実験用データ収集プログラム
		GamificationInfo[] LogArray=this.GamificationService.getLog(YOBI_StartDay);

		System.out.println("--------------------------------Log------------------------------------");
		for(int i=0; i<LogArray.length;i++) {
			System.out.println("UserId:"+LogArray[i].getUserId());
			System.out.println("NickName:"+LogArray[i].getNickName());
			System.out.println("TotalAllPoint:"+LogArray[i].getTotalAllPoint());
			System.out.println("TotalEbookPoint:"+LogArray[i].getTotalEbookPoint());
			System.out.println("TotalHighLightPoint:"+LogArray[i].getTotalHighLightPoint());
			System.out.println("TotalMemoPoint:"+LogArray[i].getTotalMemoPoint());
			System.out.println("TotalRedSheetPoint:"+LogArray[i].getTotalRedSheetPoint());

			int []EbookPointLog =LogArray[i].getEbookPointArray();
			int []HighLightPointLog =LogArray[i].getHighLightPointArray();
			int []MemoPointLog =LogArray[i].getMemoPointArray();
			int []RedSheetPointLog =LogArray[i].getRedSheetPointArray();
//			for(int j=0;j<EbookPointLog.length;j++) {
//				System.out.println("EbookPointLog["+j+"]:"+EbookPointLog[j]);
//			}
//			for(int j=0;j<EbookPointLog.length;j++) {
//				System.out.println("HighLightLog["+j+"]:"+HighLightPointLog[j]);
//			}
//			for(int j=0;j<EbookPointLog.length;j++) {
//				System.out.println("MemoLog["+j+"]:"+MemoPointLog[j]);
//			}
//			for(int j=0;j<EbookPointLog.length;j++) {
//				System.out.println("RedSheetLog["+j+"]:"+RedSheetPointLog[j]);
//			}
			for(int j=0;j<EbookPointLog.length;j++) {
				int tmp=EbookPointLog[j] + HighLightPointLog[j] + MemoPointLog[j] + RedSheetPointLog[j];
				System.out.println("AllPointLog["+j+"]:"+ tmp);
			}
			System.out.println();
		}
*/

		/*本実験用データ収集プログラム
		 *
		GamificationInfo[] Log2Array=this.GamificationService.getGamificationUserArray(YOBI_StartDay);
		System.out.println("------------------------Log---------------------------------");
		for(int i=0; i<Log2Array.length;i++) {
			System.out.println("UserId:"+Log2Array[i].getUserId());
			System.out.println("NickName:"+Log2Array[i].getNickName());
			System.out.println("TotalEbookPoint:"+Log2Array[i].getTotalEbookPoint());
			System.out.println("TotalHighLightPoint:"+Log2Array[i].getTotalHighLightPoint());
			System.out.println("TotalMemoPoint:"+Log2Array[i].getTotalMemoPoint());
			System.out.println("スタートダッシュ:"+Log2Array[i].getStartDash());
			System.out.println("追い上げ:"+Log2Array[i].getOiage());
			System.out.println("勤勉："+Log2Array[i].getKinben());
			System.out.println("ebookマスター:"+Log2Array[i].getEbookMaster());
			System.out.println("ハイライトマスター:"+Log2Array[i].getHighLightMaster());
			System.out.println("まずは一周:"+Log2Array[i].getIssyuu());
			int []EbookLog =Log2Array[i].getEbookPointArray();
			int []HighLightLog =Log2Array[i].getHighLightPointArray();
			int []MemoLog =Log2Array[i].getMemoPointArray();
			int []RedSheetLog =Log2Array[i].getRedSheetPointArray();
			for(int j=0;j<EbookLog.length;j++) {
				System.out.println("EbookPointArray["+j+"]:"+EbookLog[j]);
			}
			for(int j=0;j<EbookLog.length;j++) {
				System.out.println("HighLightArray["+j+"]:"+HighLightLog[j]);
			}
			for(int j=0;j<MemoLog.length;j++) {
				System.out.println("MemoArray["+j+"]:"+MemoLog[j]);
			}
			for(int j=0;j<RedSheetLog.length;j++) {
				System.out.println("RedSheetArray["+j+"]:"+RedSheetLog[j]);
			}
			System.out.println();
		}*/
		return "gamification/index";
		}
	}
}

