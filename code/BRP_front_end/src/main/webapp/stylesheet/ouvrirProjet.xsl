<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" />

    <xsl:template match="/">
            <div>
                <xsl:if test="count(//lot) = 0"> <!-- Si nouveau projet -->
                    <div class="divTitreLot" id="divTitreLot_0">
                        <input type="text" class="titreLot" placeholder="Titre Lot" />
                    </div>
                    <div class="barreInsertion" onclick="AjouterElement(this);">
                        <div class="panBarreInsertion"></div>
                        <div class="panBarreInsertion"></div>
                    </div>
                    <div class="input-group titre1">
                        <div class="input-group-prepend">
                            <span class="input-group-text"></span>
                        </div>
                        <input type="text" class="form-control" placeholder="Titre 1" value="Titre 1" />
                    </div>
                    <div class="barreInsertion" onclick="AjouterElement(this);">
                        <div class="panBarreInsertion"></div>
                        <div class="panBarreInsertion"></div>
                    </div>
                    <div id="ongletsLot">
                        <div class="ongletLot" onclick="AfficherOnglet($('#lot_0'));">0</div>
                        <div class="ongletLot" onclick="CreerOnglet();">+</div>
                    </div>
                </xsl:if>
                <xsl:for-each select="//lot">
                    <xsl:variable name="i" select="position()" />
                    <div class="divTitreLot" id="divTitreLot_{$i - 1}">
                        <input type="text" class="titreLot" placeholder="Titre Lot" />
                    </div>
                </xsl:for-each>
                <xsl:for-each select="//lot">
                    <xsl:variable name="i" select="position()" />
                    <div class="lot" id="lot_{$i - 1}">
                        <xsl:apply-templates select="." />
                        <div class="finLot"></div>
                    </div>
                </xsl:for-each>
                <xsl:if test="count(//lot) > 0">
                    <div id="ongletsLot">
                        <xsl:for-each select="//lot">
                            <xsl:variable name="i" select="position()" />
                            <div class="ongletLot" onclick="AfficherOnglet($('#lot_{$i - 1}'));">
                                <xsl:value-of select="$i - 1"/>
                            </div>
                        </xsl:for-each>
                        <div class="ongletLot" onclick="CreerOnglet();">+</div>
                    </div>
                </xsl:if>
            </div>
    </xsl:template>

    <xsl:template match="lot">
        <div class="barreInsertion" onclick="AjouterElement(this);">
            <div class="panBarreInsertion"></div>
            <div class="panBarreInsertion"></div>
        </div>
        <xsl:for-each select="titre1">
            <xsl:apply-templates select="." />
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="titre1">
        <div class="input-group titre1">
            <div class="input-group-prepend">
                <span class="input-group-text"></span>
            </div>
            <input type="text" class="form-control" placeholder="Titre 1" value="{@intitule}" />
        </div>
        <div class="barreInsertion" onclick="AjouterElement(this);">
            <div class="panBarreInsertion"></div>
            <div class="panBarreInsertion"></div>
        </div>
        <xsl:for-each select="*">
            <xsl:apply-templates select="." />
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="titre2">
        <div class="input-group titre2">
            <div class="input-group-prepend">
                <span class="input-group-text"></span>
            </div>
            <input type="text" class="form-control" placeholder="Titre 2" value="{@intitule}" />
        </div>
        <div class="barreInsertion" onclick="AjouterElement(this);">
            <div class="panBarreInsertion"></div>
            <div class="panBarreInsertion"></div>
        </div>
        <xsl:for-each select="*">
            <xsl:apply-templates select="." />
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="titre3">
        <div class="input-group titre3">
            <div class="input-group-prepend">
                <span class="input-group-text"></span>
            </div>
            <input type="text" class="form-control" placeholder="Titre 3" value="{@intitule}" />
        </div>
        <div class="barreInsertion" onclick="AjouterElement(this);">
            <div class="panBarreInsertion"></div>
            <div class="panBarreInsertion"></div>
        </div>
        <xsl:for-each select="*">
            <xsl:apply-templates select="." />
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="titre4">
        <div class="input-group titre4">
            <div class="input-group-prepend">
                <span class="input-group-text"></span>
            </div>
            <input type="text" class="form-control" placeholder="Titre 4" value="{@intitule}" />
        </div>
        <div class="barreInsertion" onclick="AjouterElement(this);">
            <div class="panBarreInsertion"></div>
            <div class="panBarreInsertion"></div>
        </div>
        <xsl:for-each select="*">
            <xsl:apply-templates select="." />
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="titre5">
        <div class="input-group titre5">
            <div class="input-group-prepend">
                <span class="input-group-text"></span>
            </div>
            <input type="text" class="form-control" placeholder="Titre 5" value="{@intitule}" />
        </div>
        <div class="barreInsertion" onclick="AjouterElement(this);">
            <div class="panBarreInsertion"></div>
            <div class="panBarreInsertion"></div>
        </div>
        <xsl:apply-templates select="." />
    </xsl:template>

    <xsl:template match="descriptif">
        <xsl:choose>
		  <xsl:when test="contains(name(..),'1')">
		    <div class="descriptif titre2" id="{@idBD}">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"></span>
                    </div>
                    <xsl:if test="@type = 'generique'">
                        <input type="text" class="form-control" placeholder="Générique" value="Générique" />
                    </xsl:if>
                    <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                        <input type="text" class="form-control" placeholder="Ouvrage/Prestation" value="Ouvrage/Prestation" />
                    </xsl:if>
                </div>
                <div class="input-group description">
                    <textarea class="form-control" aria-label="With textarea" placeholder="Description"></textarea>
                </div>
                <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                    <xsl:for-each select="ligneChiffrage">
                        <xsl:apply-templates select="."/>
                    </xsl:for-each>
                </xsl:if>
            </div>
		  </xsl:when>
		  <xsl:when test="contains(name(..),'2')">
		    <div class="descriptif titre3" id="{@idBD}">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"></span>
                    </div>
                    <xsl:if test="@type = 'generique'">
                        <input type="text" class="form-control" placeholder="Générique" value="Générique" />
                    </xsl:if>
                    <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                        <input type="text" class="form-control" placeholder="Ouvrage/Prestation" value="Ouvrage/Prestation" />
                    </xsl:if>
                </div>
                <div class="input-group description">
                    <textarea class="form-control" aria-label="With textarea" placeholder="Description"></textarea>
                </div>
                <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                    <xsl:for-each select="ligneChiffrage">
                        <xsl:apply-templates select="."/>
                    </xsl:for-each>
                </xsl:if>
            </div>
          </xsl:when>
          <xsl:when test="contains(name(..),'3')">
		    <div class="descriptif titre4" id="{@idBD}">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"></span>
                    </div>
                    <xsl:if test="@type = 'generique'">
                        <input type="text" class="form-control" placeholder="Générique" value="Générique" />
                    </xsl:if>
                    <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                        <input type="text" class="form-control" placeholder="Ouvrage/Prestation" value="Ouvrage/Prestation" />
                    </xsl:if>
                </div>
                <div class="input-group description">
                    <textarea class="form-control" aria-label="With textarea" placeholder="Description"></textarea>
                </div>
                <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                    <xsl:for-each select="ligneChiffrage">
                        <xsl:apply-templates select="."/>
                    </xsl:for-each>
                </xsl:if>
            </div>
          </xsl:when>
          <xsl:when test="contains(name(..),'4')">
		    <div class="descriptif titre5" id="{@idBD}">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"></span>
                    </div>
                    <xsl:if test="@type = 'generique'">
                        <input type="text" class="form-control" placeholder="Générique" value="Générique" />
                    </xsl:if>
                    <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                        <input type="text" class="form-control" placeholder="Ouvrage/Prestation" value="Ouvrage/Prestation" />
                    </xsl:if>
                </div>
                <div class="input-group description">
                    <textarea class="form-control" aria-label="With textarea" placeholder="Description"></textarea>
                </div>
                <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                    <xsl:for-each select="ligneChiffrage">
                        <xsl:apply-templates select="."/>
                    </xsl:for-each>
                </xsl:if>
            </div>
          </xsl:when>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="ligneChiffrage">
        <div class="ligneChiffrage">
            <input type="text" aria-label="Localisation" class="form-control" placeholder="Localisation" />
            <input type="text" aria-label="Quantite" class="form-control" placeholder="Quantité" />
            <div class="input-group-prepend">
                <span class="input-group-text">
                    <xsl:value-of select="../unite" />
                </span>
            </div>
            <div class="suppressionLigneChiffrage">
                <i class="fas fa-times"></i>
            </div>
        </div>
        <div class="barreInsertionLigneChiffrage" onclick="AjouterLigneChiffrage(this);">
            <div class="panBarreInsertionLigneChiffrage"></div>
            <div class="panBarreInsertionLigneChiffrage"></div>
        </div>
    </xsl:template>

</xsl:stylesheet>