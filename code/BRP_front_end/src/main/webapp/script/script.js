/*************** Variables globales ******************/

test_select_descriptif = false; //permet de ne pas supprimer la selection d'un element à peine faite
test_manageProjet = false;
deplacementDescriptif = false;
var testChoixPresent = false;
listeProjets = Array();

/************** Appels au chargement de la page ******************/
$(document).ready(function () {
  //Affichage Admin ou opérateur classique
  $.ajax({
    url: "./ActionServlet",
    method: "GET",
    data: {
      todo: "affichageAdmin",
    },
    dataType: "json",
  }).done(function (response) {
    // Fonction appelée en cas d'appel AJAX réussi
    //console.log("Response", response);
    if (!response.ErrorState) {
      if (response.isAdmin) {
        //Si admin alors on affiche le bouton de création de compte Opérateur
        $(".creationCompte").show();
      }
    }
    // else {
    //   window.location.href = "index.html";
    // }
  });

  //Lister coeff raccordements et cat construction dans infos projet
  $.ajax({
    url: "./ActionServlet",
    method: "GET",
    data: {
      todo: "listerRaccordementCatConst",
    },
    dataType: "json",
  }).done(function (response) {
    // Fonction appelée en cas d'appel AJAX réussi
    //console.log("Response", response);
    if (!response.ErrorState) {
      $.each(response.listeCoeffRaccordement, function (i, coeffRaccordement) {
        //console.log(coeffRaccordement);
        $("#coeffRaccordement").append(
          $("<option>", {
            value: coeffRaccordement.id,
            text:
              coeffRaccordement.localisation + " - " + coeffRaccordement.valeur,
            name: coeffRaccordement.localisation,
          })
        );
      });
      $.each(response.listeCategorieConstruction, function (
        i,
        categorieConstruction
      ) {
        $("#categorieConstruction").append(
          $("<option>", {
            value: categorieConstruction.code,
            text:
              categorieConstruction.code +
              " - " +
              categorieConstruction.intitule,
            name: categorieConstruction.code,
          })
        );
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
      //console.log("Response", response); // LOG dans Console Javascript
      if (!response.Error) {
        //On insère l'arbo dans le HTML
        for (let i = 0; i < response.arborescence.length; i++) {
          const chapitre = response.arborescence[i];
          $("#arboBDD").append(
            "<div class='lineBDD lineChapitre'><span class='iconBDD'><i class='fas fa-caret-right arrow'></i></span><div class='intitule'>" +
              chapitre.idChapitre +
              " - " +
              chapitre.intituleChapitre +
              "</div></div>"
          );
          for (let j = 0; j < chapitre.categories.length; j++) {
            const categorie = chapitre.categories[j];
            $("#arboBDD").append(
              "<div class='lineBDD lineCategorie'><span class='iconBDD'><i class='fas fa-caret-right arrow'></i></span><div class='intitule'>" +
                categorie.idCategorie.substr(3, 4) +
                " - " +
                categorie.intituleCategorie +
                "</div></div>"
            );
            for (let k = 0; k < categorie.familles.length; k++) {
              const famille = categorie.familles[k];
              $("#arboBDD").append(
                "<div class='lineBDD lineFamille'><span class='iconBDD'><i class='fas fa-caret-right arrow'></i></span><div class='intitule'>" +
                  famille.idFamille.substr(7, 3) +
                  " - " +
                  famille.intituleFamille +
                  "</div></div>"
              );
              for (let l = 0; l < famille.sousFamilles.length; l++) {
                const sousFamille = famille.sousFamilles[l];
                $("#arboBDD").append(
                  "<div class='lineBDD lineSousFamille'><span class='iconBDD'><i class='fas fa-caret-right arrow'></i></span><div class='intitule'>" +
                    sousFamille.idSousFamille.substr(10, 13) +
                    " - " +
                    sousFamille.intituleSousFamille +
                    "</div></div>"
                );
                for (let m = 0; m < sousFamille.descriptifs.length; m++) {
                  const descriptif = sousFamille.descriptifs[m];
                  if (descriptif.type == "generique") {
                    $("#arboBDD").append(
                      "<div class='lineBDD lineDescriptif generique'><input type='hidden' class='idDescriptif' value='" +
                        descriptif.id +
                        "'/><span class='iconBDD'>-</span><div class='intitule'>" +
                        descriptif.id.substr(13, 17) +
                        " - " +
                        descriptif.nom +
                        "</div></div>"
                    );
                  } else if (descriptif.type == "ouvrage") {
                    $("#arboBDD").append(
                      "<div class='lineBDD lineDescriptif ouvrage'><input type='hidden' class='idDescriptif' value='" +
                        descriptif.id +
                        "'/><span class='iconBDD'>-</span><div class='intitule'>" +
                        descriptif.id.substr(13, 17) +
                        " - " +
                        descriptif.nom +
                        "</div></div>"
                    );
                    for (n = 0; n < descriptif.prestations.length; n++) {
                      const prestation = descriptif.prestations[n];
                      $("#arboBDD").append(
                        "<div class='lineBDD lineDescriptif prestation'><input type='hidden' class='idDescriptif' value='" +
                          prestation.id +
                          "'/><span class='iconBDD'>-</span><div class='intitule'>" +
                          prestation.id.substr(17, 21) +
                          " - " +
                          prestation.nom +
                          "</div></div>"
                      );
                    }
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
      // console.log(
      //   "Erreur lors de l'appel AJAX pour recuperer l'arborescence BDD"
      // );
    });

  $(".container").first().click(unset_select_descriptif);
  $(".container").last().click(SuppressionChoixInsertionTitre);
  $(".container").last().click(removeSelectDescriptifXML);
  addEventsDescriptifs();

  //à ajouter une fois lorsque l'utilisateur cherche un projet
  $(".textLineProjet").click(function () {
    clicProjet($(this).parent());
  });

  getProjets();
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
    //console.log("Response", response);

    //on ferme le popup
    popUpNomProjet(false);

    if (response["Error"]) {
      alert("Une erreur est survenue, impossible de créer un nouveau projet");
    } else {
      ouvrirProjet(response["idProjet"]);
    }
  });
}

function searchProjet() {
  var morceauNom = $("#search_input").val();
  $("#containerLineProjet").html("");
  for (var i = 0; i < listeProjets.length; i++) {
    //si le morceau du nom se trouve bien dans le nom du projet, on l'affiche
    if (listeProjets[i]["nom"].includes(morceauNom)) {
      $("#containerLineProjet").append(
        '<div class="lineProjet" onclick="ouvrirProjet(' +
          listeProjets[i]["id"] +
          ')">\
              <div class="textLineProjet">\
                <div class="iconProjet"><i class="far fa-file-alt"></i></div>\
                <div class="propositionNomProjet">' +
          listeProjets[i]["nom"] +
          '</div>\
              </div>\
              <select class="actionProjet">\
                <option value="ouvrir">Ouvrir</option>\
                <option value="dupliquer">Dupliquer</option>\
              </select>\
            </div>'
      );
    }
  }
}

function getProjets() {
  $.ajax({
    url: "./ActionServlet",
    method: "POST",
    data: {
      todo: "chercherProjet",
    },
    dataType: "json",
  }).done(function (response) {
    // Fonction appelée en cas d'appel AJAX réussi
    //console.log("Response", response);
    if (!response["Error"]) {
      listeProjets = response["listeProjets"];
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
    //console.log("Response", response);

    //on ferme le popup
    popUpNomProjet(false);

    if (response) {
      if (response["Error"]) {
        alert("Une erreur est survenue, impossible de duppliquer le projet)");
      } else {
        ouvrirProjet(response["newIdProjet"]);
      }
    }
  });
}

function importFile() {
  var name = $("#importFileInput").val().substr(12);
  var operation = null;
  if (name.includes(".docx")) {
    operation = "ModifBaseDescriptif";
  }
  if (name.includes(".csv")) {
    operation = "ModifBasePrixRef";
  }

  $.ajax({
    url: "./ActionServlet",
    method: "POST",
    data: {
      todo: "import",
      name: name,
      operation: operation,
    },
    dataType: "json",
  }).done(function (response) {
    //on demande l'accord à l'utilisateur
    if (response["Explication"].includes("/")) {
      gestionSupprComplexes(response["Explication"]);
    } else {
      alert(response["Explication"]);
      window.location.href = window.location.href;
    }
  });
}

function gestionSupprComplexes(listeObjets) {
  var objetAtraiter = null;
  var erreurOccured = false;

  while (listeObjets != "") {
    objetAtraiter = listeObjets.substr(0, listeObjets.indexOf(")") + 1);
    listeObjets = listeObjets.substr(listeObjets.indexOf(")") + 1);

    if (
      confirm(
        "En supprimant l'objet " +
          objetAtraiter.substr(1, objetAtraiter.indexOf("/") - 1) +
          ", vous supprimerez " +
          objetAtraiter.substr(
            objetAtraiter.indexOf("/") + 1,
            objetAtraiter.length - 2 - objetAtraiter.indexOf("/")
          ) +
          " descriptifs"
      )
    ) {
      $.ajax({
        url: "./ActionServlet",
        method: "POST",
        data: {
          todo: "supprComplexe",
          id: objetAtraiter.substr(1, objetAtraiter.indexOf("/") - 1),
        },
        dataType: "json",
      }).done(function (response) {
        if (response.Error) {
          alert(response["Explication"]);
          erreurOccured = true;
        }
        //console.log(response);
      });
    } else {
      alert(
        "L'objet " +
          objetAtraiter.substr(1, objetAtraiter.indexOf("/") - 1) +
          " n'a pas été supprimé"
      );
    }
  }

  if (!erreurOccured) {
    alert(
      "Import effectué avec succès. Vous trouverez l'Excel lié aux objets importés dans le dossier des imports"
    );
    window.location.href = window.location.href; //!
  }
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
      idProjet: idProjet,
    },
    dataType: "json",
  }).done(function (response) {
    // Fonction appelée en cas d'appel AJAX réussi
    if (!response.ErrorState) {
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
      $("#idProjetActuel").val(idProjet);

      //On ajoute les infos projet dans la partie correspondante
      $("#nomProjet").children(":first").html(response.nomProjet);
      $(".nomProjetUpdate").val(response.nomProjet);
      if (response.refBRP != null) $(".refBRP").val(response.refBRP);
      if (response.typeMarche != null)
        $("input[value='" + response.typeMarche + "']").prop("checked", true);
      if (response.typeConstruction != null)
        $("input[value='" + response.typeConstruction + "']").prop(
          "checked",
          true
        );
      if (response.typeLot != null)
        $("input[value='" + response.typeLot + "']").prop("checked", true);
      if (response.site != null)
        $("input[value='" + response.site + "']").prop("checked", true);
      if (response.anneeRef != null) $(".datePrixref").val(response.anneeRef);
      if (response.coeffAdapt != null)
        $(".coeffAdapt").val(response.coeffAdapt);
      if (response.localisationCoeffRaccordement != null)
        $("#coeffRaccordement").val(
          response.localisationCoeffRaccordement +
            "-" +
            response.valeurCoeffRaccordement
        );

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

      //on attache l'evt pour modifier le xml
      attacheEventModifXML();

      //on actualise le xml
      var newOnglet = $("#divTitreLot_0");
      var premierTitre = $(".titre1").last();
      modifierXML(newOnglet); //!On donne les bons éléments ?
      modifierXML(premierTitre);
    }
  });
}

function modifierInfosProjet() {
  //on va chercher les infos du projet
  var idProjet = $("#idProjetActuel").val();

  if (idProjet != "") {
    var nomProjet = $(".nomProjetUpdate").val();
    var refBRP = $(".refBRP").val();
    var typeMarche = $('input[name="typeMarche"]:checked').val();
    var typeConstruction = $('input[name="typeConstruction"]:checked').val();
    var typeLot = $('input[name="typeLot"]:checked').val();
    var typeSite = $('input[name="typeSite"]:checked').val();
    var datePrixref = $(".datePrixref").val();
    var coeffAdapt = $(".coeffAdapt").val();
    var idCoeffRaccordement = $("#coeffRaccordement").val();
    var idCategorieConstruction = $("#categorieConstruction").val();
    var idSousCategorieConstruction = $("#sousCategorieConstruction").val();
    var idCaractDim = $("#caractDim").val();

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
        idProjet: idProjet,
        nomProjet: nomProjet,
        refBRP: refBRP,
        typeMarche: typeMarche,
        typeConstruction: typeConstruction,
        typeLot: typeLot,
        typeSite: typeSite,
        datePrixref: datePrixref,
        coeffAdapt: coeffAdapt,
        idCoeffRaccordement: idCoeffRaccordement,
        idCategorieConstruction: idCategorieConstruction,
        idSousCategorieConstruction: idSousCategorieConstruction,
        idCaractDim: idCaractDim,
      },
      dataType: "json",
    }).done(function (response) {
      // Fonction appelée en cas d'appel AJAX réussi
      //console.log("Response", response);
      if (response) {
        if (response["Error"]) {
          alert(
            "Une erreur est survenue lors de la sauvegarde des informations du projet (informations de la colonne de gauche)"
          );
        }
      }
    });
  }
}

function unset_select_descriptif() {
  if (!test_select_descriptif) {
    $(".selectDescriptif").removeClass("selectDescriptif");
  }
  test_select_descriptif = false;
}

function select_descriptif(element) {
  $(".selectDescriptif").removeClass("selectDescriptif");
  $(".selectDescriptifXML").css("background-color", "white");
  $(".selectDescriptifXML").removeClass("selectDescriptifXML");
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
    var idCategorieConstruction = $("#categorieConstruction")
      .find("option:selected")
      .attr("name");
    $.ajax({
      url: "./ActionServlet",
      method: "GET",
      data: {
        todo: "listerSousCatConstCaractDim",
        idCategorieConstruction: idCategorieConstruction,
      },
      dataType: "json",
    }).done(function (response) {
      // Fonction appelée en cas d'appel AJAX réussi
      //console.log("Response", response);
      if (!response.ErrorState) {
        $.each(response.listeSousCategorieConstruction, function (
          i,
          sousCategorieConstruction
        ) {
          $("#sousCategorieConstruction").append(
            $("<option>", {
              value: sousCategorieConstruction.id,
              text: sousCategorieConstruction.intitule,
              name: sousCategorieConstruction.id,
            })
          );
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
    classNextElement = $(nextElement).attr("class");
    if (classNextElement !== undefined) {
      classNextElement = classNextElement.substr(8);
    } else {
      classNextElement = null;
    }
    rangNextElement = ordre_arbo.get(classNextElement);

    if (true) {
    }
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

function GenererLivrable() {
  var idProjet = $("#idProjetActuel").val();
  var uriXML =
    "../../../../../../../../Projets/ETIC/Etude_BRP/code/BRP_front_end/src/main/webapp/XMLfiles/" +
    idProjet +
    ".xml"; //Fonctionne sur l'installation de Louis
  var choixTemplate = 1;

  $.ajax({
    url: "./ActionServlet",
    method: "GET",
    data: {
      todo: "genererLivrable",
      idProjet: idProjet,
      choixTemplate: choixTemplate,
      uriXML: uriXML,
    },
    dataType: "json",
  }).done(function (response) {
    // Fonction appelée en cas d'appel AJAX réussi
    //console.log("Response", response);
    if (!response.ErrorState) {
      //Si l'export à réussi on prévient l'opérateur
      alert("La génération du livrable a réussie");
    } else {
      //On previent l'opérateur que l'export a échoué
      alert("Erreur lors de la génération du livrable");
    }
  });
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

//Propose d'insérer le futur titre ou descptif selon les styles (titre1, titre2, ...) possibles
function AjouterElement(element) {
  //Si un descriptif a été sélectionné alors on l'insère directement dans l'arborescence
  if ($(".selectDescriptif").length || $(".selectDescriptifXML").length) {
    deplacementDescriptif = true;

    var descriptifClass = null;
    if ($(".selectDescriptif").length) {
      descriptifClass = ".selectDescriptif";
    } else {
      descriptifClass = ".selectDescriptifXML";
    }
    //On n'autorise pas un descriptif à se mettre avant un titre1
    if (
      $(element).next().children(":first").children(":first").html() != "I."
    ) {
      var styleDescriptif = null; //On cherche le style qui est juste au dessus de l'endroit où on veut insérer : on propose ensuite tous les choix du style en question juqu'à titre1
      var prev = $(element).prev();
      while (styleDescriptif == null) {
        if (prev.hasClass("titre1")) {
          styleDescriptif = "titre1";
        } else if (prev.hasClass("titre2")) {
          //Si le descriptif est un générique ou un ouvrage, on empêche de pouvoir le mettre sous un autre descriptif
          if (
            $(descriptifClass).first().hasClass("ouvrage") ||
            $(descriptifClass).first().hasClass("generique")
          ) {
            if (prev.hasClass("descriptif")) styleDescriptif = "titre1";
            else styleDescriptif = "titre2";
          } else {
            if (
              prev.hasClass("descriptif") &&
              !prev.children().eq(2).val() == "ouvrage"
            )
              styleDescriptif = "titre1";
            else styleDescriptif = "titre2";
          }
        } else if (prev.hasClass("titre3")) {
          //Si le descriptif est un générique ou un ouvrage, on empêche de pouvoir le mettre sous un autre descriptif
          if (
            $(descriptifClass).first().hasClass("ouvrage") ||
            $(descriptifClass).first().hasClass("generique")
          ) {
            if (prev.hasClass("descriptif")) styleDescriptif = "titre2";
            else styleDescriptif = "titre3";
          } else {
            if (
              prev.hasClass("descriptif") &&
              !prev.children().eq(2).val() == "ouvrage"
            )
              styleDescriptif = "titre2";
            else styleDescriptif = "titre3";
          }
        } else if (prev.hasClass("titre4")) {
          //Si le descriptif est un générique ou un ouvrage, on empêche de pouvoir le mettre sous un autre descriptif
          if (
            $(descriptifClass).first().hasClass("ouvrage") ||
            $(descriptifClass).first().hasClass("generique")
          ) {
            if (prev.hasClass("descriptif")) styleDescriptif = "titre3";
            else styleDescriptif = "titre4";
          } else {
            if (
              prev.hasClass("descriptif") &&
              !prev.children().eq(2).val() == "ouvrage"
            )
              styleDescriptif = "titre3";
            else styleDescriptif = "titre4";
          }
        }
        prev = $(prev).prev();
      }

      //On affiche un panneau demandant la position à laquelle l'utilisateur veut afficher le descriptif parmis les choix possibles
      var divInsertionStyleDescriptif = document.createElement("div");
      divInsertionStyleDescriptif.className = "divInsertionStyleDescriptif";

      if (styleDescriptif == "titre1") {
        var sousDivInsertionStyleDescriptif = $(
          "<div class='sousDivInsertionStyleDescriptif'>Titre 2</div>"
        );
        $(divInsertionStyleDescriptif).append(sousDivInsertionStyleDescriptif);
      } else if (styleDescriptif == "titre2") {
        var sousDivInsertionStyleDescriptif = $(
          "<div class='sousDivInsertionStyleDescriptif'>Titre 2</div><div class='sousDivInsertionStyleDescriptif'>Titre 3</div>"
        );
        $(divInsertionStyleDescriptif).append(sousDivInsertionStyleDescriptif);
      } else if (styleDescriptif == "titre3") {
        var sousDivInsertionStyleDescriptif = $(
          "<div class='sousDivInsertionStyleDescriptif'>Titre 2</div><div class='sousDivInsertionStyleDescriptif'>Titre 3</div><div class='sousDivInsertionStyleDescriptif'>Titre 4</div>"
        );
        $(divInsertionStyleDescriptif).append(sousDivInsertionStyleDescriptif);
      } else if (styleDescriptif == "titre4") {
        var sousDivInsertionStyleDescriptif = $(
          "<div class='sousDivInsertionStyleDescriptif'>Titre 2</div><div class='sousDivInsertionStyleDescriptif'>Titre 3</div><div class='sousDivInsertionStyleDescriptif'>Titre 4</div>"
        );
        $(divInsertionStyleDescriptif).append(sousDivInsertionStyleDescriptif);
      }

      $(divInsertionStyleDescriptif).insertBefore($(element));

      $(".sousDivInsertionStyleDescriptif").click(function () {
        AjouterDescriptif(this);
      });

      testChoixPresent = true;
    }
  } else {
    //Sinon on propose d'insérer un titre du même niveau d'arboresence que le titre d'au-dessus ou de celui d'en-dessous sous réserve d'existence
    var type1 = null;
    var type2 = null;
    var type3 = null;
    var type4 = null;
    var prev = $(element).prev();
    var next = $(element).next();
    if (prev !== null) {
      if (prev.hasClass("titre1")) {
        type1 = "titre1";
      } else if (prev.hasClass("titre2")) {
        if (prev.hasClass("descriptif")) type1 = "titre1";
        else type1 = "titre2";
      } else if (prev.hasClass("titre3")) {
        if (prev.hasClass("descriptif")) type1 = "titre2";
        else type1 = "titre3";
      } else if (prev.hasClass("titre4")) {
        if (prev.hasClass("descriptif")) type1 = "titre3";
        else type1 = "titre4";
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
      }
    }

    //Permet de gérer les cas ou on va proposer deux fois le même type de titre
    //Ou quand il n'y a rien en dessous pour proposer un titre plus bas dans l'arbo
    /*if (type1 == type2) type2 = null;
    if (type1 == "titre1" && type2 == null) type2 = "titre2";
    else if (type1 == "titre2" && type2 == null) {
      type2 = "titre3";
      type3 = "titre1";
    } else if (type1 == "titre3" && type2 == null) {
      type2 = "titre4";
      type3 = "titre2";
      type4 = "titre1";
    } else if (type1 == "titre4" && type2 == null) {
      type2 = "titre3";
      type3 = "titre2";
      type4 = "titre1";
    }*/

    if (type1.localeCompare(type2) == -1) {
      //Si on descend alors on ne peut que rester au niveau de type2 (sinon on casse la chaine père-fils)
      type1 = null;
    } else if (type1.localeCompare(type2) == 0) {
      //Si on est entre deux titres de même niveau on peut soit rester au même niveau soit descendre
      if (type1 == "titre1") type2 = "titre2";
      else if (type1 == "titre2") type2 = "titre3";
      else if (type1 == "titre3") type2 = "titre4";
      else type2 = null;
    } else if (type2 == null) {
      //alors type2 est la fin du div donc on propose des choix allant de type1 à en dessous dans l'arbo
      if (type1 == "titre1") type2 = "titre2";
      else if (type1 == "titre2") {
        type2 = "titre3";
        type3 = "titre1";
      } else if (type1 == "titre3") {
        type2 = "titre4";
        type3 = "titre2";
        type4 = "titre1";
      } else {
        type2 = "titre3";
        type3 = "titre2";
        type4 = "titre1";
      }
    }

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
        default:
          break;
      }
      divInsertionTitre.appendChild(divType2);
    }
    if (type3 !== null) {
      var divType3 = document.createElement("div");
      divType3.className = "sousDivInsertionTitre";
      switch (type3) {
        case "titre1":
          divType3.innerHTML = "Titre 1";
          break;
        case "titre2":
          divType3.innerHTML = "Titre 2";
          break;
        case "titre3":
          divType3.innerHTML = "Titre 3";
          break;
        default:
          break;
      }
      divInsertionTitre.appendChild(divType3);
    }
    if (type4 !== null) {
      var divType4 = document.createElement("div");
      divType4.className = "sousDivInsertionTitre";
      switch (type4) {
        case "titre1":
          divType4.innerHTML = "Titre 1";
          break;
        case "titre2":
          divType4.innerHTML = "Titre 2";
          break;
        default:
          break;
      }
      divInsertionTitre.appendChild(divType4);
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

function removeSelectDescriptifXML() {
  if (!deplacementDescriptif) {
    //on peut supprimer l'évènement
    $(".selectDescriptifXML").css("background-color", "white");
    $(".selectDescriptifXML").removeClass("selectDescriptifXML");
  } else {
    deplacementDescriptif = false;
  }
}

function AjouterTitre(evt) {
  var titleClassName = evt.innerHTML.replace(" ", "").toLowerCase();
  //On ajoute le titre avec le bon style
  var divNewTitle = $(
    "<div class='input-group " +
      titleClassName +
      "'><input type='hidden' id='idXML' value='_0'/><div class='input-group-prepend'><span class='input-group-text' id='basic-addon1'></span></div><input type='text' class='form-control' placeholder='" +
      evt.innerHTML +
      "'/><div class='deleteXML'><i class=\"fas fa-times-circle\"></i></div></div>"
  );
  $(divNewTitle).insertBefore($(evt).parent());

  //On insère une barre d'insertion au-dessus du nouveau titre
  $(
    "<div class='barreInsertion' onclick='AjouterElement(this);'><div class='panBarreInsertion'></div><div class='panBarreInsertion'></div></div>"
  ).insertBefore($(divNewTitle));

  //Ajout de l'hover sur toutes les barres d'insertions titre
  addEventsDescriptifs();

  //On supprime le panneau de choix des titres
  $(".divInsertionTitre").remove();

  //Appel de la fonction de numérotation de l'arborescence
  var idLot = $(divNewTitle).parent().attr("id");
  NumerotationArbo(idLot);

  //on attache l'evt pour modifier le xml
  attacheEventModifXML();

  //on actualise le xml
  modifierXML(divNewTitle);
}

function AjouterDescriptif(element) {
  //code par quentin
  if ($(".selectDescriptifXML").length) {
    elementADeplacer = $(".selectDescriptifXML");
    var elementNextLine = $(element).parent().next().next();
    var elementBeforeLine = $(element).parent().prev();

    if (
      !(
        elementADeplacer[0] == elementNextLine[0] ||
        elementADeplacer[0] == elementBeforeLine[0]
      )
    ) {
      elementAfter = $(element).parent().next().next();
      var classElement = element.innerHTML.replace(" ", "").toLowerCase();
      var classNextElement = $(element).parent().next().next().attr("class");
      var rangElement = null;
      var rangNextElement = null;

      //on détermine le rang de l'élément (a revoir)
      if (classElement.includes("titre1")) rangElement = 1;
      if (classElement.includes("titre2")) rangElement = 2;
      if (classElement.includes("titre3")) rangElement = 3;
      if (classElement.includes("titre4")) rangElement = 4;
      if (classElement.includes("titre5")) rangElement = 5;

      if (classNextElement.includes("titre1")) rangNextElement = 1;
      if (classNextElement.includes("titre2")) rangNextElement = 2;
      if (classNextElement.includes("titre3")) rangNextElement = 3;
      if (classNextElement.includes("titre4")) rangNextElement = 4;
      if (classNextElement.includes("titre5")) rangNextElement = 5;

      var data = Array();

      if (rangElement > rangNextElement || rangNextElement == null) {
        var classParent =
          "." +
          classElement.substr(0, classElement.length - 1) +
          (classElement.substr(-1) - 1);

        data = {
          todo: "deplacerDescriptif",
          idProjet: $("#idProjetActuel").val(),
          idDescriptif: $(".selectDescriptifXML").children("#idXML").val(),
          placement: "APPEND",
          idRef: $(element).parent().prev(classParent).children("#idXML").val(),
        };
        parent = $(element).parent().prev(classParent);
      } else {
        data = {
          todo: "deplacerDescriptif",
          idProjet: $("#idProjetActuel").val(),
          idDescriptif: $(".selectDescriptifXML").children("#idXML").val(),
          placement: "BEFORE",
          idRef: $(element).parent().next().next().children("#idXML").val(),
        };
      }

      //console.log(data);

      $.ajax({
        url: "./ActionServlet",
        method: "POST",
        data: data,
        dataType: "json",
      }).done(function (response) {
        // Fonction appelée en cas d'appel AJAX réussi
        //console.log("Response", response);
        if (response.Error) {
          alert(
            "Un problème est survenu lors du deplacement d'un descriptif. Rafraichir la page peut vous aider à l'identifier"
          );
        } else {
          elementADeplacer.next().remove();

          //on va chercher le lot et on l'insere à la fin
          elementADeplacer.insertBefore(elementAfter);
          elementADeplacer.removeClass("titre1 titre2 titre3 titre4 titre5");
          elementADeplacer.addClass(classElement);

          var divBarreInsertion =
            "<div class='barreInsertion' onclick='AjouterElement(this);'><div class='panBarreInsertion'></div><div class='panBarreInsertion'></div></div>";
          $(divBarreInsertion).insertAfter(elementADeplacer);

          //On supprime le panneau de choix des styles de desciptif
          $(".divInsertionStyleDescriptif").remove();

          //Ajout de l'hover sur toutes les barres d'insertions titre
          addEventsDescriptifs();

          //Appel de la fonction de numérotation de l'arborescence
          var idLot = $(divInsertionDescriptif).parent().attr("id");
          NumerotationArbo(idLot);

          //on attache l'evt pour modifier le xml
          attacheEventModifXML();
        }
      });
    }
  }
  //code par louis
  else {
    var typeDescriptif = null;
    //on determine le type qui va etre insere
    if ($(".selectDescriptif").attr("class").includes("ouvrage")) {
      typeDescriptif = "ouvrage";
    } else if ($(".selectDescriptif").attr("class").includes("generique")) {
      typeDescriptif = "generique";
    } else {
      typeDescriptif = "prestation";
    }

    //On créer le div du descriptif avec le style correspondant à sa place dans l'arbo
    var divInsertionDescriptif = document.createElement("div");
    divInsertionDescriptif.className =
      "descriptif " +
      element.innerHTML.replace(" ", "").toLowerCase() +
      " " +
      typeDescriptif;

    //Appel AJAX pour récupérer les infos du descriptif
    var idDescriptif = $(".selectDescriptif").children(":first").val();
    var unite, descriptionDescriptif, nomDescriptif, typeDescriptif;

    $.ajax({
      url: "./ActionServlet",
      method: "GET",
      data: {
        todo: "recupererDescriptif",
        idDescriptif: idDescriptif,
      },
      dataType: "json",
    }).done(function (response) {
      // Fonction appelée en cas d'appel AJAX réussi
      //console.log("Response", response);
      if (!response.ErrorState) {
        //Récupérer la description (et l'unité si pas générique)
        if (response.typeDescriptif !== "Generique") {
          unite = response.unite;
        }
        descriptionDescriptif = response.descriptionDescriptif;
        nomDescriptif = response.nomDescriptif;
        typeDescriptif = response.typeDescriptif;

        if (!$(".selectDescriptif").hasClass("generique")) {
          $(divInsertionDescriptif).html(
            "<input type='hidden' id='idXML' value='_0'/><input type='hidden' class='idDescriptif' value='" +
              idDescriptif +
              "'/><input type='hidden' class='typeDescriptif' value='" +
              typeDescriptif +
              "'/><div class='input-group'><div class='input-group-prepend'><span class='input-group-text' id='basic-addon1'></span></div><input type='text' class='form-control nomDescriptif' placeholder='Ouvrage/Prestation' value='" +
              nomDescriptif +
              "'/><div class='deleteXML'><i class=\"fas fa-times-circle\"></i></div></div><div class='input-group description'><textarea class='form-control' placeholder='Description' value='" +
              descriptionDescriptif +
              "'></textarea></div><div class='ligneChiffrage'><input type='hidden' id='idLigneChiffrage' value='1'/><input type='text' class='form-control localisation' placeholder='Localisation'/><input type='text' class='form-control quantite' placeholder='Quantité' value='1.0'/><div class='input-group-prepend'><span class='input-group-text unite'>" +
              unite +
              "</span></div><div style='width: 25px;'></div></div>"
          );
          //! Rajouter la description stylisée en AJAX

          $(divInsertionDescriptif).insertBefore($(element).parent());

          //On insère une barre d'insertion au dessus du nouveau descriptif
          divBarreInsertion = $(
            "<div class='barreInsertion' onclick='AjouterElement(this);'><div class='panBarreInsertion'></div><div class='panBarreInsertion'></div></div>"
          );
          divBarreInsertion.insertBefore($(divInsertionDescriptif));

          //On ajoute une barre d'insertion ligneChiffrage à la fin de l'ouvrage/prestation
          //var divBarreInsertionLigneChiffrage = document.createElement("div");
          divBarreInsertionLigneChiffrage = $(
            "<div class='barreInsertionLigneChiffrage' onclick='AjouterLigneChiffrage(this);'><div class='panBarreInsertionLigneChiffrage'></div><div class='panBarreInsertionLigneChiffrage'></div></div>"
          );
          $(divInsertionDescriptif).append($(divBarreInsertionLigneChiffrage));

          //On supprime le panneau de choix des styles de desciptif
          $(".divInsertionStyleDescriptif").remove();

          //Ajout de l'hover sur toutes les barres d'insertions titre
          addEventsDescriptifs();

          //Appel de la fonction de numérotation de l'arborescence
          var idLot = $(divInsertionDescriptif).parent().attr("id");
          NumerotationArbo(idLot);
        } else {
          //On insère le générique
          $(divInsertionDescriptif).html(
            "<input type='hidden' id='idXML' value='_0'/><input type='hidden' class='idDescriptif' value='" +
              idDescriptif +
              "'/><input type='hidden' class='typeDescriptif' value='" +
              typeDescriptif +
              "'/><div class='input-group'><div class='input-group-prepend'><span class='input-group-text' id='basic-addon1'></span></div><input type='text' class='form-control nomDescriptif' placeholder='Générique' value='" +
              nomDescriptif +
              "'/><div class='deleteXML'><i class=\"fas fa-times-circle\"></i></div></div><div class='input-group description'><textarea id='testTextArea' class='form-control' placeholder='Description' value='" +
              descriptionDescriptif +
              "'></textarea></div>"
          );
          $(divInsertionDescriptif).insertBefore($(element).parent());

          //On insère une barre d'insertion au dessus du nouveau descriptif
          divBarreInsertion = $(
            "<div class='barreInsertion' onclick='AjouterElement(this);'><div class='panBarreInsertion'></div><div class='panBarreInsertion'></div></div>"
          );
          divBarreInsertion.insertBefore($(divInsertionDescriptif));

          //On supprime le panneau de choix des styles de desciptif
          $(".divInsertionStyleDescriptif").remove();

          //Ajout de l'hover sur toutes les barres d'insertions titre
          addEventsDescriptifs();

          /*area2 = new nicEditor({ fullPanel: true }).panelInstance(
            "testTextArea"
          ); //! et l'id de textarea au dessus et style form-control*/

          //Appel de la fonction de numérotation de l'arborescence
          var idLot = $(divInsertionDescriptif).parent().attr("id");
          NumerotationArbo(idLot);
        }

        //on attache l'evt pour modifier le xml
        attacheEventModifXML();

        //on actualise le xml
        modifierXML(divInsertionDescriptif);

        //!Transforme les textaereas en Rich Text Field
        //bkLib.onDomLoaded(nicEditors.allTextAreas);
      }
    });
  }
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
          var numeroTitre = $(this).children().eq(0).children(":first"); //On récupère le span du chiffre romain
          numTitre1++;
          numeroTitre.html(toRoman(numTitre1) + ".");
          numTitre2 = 0;
          numTitre3 = 0;
          numTitre4 = 0;
          numTitre5 = 0;
        } else if ($(this).parent().hasClass("titre2")) {
          var numeroTitre = $(this).children().eq(0).children(":first"); //On récupère le span du chiffre romain
          numTitre2++;
          numeroTitre.html(toRoman(numTitre1) + "." + numTitre2);
          numTitre3 = 0;
          numTitre4 = 0;
          numTitre5 = 0;
        } else if ($(this).parent().hasClass("titre3")) {
          var numeroTitre = $(this).children().eq(0).children(":first"); //On récupère le span du chiffre romain
          numTitre3++;
          numeroTitre.html(numTitre2 + "." + numTitre3);
          numTitre4 = 0;
          numTitre5 = 0;
        } else if ($(this).parent().hasClass("titre4")) {
          var numeroTitre = $(this).children().eq(0).children(":first"); //On récupère le span du chiffre romain
          numTitre4++;
          numeroTitre.html(numTitre2 + "." + numTitre3 + "." + numTitre4);
          numTitre5 = 0;
        } else if ($(this).parent().hasClass("titre5")) {
          var numeroTitre = $(this).children().eq(0).children(":first"); //On récupère le span du chiffre romain
          numTitre5++;
          numeroTitre.html(
            numTitre2 + "." + numTitre3 + "." + numTitre4 + "." + numTitre5
          );
        }
      } else {
        if ($(this).hasClass("titre1")) {
          var numeroTitre = $(this).children().eq(1).children(":first"); //On récupère le span du chiffre romain
          numTitre1++;
          numeroTitre.html(toRoman(numTitre1) + ".");
          numTitre2 = 0;
          numTitre3 = 0;
          numTitre4 = 0;
          numTitre5 = 0;
        } else if ($(this).hasClass("titre2")) {
          var numeroTitre = $(this).children().eq(1).children(":first"); //On récupère le span du chiffre romain
          numTitre2++;
          numeroTitre.html(toRoman(numTitre1) + "." + numTitre2);
          numTitre3 = 0;
          numTitre4 = 0;
          numTitre5 = 0;
        } else if ($(this).hasClass("titre3")) {
          var numeroTitre = $(this).children().eq(1).children(":first"); //On récupère le span du chiffre romain
          numTitre3++;
          numeroTitre.html(numTitre2 + "." + numTitre3);
          numTitre4 = 0;
          numTitre5 = 0;
        } else if ($(this).hasClass("titre4")) {
          var numeroTitre = $(this).children().eq(1).children(":first"); //On récupère le span du chiffre romain
          numTitre4++;
          numeroTitre.html(numTitre2 + "." + numTitre3 + "." + numTitre4);
          numTitre5 = 0;
        } else if ($(this).hasClass("titre5")) {
          var numeroTitre = $(this).children().eq(1).children(":first"); //On récupère le span du chiffre romain
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
    $(".ongletLot:contains('" + Number(numBouton) + "')").css(
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
  if (isNaN(numOnglet)) {
    $(".presentationRight").hide();
    $("#ongletsLot").show();
    numOnglet = 0;
  }

  var idOnglet = "lot_" + numOnglet;

  var divNouvelOnglet = $(
    "<div class='lot' id='" +
      idOnglet +
      "'><input type='hidden' id='idXML' value='_0'/><div class='barreInsertion' onclick='AjouterElement(this);'><div class='panBarreInsertion'></div><div class='panBarreInsertion'></div></div><div class='input-group titre1'><input type='hidden' id='idXML' value='_0'/><div class='input-group-prepend'><span class='input-group-text'>I.</span></div><input type='text' class='form-control' placeholder='Titre 1'/><div class='deleteXML'><i class=\"fas fa-times-circle\"></i></div></div><div class='barreInsertion' onclick='AjouterElement(this);'><div class='panBarreInsertion'></div><div class='panBarreInsertion'></div></div><div class='finLot'></div></div>"
  );
  $(divNouvelOnglet).insertBefore($("#ongletsLot"));

  //Création de deux barres d'insertion d'un titre1 et d'une fin de lot dans ce nouvel onglet
  var divBoutonOnglet = $(
    "<div class='ongletLot' onclick='AfficherOnglet($(\"#" +
      idOnglet +
      "\"));'>" +
      numOnglet +
      "</div>"
  );
  $(divBoutonOnglet).insertBefore($(".ongletLot").last());

  //Création d'un nouveau input de titre lot
  $(
    "<div class='divTitreLot' id='divTitreLot_" +
      numOnglet +
      "'><input type='text' class='titreLot' placeholder='Titre Lot' /></div>"
  ).insertBefore($(".lot").first());

  //Ajout de l'hover sur toutes les barres d'insertions titre
  addEventsDescriptifs();

  //On affiche par défaut
  AfficherOnglet($("#" + idOnglet));

  //On affiche le bon titre lot
  AfficherTitreLot($("#divTitreLot_" + numOnglet));

  //On numérote l'unique titre
  NumerotationArbo(idOnglet);

  //on attache l'evt pour modifier le xml
  attacheEventModifXML();

  //on actualise le xml
  var newOnglet = $("#divTitreLot_" + numOnglet);
  var premierTitre = $(".titre1").last();
  modifierXML(newOnglet);
  modifierXML(premierTitre);
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
  var unite = $(element).prev().children(":eq(3)").children(":first").html(); //On va chercher l'unité dans la ligneChiffrage d'au dessus
  var divBarreInsertionLigneChiffrage = $(
    "<div class='ligneChiffrage'><input type='hidden' id='idLigneChiffrage' value='0'/><input type='text' class='form-control localisation' placeholder='Localisation'/><input type='text' class='form-control quantite' placeholder='Quantité' value='1.0'/><div class='input-group-prepend'><span class='input-group-text unite'>" +
      unite +
      "</span></div><div class='suppressionLigneChiffrage'><i class='fas fa-times-circle'</div>"
  );

  $(divBarreInsertionLigneChiffrage).insertBefore($(element));

  //on attache l'evt pour modifier le xml
  attacheEventModifXML();

  //on actualise le XML
  modifierXML(divBarreInsertionLigneChiffrage);
}

enCours = false;
function modifierXML(element) {
  if (typeof element != "undefined" && enCours == false) {
    enCours = true;
    //console.log("debut " + jQuery.now());

    //on détermine l'idProjet
    var idProjet = $("#idProjetActuel").val();
    classElement = $(element).attr("class");

    //si ce n'est pas une ligne chiffrage, on détermine sa position
    //et le parent/consecutif associé
    var idRefPlacement = null;
    var placement = null;
    var rangElement = null;
    var rangNextElement = null;
    var titreType = null;
    if (
      !classElement.includes("ligneChiffrage") &&
      !classElement.includes("divTitreLot")
    ) {
      //on détermine le rang de l'élément
      if (classElement.includes("titre1")) rangElement = 1;
      if (classElement.includes("titre2")) rangElement = 2;
      if (classElement.includes("titre3")) rangElement = 3;
      if (classElement.includes("titre4")) rangElement = 4;
      if (classElement.includes("titre5")) rangElement = 5;

      titreType = "titre" + rangElement;

      //on va chercher la class de l'élément suivant:
      var classNextElement = $(element).next().next().attr("class");

      if (classNextElement.includes("titre1")) rangNextElement = 1;
      if (classNextElement.includes("titre2")) rangNextElement = 2;
      if (classNextElement.includes("titre3")) rangNextElement = 3;
      if (classNextElement.includes("titre4")) rangNextElement = 4;
      if (classNextElement.includes("titre5")) rangNextElement = 5;

      if (rangElement > rangNextElement || rangNextElement == null) {
        placement = "APPEND";
        //on va chercher l'ID du parent
        //c'est un lot
        if (rangElement == 1) {
          idRefPlacement = $(element).parent().children("#idXML").val();
        }
        //sinon c'est le dernier titre de rang n-1
        else {
          idRefPlacement = $(element)
            .prevAll(".titre" + (rangElement - 1))
            .first()
            .children("#idXML")
            .val();
        }
      } else {
        placement = "BEFORE";
        //on va chercher l'id de l'élément d'après
        idRefPlacement = $(element).next().next().children("#idXML").val();
      }
    } else if (classElement.includes("ligneChiffrage")) {
      //c'est une ligneChiffrage, on va chercher l'ID du parent
      idRefPlacement = $(element).parent().children("#idXML").val();
    } else {
      titreType = "lot";
      //il y'a un element après
      if ($(element).next().attr("class").includes("divTitreLot")) {
        placement = "BEFORE";
        var idNext = $(element).next().attr("id").substr(12);
        idRefPlacement = $("#lot_" + idNext)
          .children("#idXML")
          .val();
      } else {
        placement = "APPEND";
        idRefPlacement = "_0";
      }
    }

    //on détermine l'objet que l'on traite et on obtient ses informations
    var data = Array();
    if (classElement.includes("descriptif")) {
      data = {
        todo: "modifierXML",
        idProjet: idProjet,
        type: "descriptif",
        id: $(element).children("#idXML").val(),
        idRefPlacement: idRefPlacement,
        placement: placement,
        idDescriptif: $(element).children(".idDescriptif").val(),
        nomDescriptif: $(element).find(".nomDescriptif").val(),
        description: $(element).find("textarea").val(),
      };
    }
    // idDescriptif: $(element).children('.idDescriptif').val(),
    /*else if(classElement.includes("ligneChiffrage")){
      var quantite = $(element).find(".quantite").val();
      if (quantite == "") {
        quantite = "1.0";
        $(element).find(".quantite").val("1.0");
      }*/
    else if (classElement.includes("ligneChiffrage")) {
      data = {
        todo: "modifierXML",
        idProjet: idProjet,
        type: "ligneChiffrage",
        id: $(element).children("#idLigneChiffrage").val(),
        idRefPlacement: idRefPlacement,
        placement: "",
        localisation: $(element).find(".localisation").val(),
        quantite: $(element).find(".quantite").val(),
      };
    } else {
      if (titreType == "lot") {
        var idDiv = $(element).attr("id").substr(12);
        var id = $("#lot_" + idDiv)
          .children("#idXML")
          .val();
      } else {
        var id = $(element).children("#idXML").val();
      }
      data = {
        todo: "modifierXML",
        idProjet: idProjet,
        titreType: titreType,
        type: "titre",
        id: id,
        idRefPlacement: idRefPlacement,
        placement: placement,
        intitule: $(element).children("input").last().val(),
      };
    }

    //console.log(data);

    //Ajax
    $.ajax({
      url: "./ActionServlet",
      method: "POST",
      data: data,
      dataType: "json",
    }).done(function (response) {
      // Fonction appelée en cas d'appel AJAX réussi
      //console.log("Response", response);
      if (response.Error) {
        alert(
          "Un problème est survenu lors de la sauvegarde des données du projet. Rafraichir la page peut vous aider à l'identifier"
        );
      } else {
        if (response["idInsere"] != "" && response["idInsere"] != null) {
          if (titreType == "lot") {
            $("#lot_" + $(element).attr("id").substr(12))
              .children("#idXML")
              .val(response["idInsere"]);
          } else {
            // si c'est un id de ligne chiffrage, il ne s'incremente pas tout seul
            if (response["idInsere"] == "_0")
              $(element)
                .children("#idLigneChiffrage")
                .val($(element).parent().children(".ligneChiffrage").length);
            else $(element).children("#idXML").val(response["idInsere"]);
          }
        }
      }

      enCours = false;
      //console.log("Fin " + jQuery.now());
    });
  } else if (
    typeof element != "undefined" &&
    enCours == true &&
    $(element).attr("class").includes("titre1")
  ) {
    setTimeout(function () {
      modifierXML(element);
    }, 30);
  }
}

supprimerXMLEnCours = false;
function supprimerXML(croix, type) {
  if (supprimerXMLEnCours == false) {
    supprimerXMLEnCours = true;
    var data = Array();

    parent = null;
    lotDelete = null;
    titreLotdelete = null;
    if (type == "ligneChiffrage") {
      data = {
        todo: "supprimerXML",
        type: type,
        idProjet: $("#idProjetActuel").val(),
        idXML: $(croix).parent().children("#idLigneChiffrage").val(),
        idDescriptifXML: $(croix).parent().parent().children("#idXML").val(),
      };
      parent = $(croix).parent();
    } else if (type == "lot") {
      data = {
        todo: "supprimerXML",
        type: "balise",
        idProjet: $("#idProjetActuel").val(),
        idXML: $("#lot_" + $(croix).parent().attr("id").substr(12))
          .children("#idXML")
          .val(),
      };
      lotDelete = $("#lot_" + $(croix).parent().attr("id").substr(12));
      titreLotdelete = $(croix).parent();
      ongletLotDelete = $(
        ".ongletLot:contains('" + $(croix).parent().attr("id").substr(12) + "')"
      );
    } else {
      //si c'est un descriptif, son idXML est deux étages au dessus
      if ($(croix).parents(".descriptif").length) {
        var idXML = $(croix).parent().parent().children("#idXML").val();
        parent = $(croix).parent().parent();
      }
      //sinon il n'est qu'à un étage au dessus
      else {
        var idXML = $(croix).parent().children("#idXML").val();
        parent = $(croix).parent();
      }

      data = {
        todo: "supprimerXML",
        type: type,
        idProjet: $("#idProjetActuel").val(),
        idXML: idXML,
      };
    }

    //console.log(data);

    //on a reunit toutes les infortmations, on fait l'appel AJAX
    $.ajax({
      url: "./ActionServlet",
      method: "POST",
      data: data,
      dataType: "json",
    }).done(function (response) {
      // Fonction appelée en cas d'appel AJAX réussi
      console.log("Response", response);
      if (response.Error) {
        alert(
          "Un problème est survenu lors de la suppression. Rafraichir la page peut vous aider à l'identifier"
        );
      } else {
        if (type != "ligneChiffrage" && lotDelete == null) {
          $(parent).next(".barreInsertion").remove();
          $(parent).remove();
        } else if (lotDelete != null) {
          $(lotDelete).remove();
          $(titreLotdelete).remove();
          $(ongletLotDelete).remove();

          var nbLots = $(".lot").length;
          if (nbLots == 0) {
            $(".presentationRight").show();
            $("#ongletsLot").hide();
          } else {
            AfficherOnglet($(".lot").first());
          }
        } else {
          $(parent).remove();
        }
        parent = null;
      }

      supprimerXMLEnCours = false;
    });
  }
}

function addSelectedClassXML(elementPuce) {
  $(".selectDescriptifXML").css("background-color", "white");
  $(".selectDescriptifXML").removeClass("selectDescriptifXML");
  deplacementDescriptif = true;
  $(elementPuce).parent().parent().addClass("selectDescriptifXML");
  $(".selectDescriptif").removeClass("selectDescriptif");
  $(".selectDescriptifXML").css("background-color", "rgba(70, 55, 0, 0.05)");
}

function survolDeplacerDescriptif(elementPuce, sens) {
  if (sens == "enter") {
    $(elementPuce)
      .parent()
      .parent()
      .css("background-color", "rgba(70, 55, 0, 0.05)");
  } else {
    if (
      !$(elementPuce)
        .parent()
        .parent()
        .attr("class")
        .includes("selectDescriptifXML")
    ) {
      $(elementPuce).parent().parent().css("background-color", "white");
    }
  }
}

function attacheEventModifXML() {
  $(".titre1").change(function () {
    modifierXML(this);
  });
  $(".titre2").change(function () {
    modifierXML(this);
  });
  $(".titre3").change(function () {
    modifierXML(this);
  });
  $(".titre4").change(function () {
    modifierXML(this);
  });
  $(".titre5").change(function () {
    modifierXML(this);
  });
  $(".ligneChiffrage").change(function () {
    modifierXML(this);
  });
  $(".divTitreLot").change(function () {
    modifierXML(this);
  });
  $(".deleteXML").click(function () {
    supprimerXML(this, "balise");
  });
  $(".deleteXMLlot").click(function () {
    supprimerXML(this, "lot");
  });
  $(".suppressionLigneChiffrage").click(function () {
    supprimerXML(this, "ligneChiffrage");
  });
  $(".descriptif > .input-group > .input-group-prepend").click(function () {
    addSelectedClassXML(this);
  });
  $(".descriptif > .input-group > .input-group-prepend").mouseenter(
    function () {
      survolDeplacerDescriptif(this, "enter");
    }
  );
  $(".descriptif > .input-group > .input-group-prepend").mouseleave(
    function () {
      survolDeplacerDescriptif(this, "leave");
    }
  );
}
