package jp.ac.tokushima_u.is.ll.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ac.tokushima_u.is.ll.dao.GamificationDao;
import jp.ac.tokushima_u.is.ll.entity.GamificationInfo;


//kns
@Service
@Transactional
public class GamificationService {
	@Autowired
	private GamificationDao gamificationdao;
	private int DAYS =7;

	//ユーザIDからそのユーザのニックネームを取得する
	public String getNickNameFromUserId(String userid){
		String result = gamificationdao.getNickNameFromUserId(userid);
		return result;
	}

	//実験開始日以降に作られたユーザのエンティティを作成し、配列化
	public GamificationInfo[] getGamificationUserArray(String StartDay){

		//実験開始日からのユーザIDのリストを取得
		List<String> UserList=gamificationdao.loadUserList(StartDay);
		//!!!!!!!!!!!!!!!!!!!本実験終了日を記載!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		String strDate = "2023/01/26";
		//ユーザIDにポイント情報＆ニックネームを付与してGamificationInfo型にし、配列化
		GamificationInfo[] result = new GamificationInfo[UserList.size()];
		for(int i=0;i<result.length;i++) {
			String userid=UserList.get(i);//ユーザID
			//コンストラクタを使ってGamificationInfo型に格納
			result[i]= new GamificationInfo(userid,//ユーザID
					this.getNickNameFromUserId(userid),//ニックネーム
					this.getEbookPointArray(userid,strDate),//ebookポイント配列
					this.getHighLightPointArray(userid,strDate),
					this.getMemoPointArray(userid,strDate),
					this.getRedSheetPointArray(userid,strDate));//highlightポイント配列
		}
		return result;
	}

	//ユーザIDから、一週間前までのポイントを格納するebookPointArray([1,2,3,4,5,6,7])を作成
	public int[] getEbookPointArray(String userid,String strDate) {
		int []result=new int[DAYS];
		for(int i=0;i<DAYS;i++) {
			Date date =new Date();//Date型で現在の日付・時刻を取得
			//本実験ログ収集用
	    	//SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
	    	//Date date = sdFormat.parse(strDate);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);//カレンダー型に変換
			calendar.add(Calendar.DATE,-i);//i日戻す
			Date day = calendar.getTime();//処理後の日にち
			String Day = new SimpleDateFormat("yyyy-MM-dd").format(day);//SQLでつかえる形に変換
			result[i]=gamificationdao.getMyDayEbookPoint(userid,Day);
		}
		return result;
	}

	//ユーザIDから、一週間前までのポイントを格納するhighlightArray([1,2,3,4,5,6,7])を作成
	public int[] getHighLightPointArray(String userid,String strDate) {
		int []result=new int[DAYS];
		for(int i=0;i<DAYS;i++) {
			Date date =new Date();//Date型で現在の日付・時刻を取得
			//本実験ログ収集用
	    	//SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
	    	//Date date = sdFormat.parse(strDate);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);//カレンダー型に変換
			calendar.add(Calendar.DATE,-i);//i日戻す
			Date day = calendar.getTime();//処理後の日にち
			String Day = new SimpleDateFormat("yyyy-MM-dd").format(day);//SQLでつかえる形に変換
			result[i]=gamificationdao.getMyDayHighLightPoint(userid,Day);
			//System.out.println("Day:"+Day);
			//System.out.println("result["+i+"]:"+result[i]);
		}
		return result;
	}

	//ユーザIDから、一週間前までのポイントを格納するmemoArray([1,2,3,4,5,6,7])を作成
	public int[] getMemoPointArray(String userid,String strDate) {
		int []result=new int[DAYS];
		for(int i=0;i<DAYS;i++) {
			Date date =new Date();//Date型で現在の日付・時刻を取得
			//本実験ログ収集用
	    	//SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
	    	//Date date = sdFormat.parse(strDate);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);//カレンダー型に変換
			calendar.add(Calendar.DATE,-i);//i日戻す
			Date day = calendar.getTime();//処理後の日にち
			String Day = new SimpleDateFormat("yyyy-MM-dd").format(day);//SQLでつかえる形に変換
			result[i]=gamificationdao.getMyDayMemoPoint(userid,Day);
		}
		return result;
	}

	//ユーザIDから、一週間前までのポイントを格納するredsheetArray([1,2,3,4,5,6,7])を作成
	public int[] getRedSheetPointArray(String userid,String strDate) {
		int []result=new int[DAYS];
		for(int i=0;i<DAYS;i++) {
			Date date =new Date();//Date型で現在の日付・時刻を取得
			//本実験ログ収集用
	    	//SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
	    	//Date date = sdFormat.parse(strDate);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);//カレンダー型に変換
			calendar.add(Calendar.DATE,-i);//i日戻す
			Date day = calendar.getTime();//処理後の日にち
			String Day = new SimpleDateFormat("yyyy-MM-dd").format(day);//SQLでつかえる形に変換
			result[i]=gamificationdao.getMyDayRedSheetPoint(userid,Day);
		}
		return result;
	}

	//スタートダッシュバッジ獲得者を取得
	public String getStartDashUserId(String startdashday) {
		String result =this.gamificationdao.getStartDashUser(startdashday);
		return result;
	}

	//ユーザIDと教科書IDから、最後のページに到達している回数を取得
	public int getMyEbookAchievement(String userid,String ebookid) {
		int result = this.gamificationdao.getMyEbookAchievement(userid, ebookid);
		return result;
	}


/*予備実験ログ収集用*/

//実験開始日以降に作られたユーザのエンティティを作成し、配列化
public GamificationInfo[] getLog(String StartDay) throws ParseException{

	//実験開始日からのユーザIDのリストを取得
	List<String> UserList=gamificationdao.loadUserList(StartDay);
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!予備実験終了日を記載!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	String strDate = "2023/01/17";
	//ユーザIDにポイント情報＆ニックネームを付与してGamificationInfo型にし、配列化
	GamificationInfo[] result = new GamificationInfo[UserList.size()];
	for(int i=0;i<result.length;i++) {
		String userid=UserList.get(i);//ユーザID
		//コンストラクタを使ってGamificationInfo型に格納
		result[i]= new GamificationInfo(userid,//ユーザID
				this.getNickNameFromUserId(userid),//ニックネーム
				this.getEbookLog(userid,strDate),
				this.getHighLightLog(userid,strDate),
				this.getMemoLog(userid,strDate),
				this.getRedSheetLog(userid,strDate));
	}
	return result;
}
//ユーザIDから、一週間前までのポイントを格納するebookPointArray([1,2,3,4,5,6,7])を作成
public int[] getEbookLog(String userid,String strDate) throws ParseException {
	int []result=new int[DAYS];
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = sdFormat.parse(strDate);//String→Date
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);//Date→calendar
	for(int i=0;i<DAYS;i++) {
		calendar.add(Calendar.DATE,-i);//i日戻す
		Date day = calendar.getTime();//calendar→Date
		String Day = new SimpleDateFormat("yyyy-MM-dd").format(day);//Date→String
		result[i]=gamificationdao.getEbookLog(userid,Day);
	}
	return result;
}
//ユーザIDから、一週間前までのポイントを格納するhighlightArray([1,2,3,4,5,6,7])を作成
public int[] getHighLightLog(String userid,String strDate) throws ParseException {
	int []result=new int[DAYS];
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = sdFormat.parse(strDate);//String→Date
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);//Date→calendar
	for(int i=0;i<DAYS;i++) {
		calendar.add(Calendar.DATE,-i);//i日戻す
		Date day = calendar.getTime();//calendar→Date
		String Day = new SimpleDateFormat("yyyy-MM-dd").format(day);//Date→String
		result[i]=gamificationdao.getHighLightLog(userid,Day);
	}
	return result;
}
//ユーザIDから、一週間前までのポイントを格納するmemoArray([1,2,3,4,5,6,7])を作成
public int[] getMemoLog(String userid,String strDate) throws ParseException {
	int []result=new int[DAYS];
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = sdFormat.parse(strDate);//String→Date
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);//Date→calendar
	for(int i=0;i<DAYS;i++) {
		calendar.add(Calendar.DATE,-i);//i日戻す
		Date day = calendar.getTime();//calendar→Date
		String Day = new SimpleDateFormat("yyyy-MM-dd").format(day);//Date→String
		result[i]=gamificationdao.getMemoLog(userid,Day);
	}
	return result;
}
//ユーザIDから、一週間前までのポイントを格納するRedSheetArray([1,2,3,4,5,6,7])を作成
public int[] getRedSheetLog(String userid,String strDate) throws ParseException {
	int []result=new int[DAYS];
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = sdFormat.parse(strDate);//String→Date
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);//Date→calendar
	for(int i=0;i<DAYS;i++) {
		calendar.add(Calendar.DATE,-i);//i日戻す
		Date day = calendar.getTime();//calendar→Date
		String Day = new SimpleDateFormat("yyyy-MM-dd").format(day);//Date→String
		result[i]=gamificationdao.getRedSheetLog(userid,Day);
	}
	return result;
}
}