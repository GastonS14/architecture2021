document.addEventListener("DOMContentLoaded", loadingPage );
document.getElementById("addStudent").addEventListener("click", addStudent);
document.getElementById("matricular").addEventListener("click", matricular);
document.getElementById("orderBy").addEventListener("change", orderBy);
document.getElementById("order").addEventListener("change", orderBy);
document.getElementById("filterGenero").addEventListener("change", filter);
document.getElementById("filterCiudad").addEventListener("change", filter);

let documentStudent = document.getElementById("documento");
let name =document.getElementById("nombre");
let surname = document.getElementById("apellido");
let age = document.getElementById("edad");
let genre = document.getElementById("genero");
let cityResidence = document.getElementById("ciudad");
let universityNotebook = document.getElementById("libreta");
let documStudent = document.getElementById("docStudent");
let idCareer = document.getElementById("idCareer");
let fIngreso = document.getElementById("fIngreso");
let fEgreso = document.getElementById("fEgreso");

const BASE_URL = "http://localhost:8080/university/api/";

function loadingPage () {
    getContent()
}

function getContent () {
    fetch(BASE_URL+'students')
        .then( response =>{
            if ( response.ok ) {
                response.json().then(students => generateCardsStudent(students))
                fetch( BASE_URL+'students/cities')
                    .then( r => {
                        if ( r.ok )
                            r.json().then( cities => showCities( cities ) )
                        else
                            showError();
                    })
                    fetch(BASE_URL + 'careers')
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
    if(!validateInputsStudent()) {
        showErrorStudent();
        return;
    }
    const data = createJSON();
    fetch( BASE_URL+'students', {
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

function showErrorStudent() {
    alert( "Something went wrong with the student fields" );
}

function showErrorCareerStudent() {
    alert( "Something went wrong with the student career fields" );
}

function createJSON () {
    return {
        "documento": documentStudent.value,
        "nombre": name.value,
        "apellido": surname.value,
        "edad": age.value,
        "genero": genre.value,
        "ciudadResidencia":  cityResidence.value,
        "libretaUniversitaria": universityNotebook.value
    };
}

function validateInput(inputValueToValidate) {
    if(typeof inputValueToValidate === 'number') return !(inputValueToValidate === '' || isNaN(inputValueToValidate));
    return !(inputValueToValidate === undefined || inputValueToValidate === null || inputValueToValidate === '');
}

function validateInputsStudent() {
    if(!validateInput(parseInt(documentStudent.value))) return false;
    if(!validateInput(name.value)) return false;
    if(!validateInput(surname.value)) return false;
    if(!validateInput(parseInt(age.value))) return false;
    if(!validateInput(genre.value)) return false;
    if(!validateInput(cityResidence.value)) return false;
    return validateInput(parseInt(universityNotebook.value));
}

function validateInputsAddCareer() {
    if(!validateInput(parseInt(documStudent.value))) return false;
    if(!validateInput(parseInt(idCareer.value))) return false;
    if(!validateInput(parseInt(fIngreso.value))) return false;
    return validateInput(parseInt(fEgreso.value))
}

function matricular () {
    if(!validateInputsAddCareer()) {
        showErrorCareerStudent();
        return;
    }
    const json = {
        "idCarrera": document.getElementById("idCareer").value,
        "fIngreso": document.getElementById("fIngreso").value,
        "fEgreso": document.getElementById("fEgreso").value
    }
    docStudent = document.getElementById("docStudent").value;
    fetch( BASE_URL+"students/"+docStudent+"/career", {
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
    fetch ( BASE_URL+'students/sort/?attribute='+sortBy+"&sortOrder="+sortOrder)
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
    fetch( BASE_URL+'students/filter/?genre='+genre+'&city='+city)
        .then( r => {
            if ( r.ok )
                r.json().then( students => generateCardsStudent( students ) );
            else
                showError();
        })
    document.getElementById("students").innerHTML = ""
}
