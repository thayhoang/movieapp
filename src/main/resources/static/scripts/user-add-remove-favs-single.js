$(document).ready(function() {
    $requestRunning = false;
    $(document).on('click', '.actions .add', function() {
        if ($requestRunning) {
            return; // Don't do anything if an AJAX request is running
        }

        $this = $(this);
        $id = $this.attr('id').split("_");
        $id = $id[1];
        $description = $('p.description').text();
        $title = $('h3.title').text();

        
        $.ajax({
        	url: $contextPath+"app/movie/"+$id+"/fav",
            type: "POST",
            timeout: 3000,
            'beforeSend': function(){
                $requestRunning = true;
                $('html').not('.loader_large').addClass('dim');
                $('.loader_large').removeClass('hidden');
                
            }, // End beforeSend
            'success': function(response) {
            	if(response=='OK'){
            	 $output = "<li title='" + $description + "' id='fav_" + $id + "'>";
                 $output += "<a href='"+$contextPath+"app/movie/" + $id  + "'>";
                 $output += $title;
                 $output += "</a></li>";                
                      
                 $('ul.favs').append($output);
                 
                 $(".favs li#fav_" + $id).draggable({
                   helper: 'clone'  ,     
                   start: function( event, ui ) {
                   $('.trash').addClass('trash_hover');
                   },
                   stop: function( event, ui ) {
                   $('.trash').removeClass('trash_hover');
                   }
                 });  
                 
                $this.html("<p>Remove from favorites<p>").removeClass("add").addClass("remove");	
            	}
            	else {
            		alert(response);
            	}
              
            },// End success
            'error' : function(){
            	alert("Error");
            },
            'complete' : function(){
            	$requestRunning = false;
            	$('.loader_large').addClass('hidden');
            	$('html').not('.loader_large').removeClass('dim');
            }
        }); // End AJAX
        
    }); // End movie_list click function
    
    $(document).on('click', '.actions .remove', function() {
    	
        if ($requestRunning) {
            return; // Don't do anything if an AJAX request is running
        }

        $this = $(this);
        $id = $this.attr('id').split("_");
        $id = $id[1];
        
        $.ajax({
            url: $contextPath+"app/movie/"+$id+"/nonfav",
        	type: "POST",
            timeout: 3000,
            'beforeSend': function(){
                $requestRunning = true;
                $('html').not('.loader_large').addClass('dim');
                $('.loader_large').removeClass('hidden');
            }, // End beforeSend
            'success': function(response) {
            	if(response=='OK'){
                     $this.html("<p>Add to favorites<p>").removeClass("remove").addClass("add");
                     $('li#fav_'+$id).remove();
            	}
            	else{
            		alert(response);
            	}
               
            },
            'error' : function(){
            	alert("Error");
            },
            'complete' : function(){
            	 $requestRunning = false;
            	 $('.loader_large').addClass('hidden');
             	 $('html').not('.loader_large').removeClass('dim');
            }// End success
        }); // End AJAX
        
    }); // End movie_list click function
}); // End document ready

