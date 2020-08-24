package com.service.provider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conditionsaver")
public class ConditionSaver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private long entityId;
	
	@Column(name = "condition_name")
	private String conditionName;

	@Column(name = "condition_value")
	private String conditionValue;

	public long getEntityId() {
		return entityId;
	}

	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String conditionV_value) {
		this.conditionValue = conditionV_value;
	}

	@Override
	public String toString() {
		return "ConditionSaver [entityId=" + entityId + ", conditionName=" + conditionName + ", conditionV_value="
				+ conditionValue + "]";
	}
	
}
