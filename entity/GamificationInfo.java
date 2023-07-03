package jp.ac.tokushima_u.is.ll.entity;

//kns
public class GamificationInfo{
	private String USERID; //ユーザID
	private String NICKNAME;//ニックネーム
	private int LEVEL;//ユーザレベル
	private int []EBOOKPOINT;//ebookポイントの配列
	private int []HIGHLIGHTPOINT;//HighLightポイントの配列
	private int []REDSHEETPOINT;//RedSheetポイントの配列
	private int []MEMOPOINT;//Memoポイントの配列
	private boolean BADGE_STARTDASH;//スタートダッシュバッジ
	private boolean BADGE_OIAGE;//追い上げバッジ
	private boolean BADGE_KINBEN; //勤勉バッジ
	private boolean BADGE_HIGHLIGHTMASTER; //ハイライトマスターバッジ
	private boolean BADGE_EBOOKMASTER; //教科書マスターバッジ
	private boolean BADGE_MEMOMASTER; //メモマスターバッジ
	private boolean BADGE_REDSHEETMASTER;//赤シートマスターバッジ
	private boolean BADGE_ISSYUU;//まずは一周バッジ
	private int DAYS =7;//記録する日数


	//コンストラクタ
	public GamificationInfo(String userid, String nickname,int[] ebookpoint,int[] highlightpoint,int[] memopoint,int[] redsheetpoint){
		this.USERID = userid;
		this.NICKNAME = nickname;
		this.EBOOKPOINT = new int[ebookpoint.length];
		this.HIGHLIGHTPOINT=new int[highlightpoint.length];
		this.MEMOPOINT =new int[memopoint.length];
		this.REDSHEETPOINT=new int[redsheetpoint.length];
		for(int i=0;i<DAYS;i++) {
			//System.out.println("for:"+highlightpoint[i]);
			this.EBOOKPOINT[i]= ebookpoint[i];
			this.HIGHLIGHTPOINT[i]=highlightpoint[i];
			this.MEMOPOINT[i]=memopoint[i];
			this.REDSHEETPOINT[i]=redsheetpoint[i];
		}
	}

	//ゲッタとセッタを記述
	public String getUserId() {
		return USERID;
	}

	public void setUserId(String uSERID) {
		USERID = uSERID;
	}

	public String getNickName() {
		return NICKNAME;
	}

	public void setNickName(String NickName) {
		NICKNAME = NickName;
	}

	public int getLevel() {
		return LEVEL;
	}

	public void setLevel(int Level) {
		LEVEL = Level;
	}

	public int[] getEbookPointArray() {
		return EBOOKPOINT;
	}

	public void setEbookPoint(Integer index,Integer ebookpoint) {
		EBOOKPOINT[index] = ebookpoint;
	}

	public int getTotalEbookPoint() {
		int result = 0;
		for(int i=0;i<EBOOKPOINT.length;i++) {
			result+=EBOOKPOINT[i];
		}
		return result;
	}

	public int getTodayEbookPoint() {
		return EBOOKPOINT[0];
	}

	public int[] getHighLightPointArray() {
		return HIGHLIGHTPOINT;
	}

	public void setHighLightPoint(Integer index,Integer highlightpoint) {
		HIGHLIGHTPOINT[index] = highlightpoint;
	}

	public int getTotalHighLightPoint() {
		int result = 0;
		for(int i=0;i<HIGHLIGHTPOINT.length;i++) {
			result+=HIGHLIGHTPOINT[i];
		}
		return result;
	}

	public int getTodayHighLightPoint() {
		return HIGHLIGHTPOINT[0];
	}

	public int[] getMemoPointArray() {
		return MEMOPOINT;
	}

	public void setMemoPoint(Integer index,Integer memopoint) {
		MEMOPOINT[index] = memopoint;
	}

	public int getTotalMemoPoint() {
		int result = 0;
		for(int i=0;i<MEMOPOINT.length;i++) {
			result+=MEMOPOINT[i];
		}
		return result;
	}

	public int getTodayMemoPoint() {
		return MEMOPOINT[0];
	}

	public int[] getRedSheetPointArray() {
		return REDSHEETPOINT;
	}

	public void setRedSheetPoint(Integer index,Integer redsheetpoint) {
		REDSHEETPOINT[index] = redsheetpoint;
	}

	public int getTotalRedSheetPoint() {
		int result = 0;
		for(int i=0;i<REDSHEETPOINT.length;i++) {
			result+=REDSHEETPOINT[i];
		}
		return result;
	}

	public int getTodayRedSheetPoint() {
		int result;
		result=REDSHEETPOINT[0];
		return result;
	}

	public Boolean getStartDash() {
		return BADGE_STARTDASH;
	}

	public void setStartDash(Boolean TF) {
		BADGE_STARTDASH = TF;
	}

	public boolean getOiage() {
		return BADGE_OIAGE;
	}

	public void setOiage(boolean TF) {
		BADGE_OIAGE = TF;
	}

	public boolean getKinben() {
		return BADGE_KINBEN;
	}

	public void setKinben(boolean TF) {
		BADGE_KINBEN = TF;
	}

	public boolean getHighLightMaster() {
		return BADGE_HIGHLIGHTMASTER;
	}

	public void setHighLightMaster(boolean TF) {
		BADGE_HIGHLIGHTMASTER = TF;
	}

	public boolean getEbookMaster() {
		return BADGE_EBOOKMASTER;
	}

	public void setEbookMaster(boolean TF) {
		BADGE_EBOOKMASTER = TF;
	}

	public boolean getMemoMaster() {
		return BADGE_MEMOMASTER;
	}

	public void setMemoMaster(boolean TF) {
		BADGE_MEMOMASTER = TF;
	}

	public boolean getRedSheetMaster() {
		return BADGE_REDSHEETMASTER;
	}

	public void setRedSheetMaster(boolean TF) {
		BADGE_REDSHEETMASTER = TF;
	}

	public boolean getIssyuu() {
		return BADGE_ISSYUU;
	}

	public void setIssyuu(boolean TF) {
		BADGE_ISSYUU = TF;
	}

	public int getTotalAllPoint() {
		int result = 0;
		for(int i=0;i<EBOOKPOINT.length;i++) {
			result=result+EBOOKPOINT[i]+HIGHLIGHTPOINT[i]+MEMOPOINT[i]+REDSHEETPOINT[i];
		}
		return result;
	}

	public int getTodayAllPoint() {
		int result = 0;
		result=EBOOKPOINT[0]+HIGHLIGHTPOINT[0]+MEMOPOINT[0]+REDSHEETPOINT[0];
		return result;
	}
}
