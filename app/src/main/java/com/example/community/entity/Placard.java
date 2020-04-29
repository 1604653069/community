package com.example.community.entity;
/*公告*/
public class Placard {
	/* 公告id */
	private int pid;
	/* 公告的标题 */
	private String title;
	/* 公告的内容 */
	private String content;
	/* 公告发布的日期 */
	private String date;
	/*小标题*/
	private String stitle;
	/*公告类型*/
	private String type;
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Placard [pid=" + pid + ", title=" + title + ", content=" + content + ", date=" + date + ", stitle="
				+ stitle + ", type=" + type + "]";
	}
}

