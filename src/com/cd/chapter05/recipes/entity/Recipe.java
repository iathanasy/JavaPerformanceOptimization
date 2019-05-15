package com.cd.chapter05.recipes.entity;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 食谱
 * @author cd
 * @date 2019年5月15日 下午5:02:00
 * @desc
 */
public class Recipe {

	private String title;

    private String desc;

    private List<String> images;
    
    private String recruit;//难度等级
    
    private String cookMethod;

    private String taste;

    private String setupTime;

    private String cookingTime;

    private List<Ingredient> ingredients;
    
    private Set<String> tags;
    
    private String funcational;

    private String tips;

    private int likedNum = Math.abs(random.nextInt()) % 2000;

    private int cookedNum = Math.abs(random.nextInt()) % 2000;

    private int collectedNum = Math.abs(random.nextInt()) % 2000;

    private float score;//评分

    private static final Random random = new Random(System.currentTimeMillis());

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getRecruit() {
		return recruit;
	}

	public void setRecruit(String recruit) {
		this.recruit = recruit;
	}

	public String getCookMethod() {
		return cookMethod;
	}

	public void setCookMethod(String cookMethod) {
		this.cookMethod = cookMethod;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public String getSetupTime() {
		return setupTime;
	}

	public void setSetupTime(String setupTime) {
		this.setupTime = setupTime;
	}

	public String getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(String cookingTime) {
		this.cookingTime = cookingTime;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public String getFuncational() {
		return funcational;
	}

	public void setFuncational(String funcational) {
		this.funcational = funcational;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public int getLikedNum() {
		return likedNum;
	}

	public void setLikedNum(int likedNum) {
		this.likedNum = likedNum;
	}

	public int getCookedNum() {
		return cookedNum;
	}

	public void setCookedNum(int cookedNum) {
		this.cookedNum = cookedNum;
	}

	public int getCollectedNum() {
		return collectedNum;
	}

	public void setCollectedNum(int collectedNum) {
		this.collectedNum = collectedNum;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
    
    
}
