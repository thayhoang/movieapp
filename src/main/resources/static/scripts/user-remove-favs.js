$(document).ready(function() {
                  
    $('.favs li').draggable({
        helper: 'clone',
        start: function( event, ui ) {
        	 $('.trash').addClass('trash_hover');
        },
        stop: function( event, ui ) {
        	$('.trash').removeClass('trash_hover');
        }
    });
    
    

    $('.trash').droppable({
        accept: '.favs li', 
        tolerance: "touch",
        drop: function(event, ui) {
            $this = $(ui.draggable);
            $id = $this.attr('id').split("_");
            $id = $id[1];
            $title = $this.text();
            $description = $this.attr('title');
            
            $.ajax({
                url: $contextPath+"/user/movies/remove",
                type: "POST",
                timeout: 3000,
                data: {
                    'movieId' : $id
                }, 
                'beforeSend': function() {
                	$('.loader_large').removeClass('hidden');
                	$('html').not('.loader_large').addClass('dim');
                	$('.success').removeClass('success');
                }, 
                'success': function(response) {
                	if(response=='OK'){
                		 $this.remove();
                		 $output = "<li id='nonfav_" + $id + "'>";
                         $output += "<figure>";
                         $output += "<a href='"+$contextPath+"/user/movies?id=" + $id  + "'>";
                         $output += "<img class='thumbnail' alt='" + $title + "' src='"+$contextPath+"/images-movies/" + $id + "-tn.jpg'></a>";
                         $output += "<figcaption>";
                         $output += "<div class='title'>";
                         $output += "<a href='"+$contextPath+"/user/movies?id=" + $id  + "'>";
                         $output += $title;
                         $output += "</a></div>";
                         $output += "<div class='description'>" + $description + "</div>";
                         $output += "<div class='add success'></div>";
                         $output += "</figcaption>";
                         $output += "</figure>";
                         $output += "</li>";                    
                        
                         $("ul.non_favs").prepend($output);
                         
                     /*    $('.non_favs li').mouseover(function(){
                        	$('.success').removeClass('success');
                         });*/
                         
                        $singleId = $('.movie_single').attr('id');
                        if($singleId!=null){
                        	 $singleId= $singleId.split("_");
                        	 $singleId= $singleId[1];
                        	 if($singleId==$id){
                        		 $('.remove').html("<p>Add to favorites<p>").removeClass("remove").addClass("add");
                        	 }
                        }
                	}else {
                		alert(response);
                	}
                	
                }, 
                'error' : function(){
                	alert("Error");
	            },
                'complete' : function(){
                	$('.loader_large').addClass('hidden');
                	$('html').not('.loader_large').removeClass('dim');
                }
            });  
                          
           
        } 
    });
    
    $(document).on('mouseover','.non_favs li',function(){
    	$('.success',this).removeClass('success');
    });
}); 

