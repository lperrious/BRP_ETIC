<?xml version="1.0" encoding="UTF-8" ?>

<!-- New document created with EditiX at Tue Feb 25 07:43:53 CET 2020 -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" />

	<xsl:template match="metadonnees">
		<html>
			<p style="text-align:center; color:blue;">
				Objectif :
				<xsl:value-of select="objectif" />
			</p>
			<br />
		</html>
	</xsl:template>

	<xsl:template match="/">
		<html>
			<head>
				<title>
    				Pays du monde 
  				</title>
			</head>

			<body style="background-color:white;">
				<h1>Les pays du monde</h1>
				<p style="text-align:center; color:blue;">
					Mise en forme par : ROB, LAFAILLE (B3130)</p>
			</body>
			<hr />
			<hr />
			<p>
				Pays avec 6 voisins :
				<xsl:for-each select="//country/borders/neighbour[last() = 6]/ancestor::*/ancestor::*/name/common">
					<xsl:value-of select="." />
					<xsl:if test="position() != last()">
						<xsl:text>, </xsl:text>
					</xsl:if>
				</xsl:for-each>
			</p>
			<p>
				<xsl:for-each select="//country">
					<xsl:sort select="count(borders/neighbour)" order="descending" data-type="number" />
					<xsl:if test="position() &lt;= 1">
						Pays ayant le plus de voisins :
						<xsl:value-of select="name/common" />
						, nombre de voisins :
						<xsl:value-of select="count(borders/neighbour)" />
					</xsl:if>
				</xsl:for-each>
			</p>

			<!-- Parcours tous les continents -->
			<xsl:for-each select="//infosContinent/continent[not(. = ../preceding::infosContinent/continent)][not(./ancestor::*/ancestor::*/name/common = 'Antarctica')]">
				<!-- Attention au '' et non pas "" si deja dans des "" -->
				<xsl:apply-templates select="."></xsl:apply-templates>
			</xsl:for-each>
		</html>
	</xsl:template>

	<xsl:template match="continent">
		<h3>
			Pays du continent :
			<xsl:value-of select="." />
			par sous-régions :
		</h3>
		<!-- Parours toutes tous les subregions -->
		<xsl:for-each select="//infosContinent/continent[. = current()]/ancestor::*/subregion[not(. = ../preceding::infosContinent/continent[. = current()]/ancestor::*/subregion)]">
			<xsl:apply-templates select="."></xsl:apply-templates>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="subregion">
		<h4>
			<xsl:value-of select="." />
			(
			<xsl:value-of select="count(//country/infosContinent[subregion = current()])" />
			pays)
		</h4>
		<table border="3" width="100%" align="center">
			<tr>
				<th>N°</th>
				<th>Nom</th>
				<th>Capitale</th>
				<th>Voisins</th>
				<th>Coordonnées</th>
				<th>Drapeau</th>
			</tr>
			<!-- Parcours tous les pays -->
			<xsl:for-each select="//country/infosContinent[subregion = current()]">
				<xsl:apply-templates select=".." />
			</xsl:for-each>
		</table>
	</xsl:template>

	<!-- Selectionne tous les pays -->
	<xsl:template match="country">
		<tr>
			<td>
				<xsl:value-of select="count(preceding-sibling::*/infosContinent[subregion = current()/infosContinent/subregion])+1" />
			</td>
			<td>
				<span style="color:green">
					<xsl:value-of select="name/common" />
				</span>
				(
				<xsl:value-of select="name/official" />
				)
				<br />
				<xsl:if test="name/native_name/@lang='eng'">
					<span style="color:blue">
						Nom anglais :
						<xsl:value-of select="name/native_name[@lang='eng']/official" />
					</span>
				</xsl:if>
			</td>
			<td>
				<xsl:value-of select="capital" />
			</td>
			<td>
				<xsl:if test="borders">
					<xsl:for-each select="borders/neighbour">
						<xsl:value-of select="//codes[cca3 = current()]/ancestor::*/name/common" /> <!-- current()-->
						<xsl:if test="position() != last()">
							<xsl:text>,</xsl:text>
						</xsl:if>
					</xsl:for-each>
				</xsl:if>
				<!-- Test si précense de voisins -->
				<xsl:if test="count(borders) = 0">
					<xsl:text>Île</xsl:text>
				</xsl:if>
			</td>

			<td>
				<xsl:text>
					Latitude :
				</xsl:text>
				<xsl:value-of select="coordinates/@lat" />
				<br />
				<xsl:text>
					Longitude :
				</xsl:text>
				<xsl:value-of select="coordinates/@long" />
			</td>
			<td>
				<!-- Assemble l'URL en fonction du code cca2 du pays -->
				<img src="{concat(concat('http://www.geonames.org/flags/x/',translate(codes/cca2, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')),'.gif')}" alt="" height="40" width="60" />
			</td>
		</tr>
	</xsl:template>

</xsl:stylesheet>