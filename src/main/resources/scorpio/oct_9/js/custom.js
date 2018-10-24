$('.more-tr .add').click(function(){
	var addTr = $(this).parents('.tab-pane.active').find('#draggable table tbody').find('.firstTr').html();
	addTr = '<tr>'+ addTr +'</tr>';
	var a = $(this).parents('.tab-pane.active').find('#draggable table tbody').find('tr:last').find('td:first').html();
	a = (parseInt(a)+1);
	$(this).parents('.tab-pane.active').find('#draggable table tbody').append(addTr);
	$(this).parents('.tab-pane.active').find('#draggable table tbody tr:last-child').find('td:first-child').text(a);
});
$('.more-tr .remove').click(function(){
	var firstTr = $(this).parents('.tab-pane').find('#draggable table tbody tr:last-child').attr('class');
	if(firstTr != 'firstTr'){
		$(this).parents('.tab-pane').find('#draggable table tbody tr:last-child').remove();
	}
});

$('body').on('dblclick', 'table tr td', function() {
	$(this).find('.display-block-input').removeClass('display-none');
});


// tab first tab 
$('.more-tr-first-add .add-first-new').click(function(){
	var addTr = $(this).parents('.tab-pane.active:first-child').find('#draggable table tbody').find('.firstTr').html();
	addTr = '<tr>'+ addTr +'</tr>';
	var a = $(this).parents('.tab-pane.active:first-child').find('#draggable table tbody').find('tr:last').find('td:first').html();
	a = (parseInt(a)+1)
	$(this).parents('.tab-pane.active:first-child').find('#draggable table tbody').append(addTr);
	$(this).parents('.tab-pane.active:first-child').find('#draggable table tbody tr:last-child').find('td:first-child').text(a);
});

$('.more-tr-first-add .remove-first-new').click(function(){
	var firstTr = $(this).parents('.tab-pane:first-child').find('#draggable table tbody tr:last-child').attr('class');
	if(firstTr != 'firstTr'){
		$(this).parents('.tab-pane:first-child').find('#draggable table tbody tr:last-child').remove();
	}
});

// mew tab table
$('.more-tr-add .add-new').click(function(){
	var addTr = $(this).parents('.tab-pane-new.active').find('#draggable table tbody').find('.firstTr').html();
	addTr = '<tr>'+ addTr +'</tr>';
	var a = $(this).parents('.tab-pane-new.active').find('#draggable table tbody').find('tr:last').find('td:first').html();
	a = (parseInt(a)+1)
	$(this).parents('.tab-pane-new.active').find('#draggable table tbody').append(addTr);
	$(this).parents('.tab-pane-new.active').find('#draggable table tbody tr:last-child').find('td:first-child').text(a);
});
$('.more-tr-add .remove-new').click(function(){
	var firstTr = $(this).parents('.tab-pane-new').find('#draggable table tbody tr:last-child').attr('class');
	if(firstTr != 'firstTr'){
		$(this).parents('.tab-pane-new').find('#draggable table tbody tr:last-child').remove();
	}
});


// chanje width div
$('.add-class').on('click', function() {
	var no = $(this).parent('th').index()+1;
    $(this).parent('th').toggleClass('width');
    $(this).parents('table').find('tr td:nth-child('+no+')').toggleClass('width');
    $(this).find('i').toggleClass('fa-plus');
    $(this).find('i').toggleClass('fa-minus');
});


$('body').on('click', '.edit-table-btn', function() {
	$(this).parent('td').parent('tr').find('td').find('input.td-input').css('display','block');
	$(this).parent('td').parent('tr').find('td').find('span').remove();
});