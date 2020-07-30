;;; Sierra Script 1.0 - (do not remove this comment)
(script# 49)
(include game.sh)
(use Main)
(use Intrface)
(use Osc)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm49 0
	openingScript 1
	robotAsksScript 2
	pushButtons 3
	holdLever 4
	scratchHead 5
	doHyperSpace 6
	jetSound 7
)

(instance rm49 of Room
	(properties
		picture 16
	)
	
	(method (init)
		(super init:)
		(addToPics add: keronaATP_a eachElementDo: #init doit:)
		(self setScript: openingScript)
	)
)

(instance openingScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(jetSound number: 533 loop: -1 play:)
				(ship1 init: setCycle: Forward setMotion: MoveTo -39 75 self)
			)
			(1
				(curRoom drawPic: 17 -32759)
				(= cycles 2)
			)
			(2
				(ship1
					posn: 380 110
					setPri: 14
					setStep: 6 6
					setMotion: MoveTo 112 -40 self
				)
				(soundFx number: 464 loop: -1 play:)
				(satelite
					init:
					setCycle: Forward
					setMotion: MoveTo 327 -15 self
				)
			)
			(3 0)
			(4
				(soundFx stop:)
				(ship1 dispose:)
				(satelite dispose:)
				(= ticks 1)
			)
			(5
				(jetSound fade:)
				(curRoom drawPic: 49 -32760)
				(arm init:)
				(dial0 init: setCycle: Oscillate)
				(dial1 init: setCycle: Oscillate)
				(dial2 init: setCycle: Oscillate)
				(screen init: setCycle: Oscillate)
				(self setScript: robotAsksScript self)
			)
			(6
				(Print 49 0)
				(self setScript: holdLever self 10)
			)
			(7
				(switch shipDestination
					(shipNOSECTOR
						(self setScript: (ScriptID 401 0))
					)
					(shipWRONGSECTOR
						(switch (Random 0 1)
							(0
								(self setScript: (ScriptID 402 0))
							)
							(1
								(self setScript: (ScriptID 401 0))
							)
						)
					)
					(shipDELTAUR
						(self setScript: (ScriptID 401 0) self 1)
					)
				)
			)
			(8
				(self setScript: approachDeltaur self)
			)
			(9
				(EgoDead 2 3 2 49 1)
				(self dispose:)
			)
		)
	)
)

(instance robotAsksScript of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(arm init: stopUpd:)
				(dial0 init: setCycle: Oscillate)
				(dial1 init: setCycle: Oscillate)
				(dial2 init: setCycle: Oscillate)
				(screen init: setCycle: Oscillate)
				(self setScript: pushButtons self)
			)
			(1
				(Print 49 2)
				(self setScript: holdLever self)
			)
			(2
				(Print 49 3 #at 76 10 #dispose)
				(self setScript: (ScriptID KEYPAD 0))
			)
			(3 0)
			(4
				(if (== shipDestination shipNOSECTOR) (Print 49 4))
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if (and register (event type?))
			(if modelessDialog
				(modelessDialog seconds: 0 caller: 0 dispose:)
			)
			(= register 0)
			(= cycles 1)
			(event claimed: TRUE)
		else
			(super handleEvent: event)
		)
	)
)

(instance pushButtons of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(arm loop: 0 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(1 (arm cel: 1) (= cycles 12))
			(2 (arm cel: 2) (= cycles 3))
			(3 (arm setCycle: BegLoop self))
			(4
				(arm stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance holdLever of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(arm loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(1
				(if register
					(self setScript: doHyperSpace self register)
				else
					(= seconds 4)
				)
			)
			(2 (arm setCycle: BegLoop self))
			(3
				(= register 0)
				(arm stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance scratchHead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(arm startUpd: loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(1
				(droidHand init: loop: 4 cel: 1 setCycle: Forward)
				(arm loop: 3)
				(= seconds 3)
			)
			(2
				(droidHand dispose:)
				(arm loop: 2 cel: 0 setCycle: BegLoop self)
			)
			(3
				(arm stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance doHyperSpace of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 0) (== (globalSound prevSignal?) 10))
			(music prevSignal: 0)
			(self cue:)
		)
		(Palette PALCycle 238 255 -1)
	)
	
	(method (dispose)
		(= register 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(music pause:)
				(globalSound number: 332 loop: 1 flags: 0 play:)
			)
			(1
				(curRoom overlay: 149 IRISOUT)
				(= cycles 1)
			)
			(2 (= seconds (- register 2)))
			(3
				(if (== shipDestination 0)
					(Print 49 5)
					(self caller: 0)
					(client setScript: (ScriptID 401 0))
				else
					(= seconds 2)
				)
			)
			(4
				(globalSound fade:)
				(music setVol: 40 pause: 0 fade: 127 25 10 0)
				(curRoom drawPic: 49 IRISOUT)
				(self dispose:)
			)
		)
	)
)

(instance approachDeltaur of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 49 IRISOUT)
				(arm init: show: loop: 2 cel: 0 setPri: 5 stopUpd:)
				(dial0 init: setCycle: Oscillate show:)
				(dial1 init: setCycle: Oscillate show:)
				(dial2 init: setCycle: Oscillate show:)
				(screen init: setCycle: Oscillate show:)
				(self setScript: pushButtons self)
			)
			(1
				(Print 49 6)
				(music stop:)
				(globalSound number: 500 loop: 1 flags: mNOPAUSE play:)
				(self setScript: scratchHead self)
			)
			(2
				(deltaur
					init:
					setStep: 5 5
					setMotion: MoveTo 352 249 self
				)
				(arm setScript: holdLever)
			)
			(3
				(deltaur setStep: 3 3 setMotion: MoveTo 312 169 self)
				(arm setScript: pushButtons)
			)
			(4
				(deltaur setStep: 1 1 setMotion: MoveTo 250 107 self)
			)
			(5
				(Print 49 7)
				(if
					(Print 49 8
						#button { OK._} 1
						#button { No._} 0
					)
					(self dispose:)
				else
					(music fade:)
					(HandsOn)
					(curRoom newRoom: 50)
				)
			)
		)
	)
)

(instance keronaATP_a of PicView
	(properties
		x 227
		y 189
		view 117
		loop 2
		priority 1
		signal (| ignrAct fixPriOn)
	)
)

(instance keronaATP_b of PicView
	(properties
		x 227
		y 189
		view 117
		loop 2
		priority 1
		signal fixPriOn
	)
)

(instance satelite of Actor
	(properties
		x -44
		y 180
		yStep 8
		view 118
		loop 3
		priority 14
		signal (| ignrHrz fixedLoop fixPriOn)
		cycleSpeed 3
		xStep 8
		moveSpeed 3
	)
)

(instance deltaur of Actor
	(properties
		x 349
		y 235
		view 249
		priority 2
		signal (| ignrAct fixedCel fixedLoop fixPriOn)
		cycleSpeed 2
		moveSpeed 2
	)
)

(instance arm of Prop
	(properties
		x 207
		y 109
		z -56
		view 149
		priority 5
		signal (| ignrAct fixPriOn)
		cycleSpeed 2
	)
)

(instance droidHand of Prop
	(properties
		x 220
		y 98
		view 149
		loop 4
		priority 5
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 2
	)
)

(instance dial0 of Prop
	(properties
		x 58
		y 176
		view 149
		loop 8
		priority 4
		signal (| ignrAct fixPriOn)
		cycleSpeed 4
		detailLevel 1
	)
)

(instance dial1 of Prop
	(properties
		x 189
		y 84
		view 149
		loop 7
		cel 1
		priority 4
		signal (| ignrAct fixPriOn)
		cycleSpeed 3
	)
)

(instance dial2 of Prop
	(properties
		x 248
		y 132
		view 149
		loop 6
		cel 4
		priority 4
		signal (| ignrAct fixPriOn)
		cycleSpeed 2
		detailLevel 2
	)
)

(instance screen of Prop
	(properties
		x 116
		y 131
		view 149
		loop 5
		cel 3
		priority 4
		signal (| ignrAct fixPriOn)
		cycleSpeed 4
		detailLevel 3
	)
)

(instance ship1 of Actor
	(properties
		x 142
		y 213
		yStep 4
		view 156
		priority 14
		signal (| ignrHrz fixedLoop fixPriOn)
		cycleSpeed 3
		illegalBits $0000
		xStep 4
		moveSpeed 3
	)
)

(instance jetSound of Sound
	(properties)
)
