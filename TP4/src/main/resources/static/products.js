
const uriProduct = "http://localhost:8080/market/products";
const uriSale = "http://localhost:8080/market/sales";

let containerProducts;
document.addEventListener("DOMContentLoaded", loadPage);

function loadPage() {
    containerProducts = document.getElementById("products");
    document.getElementById("addProduct").addEventListener("click", addProduct);
    document.getElementById("sale").addEventListener("click", sale);
    document.getElementById("edit").addEventListener("click", edit);
    getProducts();
}

function getProducts () {
    containerProducts.innerHTML = "";
    fetch( uriProduct )
        .then( r => {
            if( r.ok )
                r.json().then( products => addToDOM( products ) )
            else
                showError();
        });
}

function addToDOM ( products ) {
    products.forEach( p => createCard( p ) );
}

function createCard( product ) {
    let container = document.createElement( "div");
    container.classList.add("card","bg-dark","bg-gradient","bg-opacity-75","text-white","mb-3","myCard");
    container.style.maxWidth = "18rem";
    let header = document.createElement("div");
    header.classList.add( "card-header", "text-center", "fs-3", "border-2");
    header.innerHTML = product.name.toUpperCase();
    let body = document.createElement("div");
    body.classList.add( "card-body", "text-center","bg-opacity-50");
    let title = document.createElement("h5");
    let span = document.createElement("span");
    span.innerHTML = " $ ";
    title.classList.add( "card-title","text-uppercase", "fs-4");
    title.appendChild( span );
    title.innerHTML += product.price;
    body.appendChild( title );
    let description = document.createElement("p");
    description.classList.add( "card-text");
    description.innerHTML = product.stock + " units";
    let buy = document.createElement("button");
    buy.innerHTML = "BUY";
    buy.type = "button";
    buy.classList.add("btn","btn-warning", "btn-sm", "me-1");
    buy.id = product.id_product;
    buy.addEventListener( "click",
        () => document.getElementById("idProductForSale").value = event.currentTarget.id );
    buy.setAttribute( "data-bs-toggle", "modal");
    buy.setAttribute( "data-bs-target", "#modalBuyProduct");
    let edit = document.createElement("button");
    edit.innerHTML = "EDIT";
    edit.type = "button";
    edit.classList.add("btn","btn-light", "btn-sm", "ms-1", "me-1");
    edit.id = product.id_product;
    edit.addEventListener( "click",
        () => document.getElementById("idProductForEdit").value = event.currentTarget.id );
    edit.setAttribute( "data-bs-toggle", "modal");
    edit.setAttribute( "data-bs-target", "#modalEditProduct");
    let clean = document.createElement("button");
    clean.innerHTML = "DELETE";
    clean.type = "button";
    clean.classList.add("btn","btn-danger", "btn-sm", "ms-1");
    clean.id = product.id_product;
    clean.addEventListener( "click", deleteProduct );
    body.appendChild( description );
    body.appendChild( buy );
    body.appendChild( edit );
    body.appendChild( clean );
    container.appendChild( header );
    container.appendChild( body );
    containerProducts.appendChild( container );
}

function addProduct () {
    let invalidInput = () => {
        return ( document.getElementById("nameProduct").value === "" ||
            parseFloat(document.getElementById("price").value) < 1 ||
            parseInt(document.getElementById("stock").value) < 1 );
    }
    if ( invalidInput() ) {
        alert("Please complete all fields ");
        return;
    }
    const product = {
        "name" : document.getElementById("nameProduct").value,
        "price" : parseFloat(document.getElementById("price").value),
        "stock": parseInt(document.getElementById("stock").value)
    };
    fetch( uriProduct, {
        method: "post",
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify( product )
    }).then( r => {
        if ( r.status === 201 ) {
            r.json().then(product => createCard(product));
            clearFieldsAddProduct()
        }
        else
            showError();
    });
}

function edit () {
    let invalidInput = () => {
        return ( document.getElementById("idProductForEdit").value === "" ||
            document.getElementById("newNameEditProduct").value === "" ||
            parseFloat(document.getElementById("newPriceEditProduct").value) < 1 ||
            parseInt(document.getElementById("newStockEditProduct").value ) < 1 );
    }
    if ( invalidInput() ) {
        alert("Please complete all fields ");
        return;
    }
    const id_product = parseInt(document.getElementById("idProductForEdit").value );
    const Product = {
        "name": document.getElementById("newNameEditProduct").value,
        "price": parseFloat( document.getElementById("newPriceEditProduct").value ),
        "stock": parseInt( document.getElementById("newStockEditProduct").value )
    }
    fetch( uriProduct+'/'+id_product, {
        method:"PUT",
        mode:"cors",
        headers:{ 'Content-Type':'application/json'},
        body: JSON.stringify( Product )
    }).then( r => {
        if ( r.status === 200 ) {
            r.json().then( r => getProducts() );
        } else
            showError();
    })
}

function sale () {
    let invalidInput = () => {
        return ( document.getElementById("idClient").value === "" ||
            parseInt( document.getElementById("quantity").value ) < 1 ||
            document.getElementById("idProductForSale").value === "" );
    }
    if ( invalidInput() ) {
        alert("Please complete all fields ");
        return;
    }
    const sale = {
        "client": parseInt( document.getElementById("idClient").value ),
        "productQuantity" : [
            {
                "unidades": parseInt( document.getElementById("quantity").value ),
                "idProducto": parseInt( document.getElementById("idProductForSale").value )
            }
        ]
    }
    fetch( uriSale, {
        method:"post",
        mode:"cors",
        headers:{ 'Content-Type':'application/json'},
        body: JSON.stringify( sale )
    }).then( r => {
        if ( r.status === 201 ) {
            r.json().then( r => getProducts() );
        } else {
            if ( r.status === 400 )
                // probar esto:
                alert( "Client doesn't exist or his purchases reached the limit." +
                    " Remember, you can buy just 3 units per product per day ! ");
            else
                showError();
        }
    })
}

function deleteProduct () {
    fetch( uriProduct+'/'+parseInt(event.currentTarget.id), {
        method:"DELETE"
    } )
        .then( r => {
            if (r.status === 200)
                getProducts();
            else
                showError();
        } );
}

function showError() {
    alert ( "Something went wrong");
}

function clearFieldsAddProduct() {
    document.getElementById("nameProduct").value = "";
    document.getElementById("price").value = "";
    document.getElementById("stock").value = "";
}