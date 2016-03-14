<%@attribute name="modalId" %>
<%@attribute name="modalTitle" %>
<%@attribute name="modalAccept" %>
<%@attribute name="modalAction" %>

<div class="modal fade" id="${modalId}" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">${modalTitle}</h4>
			</div>
			<div class="modal-body">
				<jsp:doBody/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-modal" data-dismiss="modal">Annuler</button>
				<button type="button" class="btn btn-modal" onclick="${modalAction}">${modalAccept}</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->