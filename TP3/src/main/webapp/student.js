document.addEventListener("DOMContentLoaded", loadingPage );
document.getElementById("addStudent").addEventListener("click", addStudent);
document.getElementById("matricular").addEventListener("click", matricular);
document.getElementById("orderBy").addEventListener("change", orderBy);
document.getElementById("order").addEventListener("change", orderBy);
document.getElementById("filterGenero").addEventListener("change", filter);
document.getElementById("filterCiudad").addEventListener("change", filter);
const base_url = "http://localhost:8080/university/api/";


function loadingPage () {
    getContent()
}

function getContent () {
    fetch(base_url+'students')
        .then( response =>{
            if ( response.ok ) {
                response.json().then(students => generateCardsStudent(students))
                fetch( base_url+'students/cities')
                    .then( r => {
                        if ( r.ok )
                            r.json().then( cities => showCities( cities ) )
                        else
                            showError();
                    })
                    fetch(base_url + 'careers')
                        .then(r => {
                            if (r.ok)
                                r.json().then(careers => loadCareersInForm(careers));
                            })
            }
            else
                showError();
            });
}

function generateCardsStudent ( students ) {
    students.forEach( s => createCardStudent( s ) );
}

function createCardStudent ( s ) {
    let parentNode = document.getElementById("students");
    let container = document.createElement( "div");
    container.classList.add("card","bg-dark","text-white","mb-3","myCard","col", "ms-2", "me-2");
    container.style.maxWidth = "18rem";
    container.style.minWidth = "180px";
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

function loadCareersInForm( careers ) {
    careers.forEach( c => createOption ( c ) );
}

function createOption ( career ) {
    let select = document.getElementById("idCareer");
    let option = document.createElement("option");
    option.value = career.id_carrera;
    option.innerHTML = career.nombre;
    select.appendChild( option );
}

function addStudent ( ) {
    const data = createJSON();
    fetch( base_url+'students', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then( r => {
        if (r.status === 201 ) {
            r.json()
                .then( student => createCardStudent( student ) )
        } else
            showError();
    });
}

function showError() {
    alert( "Something went wrong" );
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

function matricular () {
    const json = {
        "idCarrera": document.getElementById("idCareer").value,
        "fIngreso": document.getElementById("fIngreso").value,
        "fEgreso": document.getElementById("fEgreso").value
    }
    docStudent = document.getElementById("docStudent").value;
    fetch( base_url+"students/"+docStudent+"/career", {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify( json )
    }).then( r => {
        if ( r.status === 201 ) {
            r.json().then( alert("Se agrego") )
        } else
            alert( "Something went wrong")
    })
}

function orderBy () {
    const sortBy = document.getElementById("orderBy").value;
    const sortOrder = document.getElementById("order").value;
    fetch ( base_url+'students/sort/?attribute='+sortBy+"&sortOrder="+sortOrder)
        .then( r => {
            if (r.ok)
                r.json().then(students => generateCardsStudent(students))
            else
                showError()
        });
    document.getElementById("students").innerHTML = ""
}

function showCities( cities ) {
    cities.forEach( c => {
        if (c != null)
            createOptionCity(c)
    });
}

function createOptionCity( c ) {
    let select = document.getElementById("filterCiudad");
    let option = document.createElement("option");
    option.value = c;
    option.innerHTML = c;
    select.appendChild( option );
}

function filter ( ) {
    const genre = document.getElementById("filterGenero").value;
    const city = document.getElementById("filterCiudad").value;
    fetch( base_url+'students/filter/?genre='+genre+'&city='+city)
        .then( r => {
            if ( r.ok )
                r.json().then( students => generateCardsStudent( students ) );
            else
                showError();
        })
    document.getElementById("students").innerHTML = ""
}