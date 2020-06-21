/*************** Variables globales ******************/

test_select_descriptif = false; //permet de ne pas supprimer la selection d'un element à peine faite
test_manageProjet = false;
var testChoixPresent = false;

/************** Appels au chargement de la page ******************/
$(document).ready(function () {

  $.ajax({
    url: "./ActionServlet",
    method: "GET",
    data: {
      todo: "listerRaccordementCatConst"
    },
    dataType: "json",
  }).done(function (response) {
    // Fonction appelée en cas d'appel AJAX réussi
    console.log("Response", response);
    if(!response.ErrorState) {
      $.each(response.listeCoeffRaccordement, function (i, coeffRaccordement) {
        $('#coeffRaccordement').append($('<option>', { 
            value: i,
            text : coeffRaccordement.localisation + " - " + coeffRaccordement.valeur,
            name: coeffRaccordement.localisation
        }));
      });
      $.each(response.listeCategorieConstruction, function (i, categorieConstruction) {
        $('#categorieConstruction').append($('<option>', { 
            value: i,
            text: categorieConstruction.code + " - " + categorieConstruction.intitule,
            name: categorieConstruction.code
        }));
      });
    }
  });

  //Récupère l'arbo Descriptifs de la BDD
  $.ajax({
    url: "./ActionServlet",
    method: "GET",
    data: {
      todo: "arboDescriptifs",
    },
    dataType: "json",
  })
    .done(function (response) {
      // Fonction appelée en cas d'appel AJAX réussi
      console.log("Response", response); // LOG dans Console Javascript
      if (!response.Error) {
        //On insère l'arbo dans le HTML
        for (let i = 0; i < response.arborescence.length; i++) {
          const chapitre = response.arborescence[i];
          $("#arboBDD").append(
            "<div class='lineBDD lineChapitre'><span class='iconBDD'><i class='fas fa-caret-right arrow'></i></span><div class='intitule'>" +
              chapitre.idChapitre + " - " + chapitre.intituleChapitre +
              "</div></div>"
          );
          for (let j = 0; j < chapitre.categories.length; j++) {
            const categorie = chapitre.categories[j];
            $("#arboBDD").append(
              "<div class='lineBDD lineCategorie'><span class='iconBDD'><i class='fas fa-caret-right arrow'></i></span><div class='intitule'>" +
                categorie.idCategorie.substr(3, 4) + " - " + categorie.intituleCategorie +
                "</div></div>"
            );
            for (let k = 0; k < categorie.familles.length; k++) {
              const famille = categorie.familles[k];
              $("#arboBDD").append(
                "<div class='lineBDD lineFamille'><span class='iconBDD'><i class='fas fa-caret-right arrow'></i></span><div class='intitule'>" +
                  famille.idFamille.substr(7, 3) + " - " + famille.intituleFamille +
                  "</div></div>"
              );
              for (let l = 0; l < famille.sousFamilles.length; l++) {
                const sousFamille = famille.sousFamilles[l];
                $("#arboBDD").append(
                  "<div class='lineBDD lineSousFamille'><span class='iconBDD'><i class='fas fa-caret-right arrow'></i></span><div class='intitule'>" +
                    sousFamille.idSousFamille.substr(10, 13) + " - " + sousFamille.intituleSousFamille +
                    "</div></div>"
                );
                for (let m = 0; m < sousFamille.descriptifs.length; m++) {
                  const descriptif = sousFamille.descriptifs[m];
                  if (descriptif.type == "generique") {
                    $("#arboBDD").append(
                      "<div class='lineBDD lineDescriptif generique'><input type='hidden' class='idDescriptif' value='" +
                        descriptif.id +
                        "'/><span class='iconBDD'>-</span><div class='intitule'>" +
                        descriptif.id.substr(13, 17) + " - " + descriptif.nom +
                        "</div></div>"
                    );
                  } else if (descriptif.type == "ouvrage") {
                    $("#arboBDD").append(
                      "<div class='lineBDD lineDescriptif ouvrage'><input type='hidden' class='idDescriptif' value='" +
                        descriptif.id +
                        "'/><span class='iconBDD'>-</span><div class='intitule'>" +
                        descriptif.id.substr(13, 17) + " - " + descriptif.nom +
                        "</div></div>"
                    );
                  } else if (descriptif.type == "prestation") {
                    $("#arboBDD").append(
                      "<div class='lineBDD lineDescriptif prestation'><input type='hidden' class='idDescriptif' value='" +
                        descriptif.id +
                        "'/><span class='iconBDD'>-</span><div class='intitule'>" +
                        descriptif.id.substr(17, 21) + " - " + descriptif.nom +
                        "</div></div>"
                    );
                  }
                }
              }
            }
          }
        }
        $(".lineDescriptif").click(function () {
          select_descriptif(this);
        });
        $(".lineChapitre").click(function () {
          gestion_arbo_bdd(this);
        });
        $(".lineCategorie").click(function () {
          gestion_arbo_bdd(this);
        });
        $(".lineFamille").click(function () {
          gestion_arbo_bdd(this);
        });
        $(".lineSousFamille").click(function () {
          gestion_arbo_bdd(this);
        });
      }
    })
    .fail(function (error) {
      // Fonction appelée en cas d'erreur lors de l'appel AJAX
      //console.log("Error", error); // LOG dans Console Javascript
      console.log(
        "Erreur lors de l'appel AJAX pour recuperer l'arborescence BDD"
      );
    });

  $(".container").first().click(unset_select_descriptif);
  $(".container").last().click(SuppressionChoixInsertionTitre);
  addEventsDescriptifs();
  AjoutEventSupprLigneChiffrage();

  //à ajouter une fois lorsque l'utilisateur cherche un projet
  $(".textLineProjet").click(function () {
    clicProjet($(this).parent());
  });
});

/****************** Fonctions (popup) *********************/
function popUpNomProjet(sens, idProjet) {
  //on le montre
  if (sens) {
    $("#popUpWindow").css("display", "flex");
    $("#container").css("filter", "blur(5px)");
  }
  //sinon on le cache
  else {
    $("#popUpWindow").hide();
    $("#nomProjetInput").val("");
    $("#container").css("filter", "blur(0px)");
  }

  //on tente de duppliquer
  if (idProjet)
    //on essaie juste de créer un nouveau projet
    $(".popupNomProjet > .buttons > .button")
      .last()
      .click(function () {
        dupliquerProjet(idProjet);
      });
  else $(".popupNomProjet > .buttons > .button").last().click(createProject);
}

/****************** Fonctions (partie gauche) *********************/
function createProject() {
  var nomProjet = $("#nomProjetInput").val();

  $.ajax({
    url: "./ActionServlet",
    method: "POST",
    data: {
      todo: "creationProjet",
      nomProjet: nomProjet,
    },
    dataType: "json",
  }).done(function (response) {
    // Fonction appelée en cas d'appel AJAX réussi
    console.log("Response", response);

    //on ferme le popup
    popUpNomProjet(false);

    if (response) {
      if (response['Error']) {
        alert("Une erreur est survenue, impossible de créer un nouveau projet)");
      }
      else{
        ouvrirProjet(response["idProjet"]);
      }
    }
  });
}

function clicProjet(element) {
  var idProjet = $(element).children(".idProjet").val();
  var action = $(element).children(".actionProjet").val();

  if (action == "ouvrir") ouvrirProjet(idProjet);
  else popUpNomProjet(true, idProjet);
}

function dupliquerProjet(idProjet) {
  var nomProjet = $("#nomProjetInput").val();

  $.ajax({
    url: "./ActionServlet",
    method: "POST",
    data: {
      todo: "dupliquerProjet",
      idProjet: idProjet,
      nomProjet: nomProjet,
    },
    dataType: "json",
  }).done(function (response) {
    // Fonction appelée en cas d'appel AJAX réussi
    console.log("Response", response);

    //on ferme le popup
    popUpNomProjet(false);

    if (response) {
      if (response['Error']) {
        alert("Une erreur est survenue, impossible de duppliquer le projet)");
      }
      else{
        ouvrirProjet(response["newIdProjet"]);
      }
    }
  });
}

//charge le fichier XML se trouvant à l'URL relative donné dans le paramètre et le retourne
function chargerHttpXML(xmlDocumentUrl) {
  var httpAjax;

  httpAjax = window.XMLHttpRequest
    ? new XMLHttpRequest()
    : new ActiveXObject("Microsoft.XMLHTTP");

  if (httpAjax.overrideMimeType) {
    httpAjax.overrideMimeType("text/xml");
  }

  //chargement du fichier XML à l'aide de XMLHttpRequest synchrone (le 3ème paramètre est défini sur false)
  httpAjax.open("GET", xmlDocumentUrl, false);
  httpAjax.send();

  return httpAjax.responseXML;
}

function ouvrirProjet(idProjet) {

  $.ajax({
    url: "./ActionServlet",
    method: "GET",
    data: {
      todo: "ouvrirProjet",
      idProjet: idProjet
    },
    dataType: "json",
  }).done(function (response) {
    // Fonction appelée en cas d'appel AJAX réussi
    console.log("Response", response);
    if(!response.ErrorState) {
      var xslDocumentUrl = "stylesheet/ouvrirProjet.xsl";
      var xmlDocumentUrl = "XMLfiles/" + idProjet + ".xml";

      var xsltProcessor = new XSLTProcessor();

      // Chargement du fichier XSL à l'aide de XMLHttpRequest synchrone
      var xslDocument = chargerHttpXML(xslDocumentUrl);

      // Importation du .xsl
      xsltProcessor.importStylesheet(xslDocument);

      // Chargement du fichier XML à l'aide de XMLHttpRequest synchrone
      var xmlDocument = chargerHttpXML(xmlDocumentUrl);

      // Création du document XML transformé par le XSL
      var newXmlDocument = xsltProcessor.transformToDocument(xmlDocument);

      // Le div permet juste de récupèrer tout le DOM
      var elementAInserer = newXmlDocument.getElementsByTagName("div")[0];

      // On remplace toutes les balises concernant l'ancien projet actuellement existantes par les nouvelles
      $(".container").last().html(elementAInserer.childNodes);

      //On spécifie l'id du projet acutellement ouvert dans la partie infos projet
      $('#idProjetActuel').val(idProjet);

      //On ajoute les infos projet dans la partie correspondante
      $('#nomProjet').children(':first').html(response.nomProjet);
      $('.nomProjetUpdate').val(response.nomProjet);
      if(response.refBRP != null) $('.refBRP').val(response.refBRP);
      if(response.typeMarche != null) $("input[value='" + response.typeMarche + "']").prop("checked",true);
      if(response.typeConstruction != null) $("input[value='" + response.typeConstruction + "']").prop("checked",true);
      if(response.typeLot != null) $("input[value='" + response.typeLot + "']").prop("checked",true);
      if(response.site != null) $("input[value='" + response.site + "']").prop("checked",true);
      if(response.anneeRef != null) $('.datePrixref').val(response.anneeRef);
      if(response.coeffAdapt != null) $('.coeffAdapt').val(response.coeffAdapt);
      if(response.localisationCoeffRaccordement != null) $('#coeffRaccordement').val(response.localisationCoeffRaccordement + "-" + response.valeurCoeffRaccordement);

      //Ajout de l'hover sur toutes les barres d'insertions titre
      addEventsDescriptifs();

      //On affiche par défaut le premier lot
      AfficherOnglet($("#lot_0"));

      //On affiche le bon titre lot
      AfficherTitreLot($("#divTitreLot_0"));

      //On numérote l'arbo
      for (let index = 0; index < $(".lot").length; index++) {
        NumerotationArbo("lot_" + index);
      }
    }
  });
}

function modifierInfosProjet(){

    //on va chercher les infos du projet
    var idProjet = $('#idProjetActuel').val();
    var nomProjet = $('.nomProjetUpdate').val();
    var refBRP = $('.refBRP').val();
    var typeMarche = $('input[name="typeMarche"]:checked').val();
    var typeConstruction = $('input[name="typeConstruction"]:checked').val();
    var typeLot = $('input[name="typeLot"]:checked').val();
    var typeSite = $('input[name="typeSite"]:checked').val();
    var datePrixref = $('.datePrixref').val();
    var coeffAdapt = $('.coeffAdapt').val();
    var idCoeffRaccordement = $('#coeffRaccordement').val();
    var idCategorieConstruction = $('#categorieConstruction').val(); 
    var idSousCategorieConstruction = $('#sousCategorieConstruction').val(); 
    var idCaractDim = $('#caractDim').val();  

    //on formate les données
    if (typeof typeMarche === "undefined") typeMarche = "";
    if (typeof typeConstruction === "undefined") typeConstruction = ""; 
    if (typeof typeLot === "undefined") typeLot = ""; 
    if (typeof typeSite === "undefined") typeSite = ""; 
    if (idCategorieConstruction == "") {
        idSousCategorieConstruction = "";
        idCaractDim = "";
    }

    //on envoie à l'ajax
    $.ajax({
      url: "./ActionServlet",
      method: "POST",
      data: {
        todo: "editerInfosProjet",
        idProjet:idProjet,
        nomProjet:nomProjet,
        refBRP:refBRP,
        typeMarche : typeMarche,
        typeConstruction : typeConstruction,
        typeLot : typeLot,
        typeSite : typeSite,
        datePrixref : datePrixref,
        coeffAdapt : coeffAdapt,
        idCoeffRaccordement : idCoeffRaccordement,
        idCategorieConstruction : idCategorieConstruction,
        idSousCategorieConstruction : idSousCategorieConstruction,
        idCaractDim : idCaractDim,
      },
      dataType: "json",
    })
        .done(function (response) {
          // Fonction appelée en cas d'appel AJAX réussi
          console.log("Response", response);

          if (response) {
            if (response['Error']) {
              alert("Une erreur est survenue lors de la sauvegarde des informations du projet (informations de la colonne de gauche)");
            }
          }
          
    });
}

function unset_select_descriptif() {
  if (!test_select_descriptif) {
    $(".selectDescriptif").removeClass("selectDescriptif");
  }
  test_select_descriptif = false;
}

function select_descriptif(element) {
  $(".selectDescriptif").removeClass("selectDescriptif");
  $(element).addClass("selectDescriptif");
  test_select_descriptif = true;
  //alert($(element).children(".idDescriptif").val());	//affiche l'idDescriptif selectionné
}

function gestion_menu_bdd(panel) {
  if (panel == "biblio") {
    $(".menuBiblio").css("color", "black");
    $(".menuInfos").css("color", "grey");
    $("#arboBDD").show();
    $("#infosProjet").hide();
  }

  if (panel == "infos") {
    $(".menuInfos").css("color", "black");
    $(".menuBiblio").css("color", "grey");
    $("#infosProjet").show();
    $("#arboBDD").hide();
  }
}

function apply_filter() {
  var filtre = $(".filtre").val();
  var array_show = new Array(),
    array_hide = new Array();

  switch (filtre) {
    case "generique":
      array_show = ["generique"];
      array_hide = ["ouvrage", "prestation"];
      break;
    case "ouvrage":
      array_show = ["ouvrage"];
      array_hide = ["generique", "prestation"];
      break;
    case "prestation":
      array_show = ["prestation"];
      array_hide = ["ouvrage", "generique"];
      break;
    default:
      array_show = ["generique", "ouvrage", "prestation"];
      array_hide = [];
  }

  for (var i = 0; i < array_show.length; i++) {
    $("." + array_show[i]).each(function () {
      //on va selectionner le parent
      var getParent = false;
      var beforeElement = this;
      while (getParent == false) {
        beforeElement = $(beforeElement).prev();
        if (!beforeElement.attr("class").includes("lineDescriptif")) {
          var parent = beforeElement;
          getParent = true;
        }
      }

      //si le parent est visible alors on montre
      if ($(parent).is(":visible")) $(this).css("display", "flex");
    });
  }
  for (var i = 0; i < array_hide.length; i++) {
    $("." + array_hide[i]).each(function () {
      $(this).hide();
    });
  }
}

function show_catConstruction() {
  var val = $("#categorieConstruction").val();

  if (val == "") {
    $(".lineSousCatConstruction").hide();
    $(".lineCaractDim").hide();
  } else {
    $(".lineSousCatConstruction").css("display", "flex");
    $(".lineCaractDim").css("display", "flex");
    //On va chercher les sousCategorieConstruction et CaractDim en lien avec la CatConstruction sélectionnée
    var idCategorieConstruction = $("#categorieConstruction").find('option:selected').attr("name");
    $.ajax({
      url: "./ActionServlet",
      method: "GET",
      data: {
        todo: "listerSousCatConstCaractDim",
        idCategorieConstruction: idCategorieConstruction
      },
      dataType: "json",
    }).done(function (response) {
      // Fonction appelée en cas d'appel AJAX réussi
      console.log("Response", response);
      if(!response.ErrorState) {
        $.each(response.listeSousCategorieConstruction, function (i, sousCategorieConstruction) {
          $('#sousCategorieConstruction').append($('<option>', { 
              value: i,
              text : sousCategorieConstruction.intitule,
              name: sousCategorieConstruction.code
          }));
        });
        /*$.each(response.listeCaractDim, function (i, caractDim) { //! Régler Caract Dim plus tard
          $('#sousCategorieConstruction').append($('<option>', { 
              value: i,
              text : caractDim.valeur,
              name: caractDim.code
          }));
        });*/
      }
    });
  }
}

function gestion_arbo_bdd(element) {
  //on range les différents niveaux de l'arborescence dans un tableau
  var ordre_arbo = new Map();
  ordre_arbo.set("lineChapitre", 1);
  ordre_arbo.set("lineCategorie", 2);
  ordre_arbo.set("lineFamille", 3);
  ordre_arbo.set("lineSousFamille", 4);

  //on détermine le rang de l'objet clické
  var classElement = $(element).attr("class").substr(8);
  var rangElement = ordre_arbo.get(classElement);

  //on détermine le type d'opération
  var spanArrow = $(element).children(".iconBDD");
  var classArrow = $(spanArrow).children(".arrow").attr("class");

  if (classArrow.includes("down")) var action = "remonter";
  else var action = "derouler";

  //on modifie la fleche
  if (action == "derouler")
    $(spanArrow).html('<i class="fas fa-caret-down arrow">');
  else $(spanArrow).html('<i class="fas fa-caret-right arrow">');

  //on détermine tous les objets de rangs directement supérieur ou égal à 5
  var oldElement = element;
  var nextElement = null;
  var classNextElement = null;
  var rangNextElement = null;
  var rangOldElement = null;
  var valFilter = $(".filtre").val();
  do {
    nextElement = $(oldElement).next();
    classNextElement = $(nextElement).attr("class").substr(8);
    rangNextElement = ordre_arbo.get(classNextElement);

    if (classNextElement && rangNextElement == null) rangNextElement = 5;

    //on descend dans l'arbo
    if (action == "derouler") {
      if (
        rangNextElement == rangElement + 1 ||
        (rangNextElement == 5 && rangOldElement == rangElement)
      ) {
        if (rangNextElement == 5 && valFilter != "") {
          //si c'est un descriptif et qu'un filtre est demandé, on l'applique
          if (classNextElement.includes(valFilter)) {
            $(nextElement).css("display", "flex");
          }
        } else {
          $(nextElement).css("display", "flex");
        }
      }
    } else {
      //on remonte dans l'arbo
      if (rangNextElement > rangElement) {
        $(nextElement).css("display", "none");
      }
    }

    if (
      rangNextElement != null &&
      rangNextElement > rangElement &&
      rangNextElement != 5
    ) {
      //on modifie la fleche de l'élement enfant
      spanArrow = $(nextElement).children(".iconBDD");
      if (action == "remonter")
        $(spanArrow).html('<i class="fas fa-caret-right arrow">');
    }

    oldElement = nextElement;
    rangOldElement = rangNextElement;
  } while (rangNextElement > rangElement);
}

function display_manage_project() {
  if (!test_manageProjet) {
    $("#manageProjet").css("display", "flex");
    $("#arboBDD").hide();
    $("#menu").hide();
    $("#infosProjet").hide();
    $(".menuGestion").hide();
    $(".nomProjet").last().html('<i class="fas fa-chevron-up">');
    test_manageProjet = true;
  } else {
    $("#manageProjet").hide();
    $("#arboBDD").show();
    $("#menu").show();
    $("#infosProjet").hide();
    $(".menuGestion").show();
    $(".nomProjet").last().html('<i class="fas fa-chevron-down">');
    test_manageProjet = false;
  }
}

/****************** Fonctions (partie droite) *********************/

function addEventsDescriptifs() {
  $(".barreInsertion").mouseenter(function () {
    var element = $(this).children(":first");
    $(element).css("border-bottom", "solid");
    $(element).css("border-width", "1.5px");
    $(element).css("border-color", "rgb(95, 95, 95)");
  });
  $(".barreInsertion").mouseleave(function () {
    var element = $(this).children(":first");
    $(element).css("border-bottom", "none");
  });
}

function AjoutEventSupprLigneChiffrage() {
  $(".descriptif > .ligneChiffrage").first().children().last().html("");
  $(".suppressionLigneChiffrage").click(function () {
    $(this).parent().remove();
  });
}

function AjouterElement(element) {
  //Si un descriptif a été sélectionné alors on l'insère directement dans l'arborescence
  if ($(".selectDescriptif").length) {
    //On n'autorise pas un descriptif à se mettre avant un titre1
    if (
      $(element)
        .next()
        .children(":first")
        .children(":first")
        .html() != "I."
    ) {
      //On créer le div du descriptif avec le style correspondant à sa place dans l'arbo
      var divInsertionDescriptif = document.createElement("div");

      var titreAuDessus = $(element).prev();
      if (!titreAuDessus.hasClass("descriptif")) {
        if (titreAuDessus.hasClass("titre1")) {
          divInsertionDescriptif.className = "descriptif titre2";
        } else if (titreAuDessus.hasClass("titre2")) {
          divInsertionDescriptif.className = "descriptif titre3";
        } else if (titreAuDessus.hasClass("titre3")) {
          divInsertionDescriptif.className = "descriptif titre4";
        } else if (titreAuDessus.hasClass("titre4")) {
          divInsertionDescriptif.className = "descriptif titre5";
        }
      } else {
        var classDescriptifAuDessus = titreAuDessus.attr("class");
        divInsertionDescriptif.className = classDescriptifAuDessus;
      }

      //Appel AJAX pour récupérer les infos du descriptif
      var idDescriptif = $(".selectDescriptif").children(":first").val();
      var unite, description, nomDescriptif;

      $.ajax({
        url: "./ActionServlet",
        method: "GET",
        data: {
          todo: "recupererDescriptif",
          idDescriptif: idDescriptif
        },
        dataType: "json",
      }).done(function (response) {
        // Fonction appelée en cas d'appel AJAX réussi
        console.log("Response", response);
        if (!response.ErrorState) {
          //Récupérer la description (et l'unité si pas générique)
          if(response.typeDescriptif !== "Generique") {
            unite = response.unite;
          }
          descriptionDescriptif = response.descriptionDescriptif;
          nomDescriptif = response.nomDescriptif;

          if (!$(".selectDescriptif").hasClass("generique")) {

            $(divInsertionDescriptif).html("<input type='hidden' class='idDescriptif' value='"+ idDescriptif +"'><div class='input-group'><div class='input-group-prepend'><span class='input-group-text' id='basic-addon1'></span></div><input type='text' class='form-control' placeholder='Ouvrage/Prestation' value='" + nomDescriptif + "'/></div><div class='input-group description'><textarea class='form-control' placeholder='Description' value='" + descriptionDescriptif + "'></textarea></div><div class='ligneChiffrage'><input type='text' class='form-control localisation' placeholder='Localisation'/><input type='text' class='form-control quantite' placeholder='Quantité'/><div class='input-group-prepend'><span class='input-group-text unite'>" + unite + "</span></div></div>");
            //! Rajouter la description stylisée en AJAX
    
            $(divInsertionDescriptif).insertBefore($(element));
    
            //On insère une barre d'insertion au dessus du nouveau descriptif
            var divBarreInsertion = document.createElement("div");
            $(divBarreInsertion).html("<div class='barreInsertion' onclick='AjouterElement(this);'><div class='panBarreInsertion'></div><div class='panBarreInsertion'></div></div>");
            $(divBarreInsertion).insertBefore($(divInsertionDescriptif));
    
            //On ajoute une barre d'insertion ligneChiffrage à la fin de l'ouvrage/prestation
            var divBarreInsertionLigneChiffrage = document.createElement("div");
            $(divBarreInsertionLigneChiffrage).html("<div class='barreInsertionLigneChiffrage' onclick='AjouterLigneChiffrage(this);'><div class='panBarreInsertionLigneChiffrage'></div><div class='panBarreInsertionLigneChiffrage'></div></div>");
            $(divInsertionDescriptif).append($(divBarreInsertionLigneChiffrage));
    
            //Appel de la fonction de numérotation de l'arborescence
            var idLot = $(divInsertionDescriptif).parent().attr("id");
            NumerotationArbo(idLot);
          } else {
            //On insère le générique
            $(divInsertionDescriptif).html("<input type='hidden' class='idDescriptif' value='"+ idDescriptif +"'><div class='input-group'><div class='input-group-prepend'><span class='input-group-text' id='basic-addon1'></span></div><input type='text' class='form-control' placeholder='Générique' value='" + nomDescriptif + "'/></div><div class='input-group description'><textarea class='form-control' placeholder='Description' value='" + descriptionDescriptif + "'></textarea></div>");
            $(divInsertionDescriptif).insertBefore($(element));
            //! Rajouter la description stylisée en AJAX
    
            //On insère une barre d'insertion au dessus du nouveau descriptif
            var divBarreInsertion = document.createElement("div");
            $(divBarreInsertion).html("<div class='barreInsertion' onclick='AjouterElement(this);'><div class='panBarreInsertion'></div><div class='panBarreInsertion'></div></div>");
            $(divBarreInsertion).insertBefore($(divInsertionDescriptif));
    
            //Ajout de l'hover sur toutes les barres d'insertions titre
            addEventsDescriptifs();
    
            //Appel de la fonction de numérotation de l'arborescence
            var idLot = $(divInsertionDescriptif).parent().attr("id");
            NumerotationArbo(idLot);
          }
          //on actualise le xml
          modifierXML();
        }
      });
    }
  } else {
    //Sinon on propose d'insérer un titre du même niveau d'arboresence que le titre d'au-dessus ou de celui d'en-dessous sous réserve d'existence
    var type1 = null;
    var type2 = null;
    var prev = $(element).prev();
    var next = $(element).next();
    if (prev !== null) {
      if (prev.hasClass("titre1")) {
        type1 = "titre1";
      } else if (prev.hasClass("titre2")) {
        type1 = "titre2";
      } else if (prev.hasClass("titre3")) {
        type1 = "titre3";
      } else if (prev.hasClass("titre4")) {
        type1 = "titre4";
      } else if (prev.hasClass("titre5")) {
        type1 = "titre5";
      }
    }
    if (next !== null && next.attr("class") !== "finLot") {
      if (next.hasClass("titre1")) {
        type2 = "titre1";
      } else if (next.hasClass("titre2")) {
        type2 = "titre2";
      } else if (next.hasClass("titre3")) {
        type2 = "titre3";
      } else if (next.hasClass("titre4")) {
        type2 = "titre4";
      } else if (next.hasClass("titre5")) {
        type2 = "titre5";
      }
    } else {
      if (type1 == "titre1") {
        type2 = "titre2";
      } else if (type1 == "titre2") {
        type2 = "titre3";
      } else if (type1 == "titre3") {
        type2 = "titre4";
      } else if (type1 == "titre4") {
        type2 = "titre5";
      }
    }

    //Permet de gérer les cas ou on va proposer deux fois le même type de titre
    //Ou quand il n'y a rien en dessous pour proposer un titre plus bas dans l'arbo
    if (type1 == type2) type2 = null;
    else if (type1 == "titre1" && type2 == null) type2 = "titre2";
    else if (type1 == "titre2" && type2 == null) type2 = "titre3";
    else if (type1 == "titre3" && type2 == null) type2 = "titre4";
    else if (type1 == "titre4" && type2 == null) type2 = "titre5";

    //On affiche un panneau demandant quel type de titre il veut afficher parmis les choix dispo
    var divInsertionTitre = document.createElement("div");
    divInsertionTitre.className = "divInsertionTitre";

    if (type1 !== null) {
      var divType1 = document.createElement("div");
      divType1.className = "sousDivInsertionTitre";
      switch (type1) {
        case "titre1":
          divType1.innerHTML = "Titre 1";
          break;
        case "titre2":
          divType1.innerHTML = "Titre 2";
          break;
        case "titre3":
          divType1.innerHTML = "Titre 3";
          break;
        case "titre4":
          divType1.innerHTML = "Titre 4";
          break;
        case "titre5":
          divType1.innerHTML = "Titre 5";
          break;
        default:
          break;
      }
      divInsertionTitre.appendChild(divType1);
    }
    if (type2 !== null) {
      var divType2 = document.createElement("div");
      divType2.className = "sousDivInsertionTitre";
      switch (type2) {
        case "titre1":
          divType2.innerHTML = "Titre 1";
          break;
        case "titre2":
          divType2.innerHTML = "Titre 2";
          break;
        case "titre3":
          divType2.innerHTML = "Titre 3";
          break;
        case "titre4":
          divType2.innerHTML = "Titre 4";
          break;
        case "titre5":
          divType2.innerHTML = "Titre 5";
          break;
        default:
          break;
      }
      divInsertionTitre.appendChild(divType2);
    }

    if (type1 == null || type2 == null) divInsertionTitre.style.height = "25px";

    $(divInsertionTitre).insertBefore($(element));

    $(".sousDivInsertionTitre").click(function () {
      AjouterTitre(this);
    });

    testChoixPresent = true;
  }
}

function SuppressionChoixInsertionTitre() {
  if (!testChoixPresent) $(".divInsertionTitre").remove();
  testChoixPresent = false;
}

function AjouterTitre(evt) {
  var titleClassName = evt.innerHTML.replace(" ", "").toLowerCase();
  //On ajoute le titre avec le bon style
  var divNewTitle = $("<div class='input-group " + titleClassName + "'><div class='input-group-prepend'><span class='input-group-text' id='basic-addon1'></span></div><input type='text' class='form-control' placeholder='" + evt.innerHTML + "'/></div>");
  $(divNewTitle).insertBefore($(evt).parent());

  //On insère une barre d'insertion au-dessus du nouveau titre
  $("<div class='barreInsertion' onclick='AjouterElement(this);'><div class='panBarreInsertion'></div><div class='panBarreInsertion'></div></div>").insertBefore($(divNewTitle));

  //Ajout de l'hover sur toutes les barres d'insertions titre
  addEventsDescriptifs();

  //On supprime le panneau de choix des titres
  $(".divInsertionTitre").remove();

  //Appel de la fonction de numérotation de l'arborescence
  var idLot = $(divNewTitle).parent().attr("id");
  NumerotationArbo(idLot);
  //on actualise le xml
  modifierXML();
}

function NumerotationArbo(idOnglet) {
  var numTitre1 = 0;
  var numTitre2 = 0;
  var numTitre3 = 0;
  var numTitre4 = 0;
  var numTitre5 = 0;
  $(".input-group").each(function () {
    if (
      !$(this).hasClass("description") &&
      !$(this).hasClass("ligneChiffrage") &&
      ($(this).parent().attr("id") == idOnglet ||
        $(this).parent().parent().attr("id") == idOnglet)
    ) {
      if ($(this).parent().hasClass("descriptif")) {
        if ($(this).parent().hasClass("titre1")) {
          var numeroTitre = $(this).children(":first").children(":first"); //On récupère le span du chiffre romain
          numTitre1++;
          numeroTitre.html(toRoman(numTitre1) + ".");
          numTitre2 = 0;
          numTitre3 = 0;
          numTitre4 = 0;
          numTitre5 = 0;
        } else if ($(this).parent().hasClass("titre2")) {
          var numeroTitre = $(this).children(":first").children(":first"); //On récupère le span du chiffre romain
          numTitre2++;
          numeroTitre.html(toRoman(numTitre1) + "." + numTitre2);
          numTitre3 = 0;
          numTitre4 = 0;
          numTitre5 = 0;
        } else if ($(this).parent().hasClass("titre3")) {
          var numeroTitre = $(this).children(":first").children(":first"); //On récupère le span du chiffre romain
          numTitre3++;
          numeroTitre.html(numTitre2 + "." + numTitre3);
          numTitre4 = 0;
          numTitre5 = 0;
        } else if ($(this).parent().hasClass("titre4")) {
          var numeroTitre = $(this).children(":first").children(":first"); //On récupère le span du chiffre romain
          numTitre4++;
          numeroTitre.html(numTitre2 + "." + numTitre3 + "." + numTitre4);
          numTitre5 = 0;
        } else if ($(this).parent().hasClass("titre5")) {
          var numeroTitre = $(this).children(":first").children(":first"); //On récupère le span du chiffre romain
          numTitre5++;
          numeroTitre.html(
            numTitre2 + "." + numTitre3 + "." + numTitre4 + "." + numTitre5
          );
        }
      } else {
        if ($(this).hasClass("titre1")) {
          var numeroTitre = $(this).children(":first").children(":first"); //On récupère le span du chiffre romain
          numTitre1++;
          numeroTitre.html(toRoman(numTitre1) + ".");
          numTitre2 = 0;
          numTitre3 = 0;
          numTitre4 = 0;
          numTitre5 = 0;
        } else if ($(this).hasClass("titre2")) {
          var numeroTitre = $(this).children(":first").children(":first"); //On récupère le span du chiffre romain
          numTitre2++;
          numeroTitre.html(toRoman(numTitre1) + "." + numTitre2);
          numTitre3 = 0;
          numTitre4 = 0;
          numTitre5 = 0;
        } else if ($(this).hasClass("titre3")) {
          var numeroTitre = $(this).children(":first").children(":first"); //On récupère le span du chiffre romain
          numTitre3++;
          numeroTitre.html(numTitre2 + "." + numTitre3);
          numTitre4 = 0;
          numTitre5 = 0;
        } else if ($(this).hasClass("titre4")) {
          var numeroTitre = $(this).children(":first").children(":first"); //On récupère le span du chiffre romain
          numTitre4++;
          numeroTitre.html(numTitre2 + "." + numTitre3 + "." + numTitre4);
          numTitre5 = 0;
        } else if ($(this).hasClass("titre5")) {
          var numeroTitre = $(this).children(":first").children(":first"); //On récupère le span du chiffre romain
          numTitre5++;
          numeroTitre.html(
            numTitre2 + "." + numTitre3 + "." + numTitre4 + "." + numTitre5
          );
        }
      }
    }
  });
}

function AfficherOnglet(lotAfficher) {
  $(".lot").hide();
  lotAfficher.show();
  var numBouton = lotAfficher.attr("id").slice(-1); //On récupère le numéro de l'onglet qui doit être mis en couleur plus foncée
  $(".ongletLot").css("background-color", "#3a88c5");
  if (numBouton != "+") {
    $(".ongletLot:nth-child(" + (Number(numBouton) + 1) + ")").css(
      "background-color",
      "#0070c9"
    );
  }
  //on affiche le titre du lot
  AfficherTitreLot($("#divTitreLot_" + numBouton));
}

function AfficherTitreLot(divTitreLotAfficher) {
  $(".divTitreLot").hide();
  divTitreLotAfficher.show();
}

function CreerOnglet() {
  //Création du div contenant tout le lot (on l'affiche par défaut)
  var numOnglet = Number($("#ongletsLot").children().last().prev().html()) + 1;
  var idOnglet = "lot_" + numOnglet;

  var divNouvelOnglet = $("<div class='lot' id='" + idOnglet + "'><div class='barreInsertion' onclick='AjouterElement(this);'><div class='panBarreInsertion'></div><div class='panBarreInsertion'></div></div><div class='input-group titre1'><div class='input-group-prepend'><span class='input-group-text'>I.</span></div><input type='text' class='form-control' placeholder='Titre 1'/></div><div class='barreInsertion' onclick='AjouterElement(this);'><div class='panBarreInsertion'></div><div class='panBarreInsertion'></div></div><div class='finLot'></div></div>");
  $(divNouvelOnglet).insertBefore($("#ongletsLot"));

  //Création de deux barres d'insertion d'un titre1 et d'une fin de lot dans ce nouvel onglet
  var divBoutonOnglet = $("<div class='ongletLot' onclick='AfficherOnglet($(\"#" + idOnglet + "\"));'>"+ numOnglet + "</div>");
  $(divBoutonOnglet).insertBefore($(".ongletLot").last());

  //Création d'un nouveau input de titre lot
  $("<div class='divTitreLot' id='divTitreLot_" + numOnglet + "'><input type='text' class='titreLot' placeholder='Titre Lot' /></div>").insertBefore($(".lot").first());

  //Ajout de l'hover sur toutes les barres d'insertions titre
  addEventsDescriptifs();

  //On affiche par défaut
  AfficherOnglet($("#" + idOnglet));

  //On affiche le bon titre lot
  AfficherTitreLot($("#divTitreLot_" + numOnglet));

  //On numérote l'unique titre
  NumerotationArbo(idOnglet);

  //on actualise le xml
  modifierXML();
}

function toRoman(num) {
  if (isNaN(num)) return NaN;
  var digits = String(+num).split(""),
    key = [
      "",
      "C",
      "CC",
      "CCC",
      "CD",
      "D",
      "DC",
      "DCC",
      "DCCC",
      "CM",
      "",
      "X",
      "XX",
      "XXX",
      "XL",
      "L",
      "LX",
      "LXX",
      "LXXX",
      "XC",
      "",
      "I",
      "II",
      "III",
      "IV",
      "V",
      "VI",
      "VII",
      "VIII",
      "IX",
    ],
    roman = "",
    i = 3;
  while (i--) roman = (key[+digits.pop() + i * 10] || "") + roman;
  return Array(+digits.join("") + 1).join("M") + roman;
}

function AjouterLigneChiffrage(element) {
  //Création d'une nouvelle ligneChiffrage au dessus de la barre d'insertion ligneChiffrage
  var divBarreInsertionLigneChiffrage = $("<div class='ligneChiffrage'><input type='text' class='form-control' placeholder='Localisation'/><input type='text' class='form-control' placeholder='Quantité'/><div class='input-group-prepend'><span class='input-group-text'></span></div><div class='suppressionLigneChiffrage'><i class='fas fa-times'></i></div></div>");
  //! Appel AJAX pour rajouter l'unité dans le div juste inséré
  $(divBarreInsertionLigneChiffrage).insertBefore($(element));

  //Ajout évènement suppression ligne chiffrage
  AjoutEventSupprLigneChiffrage();
}

function modifierXML(){

  var xmlDatas = Array();
  var i = 0;
  var j = 0;
  var classElement = null;
  var containerDescription = null;
  var localisation = null;
  var quantite = null;
  var firstLigneChiffrage = true;
  
  //pour chaque lot, on reconstitue l'arborescence
  $('.divTitreLot').each(function () {

    //on crée le tableau secondaire
    xmlDatas[i] = Array();
     
    //on enregistre le nom du lot
    xmlDatas[i]['type'] = 'lot';
    xmlDatas[i]['intitule'] = $(this).children('.titreLot').val();
    idLot = $(this).attr("id").substr(12);
    i++;

    //on va chercher les enfants du lot
    $('#lot_'+idLot).children().each(function () {
      classElement = $(this).attr("class");
      
      //on verifie qu'on est dans un titre ou un descritpif
      if (typeof(classElement) != 'undefined' && classElement != "barreInsertion" && classElement != "finLot") {
        //on crée le tableau secondaire
        xmlDatas[i] = Array();

        //on enregistre son echelon
        if (classElement.includes("titre1")) 
            xmlDatas[i]['type'] = 'titre1';
        if (classElement.includes("titre2")) 
          xmlDatas[i]['type'] = 'titre2';
        if (classElement.includes("titre3")) 
          xmlDatas[i]['type'] = 'titre3';
        if (classElement.includes("titre4")) 
          xmlDatas[i]['type'] = 'titre4';
        if (classElement.includes("titre5")) 
          xmlDatas[i]['type'] = 'titre5';

        //si l'élement est un titre
        if (!classElement.includes("descriptif")) {
          xmlDatas[i]['intitule'] = $(this).children('input').val();
        }
        //sinon c'est un descriptif
        else{
          xmlDatas[i]['idDescriptif'] = $(this).children('.idDescriptif').val();
          xmlDatas[i]['nomDescriptif'] = $(this).find('.nomDescriptif').val();
          xmlDatas[i]['description'] = $(this).find('textarea').val();

          //on va chercher les lignes chiffrage s'il y en a 
          $(this).find(".ligneChiffrage").each(function(){
            localisation = $(this).find(".localisation").val();
            quantite = $(this).find(".quantite").val();
            //si la ligne chiffrage comporte une info, on l'enregistre
            if (!(localisation == "" && quantite == "")) {

              if (firstLigneChiffrage) {
                xmlDatas[i]['ligneChiffrage'] = Array();
                firstLigneChiffrage = false;
              }

              xmlDatas[i]['ligneChiffrage'][j] = Array();
              xmlDatas[i]['ligneChiffrage'][j]['localisation'] = localisation;
              xmlDatas[i]['ligneChiffrage'][j]['quantite'] = quantite;
              xmlDatas[i]['ligneChiffrage'][j]['unite'] = $(this).find(".unite").text();
              j++;
            }
          });
          j = 0;
        }
        firstLigneChiffrage = true;
        i++;
      }
    });
  });

  console.log(xmlDatas);

  //une fois qu'on a rassemblé les informations de manière ordonnée, on fait l'appel AJAX
  // var idProjet = $(element).children("#idProjetActuel").val();

  // $.ajax({
  //   url: "./ActionServlet",
  //   method: "POST",
  //   data: {
  //     todo: "modifierXML",
  //     idProjet:idProjet,
  //     xmlDatas:xmlDatas
  //   },
  //   dataType: "json",
  // }).done(function (response) {
  //   // Fonction appelée en cas d'appel AJAX réussi
  //   console.log("Response", response);
  //   if(response.ErrorState){
  //     alert("Un problème est survenu lors de la sauvegarde des données du projet. Rafraichir la page peut vous aider à l'identifier");
  //   }
  // });
}































