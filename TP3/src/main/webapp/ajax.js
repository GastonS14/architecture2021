
document.addEventListener("DOMContentLoaded", loadingPage );
document.getElementById("addStudent").addEventListener("click", addStudent);
const base_url = "http://localhost:8080/university";

function loadingPage () {
    getContent();
}

function getContent () {
    fetch(base_url+'/api/career')
        .then( response => response.json() )
        .then( careers => generateCardsCareer( careers ) )
        .then( r => fetch(base_url+'/api/student')
            .then(response => response.json())
            .then( students => generateCardsStudent( students ) )
        );
}

function generateCardsCareer( data ) {
    for (const d of data) {
        createCardCareer( d );
    }
}

function generateCardsStudent ( students ) {
    students.forEach( s => createCardStudent( s ) );
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

function createCardStudent ( s ) {
    let parentNode = document.getElementById("students");
    let container = document.createElement( "div");
    container.classList.add("card","bg-dark","text-white","mb-3","myCard","col", "ms-2", "me-2");
    container.style.maxWidth = "18rem";
    let header = document.createElement("div");
    header.classList.add( "card-header");
    header.innerHTML = "Document: "+s.documento;
    let body = document.createElement("div");
    body.classList.add( "card-body", "text-wrap");
    let title = document.createElement("h5");
    title.classList.add( "card-title","text-uppercase", "text-wrap");
    title.innerHTML = s.nombre +" "+ s.apellido;
    body.appendChild( title );
    let ul = document.createElement("ul");
    ul.classList.add( "list-group", "list-group-flush");
    ul.style.maxWidth = "fit-content";
    let liLibretaU = document.createElement("li");
    liLibretaU.classList.add("list-group-item","bg-dark","text-white");
    liLibretaU.innerHTML = "N°: " + s.libretaUniversitaria;
    let liEdad = document.createElement("li");
    liEdad.classList.add("list-group-item","bg-dark","text-white","border-bottom","border-white");
    liEdad.innerHTML = "Age: "+s.edad;
    let liGenero = document.createElement("li");
    liGenero.classList.add("list-group-item","bg-dark","text-white","border-bottom","border-white");
    liGenero.innerHTML = "Genre: "+ s.genero;
    let liCiudad = document.createElement("li");
    liCiudad.classList.add("list-group-item","bg-dark","text-white","border-bottom","border-white");
    liCiudad.innerHTML = "City: "+s.ciudadResidencia;
    ul.append( liGenero, liEdad, liCiudad, liLibretaU);
    body.appendChild( ul );
    container.appendChild( header );
    container.appendChild( body );
    parentNode.appendChild( container );
}

function addStudent ( event ) {
    event.preventDefault();
    const data = createJSON();
    fetch( base_url+'/api/student', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then( r => {
        if (r.status === 201 ) {
            r.json()
                .then(r => createCardStudent(r))
        } else
            showError();
        });
}

function showError() {
    alert( "cannot add student" );
}

function createJSON () {
    let data = {
        "documento": parseInt(document.getElementById("documento").value ),
        "nombre": document.getElementById("nombre").value,
        "apellido": document.getElementById("apellido").value,
        "edad": parseInt(document.getElementById("edad").value ),
        "genero": document.getElementById("genero").value,
        "ciudadResidencia": document.getElementById("ciudad").value,
        "libretaUniversitaria": parseInt(document.getElementById("libreta").value)
    }
    return data;
}