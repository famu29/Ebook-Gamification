package jp.ac.tokushima_u.is.ll.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
//import jp.ac.tokushima_u.is.ll.dto.ItemDTO;

public interface GamificationDao {

	public String getNickNameFromUserId(@Param("userid") String userid); // ユーザIDからニックネームを取得
	public List<String> loadUserList(@Param("startday") String startday);//startday以降に作られたユーザリストを取得
	public String getStartDashUser(@Param("startdashday") String startdashday);//スタートダッシュバッジの獲得ユーザIDを取得
	public int getMyDayEbookPoint(@Param("userid") String userid,@Param("date") String date);//ユーザIDからMyDayEbookPointを取得
	public int getMyDayHighLightPoint(@Param("userid") String userid,@Param("date") String date);//ユーザIDからMyDayHighLightPointを取得
	public int getMyDayMemoPoint(@Param("userid") String userid,@Param("date") String date);//ユーザIDからMyDayMemoPointを取得
	public int getMyDayRedSheetPoint(@Param("userid") String userid,@Param("date") String date);//ユーザIDからMyDayRedSheetPointを取得
	public int getMyEbookAchievement(@Param("userid") String userid,@Param("ebookid") String ebookid);//ユーザIDと教科書IDから到達してるか否かを取得

	//予備実験ログ収集用
	public int getEbookLog(@Param("userid") String userid,@Param("date") String date);//ユーザIDからMyDayEbookPointを取得
	public int getHighLightLog(@Param("userid") String userid,@Param("date") String date);//ユーザIDからMyDayHighLightPointを取得
	public int getMemoLog(@Param("userid") String userid,@Param("date") String date);//ユーザIDからMyDayHighLightPointを取得
	public int getRedSheetLog(@Param("userid") String userid,@Param("date") String date);//ユーザIDからMyDayRedSheetPointを取得
}