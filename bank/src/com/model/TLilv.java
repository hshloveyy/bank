package com.model;

/**
 * TLivi generated by MyEclipse Persistence Tools
 */

public class TLilv implements java.io.Serializable
{

	// Fields

	private Integer id;

	private Double lilv;

	// Constructors

	/** default constructor */
	public TLilv()
	{
	}

	/** full constructor */
	public TLilv(Double lilv)
	{
		this.lilv = lilv;
	}

	// Property accessors

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Double getLilv()
	{
		return this.lilv;
	}

	public void setLilv(Double lilv)
	{
		this.lilv = lilv;
	}

}