;;; Sierra Script 1.0 - (do not remove this comment)
(script# 375)
(include game.sh)
(use Main)
(use Procs)
(use Game)
(use Actor)
(use System)

(public
	titleRoom 0
)

(local
	buttonPressed
	saveCur
	savePMouse
)
(instance titleRoom of Room

	(method (init)
		(super init:)
		(theIconBar disable:)
		(mouseDownHandler add: curRoom)
		(keyDownHandler add: curRoom)
		(directionHandler add: curRoom)
		(= savePMouse pMouse)
		(= pMouse 0)
		(= saveCur (theGame setCursor: INVIS_CURSOR))
		(self setScript: roomScript)
	)
	
	(method (doit &tmp obj)
		(if buttonPressed
			(psuedoCursor posn: mouseX mouseY)
			(if (not (buttonPressed onMe: mouseX mouseY))
				(if
					(and
						(= obj
							(cond 
								((playButt onMe: mouseX mouseY) playButt)
								((restoreButt onMe: mouseX mouseY) restoreButt)
								((quitButt onMe: mouseX mouseY) quitButt)
								(else 0)
							)
						)
						(!= obj buttonPressed)
					)
					(buttonPressed hide:)
					(obj show:)
					(= buttonPressed obj)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(SetVideoMode 0)
		(mouseDownHandler delete: curRoom)
		(keyDownHandler delete: curRoom)
		(directionHandler delete: curRoom)
		(Bset fUsingMagnifyingGlass)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp obj)
		(if (== 4 (roomScript state?))
			(event claimed: TRUE)
			(if (& (event type?) direction)
				(= obj
					(switch buttonPressed
						(playButt
							(if (OneOf (event message?) dirW dirNW dirN dirNE)
								quitButt
							else
								restoreButt
							)
						)
						(restoreButt
							(if (OneOf (event message?) dirW dirNW dirN dirNE)
								playButt
							else
								quitButt
							)
						)
						(quitButt
							(if (OneOf (event message?) dirW dirNW dirN dirNE)
								restoreButt
							else
								playButt
							)
						)
					)
				)
				(SetCursor 42 (- (obj y?) 1))
			else
				(roomScript cue:)
			)
		)
	)
)

(instance roomScript of Script

	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(theMusic1
					number: (if (== 32 (DoSound NumVoices)) 370 else 5370)
					play:
				)
				(= seconds 3)
			)
			(1
				(SetVideoMode 1)
				(Palette PALIntensity 0 256 100)
				(= cycles 1)
			)
			(2
				(ShowMovie {TITLE.SEQ} 8)
				(= cycles 1)
			)
			(3
				(psuedoCursor init:)
				(= cycles 1)
			)
			(4 1)
			(5
				(if (== buttonPressed playButt)
					(++ state)
				else
					(psuedoCursor hide:)
					(buttonPressed hide:)
				)
				(= cycles 1)
			)
			(6
				(SetVideoMode 0)
				(theGame setCursor: ARROW_CURSOR TRUE mouseX mouseY)
				(if (== buttonPressed quitButt)
					(theGame quitGame:)
				else
					(theGame restore:)
				)
				(theGame setCursor: INVIS_CURSOR TRUE mouseX mouseY)
				(buttonPressed show:)
				(psuedoCursor show:)
				(-= state 3)
				(= cycles 1)
			)
			(7
				(playButt dispose:)
				(restoreButt dispose:)
				(quitButt dispose:)
				(psuedoCursor dispose:)
				(= cycles 1)
			)
			(8
				(DrawPic 99 (| BLACKOUT FADEOUT) TRUE)
				(= seconds 2)
			)
			(9
				(theIconBar enable:)
				(= pMouse savePMouse)
				(theMusic1 fade:)
				(curRoom newRoom: 370)
				(self dispose:)
			)
		)
	)
)

(instance psuedoCursor of View
	(properties
		view 3759
		loop 3
	)
	
	(method (init)
		(playButt init:)
		(restoreButt init:)
		(quitButt init:)
		(= buttonPressed (restoreButt show: yourself:))
		(super init:)
		(SetCursor 42 127)
		(self setPri: 15 posn: 42 127)
	)
)

(instance playButt of View
	(properties
		x 24
		y 112
		view 3759
	)
	
	(method (hide)
		(= cel 0)
	)
	
	(method (show)
		(= cel 1)
	)
)

(instance restoreButt of View
	(properties
		x 24
		y 128
		view 3759
		loop 1
	)
	
	(method (hide)
		(= cel 0)
	)
	
	(method (show)
		(= cel 1)
	)
)

(instance quitButt of View
	(properties
		x 24
		y 144
		view 3759
		loop 2
	)
	
	(method (hide)
		(= cel 0)
	)
	
	(method (show)
		(= cel 1)
	)
)
