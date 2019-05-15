package com.cd.chapter05.recipes.entity;
/**
 * 步骤
 * @author cd
 * @date 2019年5月15日 下午4:59:34
 * @desc
 */
public class CookStep {

	private Integer stepOrder;

    private String description;

    private String image;

	public CookStep(Integer stepOrder, String description, String image) {
		super();
		this.stepOrder = stepOrder;
		this.description = description;
		this.image = image;
	}

	public Integer getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(Integer stepOrder) {
		this.stepOrder = stepOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
    
    
}
