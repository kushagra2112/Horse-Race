
var conditionStatement = [];
var conditionObject = {} ;
var limit = "";
var sheetName = "";

var sheetNameList = [];
var conditionCountList = {}

var conditionIndex = 1; 

$(document).ready(function() {
    /*
     * $("#uploadResult").click(function() { console.log("button clicked"); var
     * formData = new FormData(); formData.append('file',
     * $('#resultFile')[0].files[0]); $.ajax({ url : './horse/uploadResult',
     * type : 'POST', data : formData, processData: false, // tell jQuery not to
     * process the data contentType: false, // tell jQuery not to set
     * contentType success : function(data) { console.log(data); alert(data); }
     * }); });
     */
	
	$('select').selectpicker();
	$('.loader').hide();
	//$('#datetimepicker1').datetimepicker();
	
/*	
	$('#GetRefreshConditions').click(function(){
		$('.loader').show();
	    $.ajax({
	        url: './horse-racing/getData',
	        type: 'GET',
	        success: function(data) {
	            console.log("data received");
	            // alert(data);
	            var index = 1;
	            var heading = "";
	            Object.keys(data).forEach(function(key) {
	            	
	            	sheetNameList.push(key);
	                heading = heading + '<div class="card">';
	                heading = heading + '<div class="card-header" id="headingOne">';
	                heading = heading + '<h5 class="mb-0">';
	                heading = heading + '<button class="btn btn-link" data-toggle="collapse"';
	                heading = heading + 'data-target="#collapse' + index + '"';

	                if (index == 1) {
	                    heading = heading + 'aria-expanded="true"';
	                } else {
	                    heading = heading + 'aria-expanded="false"';
	                }

	                heading = heading + 'aria-controls="collapse' + index + '">' + key + '</button>';
	                heading = heading + '</h5>';
	                heading = heading + '</div>';

	                heading = heading + '<div id="collapse' + index + '" class="collapse ';
	                if (index == 1) {
	                    heading = heading + 'show"';
	                } else {
	                    heading = heading + ' hide"';
	                }

	                heading = heading + 'aria-labelledby="headingOne" data-parent="#accordion">';

	                var sheetData = data[key];

	                var leftBar = "";
	                var tableBar = "";
	                var conditionIndex = 1;
	                 

	                Object.keys(sheetData).forEach(function(condition) {
	                	
	                	if(sheetData[condition].length == 0){
	        				
	        				var parameter  = {};
	                        
	                        parameter['condition'] = 'Condition ' + conditionIndex + ' : ' + condition;
	                        parameter['value'] = "";
	                        parameter['key'] = key;
	                        parameter = JSON.stringify(parameter);
	                        
	                        leftBar = '<button class = "btn btn-primary btn-block mt-10" onclick = \'getNoHorsePrompt('+ parameter +')\' > Condition ' + conditionIndex + ' </button>';
	            			
			            	tableBar = tableBar + '<lable class = "form-control"> ';
			                tableBar = tableBar + condition;
			                tableBar = tableBar + ' </lable>';
			            	
			                
			                tableBar = tableBar + '<lable class = "form-control mt-25 mb-25" style= "color:red"> ';
			                tableBar = tableBar + "No Horse Qualified Given Conditions Hence Table Is Not Loaded";
			                tableBar = tableBar + ' </lable>';
			                
	        			}else{
		                    var value = sheetData[condition]
		                    var parameter  = {};
		                    
		                    parameter['condition'] = 'Condition ' + conditionIndex + ' : ' + condition;
		                    parameter['value'] = value;
		                    parameter['key'] = key;
		                    parameter = JSON.stringify(parameter);
		                    
		                    leftBar = leftBar + '<button class = "btn btn-primary btn-block mt-10" onclick = \'getTable('+ parameter +')\' > Condition ' + conditionIndex + ' </button>';
		                    
		                    tableBar = tableBar + '<lable class = "form-control"> ';
		                    tableBar = tableBar + 'Condition ' + conditionIndex + ' : ' + condition;
		                    tableBar = tableBar + ' </lable>';
		                    tableBar = tableBar + '<table class="table table-striped">';
		                    tableBar = tableBar + '<thead>';
		                    tableBar = tableBar + '<tr>';
		                    tableBar = tableBar + '<th scope="col">#</th>';
		
		                    Object.keys(value[0]).forEach(function(key2) {
		                        tableBar = tableBar + '<th scope="col">' + key2 + '</th>';
		                    });
		                    tableBar = tableBar + '</tr>';
		                    tableBar = tableBar + '</thead><tbody>';
		
		                    for (var row = 0; row < value.length; row++) {
		
		                        tableBar = tableBar + '<tr style= "font-size : 15px;">';
		                        tableBar = tableBar + '<td scope="col">' + (row + 1) + '</td>';
		                        Object.keys(value[row]).forEach(function(key2) {
		                            tableBar = tableBar + '<td scope="col">' + value[row][key2] + '</td>';
		                        });
		                        tableBar = tableBar + '</tr>';
		
		                    }
		                    tableBar = tableBar + '<tbody> </table>';
		                    
	        			}
	                });
	                
	                conditionCountList[key] = conditionIndex + 1;
	                
	                heading = heading + '<div class = "row col-md-12 mt-25"><div class ="col-md-2 mb-10">';
	                heading = heading + '<div class ="col-md-12 mb-10" id = "'+ key +'_condition">';
	                heading = heading + leftBar + '';
	                heading = heading + '</div><div class ="col-md-12 mb-10 mt-10">';
	                heading = heading + '<button type="button" class="btn btn-primary btn-block" onclick= \' openConditionModel("'+key+'") \'>Add Condition</button></div>';
	                heading = heading + '</div> <div class = "col-md-10" id = "'+ key +'_info" >';
	                heading = heading + tableBar;
	                heading = heading + '</div></div>';
	                
	                heading = heading + '</div>';
	                heading = heading + '</div>';

	                index = index + 1;

	            });
	            $('#horseAnalysis').empty();
	            $('#horseAnalysis').append(heading);
	            
	            var trackId = "";
	            trackId = trackId + '<option value="" selected> Select your sheet name </option>';
	            trackId = trackId + '<option value="All">All</option>';
	            Object.keys(conditionCountList).forEach(function(key) {
	            	trackId = trackId + '<option value="'+ key +'">'+key+'</option>';
	            });
	            
	            $('#trackId').empty();
	            $('#trackId').append(trackId);
	            $('select').selectpicker("refresh");
	            $('.loader').hide();
	        },
	        error: function() {
	            console.log("Something went wrong")
	        }
	    });

		
		
	});*/
	    
	$("#addConditions").click(function(){
		$('#addCondition').prop('disabled', false);
		$('#dataValidatedLabel').hide();
		$('#generated_sheetName').hide();
		$('#generated_Limit').hide();
		$('#generated_condition').hide();
		$('#generated_Note').hide();
		$('#addCondition').prop('disabled', true);
		
		//$('#trackId').selectpicker('val', sheetName);
		$('.conditionModel').modal({
		  keyboard: true,
		  show : true,
		  focus : true
		});
		
	});
    
    $("#addCondition").click(function(){
    	
    	$('.conditionModel').modal('hide');
    	
    	$('.loader').show();
    	$.ajax({
            url: './horse-racing/addCondition',
            type: 'POST',
            datatype : 'json',
            data :{ 'conditionString' : JSON.stringify(conditionStatement)},
            success: function(data) {
            	console.log(data);
            		
        		var leftBar = "";
        		var parameter  = {};
        		
        		parameter['condition'] = 'Condition ' + conditionIndex + ' : ' + data['condition'];
        		parameter['statsTable'] = data['winRaceStats'];
        		parameter['winningTabel'] = data['winners'];
        		parameter['daysReport'] = data['daysReport'];
        		parameter['stikes'] = data['stikes'];
        		parameter['conditionStatement'] =  JSON.stringify(conditionStatement);
        		
        		var parameter_str = JSON.stringify(parameter);
        		leftBar = '<button class = "btn btn-primary btn-block mt-10" onclick = \'getTable('+ parameter_str +')\' > Condition ' + conditionIndex + ' </button>';
        		conditionIndex = conditionIndex + 1;
        		var currenthtml = $('#conditionButton').html();

                $('#conditionButton').empty();
                $('#conditionButton').append(currenthtml + leftBar);
                getTable(parameter);
                
            	$('.loader').hide();
            	
            },
            error: function(error){
            	alert("Something went wrong");
            }
    	});
    	
    });
    
    // Validating Query Generation
    $('#validateCondition').click(function(){
		
		var conditionString = "";
		var noteString = "";
		conditionStatement = [];
		conditionObject = {};
		
		
		/*limit = 25;
		//sheetName =  $('#trackId').val().toString();
		
		
		//Limit 
		
		var numberOfTopRows  = $('#speed_toprow_value').val().trim();
		
		if(numberOfTopRows == ""){
			noteString = noteString + "Limit of values was blank so taking default 25 as the value <br>";
			limit = 25;
		}else if(isNaN(numberOfTopRows)){
			noteString = noteString + "Limit of values was not a number so taking default 25 as the value <br>";
			limit = 25;
		}else{
			limit = Number(numberOfTopRows);
		}*/
		
		//Days Since Run
		
		var daySinceRunSelect = $('#speed_daysincerun_condition').val().trim();
		var daySinceRunInput = $('#speed_daysincerun_value').val().trim();
		conditionObject = {};
		
		conditionObject['conditionParameter'] = "daySinceRun";

		if(daySinceRunSelect == ""){
		
		
		}else if(daySinceRunSelect != ""  && daySinceRunInput == ""){
			noteString = noteString + " Since no value was present for day since run parameter we ignored it <br>";
		}else{
			if(daySinceRunSelect == "between"){
				if(daySinceRunInput.includes(" and ") ||daySinceRunInput.includes(" AND ") || daySinceRunInput.includes(" And ")){
					if(daySinceRunInput.toLowerCase().split(" and ").length == 2){
						conditionObject['conditionStatement'] = "between";
						conditionObject['conditionValue'] = daySinceRunInput;
						conditionStatement.push(conditionObject);
						
						conditionString = conditionString + "a.days_since_run " + daySinceRunSelect + " " + daySinceRunInput + " ; ";
					}else{
						noteString = noteString + " Since Day since run does not have correct upper and lower limit IGNORED <br>";
					}
				}else{
						noteString = noteString + " Since Day since run does not have correct upper and lower limit IGNORED <br>";
				}
			}else{
				if(isNaN(daySinceRunInput)){
					noteString = noteString + " Since Day since run does not have a correct value we ignored it <br>";
				}else{
					conditionObject['conditionStatement'] = daySinceRunSelect;
					conditionObject['conditionValue'] = daySinceRunInput;
					conditionStatement.push(conditionObject);
					conditionString = conditionString +  "a.days_since_run " + daySinceRunSelect + " " + daySinceRunInput + " ; ";
				}				
			}
		}
		
		// Selected Track
		
		
		var selectTrack = $('#speed_course_condition').val();
		conditionObject = {};
		
		conditionObject['conditionParameter'] = "course";
		
		if(selectTrack != ""){
			conditionObject['conditionStatement'] = "=";
			conditionObject['conditionValue'] = selectTrack;
			conditionStatement.push(conditionObject);
			conditionString = conditionString + " a.course = \'" + selectTrack +"\' ; ";
		}
	
		//Race Distance
		
		/*var raceDistanceSelect = $('#speed_racedistance_condition').val().trim();
		var raceDistanceInput = $('#speed_racedistance_value').val().trim();
		conditionObject = {};
		
		conditionObject['conditionParameter'] = "raceDistance";
		
		if(raceDistanceSelect == ""){
			
		}else if(raceDistanceSelect != ""  && raceDistanceInput == ""){
			noteString = noteString + " Since no value was present for race distance parameter we ignored it <br>";
		}else{
			if(raceDistanceSelect == "between"){
				if(raceDistanceInput.includes(" and ") ||raceDistanceInput.includes(" AND ") || raceDistanceInput.includes(" And ")){
					if(raceDistanceInput.toLowerCase().split(" and ").length == 2){
						conditionObject['conditionStatement'] = "between";
						conditionObject['conditionValue'] = raceDistanceInput;
						conditionStatement.push(conditionObject);
						conditionString = conditionString + "a.race_distance between " + raceDistanceInput + " ; ";
					}else{
						noteString = noteString + " Since race distance does not have correct upper and lower limit IGNORED <br>";
					}
				}else{
						noteString = noteString + " Since race distance does not have correct upper and lower limit IGNORED <br>";
				}
			}else{
				if(isNaN(raceDistanceInput)){
					noteString = noteString + " Since race distance does not have a correct value we ignored it <br>";
				}else{
					conditionObject['conditionStatement'] = raceDistanceSelect;
					conditionObject['conditionValue'] = raceDistanceInput;
					conditionStatement.push(conditionObject);
					conditionString = conditionString + "a.race_distance " + raceDistanceSelect + " " + Number(raceDistanceInput) + " ";
					
				}				
			}
		}*/
		
		// official going
		
		var officialGoing = $('#speed_officialgoing_condition').val().toString();
		conditionObject = {};
		conditionObject['conditionParameter'] = "officialGoing";
		
		if(officialGoing != ""){
			conditionObject['conditionStatement'] = "=";
			conditionObject['conditionValue'] = officialGoing;
			conditionStatement.push(conditionObject);
			conditionString = " a.official_going is anyone of  (" + officialGoing +") ";
		}
		
		//Horse Class
		
		var horseClassSelect = $('#speed_class_condition').val().toString();
		conditionObject = {};
		conditionObject['conditionParameter'] = "classCaptured";
		
		if(horseClassSelect != ""){
			conditionObject['conditionStatement'] = "=";
			conditionObject['conditionValue'] = horseClassSelect;
			conditionStatement.push(conditionObject);
			conditionString = " a.class = " + horseClassSelect +" ; ";
		}
		
		// Race Surface
		
		/*var raceSurface = $('#speed_Surface_condition').val();
		conditionObject = {};
		conditionObject['conditionParameter'] = "raceSurface";
		
		if(raceSurface != ""){
			conditionObject['conditionStatement'] = "=";
			conditionObject['conditionValue'] = raceSurface;
			conditionStatement.push(conditionObject);
			conditionString = conditionString + " a.surface = \'" + raceSurface +"\' ;";
		}
		*/
		//Horse Age
		
		var speedPointSelect = $('#result_speedpoint_condition').val().trim();
		var speedPointInput = $('#result_speedpoint_value').val().trim();
		conditionObject = {};
		conditionObject['conditionParameter'] = "speedPoint";
		
		if(speedPointSelect == ""){
			
		}else if(speedPointSelect != ""  && speedPointInput == ""){
			noteString = noteString + " Since no value was present for speed point parameter we ignored it <br>";
		}else{
			if(speedPointSelect == "between"){
				if(speedPointInput.includes(" and ") ||speedPointInput.includes(" AND ") || speedPointInput.includes(" And ")){
					if(speedPointInput.toLowerCase().split(" and ").length == 2){
						
						conditionObject['conditionStatement'] = "between";
						conditionObject['conditionValue'] = speedPointInput;
						conditionStatement.push(conditionObject);
						conditionString = conditionString + "b.speedPoint between " + speedPointInput + " ; ";
						
					}else{
						noteString = noteString + " Since speed point does not have correct upper and lower limit IGNORED <br>";
					}
				}else{
						noteString = noteString + " Since speed point does not have correct upper and lower limit IGNORED <br>";
					}
			}else{
				if(isNaN(speedPointInput)){
					noteString = noteString + " Since speed point does not have a correct value we ignored it <br>";
				}else{
					conditionObject['conditionStatement'] = speedPointSelect;
					conditionObject['conditionValue'] = speedPointInput;
					conditionStatement.push(conditionObject);
					conditionString = conditionString + "b.speedPoint " + speedPointSelect + " " + speedPointInput + " ; ";
				}				
			}
		}
		
		
		//Distance Furlongs
		
		var distanceFurlongSelect = $('#result_distancefurlongs_condition').val().trim();
		var distanceFurlongInput = $('#result_distancefurlongs_value').val().trim();
		conditionObject = {};
		conditionObject['conditionParameter'] = "distanceFurlongs";
		
		if(distanceFurlongSelect == ""){
			
		}else if(distanceFurlongSelect != ""  && distanceFurlongInput == ""){
			noteString = noteString + " Since no value was present for distance furlongs parameter we ignored it <br>";
		}else{
			if(distanceFurlongSelect == "between"){
				if(distanceFurlongInput.includes(" and ") ||distanceFurlongInput.includes(" AND ") || distanceFurlongInput.includes(" And ")){
					if(distanceFurlongInput.toLowerCase().split(" and ").length == 2){
						
						conditionObject['conditionStatement'] = "between";
						conditionObject['conditionValue'] = distanceFurlongInput;
						conditionStatement.push(conditionObject);
						conditionString = conditionString + "b.distFurlongs between " + distanceFurlongInput + " ; ";
						
					}else{
						noteString = noteString + " Since distance furlongs does not have correct upper and lower limit IGNORED <br>";
					}
				}else{
						noteString = noteString + " Since distance furlongs does not have correct upper and lower limit IGNORED <br>";
					}
			}else{
				if(isNaN(distanceFurlongInput)){
					noteString = noteString + " Since distance furlongs does not have a correct value we ignored it <br>";
				}else{
					conditionObject['conditionStatement'] = distanceFurlongSelect;
					conditionObject['conditionValue'] = distanceFurlongInput;
					conditionStatement.push(conditionObject);
					conditionString = conditionString + "b.distFurlongs " + distanceFurlongSelect + " " + distanceFurlongInput + " ;";
					
				}				
			}
		}
		
		
		//Rank Filter
		
		var rankSelect = $('#result_horserank_condition').val().trim();
		var rankInput = $('#result_horserank_value').val().trim();
		conditionObject = {};
		conditionObject['conditionParameter'] = "rank";
		
		if(rankSelect == ""){
			
		}else if(rankSelect != ""  && rankInput == ""){
			noteString = noteString + " Since no value was present for rank parameter we ignored it <br>";
		}else{
			if(rankSelect == "between"){
				if(rankInput.includes(" and ") ||rankInput.includes(" AND ") || rankInput.includes(" And ")){
					if(rankInput.toLowerCase().split(" and ").length == 2){
						
						conditionObject['conditionStatement'] = "between";
						conditionObject['conditionValue'] = rankInput;
						conditionStatement.push(conditionObject);
						conditionString = conditionString + "b.rank between " + rankInput + " ;";
						
					}else{
						noteString = noteString + " Since rank does not have correct upper and lower limit IGNORED <br>";
					}
				}else{
						noteString = noteString + " Since rank does not have correct upper and lower limit IGNORED <br>";
					}
			}else{
				if(isNaN(rankInput)){
					noteString = noteString + " Since rank does not have a correct value we ignored it <br>";
				}else{
					conditionObject['conditionStatement'] = rankSelect;
					conditionObject['conditionValue'] = rankInput;
					conditionStatement.push(conditionObject);
					conditionString = conditionString + "b.rank " + rankSelect + " " + Number(rankInput) + " ; ";

				}				
			}
		}	
		
		//Placing Numerical
		
		var placingNumSelect = $('#result_finishingPosition_condition').val().trim();
		var placingNumInput = $('#result_finishingPosition_value').val().trim();
		conditionObject = {};
		conditionObject['conditionParameter'] = "finishingPosition";
		
		if(placingNumSelect == ""){
			
		}else if(placingNumSelect != ""  && placingNumInput == ""){
			noteString = noteString + " Since no value was present for placing numerical parameter we ignored it <br>";
		}else{
			if(placingNumSelect == "between"){
				if(placingNumInput.includes(" and ") ||placingNumInput.includes(" AND ") || placingNumInput.includes(" And ")){
					if(placingNumInput.toLowerCase().split(" and ").length == 2){
						
						conditionObject['conditionStatement'] = "between";
						conditionObject['conditionValue'] = placingNumInput;
						conditionStatement.push(conditionObject);
						conditionString = conditionString + "b.placingNumerical between " + placingNumInput + " ;";
						
					}else{
						noteString = noteString + " Since placing numerical does not have correct upper and lower limit IGNORED <br>";
					}
				}else{
						noteString = noteString + " Since placing numerical does not have correct upper and lower limit IGNORED <br>";
					}
			}else{
				if(isNaN(placingNumInput)){
					noteString = noteString + " Since placing numerical does not have a correct value we ignored it <br>";
				}else{
					conditionObject['conditionStatement'] = placingNumSelect;
					conditionObject['conditionValue'] = placingNumInput;
					conditionStatement.push(conditionObject);
					conditionString = conditionString + "b.placingNumerical " + placingNumSelect + " " + Number(placingNumInput) + " ; ";

				}				
			}
		}
		
		//Favorite Or Not
		
		var horseFavorite = $('#speed_fav_condition').val().trim();
		conditionObject = {};
		conditionObject['conditionParameter'] = "favorite";
		
		if(horseFavorite != ""){
			conditionObject['conditionStatement'] = "=";
			conditionObject['conditionValue'] = horseFavorite;
			conditionStatement.push(conditionObject);
			conditionString = " a.horseFavorite = " + horseFavorite +" ; ";
		}
		
		//BFSP
		
		var bfspSelect = $('#result_bfsp_condition').val().trim();
		var bfspInput = $('#result_bfsp_value').val().trim();
		conditionObject = {};
		conditionObject['conditionParameter'] = "bfsp";
		
		if(bfspSelect == ""){
			
		}else if(bfspSelect != ""  && bfspInput == ""){
			noteString = noteString + " Since no value was present for bfsp parameter we ignored it <br>";
		}else{
			if(bfspSelect == "between"){
				if(bfspInput.includes(" and ") ||bfspInput.includes(" AND ") || bfspInput.includes(" And ")){
					if(bfspInput.toLowerCase().split(" and ").length == 2){
						
						conditionObject['conditionStatement'] = "between";
						conditionObject['conditionValue'] = bfspInput;
						conditionStatement.push(conditionObject);
						conditionString = conditionString + "b.bfsp between " + bfspInput + " ; ";
						
					}else{
						noteString = noteString + " Since bfsp does not have correct upper and lower limit IGNORED <br>";
					}
				}else{
						noteString = noteString + " Since bfsp does not have correct upper and lower limit IGNORED <br>";
					}
			}else{
				if(isNaN(bfspInput)){
					noteString = noteString + " Since bfsp does not have a correct value we ignored it <br>";
				}else{
					conditionObject['conditionStatement'] = bfspSelect;
					conditionObject['conditionValue'] = bfspInput;
					conditionStatement.push(conditionObject);
					conditionString = conditionString + "b.bfsp " + bfspSelect + " " + Number(bfspInput) + "; ";

				}				
			}
		}
		
		//BFSP Place
		
		var bfspPlaceSelect = $('#result_bfspplace_condition').val().trim();
		var bfspPlaceInput = $('#result_bfspplace_value').val().trim();
		conditionObject = {};
		conditionObject['conditionParameter'] = "bfspPlace";
		
		if(bfspPlaceSelect == ""){
			
		}else if(bfspPlaceSelect != ""  && bfspPlaceInput == ""){
			noteString = noteString + " Since no value was present for bfspPlace parameter we ignored it <br>";
		}else{
			if(bfspPlaceSelect == "between"){
				if(bfspPlaceInput.includes(" and ") ||bfspPlaceInput.includes(" AND ") || bfspPlaceInput.includes(" And ")){
					if(bfspPlaceInput.toLowerCase().split(" and ").length == 2){
						
						conditionObject['conditionStatement'] = "between";
						conditionObject['conditionValue'] = bfspPlaceInput;
						conditionStatement.push(conditionObject);
						conditionString = conditionString + "b.bfspPlace between " + bfspPlaceInput + " ; ";
						
					}else{
						noteString = noteString + " Since bfspPlace does not have correct upper and lower limit IGNORED <br>";
					}
				}else{
						noteString = noteString + " Since bfspPlace does not have correct upper and lower limit IGNORED <br>";
					}
			}else{
				if(isNaN(bfspPlaceInput)){
					noteString = noteString + " Since bfspPlace does not have a correct value we ignored it <br>";
				}else{
					conditionObject['conditionStatement'] = bfspPlaceSelect;
					conditionObject['conditionValue'] = bfspPlaceInput;
					conditionStatement.push(conditionObject);
					conditionString = conditionString + "b.bfspPlace " + bfspPlaceSelect + " " + Number(bfspPlaceInput) + " ; ";

				}				
			}
		}
		
		//PLCS Paid
		
		var plcsPaidSelect = $('#result_plcspaid_condition').val().trim();
		var plcsPaidInput = $('#result_plcspaid_value').val().trim();
		conditionObject = {};
		conditionObject['conditionParameter'] = "plscPaid";
		
		if(plcsPaidSelect == ""){
			
		}else if(plcsPaidSelect != ""  && plcsPaidInput == ""){
			noteString = noteString + " Since no value was present for plcsPaid parameter we ignored it <br>";
		}else{
			if(plcsPaidSelect == "between"){
				if(plcsPaidInput.includes(" and ") ||plcsPaidInput.includes(" AND ") || plcsPaidInput.includes(" And ")){
					if(plcsPaidInput.toLowerCase().split(" and ").length == 2){
						
						conditionObject['conditionStatement'] = "between";
						conditionObject['conditionValue'] = plcsPaidInput;
						conditionStatement.push(conditionObject);
						conditionString = conditionString + "b.plcsPaid between " + plcsPaidInput + " ; ";
						
					}else{
						noteString = noteString + " Since plcsPaid does not have correct upper and lower limit IGNORED <br>";
					}
				}else{
						noteString = noteString + " Since plcsPaid does not have correct upper and lower limit IGNORED <br>";
					}
			}else{
				if(isNaN(plcsPaidInput)){
					noteString = noteString + " Since plcsPaid does not have a correct value we ignored it <br>";
				}else{
					conditionObject['conditionStatement'] = plcsPaidSelect;
					conditionObject['conditionValue'] = plcsPaidInput;
					conditionStatement.push(conditionObject);
					conditionString = conditionString + "b.plcsPaid " + plcsPaidSelect + " " + Number(plcsPaidInput) + " ; ";
				}				
			}
		}
		
		//BF PLCS Paid
		
		/*var bfPlcsPaidSelect = $('#result_bfplspaid_condition').val().trim();
		var bfplcsPaidInput = $('#result_bfplspaid_value').val().trim();
		conditionObject = {};
		conditionObject['conditionParameter'] = "bfPlscPaid";
		
		if(bfPlcsPaidSelect == ""){
			
		}else if(bfPlcsPaidSelect != ""  && bfplcsPaidInput == ""){
			noteString = noteString + " Since no value was present for bfPlcsPaid parameter we ignored it <br>";
		}else{
			if(bfPlcsPaidSelect == "between"){
				if(bfplcsPaidInput.includes(" and ") ||bfplcsPaidInput.includes(" AND ") || bfplcsPaidInput.includes(" And ")){
					if(bfplcsPaidInput.toLowerCase().split(" and ").length == 2){
						
						conditionObject['conditionStatement'] = "between";
						conditionObject['conditionValue'] = bfplcsPaidInput;
						conditionStatement.push(conditionObject);
						conditionString = conditionString + "b.bfPlcsPaid between " + bfplcsPaidInput + " ; ";
						
					}else{
						noteString = noteString + " Since bfPlcsPaid does not have correct upper and lower limit IGNORED <br>";
					}
				}else{
						noteString = noteString + " Since bfPlcsPaid does not have correct upper and lower limit IGNORED <br>";
					}
			}else{
				if(isNaN(bfplcsPaidInput)){
					noteString = noteString + " Since bfPlcsPaid does not have a correct value we ignored it <br>";
				}else{
					conditionObject['conditionStatement'] = bfPlcsPaidSelect;
					conditionObject['conditionValue'] = bfplcsPaidInput;
					conditionStatement.push(conditionObject);
					conditionString = conditionString + "b.bfPlcsPaid " + bfPlcsPaidSelect + " " + Number(bfplcsPaidInput) + " ; ";
					
				}				
			}
		}*/
		
		
		// Race Type
		
		var raceType = $('#result_racetype_condition').val().toString();
		conditionObject = {};
		conditionObject['conditionParameter'] = "raceType";
		
		if(raceType == ""){
			
		}else{
			conditionObject['conditionStatement'] = "=";
			conditionObject['conditionValue'] = raceType;
			conditionStatement.push(conditionObject);
			conditionString = " a.official_going in one of ( "+ raceType +" ) ; ";
		}
		
		//card Number
		var cardNumberSelect = $('#result_cardnumber_condition').val().trim();
		var cardNumberInput = $('#result_cardnumber_value').val().trim();
		conditionObject = {};
		conditionObject['conditionParameter'] = "cardNumber";
		
		if(cardNumberSelect == ""){
			
		}else if(cardNumberSelect != ""  && cardNumberInput == ""){
			noteString = noteString + " Since no value was present for cardNumber parameter we ignored it <br>";
		}else{
			if(cardNumberSelect == "between"){
				if(cardNumberInput.includes(" and ") ||cardNumberInput.includes(" AND ") || cardNumberInput.includes(" And ")){
					if(cardNumberInput.toLowerCase().split(" and ").length == 2){
						
						conditionObject['conditionStatement'] = "between";
						conditionObject['conditionValue'] = cardNumberInput;
						conditionStatement.push(conditionObject);
						
						conditionString = conditionString + "b.cardNo between " + cardNumberInput + " ";
						
					}else{
						noteString = noteString + " Since cardNumber does not have correct upper and lower limit IGNORED <br>";
					}
				}else{
						noteString = noteString + " Since cardNumber does not have correct upper and lower limit IGNORED <br>";
					}
			}else{
				if(isNaN(cardNumberInput)){
					noteString = noteString + " Since cardNumber does not have a correct value we ignored it <br>";
				}else{
					conditionObject['conditionStatement'] = cardNumberSelect;
					conditionObject['conditionValue'] = cardNumberInput;
					conditionStatement.push(conditionObject);
					conditionString = conditionString + "b.cardNo " + cardNumberSelect + " " + Number(cardNumberInput) + " ; ";
				}				
			}
		}
		
		/*console.log(conditionStatement);
		$('#generated_sheetName').empty();
		$('#generated_sheetName').append("Sheet Name : <span style ='color: blue;'>" + $('#trackId').val().toString() + "</span>");
		*/
		
		/*$('#generated_Limit').empty();
		$('#generated_Limit').append("Number of top Rows : <span style ='color: blue;'>" + limit + "</span>");
		*/
		$('#generated_condition').empty();
		if(conditionString == ""){
			$('#generated_condition').append("Validated Condition : <span style ='color: red;'> No other condition made </span> ");
		}else{
			$('#generated_condition').append("Validated Condition : <span style ='color: blue;'>" + conditionString + "</span>");
		}
		
		if(noteString != ""){
			$('#generated_Note').empty();
			$('#generated_Note').append("Note for the adjustments made autumatically : " + noteString);
			$('#generated_Note').show();
		}else{
			$('#generated_Note').hide();
		}
		
		$('#dataValidatedLabel').show();
		//$('#generated_sheetName').show();
		$('#generated_Limit').show();
		$('#generated_condition').show();
		
		$('#addCondition').prop('disabled', false);

	});
    
    
    

    
    $("#uploadResult").click(function(event) {
        event.preventDefault();
        var formData = new FormData();
        $('#resultResponse').empty();
        $('#resultResponse').append("Uploading....");
        formData.append('resultFile', $('#resultFile')[0].files[0]);
        formData.append('speedFile', $('#speedFile2')[0].files[0]);
        formData.append('date', $('#speedFileDate2').val());
        
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "./horse-racing/readResultFile",
            data: formData,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
            	console.log(data);
            	$('#resultResponse').empty();
            	$('#resultResponse').append(data['message']);
            },
            error: function (e) {
                alert("Something went wrong");
                $('#resultResponse').append("Something went wrong");
                console.log(e);
            }
        });
    });
    
    
  /*  $("#uploadSpeed").click(function(event) {
        event.preventDefault();
        var formData = new FormData();
        $('#speedResponse').empty();
        $('#speedResponse').append("Uploading....");
        formData.append('file', $('#speedFile')[0].files[0]);
        formData.append('date', $('#speedFileDate').val());
        
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "./horse-racing/readSpeedFile",
            data: formData,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
            	console.log(data);
            	$('#speedResponse').empty();
            	$('#speedResponse').append(data['message']);
            	$('#horseAnalysis').empty();
	            
            },
            error: function(jqXHR, exception) {	
            	$('#speedResponse').empty();
                $('#speedResponse').append(jqXHR.responseJSON['errorMsg']);
                alert("Something went wrong");
				// Your error handling logic here..
			}
        });
    });*/
    
    
    $('#saveCondition').click(function(){
    	var condition = $('#conditionName').val().trim();
    	if(condition == ""){
    		alert("Invalid value");    		
    	}else{
    		var formData = new FormData();
            formData.append('condition', condition);
            formData.append('conditionStatement', $('#conditionStatement').html().trim());
    		$.ajax({
                type: "POST",
                url: "./horse-racing/saveCondition",
                data: formData,
                success: function (data) {
                	console.log(data);
                },
                error: function(jqXHR, exception) {
                    alert("Something went wrong");
    			}
            });
    	}
    });
});


function getTable(parameter){
	
	
	var condition = parameter['condition'];
	var stats  = parameter['statsTable'];
	var winnerInfo = parameter['winningTabel'];
	var daysReport = parameter['daysReport']
	var streaks =  parameter['stikes'];
	var conditionStat  = parameter['conditionStatement'];
	

	var tableBar = "";
	tableBar = tableBar + '<div class = "row"> <div class="col-md-5">';
	tableBar = tableBar + '<h6>';
	tableBar = tableBar + condition;
	tableBar = tableBar + '</h6>';
	tableBar = tableBar + '</div> <div class = "col-md-5">';
	tableBar = tableBar + '<input class="form-control" id="conditionName"/> ';
	tableBar = tableBar + '<label style="display:none" id = "conditionStatement" >'+ conditionStat +'</label></div>';
	tableBar = tableBar + '<div class ="col-md-2" > <button class ="btn btn-md btn-block" id="saveCondition"> Save Condition </button>';
	tableBar = tableBar + '</div></div>';
	tableBar = tableBar + '<table class="table table-striped border">';    
    tableBar = tableBar + '<tr style= "font-size : 15px;">';
    tableBar = tableBar + '<th scope="col">Total Qualified Horses</th>';
    tableBar = tableBar + '<td scope="col">' + stats['qualifiedRace'] + '</td>';
    tableBar = tableBar + '<th scope="col"></th>';
    tableBar = tableBar + '<td scope="col"></td>';
    tableBar = tableBar + '</tr>';
    tableBar = tableBar + '<tr style= "font-size : 15px;">';
    tableBar = tableBar + '<th scope="col"> Total Horses Won</th>';
    tableBar = tableBar + '<td scope="col">' + stats['totalWinCount'] + '</td>';
    tableBar = tableBar + '<th scope="col">Percentage Horse Win</th>';
    tableBar = tableBar + '<td scope="col">' + stats['winPercentage'] + '</td>';
    tableBar = tableBar + '</tr>';
    tableBar = tableBar + '<tr style= "font-size : 15px;">';
    tableBar = tableBar + '<th scope="col">Total BFSP</th>';
    tableBar = tableBar + '<td scope="col">' + Math.round(stats['bfspTotal']*100)/100 + '</td>';
    tableBar = tableBar + '<th scope="col">Profit BFSP</th>';
    tableBar = tableBar + '<td scope="col">' + Math.round(stats['profitBfsp']*100)/100 + '</td>';
    tableBar = tableBar + '</tr>';
    tableBar = tableBar + '<tr style= "font-size : 15px;">';
    tableBar = tableBar + '<th scope="col">Total BFSP Place</th>';
    tableBar = tableBar + '<td scope="col">' + Math.round(stats['bfspPlaceTotal']*100)/100 + '</td>';
    tableBar = tableBar + '<th scope="col">Profit BFSP Place</th>';
    tableBar = tableBar + '<td scope="col">' + Math.round(stats['bfspPlaceProfit']*100)/100 + '</td>';
    tableBar = tableBar + '</tr>';
    
    if(stats['bfspPlaceWin'] != 0){
    	tableBar = tableBar + '<tr style= "font-size : 15px;">';
        tableBar = tableBar + '<th scope="col">Total Horse BFSP Place Win</th>';
        tableBar = tableBar + '<td scope="col">' + stats['bfspPlaceWin'] + '</td>';
        tableBar = tableBar + '<th scope="col">Percentage Horse BFSP Place Win</th>';
        tableBar = tableBar + '<td scope="col">' + Math.round(stats['bfspPlaceWinPercnt']*100)/100 + '</td>';
        tableBar = tableBar + '</tr>';
    }
    
    for (var row = 0; row < 2; row++) {
	    tableBar = tableBar + '<tr style= "font-size : 15px;">';
	    tableBar = tableBar + '<th scope="col">'+ daysReport[row]['condition'] +'</th>';
	    tableBar = tableBar + '<td scope="col" colspan = "3">' + daysReport[row]['qualifier'] + '</td>';
	    tableBar = tableBar + '</tr>';
	    tableBar = tableBar + '<tr style= "font-size : 15px;">';
	    tableBar = tableBar + '<td scope="col" colspan = "4">' + daysReport[row]['positionMarker'] + '</td>';
	    tableBar = tableBar + '</tr>';
    }
    
    for (var row = 0; row < streaks.length; row += 2) {
	    tableBar = tableBar + '<tr style= "font-size : 15px;">';
	    tableBar = tableBar + '<th scope="col">'+ streaks[row]['strikeString'] +'</th>';
	    tableBar = tableBar + '<td scope="col">' + streaks[row]['strikeCount'] + '</td>';
	    tableBar = tableBar + '<th scope="col">'+ streaks[row+1]['strikeString'] +'</th>';
	    tableBar = tableBar + '<td scope="col">' + streaks[row+1]['strikeCount'] + '</td>';
	    tableBar = tableBar + '</tr>';
    }
    
    tableBar = tableBar + '<tbody> </table>';
    tableBar = tableBar + '<br> <div class="row"><div class="col-md-12" style="word-break:break-all;">' + daysReport[2]['positionMarker'] + '</div></div>';
    
    if(winnerInfo.length > 0 ){
    	
    	tableBar = tableBar + '<div class="row mt-25 mb-10"><h4>Winner Information</h4></div>';
    	tableBar = tableBar + '<table id="winingDescription" class="display" width="100%"></table>';
    	$('#condition_info').empty();
        $('#condition_info').append(tableBar);
    	
    	var dataSet = [];
    	      
        for (var row = 0; row < winnerInfo.length; row++) {
        	var rowData = [];
        	rowData.push(winnerInfo[row]['raceDate']);
        	rowData.push(winnerInfo[row]['track']);
        	rowData.push(winnerInfo[row]['horseName']);
        	rowData.push(winnerInfo[row]['distance']);
        	rowData.push(Math.round(winnerInfo[row]['bfsp'] * 100)/100);
        	dataSet.push(rowData);
        }
     
        $(document).ready(function() {
            $('#winingDescription').DataTable( {
            	"bSort": false,
                data: dataSet,
                columns: [
                    { title: "Race Date" },
                    { title: "Track" },
                    { title: "Horse Name" },
                    { title: "Distance Furlongs" },
                    { title: "BFSP" }
                ]
            });
        });
    }else{
    	$('#condition_info').empty();
        $('#condition_info').append(tableBar);
    }
    
    
    
}
/*
function getNoHorsePrompt(parameter){
	var condition = parameter['condition'];
	var value  = parameter['value'];
	var id = parameter['key'] + "_info";
	
	var tableBar = "";
	
	tableBar = tableBar + '<lable class = "form-control"> ';
    tableBar = tableBar + condition;
    tableBar = tableBar + ' </lable>';
	
    
    tableBar = tableBar + '<lable class = "form-control mt-25 mb-25" style= "color:red"> ';
    tableBar = tableBar + "No Horse Qualified Given Conditions Hence Table Is Not Loaded";
    tableBar = tableBar + ' </lable>';
	
    $('#'+id+'').empty();
    $('#'+id+'').append(tableBar);
    
}

*/

