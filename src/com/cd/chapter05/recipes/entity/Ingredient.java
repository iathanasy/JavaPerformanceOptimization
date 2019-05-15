package com.cd.chapter05.recipes.entity;
/**
 * 材料
 * @author cd
 * @date 2019年5月15日 下午5:00:36
 * @desc
 */
public class Ingredient {

	private  String ingredientName;//配料名称
    private  String quantityDesc;  //用量描述
    private String url;
    
	public Ingredient(String ingredientName, String quantityDesc, String url) {
		super();
		this.ingredientName = ingredientName;
		this.quantityDesc = quantityDesc;
		this.url = url;
	}
	
	
	public Ingredient(String ingredientName, String quantityDesc) {
		super();
		this.ingredientName = ingredientName;
		this.quantityDesc = quantityDesc;
	}


	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public String getQuantityDesc() {
		return quantityDesc;
	}
	public void setQuantityDesc(String quantityDesc) {
		this.quantityDesc = quantityDesc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    
    
}
