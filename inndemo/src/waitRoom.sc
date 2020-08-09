;;; Sierra Script 1.0 - (do not remove this comment)
;********************************************************************
;**
;** WAITROOM.SC
;**	 Demo version by Robert W. Lindsley
;**
;********************************************************************

(script#		WAITROOM)
(include game.sh)
(use Main)
(use InnRoom)
(use Print)
(use Motion)
(use Actor)
(use System)

(public
	waitRoom			0
)

(local
	theY = 161
	theX = 22
)

(instance waitRoom of InnRoom
	(properties
		picture		pWAITROOM
		nextRoom		BOARDS
		prevRoom		STRATEGO
	)
	(method (init)
		(dGuy init:)
		(pointer init:)
		(super init:	&rest)
		(self setScript:	sWaitRoom)
	)
)

(instance sWaitRoom of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(= seconds 2)
			)
			(
				(Print
					posn:			-1 180,
					modeless:	TRUE,
					width:		290,
					addText:		{With a simple click of a mouse, your
									circle of friends will suddenly expand
									to include potentially tens of thousands 
									of people from all over the country.},
					posn:			10 200,
					init:
				)
				(= seconds 8)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(pointer moveSpeed:	1, setMotion:	MoveTo 136 55 self)
			)
			(
				(= cycles 10)
			)
			(
				(dGuy dispose:)
				(= cycles 10)
			)
			(
				(talk init:)
				(= seconds 2)
			)
			(
				(self setScript:	doDisplays self)
			)
			(
				(talk dispose:)
				(= seconds 2)
			)
			(
				(Print
					posn:			-1 180,
					modeless:	TRUE,
					width:		290,
					addText:		{You'll be able to share your thoughts
									with other people via the ImagiNation 
									live chat areas and Bulletin Boards.},
					posn:			10 200,
					init:
				)
				(= seconds 8)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(= cycles 10)
			)
			(
				(curRoom newRoom:	(curRoom nextRoom?))
			)
		)
	)
)

;
; I wrote this STUPID script because I couldn't figure out
; how to dereference an array in SCI-16.  It's been a while.

(instance doDisplays of Script
	(method (changeState ns)
		(soundFx number:	3003, play:)
		(switchto (= state ns)
			(
				(Display {S}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {o}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {,}
					p_at	theX theY
					p_color	255
					p_font	1006
				)
				(+= theX 3)
				(= cycles 70)
			)
			(
				(Display { }
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 6)
				(= cycles 40)
			)
			(
				(Display {w}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 7)
				(= cycles 70)
			)
			(
				(Display {h}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display {a}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {t}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display { }
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {d}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 6)
				(= cycles 70)
			)
			(
				(Display {i}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 3)
				(= cycles 70)
			)
			(
				(Display {d}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display { }
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display {y}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {o}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display {u}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display { }
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display {t}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display {h}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {i}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 3)
				(= cycles 40)
			)
			(
				(Display {n}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display {k}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display { }
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display {o}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {f}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display { }
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {t}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display {h}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {e}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display { }
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {g}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {a}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {m}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 6)
				(= cycles 70)
			)
			(
				(Display {e}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display { }
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display {l}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 3)
				(= cycles 70)
			)
			(
				(Display {a}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {s}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {t}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display { }
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {n}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display {i}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 3)
				(= cycles 40)
			)
			(
				(Display {g}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 70)
			)
			(
				(Display {h}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {t}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= cycles 40)
			)
			(
				(Display {?}
					p_at	theX theY
					p_color	255
					p_font		1006
				)
				(+= theX 5)
				(= seconds 3)
			)
			(
				(self dispose:)
			)
		)
	)
)

(instance dGuy of Prop	  ;deselected guy
	(properties
		view		160
		x			86
		y			44
	)
)

(instance pointer of Actor
	(properties
		view			999
		loop			1
		x				300
		y				100
	)
)

(instance talk of View
	(properties
		view			160
		loop			1
		x				10
		y				152
	)
)
  
