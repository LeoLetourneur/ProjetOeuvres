var page = 1;
var nombreParPage = 5;

function initialisePagination() {
	
	page = $("#currentPage").val();
	nombreParPage = $("#currentNumberPerPage").val();
	
	$(".pageButton").click( changePage );
	$(".parPageButton").click( changeNumberPerPage );
	
	$(".btndel").click( deleteItem );
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

function deleteItem() {
	$("#idSelected").val($(this).data('id'));
	$("#idSelected2").val($(this).data('id2'));
}

function confirmDelete() {
	$("#deleteForm").submit();
}

$( document ).ready( initialisePagination );