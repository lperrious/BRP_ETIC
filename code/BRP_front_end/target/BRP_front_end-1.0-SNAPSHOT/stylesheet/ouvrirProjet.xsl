<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" />

    <xsl:template match="/">
        <div>
            <xsl:if test="count(//lot) = 0">
                <!-- Si nouveau projet -->
                <div class="divTitreLot" id="divTitreLot_0">
                    <input type="text" class="titreLot" placeholder="Titre Lot" />
                    <div class='deleteXMLlot'>
                        <i class='fas fa-times-circle'></i>
                    </div>
                </div>
                <div class="lot" id="lot_0">
                    <input type="hidden" id="idXML" value="_0" />
                    <div class="barreInsertion" onclick="AjouterElement(this);">
                        <div class="panBarreInsertion"></div>
                        <div class="panBarreInsertion"></div>
                    </div>
                    <div class="input-group titre1">
                        <input type="hidden" id="idXML" value="_0" />
                        <div class="input-group-prepend">
                            <span class="input-group-text"></span>
                        </div>
                        <input type="text" class="form-control" placeholder="Titre 1" />
                        <div class='deleteXML'>
                            <i class='fas fa-times-circle'></i>
                        </div>
                    </div>
                    <div class="barreInsertion" onclick="AjouterElement(this);">
                        <div class="panBarreInsertion"></div>
                        <div class="panBarreInsertion"></div>
                    </div>
                </div>
                <div id="ongletsLot">
                    <div class="ongletLot" onclick="AfficherOnglet($('#lot_0'));">0</div>
                    <div class="ongletLot" onclick="CreerOnglet();">+</div>
                </div>
            </xsl:if>
            <xsl:for-each select="//lot">
                <xsl:variable name="i" select="position()" />
                <div class="divTitreLot" id="divTitreLot_{$i - 1}">
                    <input type="hidden" id="idXML" value="{@id}" />
                    <input type="text" class="titreLot" placeholder="Titre Lot" value="{@intitule}" />
                    <div class='deleteXMLlot'>
                        <i class='fas fa-times-circle'></i>
                    </div>
                </div>
            </xsl:for-each>
            <xsl:for-each select="//lot">
                <xsl:variable name="i" select="position()" />
                <div class="lot" id="lot_{$i - 1}">
                    <input type="hidden" id="idXML" value="{@id}" />
                    <xsl:apply-templates select="." />
                    <div class="finLot"></div>
                </div>
            </xsl:for-each>
            <xsl:if test="count(//lot) > 0">
                <div id="ongletsLot">
                    <xsl:for-each select="//lot">
                        <xsl:variable name="i" select="position()" />
                        <div class="ongletLot" onclick="AfficherOnglet($('#lot_{$i - 1}'));">
                            <xsl:value-of select="$i - 1" />
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
            <input type="hidden" id="idXML" value="{@id}" />
            <div class="input-group-prepend">
                <span class="input-group-text"></span>
            </div>
            <input type="text" class="form-control" placeholder="Titre 1" value="{@intitule}" />
            <div class='deleteXML'>
                <i class='fas fa-times-circle'></i>
            </div>
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
            <input type="hidden" id="idXML" value="{@id}" />
            <div class="input-group-prepend">
                <span class="input-group-text"></span>
            </div>
            <input type="text" class="form-control" placeholder="Titre 2" value="{@intitule}" />
            <div class='deleteXML'>
                <i class='fas fa-times-circle'></i>
            </div>
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
            <input type="hidden" id="idXML" value="{@id}" />
            <div class="input-group-prepend">
                <span class="input-group-text"></span>
            </div>
            <input type="text" class="form-control" placeholder="Titre 3" value="{@intitule}" />
            <div class='deleteXML'>
                <i class='fas fa-times-circle'></i>
            </div>
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
            <input type="hidden" id="idXML" value="{@id}" />
            <div class="input-group-prepend">
                <span class="input-group-text"></span>
            </div>
            <input type="text" class="form-control" placeholder="Titre 4" value="{@intitule}" />
            <div class='deleteXML'>
                <i class='fas fa-times-circle'></i>
            </div>
        </div>
        <div class="barreInsertion" onclick="AjouterElement(this);">
            <div class="panBarreInsertion"></div>
            <div class="panBarreInsertion"></div>
        </div>
        <xsl:for-each select="*">
            <xsl:apply-templates select="." />
        </xsl:for-each>
    </xsl:template>

    <!--<xsl:template match="titre5">
        <input type="hidden" id="{@id}"/>
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
    </xsl:template>-->

    <xsl:template match="descriptif">
        <xsl:choose>
            <xsl:when test="contains(name(..),'1')">
                <div class="descriptif titre2">
                    <input type="hidden" id="idXML" value="{@id}" />
                    <input type="hidden" class="idDescriptif" value="{@idBD}" />
                    <input type="hidden" class="typeDescriptif" value="{@type}" />
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1"></span>
                        </div>
                        <xsl:if test="@type = 'generique'">
                            <input type="text" class="form-control nomDescriptif" placeholder="Générique" value="{nomDescriptif}" />
                        </xsl:if>
                        <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                            <input type="text" class="form-control nomDescriptif" placeholder="Ouvrage/Prestation" value="{nomDescriptif}" />
                        </xsl:if>
                        <div class='deleteXML'>
                            <i class='fas fa-times-circle'></i>
                        </div>
                    </div>
                    <div class="input-group description">
                        <div class='affichageTexteDescription descriptionarea{@id}'>
                            <xsl:copy-of select="description/*" />
                        </div>
                        <div class='containerArea containerarea{@id}'>
                            <textarea style='width: 100%;' id='area{@id}'></textarea>
                        </div>
                        <div class='saveTextarea savearea{@id}'>Sauvegarder</div>
                    </div>
                    <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                        <xsl:for-each select="ligneChiffrage">
                            <xsl:apply-templates select="." />
                        </xsl:for-each>
                    </xsl:if>
                </div>
            </xsl:when>
            <xsl:when test="contains(name(..),'2')">
                <div class="descriptif titre3">
                    <input type="hidden" id="idXML" value="{@id}" />
                    <input type="hidden" class="idDescriptif" value="{@idBD}" />
                    <input type="hidden" class="typeDescriptif" value="{@type}" />
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1"></span>
                        </div>
                        <xsl:if test="@type = 'generique'">
                            <input type="text" class="form-control nomDescriptif" placeholder="Générique" value="{nomDescriptif}" />
                        </xsl:if>
                        <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                            <input type="text" class="form-control nomDescriptif" placeholder="Ouvrage/Prestation" value="{nomDescriptif}" />
                        </xsl:if>
                        <div class='deleteXML'>
                            <i class='fas fa-times-circle'></i>
                        </div>
                    </div>
                    <div class="input-group description">
                        <div class='affichageTexteDescription descriptionarea{@id}'>
                            <xsl:copy-of select="description/*" />
                        </div>
                        <div class='containerArea containerarea{@id}'>
                            <textarea style='width: 100%;' id='area{@id}'></textarea>
                        </div>
                        <div class='saveTextarea savearea{@id}'>Sauvegarder</div>
                    </div>
                    <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                        <xsl:for-each select="ligneChiffrage">
                            <xsl:apply-templates select="." />
                        </xsl:for-each>
                    </xsl:if>
                </div>
            </xsl:when>
            <xsl:when test="contains(name(..),'3')">
                <div class="descriptif titre4">
                    <input type="hidden" id="idXML" value="{@id}" />
                    <input type="hidden" class="idDescriptif" value="{@idBD}" />
                    <input type="hidden" class="typeDescriptif" value="{@type}" />
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1"></span>
                        </div>
                        <xsl:if test="@type = 'generique'">
                            <input type="text" class="form-control nomDescriptif" placeholder="Générique" value="{nomDescriptif}" />
                        </xsl:if>
                        <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                            <input type="text" class="form-control nomDescriptif" placeholder="Ouvrage/Prestation" value="{nomDescriptif}" />
                        </xsl:if>
                        <div class='deleteXML'>
                            <i class='fas fa-times-circle'></i>
                        </div>
                    </div>
                    <div class="input-group description">
                        <div class='affichageTexteDescription descriptionarea{@id}'>
                            <xsl:copy-of select="description/*" />
                        </div>
                        <div class='containerArea containerarea{@id}'>
                            <textarea style='width: 100%;' id='area{@id}'></textarea>
                        </div>
                        <div class='saveTextarea savearea{@id}'>Sauvegarder</div>
                    </div>
                    <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                        <xsl:for-each select="ligneChiffrage">
                            <xsl:apply-templates select="." />
                        </xsl:for-each>
                    </xsl:if>
                </div>
            </xsl:when>
            <xsl:when test="contains(name(..),'4')">
                <div class="descriptif titre5">
                    <input type="hidden" id="idXML" value="{@id}" />
                    <input type="hidden" class="idDescriptif" value="{@idBD}" />
                    <input type="hidden" class="typeDescriptif" value="{@type}" />
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1"></span>
                        </div>
                        <xsl:if test="@type = 'generique'">
                            <input type="text" class="form-control nomDescriptif" placeholder="Générique" value="{nomDescriptif}" />
                        </xsl:if>
                        <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                            <input type="text" class="form-control nomDescriptif" placeholder="Ouvrage/Prestation" value="{nomDescriptif}" />
                        </xsl:if>
                        <div class='deleteXML'>
                            <i class='fas fa-times-circle'></i>
                        </div>
                    </div>
                    <div class="input-group description">
                        <div class='affichageTexteDescription descriptionarea{@id}'>
                            <xsl:copy-of select="description/*" />
                        </div>
                        <div class='containerArea containerarea{@id}'>
                            <textarea style='width: 100%;' id='area{@id}'></textarea>
                        </div>
                        <div class='saveTextarea savearea{@id}'>Sauvegarder</div>
                    </div>
                    <xsl:if test="@type = 'ouvrage' or @type = 'prestation'">
                        <xsl:for-each select="ligneChiffrage">
                            <xsl:apply-templates select="." />
                        </xsl:for-each>
                    </xsl:if>
                </div>
            </xsl:when>
        </xsl:choose>
        <div class="barreInsertion" onclick="AjouterElement(this);">
            <div class="panBarreInsertion"></div>
            <div class="panBarreInsertion"></div>
        </div>
    </xsl:template>

    <xsl:template match="ligneChiffrage">
        <div class="ligneChiffrage">
            <input type="hidden" id="idLigneChiffrage" value="{@idLigneChiffrage}" />
            <input type="text" class="form-control localisation" placeholder="Localisation" value="{localisation}" />
            <input type="text" class="form-control quantite" placeholder="Quantité" value="{quantite}" />
            <div class="input-group-prepend">
                <span class="input-group-text unite">
                    <xsl:value-of select="../unite" />
                </span>
            </div>
            <xsl:if test="not(@idLigneChiffrage = 1)">
                <div class="suppressionLigneChiffrage">
                    <i class="fas fa-times-circle"></i>
                </div>
            </xsl:if>
            <xsl:if test="@idLigneChiffrage = 1">
                <div style="width: 25px;"></div>
            </xsl:if>
        </div>
        <xsl:if test="count(preceding-sibling::ligneChiffrage)+1 = count(../ligneChiffrage)">
            <div class="barreInsertionLigneChiffrage" onclick="AjouterLigneChiffrage(this);">
                <div class="panBarreInsertionLigneChiffrage"></div>
                <div class="panBarreInsertionLigneChiffrage"></div>
            </div>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>