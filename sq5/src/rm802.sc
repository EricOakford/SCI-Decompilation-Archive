;;; Sierra Script 1.0 - (do not remove this comment)
(script# 802)
(include game.sh) (include "802.shm")
(use Main)
(use Scaler)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm802 0
)

(local
	selectedPanel =  -1
)
(instance rm802 of Room
	(properties
		noun N_ROOM
		picture 48
		style FADEOUT
	)
	
	(method (init)
		(super init: &rest)
		(theMusic1 number: 37 loop: -1 play:)
		(lever1 addToPic:)
		(lever2 addToPic:)
		(hand1 addToPic:)
		(hand2 addToPic:)
		(goliath addToPic:)
		(panel1 addToPic:)
		(panel2 addToPic:)
		(panel3 addToPic:)
		(arm addToPic:)
		(self setScript: doAnimation)
	)
)

(instance doAnimation of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(messager say: N_ROOM NULL C_DO_ANIMATION 0 self)
			)
			(2 (theGame handsOn:))
			(3
				(theGame handsOff:)
				(curRoom drawPic: 134)
				(= cycles 3)
			)
			(4
				(pod
					init:
					setScale: Scaler 182 100 189 121
					setMotion: MoveTo 216 123 self
				)
			)
			(5 (pod setCycle: CycleTo 7 1 self))
			(6
				(pod setScale: Scaler 100 18 123 99)
				(switch selectedPanel
					(0
						(pod setMotion: MoveTo 227 98 self)
					)
					(1
						(pod setMotion: MoveTo 171 107 self)
					)
					(2
						(pod setMotion: MoveTo 201 104 self)
					)
				)
			)
			(7 (pod setCycle: EndLoop self))
			(8
				(curRoom drawPic: 135 -32758)
				(pod
					view: 711
					loop: 0
					cel: 0
					x: 160
					y: 144
					setScale: Scaler 100 77 144 116
				)
				(= seconds 3)
			)
			(9
				(theMusic2 number: 106 loop: 1 play:)
				(pod setCycle: EndLoop self)
			)
			(10
				(pod setMotion: MoveTo 160 134 self)
			)
			(11
				(theMusic2 number: 233 loop: 1 play:)
				(= seconds 2)
			)
			(12 (curRoom newRoom: 800))
		)
	)
)

(instance pod of Actor
	(properties
		x 150
		y 189
		view 655
		signal (| ignrAct ignrHrz)
		scaleSignal (| scalable noStepScale)
	)
)

(instance goliath of View
	(properties
		x 145
		y -50
		z -100
		view 698
		;noun N_GOLIATH	;EO: Missing by mistake
		priority 5
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance panel1 of View
	(properties
		x 199
		y 44
		noun N_PANEL1
		view 698
		loop 1
		priority 15
		signal (| ignrAct ignrHrz fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= selectedPanel 0)
			(Bclr fInGoliathChamber)
			(doAnimation cue:)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance panel2 of View
	(properties
		x 109
		y 46
		noun N_PANEL2
		view 698
		loop 1
		cel 1
		priority 15
		signal (| ignrAct ignrHrz fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(= selectedPanel 1)
			(Bset fInGoliathChamber)
			(doAnimation cue:)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance panel3 of View
	(properties
		x 163
		y 46
		noun N_PANEL3
		view 698
		loop 1
		cel 2
		priority 15
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(= selectedPanel 2)
			(Bclr fInGoliathChamber)
			(doAnimation cue:)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance lever1 of View
	(properties
		x 54
		y 135
		noun N_LEVER1
		view 270
		cel 1
		signal ignrAct
	)
)

(instance lever2 of View
	(properties
		x 270
		y 135
		noun N_LEVER2
		view 270
		loop 1
		cel 1
		signal ignrAct
	)
)

(instance hand1 of View
	(properties
		y 125
		view 270
		cel 2
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance hand2 of View
	(properties
		x 322
		y 124
		view 270
		loop 1
		cel 2
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance arm of View
	(properties
		x 319
		y 27
		noun N_ARM
		view 269
		priority 6
		signal (| ignrAct skipCheck fixPriOn)
	)
)
