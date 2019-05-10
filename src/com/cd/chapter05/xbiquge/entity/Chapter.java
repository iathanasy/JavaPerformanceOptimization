package com.cd.chapter05.xbiquge.entity;

/**
 * 章节
 * @author cd
 * @date 2019年5月9日 下午2:17:19
 * @desc
 */
public class Chapter {
	private String name;
	private String title;
	private String url;
	private String context;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Chapter(String name, String title, String url) {
		super();
		this.name = name;
		this.title = title;
		this.url = url;
	}
	public Chapter(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}
	
	public Chapter() {
		super();
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		Chapter chapter = new Chapter();
		if(url != chapter.url) return false;
		return url.equals(chapter.url);
	}
	
	@Override
	public int hashCode() {
		int result = url.hashCode();
		result = 31 * result + title.hashCode();
		return result;
	}
	@Override
	public String toString() {
		return "Chapter [name=" + name + ", title=" + title + ", url=" + url
				+ "]";
	}
	
}
