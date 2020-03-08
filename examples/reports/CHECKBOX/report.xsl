<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<div>
					<div width="100%">
						<table width="100%">
							<tr>
								<td width="20%">
									<img src="selmate-logo.png" width="200" height="100"></img>
								</td>
								<td width="20%">
									<xsl:text> </xsl:text>
								</td>
								<td width="60%">
									<div style="text-align: left">
										<xsl:text> SCRIPT </xsl:text>
										<b>
											<xsl:value-of select="/selmate-report/@name"></xsl:value-of>
										</b>

										<xsl:text> EXECUTION REPORT</xsl:text>
										<br />
										<br />
										<xsl:text>DATE : </xsl:text>
										<xsl:value-of select="/selmate-report/@exec-time" />
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div width="100%">
						<table style="border:2px solid black;" width="100%">
							<xsl:for-each select="/selmate-report/command-report">
								<tr style="border:1px black;">
									<td colspan="1">
										<b>
											<xsl:text>STEP </xsl:text>
											<xsl:value-of select="step" />
										</b>
									</td>
									<td colspan="1">
										<xsl:if test="status='PASSED'">
											<span style="color:Green;">
												<b>
													<xsl:value-of select="status" />
												</b>
											</span>
											<xsl:text> </xsl:text>
											<b>
												<xsl:value-of select="description"></xsl:value-of>
											</b>
										</xsl:if>
										<xsl:if test="status='WARNED'">
											<span style="color:Orange;">
												<b>
													<xsl:value-of select="status" />
												</b>
											</span>
											<xsl:text> - </xsl:text>
											<xsl:value-of select="description"></xsl:value-of>
										</xsl:if>
										<xsl:if test="status='FAILED'">
											<span style="color:red;">
												<b>
													<xsl:value-of select="status" />
												</b>
											</span>
											<xsl:text> - </xsl:text>
											<xsl:value-of select="description"></xsl:value-of>
										</xsl:if>
									</td>
								</tr>
								<tr>
									<td colspan="1">
										<xsl:text>COMMAND : </xsl:text>
										<xsl:value-of select="command-name" />
									</td>
									<td colspan="1" style="whitespace nowrap">
										<xsl:text>DESCRIPTION : </xsl:text>
										<xsl:value-of select="step-description" />
									</td>
								</tr>
								<xsl:if test="image-src">
									<tr>
										<td colspan="2">
											<img>
												<xsl:attribute name="src">
											<xsl:value-of select="image-src" />
										</xsl:attribute>
											</img>
										</td>
										<td></td>
									</tr>
								</xsl:if>
							</xsl:for-each>
						</table>
					</div>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>