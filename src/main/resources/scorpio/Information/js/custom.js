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

/*var t = 4;
$('body').on('click', '#tonnage .pagination li', function() {
	$('#tonnage table tbody').find('tr:first-child td:first-child').text('id');
	var getTr = $('#tonnage table tbody').find('tr:first-child').html();
	$('#tonnage table tbody').html('');
	var table = '';
	for (var i = 1; i < 5; i++) {
		t++;
		var getTrL = getTr;
		getTrL = getTrL.replace('id', t);
		table1 = '<tr>'+ getTrL +'</tr>';
		table = table + table1;
	}
	$('#tonnage table tbody').append(table);
});

$('body').on('click', '#tonnage .pagination li', function() {
	$('#tonnage .pagination li').removeClass('active');
	$(this).addClass('active');
});
*/
/*var t1 = 4;
$('body').on('click', '#spot .pagination li', function() {
	$('#spot table tbody').find('tr:first-child td:first-child').text('id');
	var getTr = $('#spot table tbody').find('tr:first-child').html();
	$('#spot table tbody').html('');
	var table = '';
	for (var i = 1; i < 5; i++) {
		t1++;
		var getTrL = getTr;
		getTrL = getTrL.replace('id', t1);
		table1 = '<tr>'+ getTrL +'</tr>';
		table = table + table1;
	}
	$('#spot table tbody').append(table);
});

$('body').on('click', '#spot .pagination li', function() {
	$('#spot .pagination li').removeClass('active');
	$(this).addClass('active');
});*/

/*var t2 = 2;
$('body').on('click', '#time-charter-report .pagination li', function() {
	$('#time-charter-report table tbody').find('tr:first-child td:first-child').text('id');
	var getTr = $('#time-charter-report table tbody').find('tr:first-child').html();
	$('#time-charter-report table tbody').html('');
	var table = '';
	for (var i = 1; i < 3; i++) {
		t2++;
		var getTrL = getTr;
		getTrL = getTrL.replace('id', t2);
		table1 = '<tr>'+ getTrL +'</tr>';
		table = table + table1;
	}
	$('#time-charter-report table tbody').append(table);
});

$('body').on('click', '#time-charter-report .pagination li', function() {
	$('#time-charter-report .pagination li').removeClass('active');
	$(this).addClass('active');
});*/

/*var t3 = 2;
$('body').on('click', '#brokertc-rate .pagination li', function() {
	$('#brokertc-rate table tbody').find('tr:first-child td:first-child').text('id');
	var getTr = $('#brokertc-rate table tbody').find('tr:first-child').html();
	$('#brokertc-rate table tbody').html('');
	var table = '';
	for (var i = 1; i < 3; i++) {
		t3++;
		var getTrL = getTr;
		getTrL = getTrL.replace('id', t3);
		table1 = '<tr>'+ getTrL +'</tr>';
		table = table + table1;
	}
	$('#brokertc-rate table tbody').append(table);
});

$('body').on('click', '#brokertc-rate .pagination li', function() {
	$('#brokertc-rate .pagination li').removeClass('active');
	$(this).addClass('active');
});*/

var t4 = 5;
$('body').on('click', '#error-list .pagination li', function() {
	$('#error-list table tbody').find('tr:first-child td:first-child').text('id');
	var getTr = $('#error-list table tbody').find('tr:first-child').html();
	$('#error-list table tbody').html('');
	var table = '';
	for (var i = 1; i < 6; i++) {
		t4++;
		var getTrL = getTr;
		getTrL = getTrL.replace('id', t4);
		table1 = '<tr>'+ getTrL +'</tr>';
		table = table + table1;
	}
	$('#error-list table tbody').append(table);
});

$('body').on('click', '#error-list .pagination li', function() {
	$('#error-list .pagination li').removeClass('active');
	$(this).addClass('active');
});


$('.email-link').click(function(){
	$('.disable-email').trigger('click');
});