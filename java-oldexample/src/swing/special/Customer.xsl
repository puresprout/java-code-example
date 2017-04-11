<?xml version="1.0" encoding="EUC-KR" ?>

<!--
    Document   : Customer.xsl
    Created on : 2005년 6월 27일 (월), 오후 2:29
    Author     : 박성현
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text" encoding="EUC-KR"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/Customers">
        <xsl:for-each select="Customer">
            <xsl:value-of select="@id"/>
            <xsl:value-of select="Name"/>
            <xsl:value-of select="Phone"/>
            <xsl:value-of select="Address"/>
            
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>
