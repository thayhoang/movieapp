$(document).ready(function() {
	
	$(document).on('focus', '.newdatarow', function() {
        $('.insert').removeClass('hidden');
    });
	
	$(document).on('click', '.insert', function() {
		
		$this = $(this);
		$username = $('input.newdata[name="username"').val();
		$password = $('input.newdata[name="password"').val();
		$role = $('input.newdata[name="role"').val();
		$fullName = $('input.newdata[name="fullName"').val();
		$dob = $('input.newdata[name="dob"').val();
		
		var user = new Object();
		user.username= $username;
		user.password=$password;
		user.fullName =$fullName;
		user.role=$role;
		user.dob=$dob;
		
		if ($username == "" || $password == ""||$role=="" ||$dob=="") {
			alert("Please provide enough information");
			return;
		}
		
		$.ajax({
			url : $contextPath+"/admin/manageusers/add",
			type : "POST",
			timeout: 3000,
			data : user,
			'beforeSend' : function() {
				$this.removeClass('insert').addClass('loader_small');
				//$('.delete').removeClass('success').addClass('hidden');
			},
			'success' :function(response){
				if ($.isNumeric(response)){
					//$('input.newdata').val('');
				 	$output = "<tr id='user_"+ response +"' class='datarow'>";
	                $output += "<td><input class='data' type='text' name='username' value='" + $username + "'></td>";
	                $output += "<td><input class='data' type='text' name='password' value='" + $password + "'></td>";
	                $output += "<td><input class='data' type='text' name='role' value='" + $role + "'></td>";
	                $output += "<td><input class='data' type='text' name='fullName' value='" + $fullName + "'></td>";
	                $output += "<td><input class='data' type='text' name='dob' value='" + $dob + "'></td>";
	                $output += "<td class='deletecell'><div class='delete hidden'></div></td>";
	                $output += "</tr>";
	                
	                $('.admin_table tr:last').before($output);
	            
	               // $this.removeClass('loader_small').addClass('insert');
				}
				else{
					alert("from server : " + response);
				}
	              
			},
			'error' : function(){
				alert("Error");
            },
			'complete' : function(){
				$('input.newdata').val('');
				$this.removeClass('loader_small').addClass('insert');
            }

		});
	});
	
/////////// Remove	
	 $(document).on('mouseover mouseout', '.deletecell', function(){
	        $('.delete', this).toggleClass('hidden');
	     //   $('.success').removeClass('success');
	    });
	    
	    $requestRunning = false;
	    $(document).on('click', '.delete', function() {
	    	
	        if ($requestRunning) {
	            return; // Don't do anything if an AJAX request is running
	        }

	        $this = $(this);
	        $id = $(this).closest('tr').attr('id').split("_");
	        $id = $id[1];

	        $.ajax({
	        	url : $contextPath+"/admin/manageusers/delete",
				type : "POST",
	            data: {
	                'id' : $id,
	            }, // End data
	            'beforeSend': function(){
	                $this.removeClass('delete').addClass('loader_small');
	                
	              //  $this_li.remove();
	            }, // End beforeSend
	            'success': function(response) {
	            	
	            	if(response=='OK'){
	            		$('tr#user_'+$id).remove();
	            	}
	            	else{
						alert("from server : " + response);
					}
		            
	              //  $requestRunning = false;
	                
	               
	            }, // End success
	            
	            'error' : function(){
					alert("Error");
	            },
				'complete' : function(){
					  $requestRunning = false;
		          	  $('.loader_small').addClass('delete').removeClass('loader_small');
	            },
	        }); // End AJAX
	        
	    }); // End movie_list click function

	    
	  ///Update exiting users
		$(".data").each(function() {
		    $(this).data("lastVal", this.value);
		});
		$(document).on('focusout', '.data', function() {
	         $this = $(this);
	         $lastVal = $this.data("lastVal");
	         $currentVal = this.value;
	 		 $id = $this.closest('tr').attr('id').split("_");
	 		 $id = $id[1];
	 	
	 		// $('div.debug2').text( $id+ "----"+$currentVal+ "---" + $lastVal  );
	 		/*var myObj = {}; // {} = new Object();
	 		myObj['a'] = 'b';
	 		myObj['c'] = 'd';*/
	 		/*var user = new Object();
	 		user.id= $id;*/
	 		//user.$thisField= $currentVal;
	 	//	user.fullName =$fullName;
	 		
	 		 if( $currentVal!= $lastVal){
	 			 
	 			$thisField = $this.attr('name');
	 			var data = {};
		 		data['id'] = $id;
		 		data['fieldName']=$thisField;
		 		data[$thisField]=$currentVal;
		 		
	 			$.ajax({
					url: $contextPath+"/admin/manageusers/update",
					type: "POST",
					timeout: 3000,
					data: data, // End data
				/*	data: {
						'id' : $id,
						'fieldName' :$thisField,
						'value': $currentVal
					}, // End data
*/					'beforeSend' : function() {
						$('.success').removeClass('success').addClass('delete hidden');
						$('#user_' + $id + ' .deletecell div').removeClass('delete hidden').addClass('loader_small');
					}, // End beforeSend
					
					'success' : function(response) {
						if(response=="OK"){
							$('.loader_small').addClass('success');
							
							 $this.data("lastVal", $currentVal);
							 
							$(document).on('mouseover', '.deletecell', function() {
								$('.success', this).addClass('delete').removeClass('hidden success');
							}); 
						}
						else {
							alert(response);
							$(".data").each(function() {
							    $(this).val($(this).data("lastVal"));
							});
							//$this.val($lastVal);
						}
						

					}, // End success
					'error' : function(){
						alert("Error");
//						/$this.val($lastVal);
						$(".data").each(function() {
						    $(this).val($(this).data("lastVal"));
						});
		            },
					'complete' : function(){
						$('.loader_small').removeClass('loader_small');
		            }
				}); // End AJAX
	 		 }
	  });
}); // End document ready

