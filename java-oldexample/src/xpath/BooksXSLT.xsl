<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<xsl:template match="/">

	<xsl:copy-of select="//Author[contains(text(), '박성현')]" />

	</xsl:template>

</xsl:stylesheet>

<!--
	XPath 표현식들
	
	- 단일노드 반환시
	<xsl:copy-of select="//Catalog/Book[@sid]" />
	
	1. 기준 축
	/descendant::Book
	/child::Book
	
	2. 노드 테스트
	/Catalog/Book/Title
	/Catalog/Book/Title/descendant::text()
	
	/Catalog/Book[1]/*
	/Catalog/Book[1]/node()
	
	3. 프리디킷
	/Catalog/Book[@sid]
	//Book[Title='Struts2']
	//Book[2]
	
	4. 함수
	//Author[contains(@major, '조원석')]
	//Author[contains(text(), '박성현']
	
	
	
	- 다중노드 반환시
	<xsl:for-each select="/Catalog/Book/attribute::*">
	| <xsl:value-of select="."/> |
	</xsl:for-each>
	
	1.1. 기준 축 어트리뷰
	/Catalog/Book/attribute::*
-->
