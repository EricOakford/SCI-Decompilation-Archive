;;; Sierra Script 1.0 - (do not remove this comment)
;********************************************************************
;***
;*** TRIVIA
;***		Demo version by Robert W. Lindsley
;***
;********************************************************************

(script#	TRIVIA)
(include game.sh)
(use Main)
(use InnRoom)
(use Print)
(use Motion)
(use Actor)
(use System)

(public
	trivia			0
)

(local
	theX = 4
	theY = 175
)

(instance trivia of InnRoom
	(properties
		picture			pTRIVIA
		nextRoom			CHESS
		prevRoom			RED_BARON
	)
	(method (init)
		(super init:	&rest)
		(theTimer init:, setCycle:	EndLoop)
		(self setScript:	sTrivia)
	)
)

(instance sTrivia of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(= seconds 2)
			)
			(
				(Print
					width:		290,
					posn:			-1 180,
					addText:		{Or, playing trivia LIVE with people all over
									the country.  NTN Trivia is the same 
									game played in many hotels, restaurants, and
									pubs across the country!},
					modeless:	TRUE,
					init:
				)
				(= seconds 10)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(self setScript:	doDisplays self)
			)
			(
				(fun init:)
				(colorLine init:)
				(= seconds 4)
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
				(Display {T}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {h}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {i}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 3)
				(= cycles 30)
			)
			(
				(Display {s}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 3)
				(= cycles 30)
			)
			(
				(Display {i}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 2)
				(= cycles 30)
			)
			(
				(Display {s}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 3)
				(= cycles 30)
			)
			(
				(Display {t}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {o}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {o}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 3)
				(= cycles 30)
			)
			(
				(Display {m}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {u}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {c}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {h}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 3)
				(= cycles 30)
			)
			(
				(Display {f}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {u}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 7)
				(= cycles 30)
			)
			(
				(Display {n}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= cycles 30)
			)
			(
				(Display {!}
					p_at	theX theY
					p_color	0
					p_font		9
				)
				(+= theX 5)
				(= seconds 2)
			)
			(
				(curRoom drawPic:	(curRoom picture?))
				(self dispose:)
			)
		)
	)
)

(instance fun of Actor
	(properties
		view		170
		x			5
		y			126
	)
)

(instance colorLine of Actor
	(properties
		view		170
 		loop		1
		x			224
		y			121
	)
)

(instance theTimer of Actor
	(properties
		view		171
		x			162
		y			11
		cycleSpeed	60
	)
)
