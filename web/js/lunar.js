// Moon
var moon = {};
moon.mtop = 50;
moon.g = 0.04 ; // Gravity

// Ship
var ship = {};
ship.y = 0; // Position
ship.v = 0; // Speed
ship.thrust = -moon.g; // Thrust

ship.maxSpeed = 20;
ship.safeSpeed = 2; // Maximum speed 'til it explodes

ship.maxFuel = 175; // Fuel/FPS
ship.fuel = ship.maxFuel; // Restore fuel

var pause = true;
var spacePressed = 0;

// Sound efects
var audio = {};
audio.puh = document.createElement("AUDIO");
audio.puh.src = './sound/puh.mp3';
$(audio.puh).prop('volume', 0);

function map(x,  in_min,  in_max,  out_min,  out_max) {
	return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
}

function reset() {
	$('#state').hide();
	$('#state h1').removeClass();
	ship.fuel = ship.maxFuel;
	ship.y = 0;
	ship.v = 0;
	spacePressed = false;
	$('#ms').css('color', '#000');
	$('#explode').removeClass();
	$('#explode').css('background', 'transparent');
	pause = false;
}

function getSpeed() {
	return parseFloat(Math.round(-ship.v * 100) / 100).toFixed(2);
}

function resize() {
	moon.base = $('#landing-pad').height() + -($('#landing-pad').height()-150)*0.4 + 30;
}

function doEvent(kind, ms) {
	pause = true;
	$('#state h1').addClass(kind);
	$('#state h1').html((kind == 'win') ? 'YOU WIN!' : 'YOU LOSE');
	$('#state h2').html((kind == 'win') ? 'Speed: ' + ms + ' m/s': 'Maybe next time...');
	$('#ms').css('color', (kind == 'win') ? '#0a0' : '#f00');
	if(kind == 'lose')
	{
		$(audio.puh).prop('volume', 1);
		audio.puh.play();
		$('#explode').addClass('exploded');
		$('#explode').css('background', 'url(\'./img/explode.gif?p=' + new Date().getTime() + '\')');
	}
	$('#state').delay((kind == 'win') ? 0 : 1000).show(0);
}

$(document).ready(function() {
	$('body').keydown(function(e) {
		//e.preventDefault();
		if(e.keyCode == 32)
			if(pause && !spacePressed && $('#state').css('display') == 'block'){
                            
                        }
				//reset(); // Space while alert is shown to restart game
			else
				spacePressed = 1;
	});
	$('body').keyup(function(e) {
		//e.preventDefault();
		if(e.keyCode == 32) spacePressed = 0;
	});
	
	$('#game').bind('touchstart', function(e){
		//e.preventDefault();
		audio.puh.play();
		spacePressed = 1;
	}).bind('touchend', function(e){
		//e.preventDefault();
		spacePressed = 0;
	});
		
	// Update
	window.setInterval(function() {
		if(!pause) {
			$(audio.puh).delay(1000).prop('volume', 0);
			ship.v += (spacePressed) ? ((ship.fuel > 0) ? ship.thrust : moon.g) : moon.g; // Aceleración
			ship.v = (ship.v > ship.maxSpeed) ? ship.maxSpeed : ((ship.v < -ship.maxSpeed) ? -ship.maxSpeed : ship.v); // Velocidad
			
			if((ship.v > 0 && ship.y < 500) || (ship.v < 0 && ship.y > 0))
				ship.y += ship.v;
			else
			{
				if(ship.y >= 500)
				{
					ship.y = 500;
					$('#ms').html(getSpeed() + ' m/s');
					doEvent((ship.v > ship.safeSpeed) ? 'lose' : 'win', getSpeed());
				}
				if(ship.y < 0)
					ship.y = 0;
				if(!pause) ship.v = 0;
			}
			
			// Quitamos fuel
			if(ship.fuel > 0 && spacePressed)
				ship.fuel--;
			
			// Dibujamos el juego
			resize();
			$('#gauge div').css('width', ship.fuel/ship.maxFuel*100 + '%');
			$('#ship').css('top', map(ship.y, 0, 500, moon.mtop, $('body').height() - moon.base));
			$('#explode').css('top', map(ship.y, 0, 500, moon.mtop, $('body').height() - moon.base) - 100);
			$('#ship').css('background', (spacePressed & ship.fuel > 0) ? 'url(\'./img/ship.png\')' : 'url(\'./img/shipOff.png\')');
			$('#ms').html(getSpeed() + ' m/s');
		}
	}, 16.6666667);
});