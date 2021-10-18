
document.addEventListener("DOMContentLoaded", loadingPage );
document.getElementById("addCareer").addEventListener("click", addCareer);
document.getElementById("btnReport").addEventListener("click", showReport );
const base_url = "http://localhost:8080/university/api/";

function loadingPage () {
    getContent();
}

function getContent () {
    fetch(base_url+'careers')
        .then( response => {
            if ( response.ok )
                response.json().then( careers => generateCardsCareer( careers ))
            else
                showError();
        });
}

function generateCardsCareer( data ) {
    for (const d of data) {
        createCardCareer( d );
    }
}

function createCardCareer ( c ) {
    let parentNode = document.getElementById("careers");
    let container = document.createElement( "div");
    container.classList.add("card","bg-dark","text-white","mb-3","myCard");
    container.style.maxWidth = "18rem";
    let header = document.createElement("div");
    header.classList.add( "card-header");
    header.innerHTML = "ID: "+c.id_carrera;
    let body = document.createElement("div");
    body.classList.add( "card-body");
    let title = document.createElement("h5");
    title.classList.add( "card-title","text-uppercase");
    title.innerHTML = c.nombre;
    body.appendChild( title );
    let description = document.createElement("p");
    description.classList.add( "card-text");
    description.innerHTML = "Some quick example text to build on the card title and make up the bulk of the card's content."
    body.appendChild( description );
    container.appendChild( header );
    container.appendChild( body );
    parentNode.appendChild( container );
}

function showError() {
    alert( "something went wrong" );
}

function addCareer () {
    const career = {
        "nombre":document.getElementById("nombre").value
    };
    if ( career.nombre === "") {
        showError();
        return;
    }
    fetch( base_url+'careers', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify( career )
    }).then( r => {
        if ( r.status === 201 )
            r.json().then( career => createCardCareer( career ))
        else
            showError();
    })
}

function showReport () {
    document.getElementById("containerReport").classList.remove("dontShow");
    document.getElementById("btnReport").classList.add("dontShow");
    fetch( base_url+'careers/report')
        .then( r => {
            if ( r.ok )
                r.json().then( report => createTable( report ));
            else
                showError();
        })
}

function createTable ( report ) {
    report.forEach ( r => generateTr( r ) );
}

function generateTr ( row ) {
    let tBody = document.getElementById("report");
    const tr = document.createElement("tr");
    const id = document.createElement("td");
    id.innerHTML = row.idCarrera;
    const name = document.createElement("td");
    name.innerHTML = row.nombreCarrera;
    const entryDate = document.createElement("td");
    entryDate.innerHTML = row.añoIngreso;
    const graduateDate = document.createElement("td");
    graduateDate.innerHTML = row.añoEgreso;
    const signedUp = document.createElement("td");
    signedUp.innerHTML = row.cantInscriptos;
    const graduates = document.createElement("td");
    graduates.innerHTML = row.cantEgresados;
    tr.append( id,name,entryDate,graduateDate,signedUp,graduates);
    tBody.appendChild( tr );
}
