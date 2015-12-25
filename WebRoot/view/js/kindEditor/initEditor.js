/**
 * ≥ı ºªØº”‘ÿ±‡º≠∆˜
 */
function initEditor(t) {
	KindEditor.ready(function(K) {
				window.editor1 = K.create('textarea[name="' + t + '"]', {
							width : '100%',
							height : '300px',
							uploadJson : path
									+ '/view/js/kindEditor/jsp/upload_json.jsp',
							fileManagerJson : path
									+ '/view/js/kindEditor/jsp/file_manager_json.jsp',
							allowFileManager : true,
							items : ['source', 'fullscreen', 'lineheight',
									'undo', 'redo', 'cut', 'copy', 'paste',
									'plainpaste', 'wordpaste', '|',
									'justifyleft', 'justifycenter',
									'justifyright', 'justifyfull',
									'insertorderedlist', 'insertunorderedlist',
									'indent', 'outdent', 'subscript',
									'superscript', '/', 'selectall', 'title',
									'fontname', 'fontsize', 'forecolor',
									'hilitecolor', '|', 'textcolor', 'bgcolor',
									'bold', 'italic', 'underline',
									'strikethrough', 'removeformat', '|',
									'table', 'image', 'advtable', 'hr', 'link',
									'unlink'],
							afterCreate : function() {
								var self = this;
								K.ctrl(document, 13, function() {
									self.sync();
										// addNewsEditSubmit(self);
									});
								K.ctrl(self.edit.doc, 13, function() {
									self.sync();
										// addNewsEditSubmit(self);
									});
								// $("#form1").bind('submit',function(){
								// self.sync();
								// addNewsEditSubmit();
								// });
							}
						});
			});

}