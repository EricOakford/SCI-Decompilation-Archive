;;; Sierra Script 1.0 - (do not remove this comment)
(script# 441)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use Print)
(use Conv)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Motion)
(use Actor)
(use System)

(public
	proc441_0 0
	proc441_1 1
	proc441_2 2
	minoTrigger 3
	minotaur 4
)

(local
	local0
	local1
	local2
)
(procedure (proc441_0 param1)
	(theGame handsOff:)
	(= local0 9)
	((ScriptID 30 0) setScript: 0)
	(if (not local2)
		(= local2 1)
		(curRoom setScript: minoTrigger 0 param1)
	)
)

(procedure (proc441_1)
	(minotaur init:)
	(celeste init:)
	(= local0 8)
	(= local1 6)
)

(procedure (proc441_2)
	(curRoom setScript: stepInFurther)
)

(instance myConv of Conversation
	(properties)
)

(instance minotaur of Actor
	(properties
		x 114
		y 147
		noun 4
		yStep 3
		view 4445
		signal $4000
		cycleSpeed 18
		xStep 5
	)
	
	(method (init)
		(self setCycle: RandCycle)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(messager say: 4 2 local0 0 self 440)
			)
			(5
				(if ((ScriptID 30 0) seenByMino?)
					(curRoom setScript: minotaurCharging 0 5)
				else
					(curRoom setScript: handToHand)
				)
			)
			(72
				(if (== (curRoom script?) minoTrigger)
					((ScriptID 30 0) scarfOnMino: 1)
					(ego view: 441 normal: 0 setLoop: 0 cel: 0)
					(UnLoad 128 900)
					(minotaur cycleSpeed: 6 setCycle: Forward)
					(minoTrigger state: 19 register: 72 cue:)
					(theGame handsOff:)
					(theGame givePoints: 3)
				else
					(messager say: 4 0 8 1 self 440)
				)
			)
			(1
				(messager say: 4 1 0 1 0 440)
			)
			(else 
				(if (not ((ScriptID 30 0) seenByMino?))
					(messager say: 4 0 8 1 self 440)
				else
					(messager say: 4 0 9 1 self 440)
				)
			)
		)
	)
	
	(method (cue)
		(if (not ((ScriptID 30 0) seenByMino?)) (proc441_0 0))
	)
)

(instance celeste of Actor
	(properties
		x 103
		y 124
		noun 5
		view 4421
		cycleSpeed 24
	)
	
	(method (init)
		(self setCycle: RandCycle)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 5 1 local0 1 0 440)
			)
			(2
				(messager say: 5 2 local0 0 self 440)
			)
			(5
				(messager say: 5 5 local0 1 self 440)
			)
			(else 
				(messager say: 5 0 local1 1 0 440)
			)
		)
	)
	
	(method (cue)
		(if (not ((ScriptID 30 0) seenByMino?)) (proc441_0 0))
	)
)

(instance stepInFurther of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(myConv add: 440 2 3 8 1 add: 440 2 3 8 2 init: self)
			)
			(1 (proc441_0 0))
		)
	)
)

(instance handToHand of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(myConv add: 440 4 5 8 1 add: 440 4 5 8 2 init: self)
			)
			(1 (proc441_0 5))
		)
	)
)

(instance minoTrigger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 111 174 self)
			)
			(1
				(ego setHeading: 0)
				(= cycles 8)
			)
			(2
				(messager say: 1 0 3 1 self 440)
			)
			(3
				((ScriptID 30 0) seenByMino: 1)
				(minotaur
					view: 443
					setCycle: 0
					setLoop: 1
					cel: 0
					posn: 110 148
				)
				(UnLoad 128 444)
				(= cycles 2)
			)
			(4
				(theMusic number: 441 setLoop: -1 play:)
				(soundFx2 number: 433 setLoop: 1 play: self)
				(minotaur setLoop: 2 cel: 5 posn: 90 151)
			)
			(5
				(myConv add: 440 1 0 3 2 add: 440 1 0 3 3 init: self)
			)
			(6
				(soundFx2 number: 433 setLoop: 1 play: self)
			)
			(7
				(myConv add: 440 1 0 3 4 add: 440 1 0 3 5 init: self)
			)
			(8
				(minotaur
					view: 443
					setLoop: 2
					setCycle: Walk
					illegalBits: 0
					cycleSpeed: 3
					setMotion: MoveTo 59 177 self
				)
				(= cycles 4)
			)
			(9
				(self setScript: backup self)
			)
			(10
				(minotaur setLoop: 0)
				(= cycles 2)
			)
			(11
				(minotaur view: 4441)
				(UnLoad 128 443)
				(= ticks 5)
			)
			(12)
			(13
				(ego setLoop: 2 setMotion: MoveTo 145 151 self)
			)
			(14
				(ego setHeading: 210)
				(= ticks 6)
			)
			(15
				(messager say: 1 0 3 6 self 440)
			)
			(16
				(ego
					view: 441
					normal: 0
					setLoop: 1
					x: (+ (ego x?) 10)
					y: (+ (ego y?) 5)
					cel: 0
				)
				(UnLoad 128 900)
				(= ticks 6)
			)
			(17
				(minotaur cycleSpeed: 6 setCycle: Forward)
				(= ticks 8)
			)
			(18
				(if ((ScriptID 30 0) scarfOnMino?)
					(self cue:)
				else
					(messager say: 1 0 3 7 self 440)
				)
			)
			(19
				(theGame handsOn:)
				(theIconBar disable: 0)
				(= seconds 2)
			)
			(20 (= cycles 8))
			(21
				(if ((ScriptID 30 0) scarfOnMino?)
					(messager say: 4 38 11 1 self 440)
				else
					(self cue:)
				)
			)
			(22
				(cond 
					((== register 5) (messager say: 4 5 9 1 self 440))
					(((ScriptID 30 0) scarfOnMino?) (messager say: 4 38 11 2 self 440))
					(else (self cue:))
				)
			)
			(23 (= seconds 2))
			(24
				(if ((ScriptID 30 0) scarfOnMino?)
					(messager say: 4 38 11 3 self 440)
				else
					(self cue:)
				)
			)
			(25 (= seconds 2))
			(26
				(soundFx2 number: 433 setLoop: 1 play: self)
			)
			(27
				(if ((ScriptID 30 0) scarfOnMino?)
					(messager say: 4 38 11 4 self 440)
				else
					(= ticks 8)
				)
			)
			(28
				(messager say: 1 0 5 1 self 440)
			)
			(29
				(client setScript: minotaurCharging 0 register)
			)
		)
	)
)

(instance minotaurCharging of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (theGame isHandsOn?) (theGame handsOff:))
				(= cycles 12)
			)
			(1
				(if ((ScriptID 30 0) scarfOnMino?)
					(ego view: 441 setLoop: 0)
					(UnLoad 128 900)
				)
				(if (== register 5)
					(messager say: 4 5 9 1 self 440)
				else
					(self cue:)
				)
			)
			(2
				(soundFx2 number: 433 setLoop: 1 play:)
				(minotaur view: 4441 cel: 7 setPri: 14)
				(= ticks 2)
			)
			(3
				(minotaur
					view: 4442
					cel: 1
					posn: (+ (minotaur x?) 7) (- (minotaur y?) 6)
				)
				(UnLoad 128 4441)
				(= ticks 2)
			)
			(4
				(minotaur
					cel: 2
					posn: (+ (minotaur x?) 19) (- (minotaur y?) 9)
				)
				(= ticks 2)
			)
			(5
				(minotaur
					cel: 3
					posn: (+ (minotaur x?) 18) (+ (minotaur y?) 5)
				)
				(= ticks 2)
			)
			(6
				(minotaur
					cel: 4
					posn: (+ (minotaur x?) 8) (minotaur y?)
				)
				(= ticks 2)
			)
			(7
				(minotaur
					cel: 5
					posn: (+ (minotaur x?) 13) (- (minotaur y?) 8)
				)
				(= ticks 2)
			)
			(8
				(if ((ScriptID 30 0) scarfOnMino?)
					(ego cel: 0 setScript: 0 setCycle: EndLoop)
					(minotaur
						cel: 6
						posn: (+ (minotaur x?) 14) (minotaur y?)
					)
					(= ticks 2)
				else
					(curRoom setScript: hornSwaggled 0 register)
				)
			)
			(9
				(celeste setCycle: 0 stopUpd:)
				(soundFx2 number: 433 setLoop: 1 play:)
				(minotaur
					view: 4444
					cel: 0
					cycleSpeed: 1
					posn: (+ (minotaur x?) 22) (+ (minotaur y?) 1)
					setCycle: EndLoop self
				)
				(UnLoad 128 4442)
				(= cycles 2)
			)
			(10
				(theMusic number: 442 setLoop: 1 play: self)
			)
			(11)
			(12
				(ego reset: 0 posn: (- (ego x?) 28) (- (ego y?) 5))
				((curRoom obstacles?) dispose:)
				(= ticks 6)
			)
			(13
				(if ((ScriptID 30 0) scarfOnMino?)
					(messager say: 4 38 11 5 self 440)
				else
					(self cue:)
				)
			)
			(14
				(theMusic number: 443 setLoop: -1 play:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								178
								157
								208
								157
								241
								151
								239
								157
								319
								157
								319
								0
								0
								0
								0
								181
								43
								181
								86
								151
								75
								151
								83
								148
								125
								145
								128
								151
								168
								147
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 0 185 296 185 265 173 248 163 319 163 319 186 0 189
							yourself:
						)
				)
				(= local1 7)
				(Bset 1)
				(minotaur dispose:)
				(curRoom setScript: freeCeleste)
			)
		)
	)
)

(instance hornSwaggled of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setLoop: 1
					posn: (+ (ego x?) 10) (ego y?)
					cycleSpeed: 0
					setCycle: CycleTo 2 1 self
				)
				(minotaur
					view: 4443
					cel: 4
					cycleSpeed: 0
					setCycle: 0
					posn: (+ (minotaur x?) 10) (+ (minotaur y?) 3)
				)
				(UnLoad 128 4442)
				(theMusic number: 402 setLoop: 1 play:)
			)
			(1
				(if (== register 5)
					(messager say: 4 5 9 2 self 440)
				else
					(messager say: 1 0 5 2 self 440)
				)
			)
			(2
				(ego setCycle: EndLoop self)
				(minotaur setCycle: EndLoop self)
			)
			(3)
			(4
				(if (== msgType 2)
					(messager say: 4 5 9 3 self 440)
				else
					(self cue:)
				)
			)
			(5
				(if (!= msgType 2)
					(Print addText: 4 5 9 3 posn: -1 10 init:)
				)
				(soundFx2 number: 442 setLoop: 1 play: self)
				(minotaur view: 443 setLoop: 0 cel: 0)
				(UnLoad 128 4443)
			)
			(6
				(if modelessDialog (modelessDialog dispose:))
				(EgoDead 25)
			)
		)
	)
)

(instance egoTwin of Actor
	(properties
		view 900
		signal $4000
		illegalBits $0000
	)
)

(instance backup of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego hide:)
				(egoTwin
					setLoop: 3
					cel: 1
					posn: 111 174
					setScale: Scaler 100 99 190 0
					init:
				)
				(= ticks 8)
			)
			(1
				(egoTwin cel: 3)
				(= ticks 8)
			)
			(2
				(egoTwin setLoop: 7 cel: 1 posn: 109 172)
				(= ticks 8)
			)
			(3
				(egoTwin cel: 0 posn: 116 174)
				(= ticks 8)
			)
			(4
				(egoTwin setLoop: 1 cel: 4 posn: 118 174)
				(= ticks 8)
			)
			(5
				(egoTwin cel: 5 posn: 128 177)
				(= ticks 8)
			)
			(6
				(egoTwin setLoop: 5 cel: 2 posn: 132 176)
				(= ticks 8)
			)
			(7
				(egoTwin cel: 4 posn: 133 176)
				(= ticks 8)
			)
			(8
				(egoTwin cel: 5 posn: 137 174)
				(= ticks 8)
			)
			(9
				(egoTwin cel: 0 posn: 140 173)
				(= ticks 8)
			)
			(10
				(egoTwin setLoop: 2 cel: 2 posn: 144 171)
				(= ticks 8)
			)
			(11
				(ego
					setLoop: (egoTwin loop?)
					cel: (egoTwin cel?)
					x: (egoTwin x?)
					y: (egoTwin y?)
					show:
				)
				(egoTwin dispose:)
				(self dispose:)
			)
		)
	)
)

(instance freeCeleste of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 118 149 self)
			)
			(1 (= ticks 4))
			(2
				(myConv
					add: 440 4 38 11 6
					add: 440 4 38 11 7
					add: 440 4 38 11 8
					add: 440 4 38 11 9
					add: 440 4 38 11 10
					init: self
				)
			)
			(3
				(ego
					view: 441
					normal: 0
					setLoop: 2
					cycleSpeed: 12
					posn: 110 150
					setCycle: EndLoop self
				)
			)
			(4
				(messager say: 4 38 11 11 self 440)
			)
			(5
				(soundFx2 number: 445 setLoop: -1 play:)
				(ego setLoop: 3 setCycle: Forward)
				(Bclr 161)
				(= seconds 5)
			)
			(6
				(soundFx2 stop:)
				(celeste
					view: 4421
					setLoop: 1
					cel: 0
					cycleSpeed: 6
					setCycle: CycleTo 2 1
				)
				(ego
					posn: 119 149
					reset: 1
					get: 8
					setMotion: MoveTo (- (ego x?) 20) (+ (ego y?) 12) self
				)
			)
			(7
				(ego setHeading: 45)
				(= seconds 1)
			)
			(8
				(ego setHeading: 90)
				(celeste setCycle: EndLoop self)
			)
			(9
				(myConv
					add: 440 4 38 11 12
					add: 440 4 38 11 13
					add: 440 4 38 11 14
					init: self
				)
			)
			(10
				(celeste
					view: 364
					setLoop: 2
					setCycle: Walk
					cycleSpeed: (ego cycleSpeed?)
					moveSpeed: (ego moveSpeed?)
					posn: (celeste x?) (+ (celeste y?) 20)
					setMotion: MoveTo 105 154 self
				)
				(UnLoad 128 4421)
			)
			(11
				(celeste setLoop: 0 setMotion: PolyPath 165 165 self)
			)
			(12
				(celeste setMotion: PolyPath 255 160 self)
			)
			(13
				(theMusic fade: 0 6 6)
				(celeste setMotion: PolyPath 340 160 self)
				(ego setMotion: PolyPath 300 160 self)
			)
			(14)
			(15
				(NextAct)
				(ego put: 41 440)
				(theIconBar curIcon: (theIconBar at: 0))
				(curRoom newRoom: 340)
			)
		)
	)
)
