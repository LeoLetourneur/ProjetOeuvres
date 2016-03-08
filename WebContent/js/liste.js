var page = 1;
var nombreParPage = 5;

function initialisePagination() {
	
	page = $("#currentPage").val();
	nombreParPage = $("#currentNumberPerPage").val();
	
	$(".pageButton").click( changePage );
	$(".parPageButton").click( changeNumberPerPage );
}

function changePage() {
	page = $(this).data('value');
	reloadList();
}

function changeNumberPerPage() {
	nombreParPage = $(this).data('value');
	reloadList();
}

function reloadList() {
	$("#currentPage").val(page);
	$("#currentNumberPerPage").val(nombreParPage);
	$("#changeTab").submit();
}

function deleteItem(id) {
	$("#idSelected").val(id);
}

function deleteReservation(idOeuvre,idAdherent) {
	$("#idSelected").val(idOeuvre);
	$("#idSelected2").val(idAdherent);
}

function confirmDelete() {
	$("#deleteForm").submit();
}

$( document ).ready( initialisePagination );