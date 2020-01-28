$(document).ready(function() {
    $(document).on('mouseover mouseout', '.non_favs li', function(){
        $('.add', this).toggleClass('favourite');
    });

    $requestRunning = false;
    $(document).on('click', '.non_favs .add', function() {
        if ($requestRunning) {
            return; // Don't do anything if an AJAX request is running
        }

        $this = $(this);
        $this_li = $this.closest('li');
        $id = $this_li.attr('id').split("_");
        $id = $id[1];
        $description = $this.siblings('.description').text();
      //  $title = $this.siblings('h3').text();
        $title = $this.siblings('.title').text();

        $.ajax({
        	url: $contextPath+"app/movie/"+$id+"/fav",
            type: "POST",
            timeout: 30000,
            'beforeSend': function(){
                $requestRunning = true;
                $('.highlight').removeClass('highlight');
                $('.loader_large').removeClass('hidden');
            	$('html').not('.loader_large').addClass('dim');
            }, // End beforeSend
            'success': function(response) {
            	if(response=='OK'){
            		 $this_li.remove();
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

                     $this_added =$('li#fav_'+$id);
                     $this_added.addClass('highlight');
                    /*
                     $('.favs li').mouseover(function(){
                    	 $('.highlight').removeClass('highlight');
                     });*/
            	}else {
            		alert(response);
            	}

            }, // End success
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

    $(document).on('mouseover','.favs li',function(){
    	$(this).removeClass('highlight');
    });

}); // End document ready

