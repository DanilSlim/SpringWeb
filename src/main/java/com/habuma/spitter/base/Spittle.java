package com.habuma.spitter.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="spittle")
@SequenceGenerator(name = "SP_ID_GENERATOR", sequenceName = "spittle_pk_sequense",allocationSize = 1)
public class Spittle {
	
	  @Id
	  @Column(name="id",updatable = false, nullable = false)
	  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SP_ID_GENERATOR")
	  private  Long id;
	  
	  @Column(name="message", nullable = false)
	  private String message;
	  
	  @Column(name="created_at",nullable = false)
	  private Date time;
	  
	  @Column(name="latitude")
	  private Double latitude;
	  
	  @Column(name="longitude")
	  private Double longitude;
	  
	  @Column(name="spitter_id", nullable = false)
	  private Long spitter_id;
	  
	  public Spittle() {}
	  
	  public Spittle(String message, Date time) {
		    this(null, message, time, null, null,null);
		  }
		  
		  public Spittle(Long id, String message, Date time, Double longitude, Double latitude, Long spitterId) {
		    this.id = id;
		    this.message = message;
		    this.time = time;
		    this.longitude = longitude;
		    this.latitude = latitude;
		  }

		public Long getId() {
			return id;
		}

		public String getMessage() {
			return message;
		}

		public Date getTime() {
			return time;
		}

		public Double getLatitude() {
			return latitude;
		}

		public Double getLongitude() {
			return longitude;
		}

		public Long getSpitter_id() {
			return spitter_id;
		}

		public void setSpitter_id(Long spitter_id) {
			this.spitter_id = spitter_id;
		}
		  
	 

}
