;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm120 0
)

(local
	local0
	[str 20]
)
(procedure (DoDisplay theX theY theColor theFont message)
	(Display message
		p_at theX theY
		p_font theFont
		p_color (- theColor 8)
	)
	(Display message
		p_at (+ theX 1) (+ theY 1)
		p_font theFont
		p_color theColor
	)
)

(instance rm120 of Room
	(properties
		picture 120
		style WIPERIGHT
	)
	
	(method (init)
;;;		;EO: Added Disney+ style disclaimer
;;;		(Print 
;;;			"This game includes negative depictions and/or mistreatment of people or cultures.
;;;			These stereotypes were wrong then and are wrong now.
;;;			Rather than remove this content, we want to acknowledge its harmful impact, learn from it, and
;;;			spark conversation to create a more inclusive feature together."
;;;			#title {Content Advisory}
;;;			#mode teJustCenter
;;;			#width 300
;;;			#font 4
;;;		)
;;;		;end disclaimer
		
		(HandsOff)
		(theGame setSpeed: 6)
		(StatusLine disable:)
		(TheMenuBar hide:)
		(Bset fSaveDisabled)
		(Bset fCursorHidden)
		(Bset fAutoSaveDisabled)
		(Load VIEW 110)
		(Load VIEW 120)
		(Load SOUND 110)
		(Load SOUND 120)
		(Load FONT 9)
		(super init:)
		(addToPics
			add: atpLeg1
			add: atpLeg2
			add: atpLeg3
			add: atpLeg4
			doit:
		)
		(self setScript: RoomScript)
		(ego init: hide:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (and (== -1 (music prevSignal?)) (== state 7))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 2)
			)
			(1
				(music stop: number: 110 loop: 1 play:)
				(= cycles 16)
			)
			(2
				(aLogoDissolve
					setCel: 255
					setCycle: BegLoop self
					setPri: 10
					init:
				)
				(aLogo setPri: 8 init:)
			)
			(3
				(aLogoDissolve dispose:)
				(aLogo stopUpd:)
				(= cycles 16)
			)
			(4
				(aSierra init: setCycle: EndLoop self)
			)
			(5
				(aSierra stopUpd:)
				(= cycles 16)
			)
			(6
				(aPresents init: setCycle: EndLoop self)
			)
			(7
				(aPresents stopUpd:)
			)
			(8
				(= cycles 12)
			)
			(9
				(music prevSignal: 0 stop: number: 120 loop: -1 play:)
				(aLogo dispose:)
				(aSierra dispose:)
				(aPresents dispose:)
				(curRoom drawPic: 120)
				(aThigh init:)
				(aCalf init:)
				(= cycles 2)
			)
			(10
				(DoDisplay 128 30 vLBLUE 9 (Format @str 120 0))
				(= cycles 10)
			)
			(11
				(DoDisplay 146 90 vLBLUE 9 (Format @str 120 1))
				(= cycles 10)
			)
			(12
				(DoDisplay 143 110 vLBLUE 9 (Format @str 120 2))
				(= cycles 10)
			)
			(13
				(DoDisplay 132 130 vLBLUE 9 (Format @str 120 3))
				(= cycles 10)
			)
			(14
				(aThigh setCycle: Forward)
				(aCalf setCycle: Forward)
				(= cycles 16)
			)
			(15
				(curRoom newRoom: 130)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?))
			(
				(and
					(== (event type?) keyDown)
					(== (event message?) `#2)
				)
				(ToggleSound)
			)
			(else
				(if (!= 120 (music number?))
					(music number: 120 loop: -1 play:)
				)
				(curRoom newRoom: 140)
			)
		)
	)
)

(instance aSierra of Prop
	(properties
		y 45
		x 234
		view 110
		priority 1
		cycleSpeed 1
	)
)

(instance aPresents of Prop
	(properties
		y 146
		x 237
		view 110
		loop 1
		priority 10
		cycleSpeed 1
	)
)

(instance aLogo of Prop
	(properties
		y 124
		x 236
		view 110
		loop 2
	)
)

(instance aLogoDissolve of Prop
	(properties
		y 124
		x 236
		view 110
		loop 3
		cel 255
		cycleSpeed 3
	)
)

(instance aThigh of Prop
	(properties
		y 66
		x 78
		view 120
		priority 3
		cycleSpeed 1
	)
)

(instance aCalf of Prop
	(properties
		y 149
		x 66
		view 120
		loop 1
		priority 4
		cycleSpeed 1
	)
)

(instance atpLeg1 of PicView
	(properties
		y 36
		x 37
		view 120
		loop 2
		priority 3
	)
)

(instance atpLeg2 of PicView
	(properties
		y 84
		x 26
		view 120
		loop 2
		cel 1
		priority 3
	)
)

(instance atpLeg3 of PicView
	(properties
		y 129
		x 18
		view 120
		loop 2
		cel 2
		priority 3
	)
)

(instance atpLeg4 of PicView
	(properties
		y 190
		x 25
		view 120
		loop 2
		cel 3
		priority 3
	)
)
