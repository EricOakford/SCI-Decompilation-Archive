;;; Sierra Script 1.0 - (do not remove this comment)
(script# 742)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use Scaler)
(use Polygon)
(use LoadMany)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	vizierWedding 0
	stopWedding 1
	giveMint 2
	kingQueenEntry 3
	genieReappears 4
	genieScr 5
	glint 7
)

(local
	local0
)
(instance vizierWedding of Script
	(properties)
	
	(method (dispose)
		(walkHandler delete: self)
		(directionHandler delete: self)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 1005 28) talkWidth: 87 textX: 60 textY: 23)
				((ScriptID 740 2) init:)
				((ScriptID 80 5)
					view: 7425
					loop: 0
					cel: 0
					x: 236
					y: 182
					scaleSignal: 1
					scaleX: 143
					scaleY: 143
					init:
					setCycle: 0
					setLoop: -1
					loop: 0
					cel: 0
					noun: 12
					stopUpd:
				)
				((ScriptID 80 6)
					view: 7426
					loop: 0
					cel: 0
					x: 78
					y: 178
					scaleSignal: 1
					scaleX: 138
					scaleY: 138
					init:
					setCycle: 0
					setLoop: -1
					loop: 0
					cel: 0
					noun: 12
					stopUpd:
				)
				(if (not (Btst 10))
					((ScriptID 740 4)
						view: 746
						loop: 0
						cel: 0
						x: 54
						y: 157
						stopUpd:
					)
				)
				((ScriptID 740 1) init: cel: 4 setCycle: Beg self)
			)
			(1 (messager say: 1 0 3 1 self))
			(2
				((ScriptID 740 2) setCycle: End self)
			)
			(3
				(Bset 59)
				(messager say: 1 0 3 2 self)
			)
			(4
				(Bclr 59)
				((ScriptID 740 2) cel: 2)
				((ScriptID 740 1) setScript: preachPriest)
				(= cycles 10)
			)
			(5
				((ScriptID 740 2) stopUpd:)
				(messager say: 1 0 3 3 self oneOnly: 0)
			)
			(6
				(theGame handsOn:)
				(walkHandler addToFront: self)
				(directionHandler addToFront: self)
				(mouseDownHandler addToFront: self)
				(keyDownHandler addToFront: self)
				(= seconds 13)
			)
			(7
				(theGame handsOff:)
				(client setScript: (ScriptID 744 1))
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (theIconBar at: 0) (theIconBar curIcon?))
				(& (event type?) $1040)
			)
			(if (< (event y?) (ego y?))
				((ScriptID 740 7) add: -1 3 3 23 1)
				(client setScript: stopWedding)
			else
				(client setScript: triedToEscape)
			)
			(event claimed: 1)
		)
		(event claimed?)
	)
)

(instance stopWedding of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 964)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 740 7) init: self)
			)
			(1
				((ScriptID 740 1) setScript: 0 setCycle: Beg)
				((ScriptID 740 2) setCycle: End self)
			)
			(2 (= cycles 10))
			(3
				((ScriptID 740 2) setCycle: Beg)
				(ego setSpeed: 8 setStep: 5 3 setMotion: MoveTo 158 146)
				((ScriptID 740 5)
					view: 7361
					setSpeed: 8
					setStep: 5 3
					setMotion: MoveTo 192 148 self
				)
			)
			(4
				((ScriptID 740 5) setHeading: 0)
				(= cycles 10)
			)
			(5
				(ego reset: 6)
				(client setScript: (ScriptID 745 0))
			)
		)
	)
)

(instance genieScr of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (not local0) ((User alterEgo?) edgeHit?))
			(= local0 1)
			(messager say: 3 3 25 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: (ScriptID 752 2) self (ScriptID 740 3))
			)
			(1
				((ScriptID 740 3)
					loop: 8
					cel: 4
					x: 109
					y: 145
					scaleSignal: 1
					scaleX: 112
					scaleY: 112
				)
				(= seconds 3)
			)
			(2
				(self setScript: (ScriptID 752 1) self (ScriptID 740 3))
			)
			(3
				(UnLoad 128 7501)
				(if (not local0)
					(messager say: 1 0 6 8 self)
				else
					(= ticks 1)
				)
			)
			(4
				(if (not local0)
					(= gEgo
						(switch gEgo
							((ScriptID 740 5)
								((ScriptID 80 5) view: 7421 yourself:)
							)
							((ScriptID 80 5)
								((ScriptID 80 6) view: 7422 yourself:)
							)
							(else  (ScriptID 740 5))
						)
					)
					((= gKillDog killDog) register: gEgo)
				else
					(messager say: 3 3 25 2)
					(= register 1)
					(= local0 0)
					(= gEgo ego)
					(= gKillDog 0)
				)
				(gEgo setCycle: End self)
			)
			(5
				(if (!= gEgo (ScriptID 740 5))
					(gEgo
						setLoop: 1
						cel: 0
						setCycle: Walk
						setMotion: MoveTo ((ScriptID 740 3) x?) ((ScriptID 740 3) y?)
					)
				)
				(= cycles 1)
			)
			(6
				(self setScript: (ScriptID 752 0) self (ScriptID 740 3))
			)
			(7
				(if (not local0)
					(if (not (OneOf gEgo (ScriptID 80 6) ego))
						(= state (- state 4))
					)
					(if (not (HaveMouse))
						(= seconds 4)
					else
						(= seconds 2)
					)
				else
					(if (== gEgo ego) (++ state))
					(= ticks 5)
				)
			)
			(8
				(if register
					(EgoDead 18)
				else
					(theGame handsOff:)
					(messager say: 1 0 8 0 self)
				)
			)
			(9
				(= gEgo ego)
				(= gKillDog 0)
				(self setScript: (ScriptID 752 0) 0 (ScriptID 740 3))
			)
		)
	)
)

(instance killDog of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setLoop: 2 cel: 0 setCycle: End self)
			)
			(1
				(gEgo setPri: (+ (gEgo priority?) 1) addToPic:)
				(self dispose:)
			)
		)
	)
)

(instance genieReappears of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(UnLoad 128 748)
				(soundFx2 number: 943 loop: 1 play:)
				((ScriptID 740 2)
					view: 7414
					setLoop: 0
					cel: 0
					setPri: (+ ((ScriptID 740 8) priority?) 1)
					setCycle: End self
				)
			)
			(1
				((ScriptID 740 2)
					view: 7413
					loop: 0
					cel: 0
					setScale: 0
					x: 207
					y: 131
					cycleSpeed: (if (not howFast) 4 else 8)
					stopUpd:
				)
				((ScriptID 740 3)
					view: 7021
					loop: 8
					cel: 4
					x: 169
					y: 141
					scaleSignal: 1
					scaleX: 112
					scaleY: 112
					ignoreActors: 1
					setPri: 10
					init:
					noun: 5
					stopUpd:
				)
				(if (== client kingQueenEntry)
					(king loop: 3 cel: 0 setCycle: End king)
				)
				((ScriptID 740 1)
					view: 7412
					setLoop: 0
					cel: 0
					posn: 152 134
					setPri: 0
					setCycle: End self
				)
				(if (not (Btst 10))
					((ScriptID 740 4)
						startUpd:
						view: 7465
						setLoop: 0
						cel: 0
						cycleSpeed: 6
						setPri: 13
						setCycle: End (ScriptID 740 4)
					)
				)
				(UnLoad 128 741)
				(UnLoad 128 746)
				(UnLoad 128 7414)
			)
			(2 (= cycles 10))
			(3
				((ScriptID 740 1) addToPic:)
				(self dispose:)
			)
		)
	)
)

(instance giveMint of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 4)
			)
			(1
				((ScriptID 740 2)
					view: 7415
					setLoop: 0
					cel: 0
					setPri: 9
					setCycle: End self
				)
			)
			(2
				(messager say: 4 67 0 0 self 160)
			)
			(3
				(client setScript: (ScriptID 744 1))
			)
		)
	)
)

(instance triedToEscape of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 3 3 24 0 self)
			)
			(1
				(client setScript: (ScriptID 744 1) 0 1)
			)
		)
	)
)

(instance preachPriest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not ((ScriptID 740 1) cel?))
					((ScriptID 740 1)
						setLoop: (Random 0 1)
						setCycle: End self
					)
				else
					((ScriptID 740 1) setCycle: Beg self)
				)
			)
			(1
				(= state -1)
				(= cycles (Random 10 40))
			)
		)
	)
)

(instance king of Actor
	(properties
		x 120
		y 182
		noun 16
		view 7441
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Walk setScale: Scaler 100 75 189 134)
	)
	
	(method (cue)
		(self view: 7464 loop: 0 cel: 2 addToPic:)
	)
)

(instance glint of Prop
	(properties
		view 902
		priority 15
		signal $4810
	)
)

(instance kingQueenEntry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(king
					init:
					setSpeed: 5
					yStep: (if (not howFast) 4 else 2)
					setCycle: Walk
					ignoreActors:
					setScale: Scaler 101 90 189 141
				)
				((ScriptID 740 2) view: 7414 setLoop: 0 cel: 0)
				(= cycles 2)
			)
			(1
				(theIconBar enable:)
				(Face ego king self)
			)
			(2 (= seconds 3))
			(3 (messager say: 1 0 7 1 self))
			(4
				(king setMotion: MoveTo 122 142 self)
			)
			(5
				(ego setHeading: 315)
				(king
					view: 744
					loop: 2
					cel: 0
					x: 133
					y: 143
					setScale: 0
					setCycle: End self
				)
				(UnLoad 128 7441)
			)
			(6
				(king stopUpd:)
				(messager say: 1 0 7 2 self)
			)
			(7 (messager say: 1 0 7 3 self))
			(8
				((ScriptID 740 2)
					view: 7415
					setLoop: 0
					cel: 0
					setPri: 9
					setCycle: CT 3 1 self
				)
			)
			(9
				((ScriptID 742 7)
					init:
					posn: 164 107
					cel: 0
					setCycle: End self
				)
			)
			(10
				((ScriptID 742 7) dispose:)
				(= cycles 2)
			)
			(11
				(Bset 59)
				(messager say: 1 0 7 4 self)
			)
			(12
				(Bclr 59)
				(messager say: 1 0 7 5 self)
			)
			(13
				(messager say: 1 0 7 6 self)
			)
			(14
				(theGame givePoints: 5)
				((ScriptID 740 2) view: 7414 cel: 0)
				(messager say: 1 0 7 7 self)
			)
			(15
				(self setScript: (ScriptID 742 4) self)
			)
			(16
				(messager say: 1 0 7 8 self)
			)
			(17
				(messager say: 1 0 7 9 self)
			)
			(18
				(messager say: 1 0 7 10 self)
			)
			(19
				(LoadMany 0 1063 1029 1001)
				(UnLoad 128 899)
				(UnLoad 128 8992)
				(UnLoad 128 891)
				(UnLoad 128 890)
				(self setScript: (ScriptID 740 23) self)
			)
			(20
				(messager say: 1 0 7 11 self)
			)
			(21
				(messager say: 1 0 7 12 self)
			)
			(22
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 150 139 208 139 208 153 180 153 150 146
							yourself:
						)
				)
				(LoadMany 0 1004 1026)
				(UnLoad 128 892)
				(UnLoad 128 899)
				(UnLoad 128 8921)
				(UnLoad 128 8992)
				(UnLoad 128 891)
				(UnLoad 128 890)
				((ScriptID 740 3) setScript: (ScriptID 742 5))
				(theGame handsOn:)
				(self dispose:)
				(DisposeScript 744)
			)
		)
	)
)
