;;; Sierra Script 1.0 - (do not remove this comment)
;*****************************************************************
;***
;***	TITLE.SC--
;***	  Show ImagiNation pic, the map, and then babble a little.
;***
;*****************************************************************

(script#		TITLE)
(include game.sh)
(use Main)
(use InnRoom)
(use Print)
(use Motion)
(use Actor)
(use System)

(public
	titleRm			0
)

(instance titleRm of InnRoom
	(properties
		style			PIXELDISSOLVE
		picture		pTITLE
		nextRoom		MAP
	)
	(method (init)
		(super init:&rest)
		(Display 100 0
			p_font 800
			p_at 62 125 
			p_color 223
		)
		(Display 100 0
			p_font 800
			p_at 60 121
			p_color 165
		)
		(self setScript:	sTitle)
	)
)

(instance sTitle of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(= cycles 2)
			)
			(
				(if debugging
					(switch
						(Print
							addText:	{Teleport to:},
							addButton:	1 {BARON}      5 20,
							addButton:	2 {BJACK}      5 40,
							addButton:	5 {BRIDGE}     5 100,
							addButton:	9 {GOLF}       80 20,
							addButton:	16 {Continue}	80 160,
							addButton:	3 {BOARDS}     5 60,
							addButton:	4 {BOOGERS}    5 80,
							addButton:	6 {CHESS}      5 120,
							addButton:	10 {MAP}       80 40,  
							addButton:	11 {STRATEGO}  80 60,  
							addButton:	12 {TITLE}     80 80,  
							addButton:	13 {TITLE2}   	80	100,
							addButton:	14 {TRIVIA}   	80	120,
							addButton:	15 {WAITROOM} 	80	140,
							init:								
						) 
						(1
							(curRoom newRoom:	RED_BARON)
						)
						(2
							(curRoom newRoom:	BJACK)
						)
						(3
							(curRoom newRoom:	BOARDS)
						)
						(4
							(curRoom newRoom:	BOOGERS)
						)
						(5
							(curRoom newRoom:	BRIDGE)
						)
						(6
							(curRoom newRoom:	CHESS)
						)
						(7
							(curRoom newRoom:	DEMO)
						)
						(8
							(curRoom newRoom:	DEMOWIN)
						)
						(9
							(curRoom newRoom:	GOLF)
						)
						(10
							(curRoom newRoom:	MAP)
						)
						(11
							(curRoom newRoom:	STRATEGO)
						)
						(12
							(curRoom newRoom:	TITLE)
						)
						(13
							(curRoom newRoom:	TITLE2)
						)
						(14
							(curRoom newRoom:	TRIVIA)
						)
						(15
							(curRoom newRoom:	WAITROOM)
						)
						(16
							(= seconds 3)
						)
		  			)
				else				
					(SetCursor FALSE)
					(= seconds 3)
				)
				(globalSound
					number:		(if (== (DoSound NumVoices) 32) 960 else 961),
					loop:	-1,
					play:
				)
			)
			(
				(yellowSparkle init:)
				(= seconds 2)
			)
			(
				(greenSparkle init:)
				(= seconds 2)
			)
			(
				(curRoom drawPic:	(curRoom picture?) PIXELDISSOLVE)
				(= cycles 10)
			)
			(
	  			(curRoom newRoom:	(curRoom nextRoom?))
			)
		)
	)
)

(instance yellowSparkle of Prop
	(properties
		view 		0
		loop 		1
		cel		0
		x			154 
		y		 	76
		cycleSpeed	8
	)
	(method (init)
		(super init:)
		(self setCycle:	EndLoop)
	)
)

(instance greenSparkle of Prop
	(properties
		view  	0 
		loop  	2 
		cel  		0 
		x  		230 
		y			55 
		cycleSpeed  6 
	)
	(method (init)
		(super init:)
		(self setCycle:  EndLoop)
	)
)
