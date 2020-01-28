$(document).ready(function(){
    
    $(document).on('focus', '.newdatarow', function() {
        $('.insert').removeClass('hidden');
    });
    
    $(document).on('click', '.insert', function() {
        $this = $(this);
        
        $title = $('input.newdata[name="title"]').val();
        $description = $('input.newdata[name="description"]').val();
        $trailer = $('input.newdata[name="trailer"]').val();
        
        if ($title=="") {
            alert("Please enter a movie title");
            return;
        }
        
        var movie =new Object();
        movie.title = $title;
        movie.description =$description;
        movie.trailer=$trailer;
        
        $.ajax({
            url: $contextPath+"admin/movie/add",
            type: "POST",
        	timeout: 30000,
            data: movie,
            'beforeSend' : function() {
                $this.removeClass('insert').addClass('loader_small');
                $('.delete').removeClass('success').addClass('hidden');
            },
            'success' : function(response) {
            	if ($.isNumeric(response)){
            		  $movieID = response;

                    //  $('input.newdata').val('');
                      
                      $title = $title.replace(/'/g, "&apos;");
                      $description = $description.replace(/'/g, "&apos;");                
                      
                      $output = "<tr id='movie_" + $movieID + "' class='datarow'>";
                      $output += "<td><input class='data' type='text' name='title' value='" + $title + "'></td>";
                      $output += "<td><input class='data description' type='text' name='description' value='" + $description + "'></td>";
                      $output += "<td><input class='data' type='text' name='trailer' value='" + $trailer + "'></td>";
                      $output += "<td class='deletecell'><div class='delete hidden'></div></td>";
                      $output += "</tr>";
                      
                      $('.admin_table tr:last').before($output);
            	}
            	else{
            	//	$('input.newdata').val('');
            		alert("from server : " + response);
            	}
              
                //$('.delete:last').removeClass('loader_class').addClass('success');
             //   $this.removeClass('loader_small').addClass('insert');
               /* 
                $(document).on('mouseover', '.deletecell', function() {
                   $('.delete', this).removeClass('hidden success');
                });*/
            },
            'error' : function(){
           	 	//$('input.newdata').val('');
				alert("Error");
            },
			'complete' : function(){
				$('input.newdata').val('');
				$this.removeClass('loader_small').addClass('insert');
            }
        });
    
    });
    
    ///Delete
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
        	url : $contextPath+"admin/movie/delete",
			type : "POST",
			timeout: 30000,
            data: {
                'id' : $id,
            }, // End data
            'beforeSend': function(){
                $this.removeClass('delete').addClass('loader_small');
                
              //  $this_li.remove();
            }, // End beforeSend
            'success': function(response) {
            	if(response=='OK'){
            		$('tr#movie_'+$id).remove();
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
          }
        }); // End AJAX
        
    }); // End movie_list click function

    
 // Updates existing movies
    $(".data").each(function() {
	    $(this).data("lastVal", this.value);
	});
	$(document).on('focusout', '.data', function() {
         $this = $(this);
         $lastVal = $this.data("lastVal");
         $currentVal = this.value;
 		 $id = $this.closest('tr').attr('id').split("_");
 		 $id = $id[1];
 	//	 $this.data("lastVal", $currentVal);
 	//	 $('div.debug2').text( $id+ "----"+$currentVal+ "---" + $lastVal  );
 		 
 		 if( $currentVal!= $lastVal){
 			 
 			$thisField = $this.attr('name');
 			var data = {};
	 		data['id'] = $id;
	 		data[$thisField]=$currentVal;
	 	
 			$.ajax({
				url: $contextPath+"admin/movie/update",
				type: "POST",
				timeout: 30000,
				data:data,
			'beforeSend' : function() {
					$('.success').removeClass('success').addClass('delete hidden');
					$('#movie_' + $id + ' .deletecell div').removeClass('delete hidden').addClass('loader_small');
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
						//$this.val($lastVal);
						$(".data").each(function() {
						    $(this).val($(this).data("lastVal"));
						});
					}
					

				}, // End success
				'error' : function(){
					alert("Error");
				//	$this.val($lastVal);
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
});