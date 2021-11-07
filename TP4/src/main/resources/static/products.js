
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
    buy.setAttribute("units", product.stock);
    buy.addEventListener( "click",
        () => {
        document.getElementById("currentUnits").value = event.currentTarget.getAttribute("units");
        document.getElementById("idProductForSale").value = event.currentTarget.id });
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
    let priceValue =document.getElementById("price").value;
    let stockValue = document.getElementById("stock").value;
    let invalidInput = () => {
        return ( document.getElementById("nameProduct").value === "" ||
            (priceValue === '' || parseFloat(priceValue) < 1) ||
            (stockValue === '' || parseInt(stockValue) < 1 ));
    }
    if ( invalidInput() ) {
        alert("Please complete all fields ");
        return;
    }
    const product = {
        "name" : document.getElementById("nameProduct").value,
        "price" : parseFloat(priceValue),
        "stock": parseInt(stockValue)
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
    const newName = document.getElementById("newNameEditProduct").value;
    const newPrice = document.getElementById("newPriceEditProduct").value;
    const newStock = document.getElementById("newStockEditProduct").value;
    let invalidInput = () => {
        return ( document.getElementById("idProductForEdit").value === "" ||
            newName === "" || newPrice === "" || parseFloat(newPrice) < 1 ||
            newStock === "" || parseInt( newStock ) < 1 );
    }
    if ( invalidInput() ) {
        alert("Please complete all fields. Make sure your stock or price is bigger than zero");
        return;
    }
    const id_product = parseInt(document.getElementById("idProductForEdit").value );
    const Product = {
        "name": newName,
        "price": parseFloat( newPrice ),
        "stock": parseInt( newStock )
    }
    fetch( uriProduct+'/'+id_product, {
        method:"PUT",
        mode:"cors",
        headers:{ 'Content-Type':'application/json'},
        body: JSON.stringify( Product )
    }).then( r => {
        if ( r.status === 200 ) {
            r.json().then( r => getProducts() );
            clearFieldsEditProduct();
        } else
            showError();
    })
}

function sale () {
    const clientID = document.getElementById("idClient").value;
    const quantity = document.getElementById("quantity").value;
    const productID = document.getElementById("idProductForSale").value;
    const currentStock = document.getElementById("currentUnits").value;
    let invalidInput = () => {
        return ( clientID === "" || quantity === "" || parseInt( quantity ) < 1 ||
            productID === "" || parseInt(quantity) > parseInt(currentStock) );
    }
    if ( invalidInput() ) {
        alert("Please complete all fields. Reminder: units should be bigger than zero; " +
            " up to the limit of stock ");
        return;
    }
    const sale = {
        "client": parseInt( clientID ),
        "productQuantity" : [
            {
                "unidades": parseInt( quantity ),
                "idProducto": parseInt( productID )
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
            clearFieldsSale();
        } else {
            if ( r.status === 400 )
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

function clearFieldsEditProduct() {
    document.getElementById("newNameEditProduct").value = "";
    document.getElementById("newPriceEditProduct").value = "";
    document.getElementById("newStockEditProduct").value = "";
}

function clearFieldsSale () {
    document.getElementById("idClient").value;
    document.getElementById("quantity").value;
}
