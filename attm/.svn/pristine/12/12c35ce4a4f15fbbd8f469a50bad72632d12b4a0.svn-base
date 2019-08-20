package com.biziitech.attm.bg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "v_bg_component")
public class ModelHome {
	
	@Id
	
	@Column(name="component_id")
	private Long componentId;
	
	@Column(name="component_name")
	private String componentName;
	
	@Column(name="parent_component_id")
	private Long parentComponentId;

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public Long getParentComponentId() {
		return parentComponentId;
	}

	public void setParentComponentId(Long parentComponentId) {
		this.parentComponentId = parentComponentId;
	}

}
