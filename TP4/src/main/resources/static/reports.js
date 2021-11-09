document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("showReportClients").addEventListener("click", reportClients);
    document.getElementById("showReportDate").addEventListener("click", reportDates);
    uri = "http://localhost:8080/market/sales/"
    tBodyClients = document.getElementById("reportByClients");
    tBodyDates = document.getElementById("reportByDate");
})

let uri;
let tBodyClients;
let tBodyDates;

function reportClients() {
    fetch( uri + 'report')
        .then( r => {
            if ( r.status === 200 )
                r.json().then( report => addReportClientsToDom ( report ) );
            else
                alert( "Something went wrong");
        })
}

function addReportClientsToDom ( report ) {
    report.forEach ( row => {
         createRowClients( row )
    })
    document.getElementById("containerReportClients").classList.remove("invisible");
}

function createRowClients ( row ) {
    const tr = document.createElement("tr");
    const id = document.createElement("td");
    const name = document.createElement("td");
    const surName = document.createElement("td");
    const amount = document.createElement("td");
    id.innerHTML = row.idClient;
    name.innerHTML = row.name;
    surName.innerHTML = row.surname;
    amount.innerHTML = row.amount;
    tr.append( id, name, surName, amount );
    tBodyClients.appendChild( tr );
}

function reportDates () {
    const startDate = document.getElementById("startDate").value;
    const endDate = document.getElementById("endDate").value;
    fetch( uri + 'inRange?startDate='+startDate+'&endDate='+endDate)
        .then( r => {
            if ( r.status === 200 )
                r.json().then( report => addReportDatesToDom ( report ) );
            else
                alert( "Something went wrong");
        })
}

function addReportDatesToDom ( report ){
    report.forEach( row => {
        tBodyDates.appendChild( createRowDates( row ) );
    })
    document.getElementById("containerReportDate").classList.remove("invisible");
}

function createRowDates( row ) {
    const tr = document.createElement("tr");
    const date = document.createElement("td");
    const amount = document.createElement("td");
    date.innerHTML = row.date;
    amount.innerHTML = row.amount;
    tr.append( date, amount);
    return tr;
}