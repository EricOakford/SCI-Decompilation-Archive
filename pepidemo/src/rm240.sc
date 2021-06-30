;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include game.sh)
(use Main)
(use CycleBet)
(use BalloonTalker)
(use TWInvItem)
(use TWRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm240 0
	watchManTalker 1
)

(local
	local0
	local1
	local2
	local3
	local4
	egoCycleSpeed
	local6
	local7
	[local8 9] = [61 161 3 14 86 0 0 240 1]
	[local17 9] = [126 163 4 0 0 0 0 240 3]
)
(instance rm240 of ADRoom
	(properties
		noun 12
		picture 240
		horizon 75
		north 250
		vanishingY -60
	)
	
	(method (init)
		(if (== gameAct 4)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							89
							154
							60
							176
							23
							176
							23
							185
							87
							185
							87
							172
							143
							172
							213
							172
							213
							145
							199
							145
							199
							138
							153
							138
							153
							154
						yourself:
					)
			)
		else
			(self
				addObstacle:
					(roomPoly
						type: 3
						init:
							61
							145
							47
							154
							89
							154
							60
							176
							23
							176
							23
							185
							87
							185
							87
							172
							123
							162
							143
							172
							213
							172
							213
							145
							199
							145
							199
							142
							155
							142
							155
							145
						yourself:
					)
			)
		)
		(super init: &rest)
		(ego
			init:
			normalize:
			x: 197
			y: 144
			ignoreActors: 1
			setScale: 0
		)
		(smallCrate init: approachVerbs: 7 6 stopUpd:)
		(door approachVerbs: 86 init:)
		(herbs init: stopUpd:)
		(bigCrate init: setOnMeCheck: 1 4)
		(eastCrates init: setOnMeCheck: 1 4096)
		(westCrates init:)
		(barrel init: setOnMeCheck: 1 8192)
		(floor init: setOnMeCheck: 1 2048)
		(theWindow init: approachVerbs: 7)
		(pillow init: approachVerbs: 6 setOnMeCheck: 1 2)
		(boxByWindow init: setOnMeCheck: 1 16384)
		(herbFeat init: setOnMeCheck: 1 2)
		(leftHerbs init: setOnMeCheck: 1 2)
		(rNail init: approachVerbs: 7 21)
		(lNail init: approachVerbs: 7 21)
		((ScriptID 895 1)
			view: 807
			x: 117
			y: 175
			init:
			normalize:
			approachVerbs: 85 7 6
			approachX: 109
			approachY: 170
			approachDist: 5
			setHeading: 45
			cel: 6
			actions: ljActions
		)
		(hammer init: approachVerbs: 7 stopUpd:)
		(canvasCover init: stopUpd:)
		(nail init: stopUpd:)
		(self setScript: bummedToon)
		(inventory
			addAfter:
				(inventory at: 5)
				(Canvas init: yourself:)
				(Hammer init: yourself:)
				(Nails init: yourself:)
				(Tools init: yourself:)
		)
		(theGame handsOn:)
	)
	
	(method (dispose)
		(inventory
			delete: Canvas
			delete: Hammer
			delete: Nails
			delete: Tools
		)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(85
				(if (== gameAct 2)
					(messager say: 12 85 3)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sHandOnWindow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (not local6) (watchMan mover?))
					(= local6 ((watchMan mover?) x?))
				)
				(watchMan
					view: 810
					setCycle: 0
					setMotion: 0
					setLoop: 0
					cel: 0
					forceUpd:
					stopUpd:
				)
				((ScriptID 895 1) normal: 99)
				(= cycles 3)
			)
			(1
				((ScriptID 895 0) view: 1800 loop: 8 forceUpd: stopUpd:)
				(= cycles 3)
			)
			(2
				(messager say: 17 7 0 0 self)
			)
			(3
				((ScriptID 895 0) normalize:)
				(watchMan cue:)
				(theGame handsOn:)
				((ScriptID 895 1)
					normal: 1
					setScript: (ScriptID 838 0) 0 0
				)
				(self dispose:)
			)
		)
	)
)

(instance sWatchMan of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== register 1) (= state 2) (= register 0))
				(= cycles 2)
			)
			(1
				(watchMan
					view: 815
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 126 128 self
				)
			)
			(2
				(if (not (curRoom script?))
					(if (or (== register 2) (not (Random 0 3)))
						(self setScript: sBlaBla self)
					else
						(= cycles 2)
					)
				else
					(= cycles 2)
				)
			)
			(3
				(watchMan
					view: 815
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 177 128 self
				)
			)
			(4 (= seconds 3))
			(5
				(watchMan
					view: 815
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 126 128 self
				)
			)
			(6
				(if (not (curRoom script?))
					(if (or (== register 2) (not (Random 0 3)))
						(self setScript: sBlaBla self)
					else
						(= cycles 2)
					)
				else
					(= cycles 2)
				)
			)
			(7
				(watchMan
					view: 815
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 45 128 self
				)
			)
			(8 (self init:))
		)
	)
)

(instance sBlaBla of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0)
				(if (ego heading?)
					(ego setHeading: 0)
					(= seconds 2)
				else
					(= cycles 2)
				)
			)
			(1
				(watchMan view: 810 setLoop: 0 cel: 0 setCycle: 0)
				(= cycles 5)
			)
			(2
				(cond 
					(modelessDialog (theGame handsOn:) (self dispose:))
					((not (== gameAct 4))
						(switch (Random 0 4)
							(0
								(messager say: 15 0 28 0 self)
							)
							(1
								(messager say: 15 0 29 0 self)
							)
							(2
								(messager say: 15 0 30 0 self)
							)
							(3
								(messager say: 15 0 31 0 self)
							)
							(4
								(messager say: 15 0 32 0 self)
							)
						)
					)
					(else (= cycles 2))
				)
			)
			(3 (= ticks 30))
			(4
				(self dispose:)
				(theGame handsOn:)
			)
		)
	)
)

(instance sHammerNails of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion:)
			)
			(1 (ego setHeading: 225 self))
			(2 (= cycles 2))
			(3
				((ScriptID 2000 3) talkWidth: 130)
				(messager say: 9 21 0 0 self)
			)
			(4
				((ScriptID 2000 3) talkWidth: 0)
				(ego setHeading: 310 self)
			)
			(5 (= cycles 2))
			(6
				(ego
					view: 241
					posn: 162 141
					setLoop: 2
					setCel: 0
					setCycle: 0
				)
				(= cycles 2)
			)
			(7
				(ego setCycle: CycleTo 2 1 self)
				(theMusic2 number: 2401 setLoop: 1 play:)
			)
			(8
				(ego setCel: 3)
				(theMusic2 number: 2401 setLoop: 1 play:)
				(nail dispose:)
				(= ticks 20)
			)
			(9
				(ego setCel: 2)
				(theMusic2 number: 2401 setLoop: 1 play:)
				(if (-- register) (= state (- state 2)))
				(= ticks 20)
			)
			(10
				(ego setCycle: EndLoop self)
				(theMusic2 number: 2401 setLoop: 1 play:)
			)
			(11 (= ticks 20))
			(12
				(theGame points: 218 2 handsOn:)
				(if (and (ego has: 6) (ego has: 7))
					(ego put: 6 put: 7 get: 9 normalize:)
					(theIconBar advanceCurIcon:)
				else
					(ego x: 168 y: 142 get: 8 normalize:)
				)
				(= cycles 2)
			)
			(13 (self dispose:))
		)
	)
)

(instance sFeelPillow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (!= (ego x?) 159) (!= (ego y?) 176))
					(-- state)
					(ego setMotion: PolyPath 159 176 self)
				else
					(ego setHeading: 125 self)
				)
			)
			(1 (= cycles 2))
			(2
				(messager say: 13 7 0 0 self)
			)
			(3
				(ego normalize:)
				(= cycles 2)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClimbBox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theGame points: 219 2)
				(ego setMotion: PolyPath 138 145 self)
				(sWatchMan dispose:)
			)
			(1
				(ego
					view: 240
					setLoop: 4
					setCel: 0
					x: 128
					y: 154
					setCycle: EndLoop self
				)
			)
			(2
				(ego
					view: 244
					setLoop: 0
					setCel: 0
					x: 130
					y: 131
					setCycle: CycleTo 5 1 self
				)
			)
			(3
				(theMusic2 number: 2400 setLoop: 1 play:)
				(ego setCycle: CycleTo 7 1 self)
			)
			(4
				(theMusic2 number: 2400 setLoop: 1 play:)
				(ego setCycle: CycleTo 12 1 self)
			)
			(5
				(theMusic2 number: 2400 setLoop: 1 play:)
				(watchMan
					view: 810
					setCycle: 0
					setMotion: 0
					setLoop: 0
					cel: 0
					forceUpd:
				)
				(ego setCycle: EndLoop self)
			)
			(6
				(canvasCover
					view: 245
					init:
					loop: 0
					cel: 0
					x: 159
					y: 122
					ignoreActors: 1
					addToPic:
				)
				(watchMan dispose:)
				(= cycles 2)
			)
			(7
				(ego
					view: 240
					setLoop: 4
					setCel: 255
					x: 90
					y: 153
					put: 6
					setCycle: BegLoop self
				)
			)
			(8
				(ego put: 9 normalize: x: 81 y: 153)
				(= cycles 2)
			)
			(9
				(messager say: 17 156 16 0 self)
			)
			(10
				(= local0 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPushBox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 68 156 self)
			)
			(1 (ego setHeading: 225 self))
			(2 (= cycles 2))
			(3 (messager say: 8 7 7 0 self))
			(4
				(theGame points: 221 1)
				(smallCrate hide:)
				(ego
					view: 240
					setLoop: 3
					setCel: 0
					setCycle: 0
					x: 55
					y: 177
				)
				(= cycles 2)
			)
			(5
				(digSpot init: approachVerbs: 86 30 6)
				(roomPoly dispose:)
				(ego setCycle: EndLoop self)
				(theMusic2 number: 2402 setLoop: 1 play:)
			)
			(6
				(roomPoly
					type: 3
					init:
						57
						148
						36
						163
						68
						163
						68
						176
						61
						176
						61
						185
						125
						185
						125
						178
						213
						178
						213
						148
						160
						148
					yourself:
				)
				(smallCrate
					show:
					x: 56
					y: 189
					setPri: 13
					ignoreActors: 1
					addToPic:
				)
				(ego normalize: x: 59 y: 167)
				(= cycles 2)
			)
			(7
				(client setScript: sPushSmallCrate)
			)
		)
	)
)

(instance sGetHammer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (not local6) (watchMan mover?))
					(= local6 ((watchMan mover?) x?))
				)
				(watchMan
					view: 810
					setCycle: 0
					setMotion: 0
					setLoop: 0
					cel: 0
					forceUpd:
					stopUpd:
				)
				((ScriptID 895 1) normal: 99)
				(ego setMotion: PolyPath 45 176 self)
			)
			(1
				(ego view: 1800 loop: 8 setCycle: 0 forceUpd: stopUpd:)
				(= cycles 3)
			)
			(2
				(messager say: 18 7 18 0 self)
			)
			(3 (= cycles 2))
			(4
				(ego normalize: loop: 8 cel: 3 heading: 360 setCycle: 0)
				(= cycles 3)
			)
			(5
				(if (and (ego has: 8) (ego has: 6))
					((ScriptID 895 0) get: 9 put: 6 put: 8 normalize:)
					(theIconBar advanceCurIcon:)
				else
					(ego get: 7 normalize:)
				)
				(if (and (not local6) (watchMan mover?))
					(= local6 ((watchMan mover?) x?))
				)
				(watchMan
					view: 810
					setCycle: 0
					setMotion: 0
					setLoop: 0
					cel: 0
				)
				(hammer dispose:)
				(= cycles 3)
			)
			(6
				(watchMan stopUpd:)
				((ScriptID 895 0) stopUpd:)
				(= cycles 3)
			)
			(7
				(theGame points: 216 1 handsOn:)
				(watchMan cue:)
				((ScriptID 895 1)
					normal: 1
					setScript: (ScriptID 838 0) 0 0
				)
				(self dispose:)
			)
		)
	)
)

(instance pawPepper of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 895 1) setMotion: MoveTo 128 175 self)
			)
			(1
				(messager say: 10 86 0 0 self)
			)
			(2
				((ScriptID 895 1) normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTalkToGuard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
					(and
						(not register)
						(!= (ego x?) 141)
						(!= (ego y?) 145)
					)
					(-- state)
					(ego setMotion: PolyPath 141 145 self)
				else
					(ego setMotion: 0)
					(watchMan setScript: 0 setMotion: 0 setCycle: 0)
					(cond 
						((== (watchMan x?) 128) (= ticks 2))
						((< (watchMan x?) 128)
							(watchMan
								setLoop: 2
								setCycle: Walk
								setMotion: MoveTo 126 128 self
							)
						)
						(else
							(watchMan
								setLoop: 3
								setCycle: Walk
								setMotion: MoveTo 126 128 self
							)
						)
					)
					(if (ego heading?)
						(ego setHeading: 0 self)
					else
						(= cycles 2)
					)
				)
			)
			(1)
			(2
				(watchMan view: 810 setLoop: 0 cel: 0 setCycle: 0)
				(= cycles 5)
			)
			(3
				(switch register
					(1
						(messager say: 3 85 3 0 self)
					)
					(2 (messager say: 8 7 5 0 self))
					(else 
						(switch (++ local2)
							(1
								(messager say: 20 85 24 0 self)
							)
							(2
								(messager say: 20 85 25 0 self)
							)
							(3
								(messager say: 20 85 26 0 self)
							)
							(else 
								(messager say: 20 85 27 0 self)
							)
						)
					)
				)
			)
			(4
				(watchMan setScript: sWatchMan 0 1)
				(ego normalize:)
				(= register 0)
				(= cycles 2)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance bummedToon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic number: 240 setLoop: -1 play:)
				((ScriptID 895 1) normal: 0)
				(ego setHeading: 225 self)
			)
			(1 (= cycles 2))
			(2
				(messager say: 15 7 23 1 3 self)
			)
			(3
				((ScriptID 895 0) stopUpd:)
				((ScriptID 2004 0) talkWidth: 60)
				(messager say: 15 7 23 4 self)
			)
			(4 (= cycles 2))
			(5
				((ScriptID 895 1) normal: 2)
				((ScriptID 2004 0) talkWidth: 0)
				(watchMan
					init:
					ignoreActors: 1
					illegalBits: 0
					x: 45
					y: 128
					setLoop: 2
					setScript: sWatchMan
					setScale: 200
				)
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetHerbs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (not local6) (watchMan mover?))
					(= local6 ((watchMan mover?) x?))
				)
				(watchMan
					view: 810
					setCycle: 0
					setMotion: 0
					setLoop: 0
					cel: 0
					forceUpd:
					stopUpd:
				)
				((ScriptID 895 1) normal: 0)
				(ego setMotion: PolyPath 222 149 self)
			)
			(1
				(ego forceUpd: stopUpd:)
				(= cycles 3)
			)
			(2
				(cond 
					((and (not local0) (not local7)) (++ local7) (messager say: 7 7 5 0 self))
					((and (not local0) (not (Btst 57))) (messager say: 7 7 6 0 self))
					((and (not (Btst 57)) (not (Btst 10))) (++ state) (= cycles 2))
				)
			)
			(3
				(theGame handsOn:)
				(watchMan cue:)
				((ScriptID 895 1) normal: 1)
				(self dispose:)
			)
			(4
				(ego
					view: 240
					setLoop: 4
					setCel: 0
					posn: 247 140
					setCycle: EndLoop self
				)
			)
			(5
				((ScriptID 895 0)
					view: 241
					x: 242
					y: 107
					setLoop: 3
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(6 (= cycles 2))
			(7 (messager say: 7 7 7 0 self))
			(8
				(theGame points: 220 3)
				(ego get: 1)
				(herbs dispose:)
				(Bset 57)
				(= seconds 2)
			)
			(9 (ego setCycle: BegLoop self))
			(10
				((ScriptID 895 0)
					view: 240
					x: 247
					y: 140
					setLoop: 4
					setCel: 10
					setCycle: BegLoop self
				)
			)
			(11
				(ego
					x: 222
					y: 149
					normalize:
					setMotion: MoveTo 200 150 self
				)
			)
			(12
				(theGame handsOn:)
				(watchMan cue:)
				((ScriptID 895 1) normal: 1)
				(self dispose:)
			)
		)
	)
)

(instance sPushSmallCrate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 73 156 self)
			)
			(1
				(ego normalize: setMotion: MoveTo 148 173 self)
			)
			(2 (ego setHeading: 225 self))
			(3 (= cycles 2))
			(4
				(messager say: 15 0 15 0 self)
			)
			(5
				(= egoCycleSpeed (ego cycleSpeed?))
				(ego
					view: 242
					setLoop: 0
					setCel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(6 (= ticks 30))
			(7
				(ego
					view: 242
					setLoop: 1
					setCel: 0
					setPri: 14
					setCycle: EndLoop self
				)
			)
			(8
				(theMusic number: 247 setLoop: 1 play: self)
			)
			(9
				(theMusic number: 241 setLoop: -1 play:)
				((ScriptID 895 0)
					actions: pepperActions
					cycleSpeed: egoCycleSpeed
				)
				((ScriptID 895 1) normal: 0)
				(theGame setEgo: (ScriptID 895 1))
				(= cycles 2)
			)
			(10
				((ScriptID 895 1)
					normalize:
					ignoreActors: 1
					illegalBits: 0
					setMotion: MoveTo 114 170 self
				)
			)
			(11
				(theGame handsOn:)
				((ScriptID 895 0) stopUpd:)
				(SetCursor 100 0)
				(self dispose:)
			)
		)
	)
)

(instance sDigTunnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 55 164 self)
			)
			(1
				(= egoCycleSpeed (ego cycleSpeed?))
				(ego
					view: 242
					setLoop: 3
					setCel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
				(theMusic2 number: 903 setLoop: -1 play:)
			)
			(2
				((ScriptID 895 1) setCycle: CycleBet 7 2 2 self)
			)
			(3
				(theMusic2 stop:)
				(messager say: 14 30 0 1 self)
			)
			(4
				(hole init: addToPic:)
				(= cycles 2)
			)
			(5
				((ScriptID 895 1)
					cycleSpeed: egoCycleSpeed
					normalize:
					setMotion: MoveTo 87 144 self
				)
			)
			(6 (ego setHeading: 225 self))
			(7
				((ScriptID 895 1) approachX: 85 approachY: 156 normal: 1)
				(= cycles 2)
			)
			(8
				(theGame setEgo: (ScriptID 895 0))
				((ScriptID 895 0) actions: 0)
				((ScriptID 895 1) ignoreActors: 0)
				(= cycles 2)
			)
			(9 (ego setCycle: BegLoop self))
			(10
				(ego normalize:)
				(roomPoly dispose:)
				(= cycles 2)
			)
			(11
				(messager say: 14 30 0 2 self)
			)
			(12
				(roomPoly
					type: 3
					init:
						61
						145
						47
						154
						89
						154
						60
						176
						23
						176
						23
						185
						87
						185
						87
						172
						213
						172
						213
						145
						199
						145
						199
						138
						153
						138
						153
						145
					yourself:
				)
				(ego setMotion: MoveTo 100 165 self)
			)
			(13 (= cycles 2))
			(14
				(messager say: 14 30 0 3 self)
			)
			(15
				(theGame handsOn:)
				(SetCursor 100 -1)
				(self dispose:)
			)
		)
	)
)

(instance sEscape of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff: points: 236 1)
				(theMusic fade:)
				(= cycles 1)
			)
			(1
				((ScriptID 895 0) setMotion: MoveTo 50 163 self)
			)
			(2 (= cycles 2))
			(3
				(messager say: 22 7 0 0 self)
			)
			(4
				(theMusic number: 243 setLoop: 1 play:)
				((ScriptID 895 0)
					view: 242
					setLoop: 4
					cel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(5
				((ScriptID 895 1)
					normalize:
					normal: 0
					setMotion: MoveTo (digSpot approachX?) (digSpot approachY?) self
				)
			)
			(6 (= seconds 2))
			(7 (curRoom newRoom: 250))
		)
	)
)

(instance sGetCanvas of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (not local6) (watchMan mover?))
					(= local6 ((watchMan mover?) x?))
				)
				(watchMan
					view: 810
					setCycle: 0
					setMotion: 0
					setLoop: 0
					cel: 0
					forceUpd:
					stopUpd:
				)
				(ego
					ignoreActors: 1
					illegalBits: 0
					setMotion: PolyPath 200 140 self
				)
				((ScriptID 895 1) normal: 99)
			)
			(1
				(ego view: 1800 loop: 8 setCycle: 0 forceUpd: stopUpd:)
				(= cycles 3)
			)
			(2
				(messager say: 2 7 18 0 self)
			)
			(3 (= cycles 2))
			(4
				(if (and (not local1) (not (Btst 10)))
					(= local1 1)
					(ego view: 241 setLoop: 0 setCel: 0 setCycle: 0)
					(canvasCover dispose:)
					(= cycles 2)
				else
					(theGame handsOn:)
					(ego normalize:)
					(watchMan cue:)
					((ScriptID 895 1)
						normal: 1
						setScript: (ScriptID 838 0) 0 0
					)
					(self dispose:)
				)
			)
			(5 (ego setCycle: EndLoop self))
			(6
				(if (and (ego has: 7) (ego has: 8))
					((ScriptID 895 0)
						normalize:
						loop: 8
						cel: 2
						get: 9
						put: 7
						put: 8
					)
					(theIconBar advanceCurIcon:)
				else
					((ScriptID 895 0) normalize: loop: 8 cel: 2 get: 6)
				)
				(= cycles 3)
			)
			(7
				(watchMan stopUpd:)
				((ScriptID 895 0) stopUpd:)
				(= cycles 2)
			)
			(8
				(ego normalize:)
				(= cycles 2)
			)
			(9
				(theGame points: 217 1 handsOn:)
				(watchMan cue:)
				((ScriptID 895 1)
					normal: 1
					setScript: (ScriptID 838 0) 0 0
				)
				(self dispose:)
			)
		)
	)
)

(instance watchMan of Actor
	(properties
		noun 20
		modNum 240
		view 815
		loop 2
		priority 2
		signal $6810
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(85
				(curRoom setScript: sTalkToGuard 0 0)
			)
			(7
				(messager say: 20 7 0 0 self)
			)
			(84
				(theMusic2 number: 927 setLoop: 1 play:)
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue &tmp temp0)
		(self startUpd:)
		(if local6
			(if (> local6 x) (= temp0 2) else (= temp0 3))
			(self
				view: 815
				setCycle: Walk
				setLoop: temp0
				setMotion: MoveTo local6 y script
			)
			(= local6 0)
		)
	)
)

(instance canvasCover of View
	(properties
		x 291
		y 140
		noun 2
		modNum 240
		view 246
		signal $4801
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(if (== ego (ScriptID 895 0))
					(messager say: 2 6 18)
				else
					(messager say: 2 6 1)
				)
			)
			(7
				(if local0
					(messager say: 2 7 33)
				else
					(curRoom setScript: sGetCanvas)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance nail of View
	(properties
		x 148
		y 117
		view 245
		loop 1
	)
)

(instance smallCrate of View
	(properties
		x 70
		y 170
		noun 8
		approachX 73
		approachY 156
		view 246
		cel 4
		priority 12
		signal $4811
	)
	
	(method (doVerb theVerb)
		(= global215 44)
		(switch theVerb
			(84
				(if (and (not (ego has: 7)) (not (ego has: 9)))
					(self noun: 18)
					(super doVerb: theVerb)
				else
					(self noun: 8)
					(super doVerb: theVerb)
				)
			)
			(6
				(if (and (not (ego has: 7)) (not (ego has: 9)))
					(messager say: 18 6 18)
				else
					(messager say: 8 6)
				)
			)
			(7
				(cond 
					(
						(and
							(not (ego has: 7))
							(not (ego has: 9))
							(not local0)
						)
						(curRoom setScript: sGetHammer)
					)
					((and (not local4) (not local0)) (= local4 1) (curRoom setScript: sTalkToGuard 0 2))
					((and local4 (not local0)) (messager say: 8 7 6))
					((features contains: digSpot) (messager say: 8 7 8))
					(else (curRoom setScript: sPushBox))
				)
			)
			(22 (messager say: 8 21))
			(85 (messager say: 8 85 5))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hole of View
	(properties
		x 25
		y 149
		noun 22
		view 246
		cel 6
		signal $4810
	)
)

(instance herbs of View
	(properties
		x 256
		y 68
		noun 7
		view 246
		cel 1
		priority 3
		signal $4810
	)
	
	(method (doVerb theVerb)
		(= global215 44)
		(switch theVerb
			(7
				(curRoom setScript: sGetHerbs)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hammer of View
	(properties
		x 41
		y 242
		z 100
		noun 18
		approachX 35
		approachY 170
		view 246
		cel 5
		priority 12
		signal $4810
	)
	
	(method (doVerb theVerb)
		(smallCrate doVerb: theVerb)
	)
)

(instance bigCrate of Feature
	(properties
		x 248
		y 122
		noun 4
		nsTop 99
		nsLeft 217
		nsBottom 145
		nsRight 280
	)
)

(instance eastCrates of Feature
	(properties
		y 110
		noun 4
	)
)

(instance westCrates of Feature
	(properties
		x 11
		y 130
		noun 16
		nsTop 88
		nsBottom 173
		nsRight 23
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(= global215 44)
		(super doVerb: theVerb)
	)
)

(instance barrel of Feature
	(properties
		y 170
		noun 1
	)
)

(instance floor of Feature
	(properties
		y 50
		noun 5
	)
)

(instance theWindow of Feature
	(properties
		x 116
		y 1
		z -100
		noun 17
		modNum 240
		nsTop 55
		nsLeft 68
		nsBottom 128
		nsRight 165
		approachX 123
		approachY 151
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(156
				(curRoom setScript: sClimbBox)
			)
			(85
				(if (== gameAct 4)
					(messager say: 17 85)
				else
					(messager say: 17 85 3)
				)
			)
			(21 (messager say: 17 21))
			(7
				(if (== gameAct 4)
					(messager say: 17 7 39)
				else
					(curRoom setScript: sHandOnWindow)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance lNail of Feature
	(properties
		x 78
		y 219
		z 100
		noun 9
		nsTop 110
		nsLeft 70
		nsBottom 125
		nsRight 85
		approachX 76
		approachY 145
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (messager say: 9 6 18))
			(7 (messager say: 9 7 18))
			(21 (rNail doVerb: 21))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rNail of Feature
	(properties
		x 150
		y 219
		z 100
		noun 9
		nsTop 110
		nsLeft 140
		nsBottom 129
		nsRight 160
		approachX 154
		approachY 146
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (messager say: 9 6 18))
			(7 (messager say: 9 7 18))
			(21
				(if (ego has: 8)
					(messager say: 19 7)
				else
					(curRoom setScript: sHammerNails)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pillow of Feature
	(properties
		x 170
		y 168
		noun 13
		nsTop 148
		nsLeft 133
		nsBottom 189
		nsRight 208
		approachX 163
		approachY 173
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 7)
			(curRoom setScript: sFeelPillow)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance boxByWindow of Feature
	(properties
		x 114
		y 126
		noun 6
		nsTop 109
		nsLeft 78
		nsBottom 143
		nsRight 150
	)
	
	(method (doVerb theVerb)
		(= global215 64)
		(switch theVerb
			(7
				(if (and (ego has: 8) (ego has: 6))
					(curRoom setScript: sClimbBox)
				else
					(messager say: 6 7)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance door of Feature
	(properties
		x 203
		y 97
		noun 3
		modNum 240
		nsTop 57
		nsLeft 174
		nsBottom 137
		nsRight 232
		approachX 172
		approachY 147
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (cast contains: watchMan)
					(curRoom setScript: sTalkToGuard 0 0)
				else
					(messager say: 3 7 3)
				)
			)
			(85
				(if
				(and (cast contains: watchMan) (not (== gameAct 4)))
					(curRoom setScript: sTalkToGuard 0 1)
				else
					(super doVerb: theVerb)
				)
			)
			(84 (curRoom doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance herbFeat of Feature
	(properties
		x 271
		y 32
		noun 21
		nsLeft 223
		nsBottom 65
		nsRight 319
	)
	
	(method (doVerb theVerb)
		(= global215 44)
		(super doVerb: theVerb)
	)
)

(instance leftHerbs of Feature
	(properties
		x 36
		y 41
		noun 21
		nsBottom 83
		nsRight 72
	)
	
	(method (doVerb theVerb)
		(= global215 44)
		(super doVerb: theVerb)
	)
)

(instance digSpot of Feature
	(properties
		x 48
		y 159
		noun 14
		nsTop 149
		nsLeft 26
		nsBottom 170
		nsRight 70
		sightAngle 40
		approachX 61
		approachY 161
		_approachVerbs 17
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(cond 
					((Btst 223) (messager say: 22 6))
					((== ego (ScriptID 895 0)) (messager say: 14 6 2))
					(else (messager say: 14 6 1))
				)
			)
			(7
				(if (not (Btst 223)) (messager say: 14 7))
				(curRoom setScript: sEscape)
			)
			(86
				(theGame points: 222 2)
				(curRoom setScript: (ScriptID 875 1) 0 @local8)
			)
			(30
				(theGame points: 223 3)
				(curRoom setScript: sDigTunnel)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance watchManTalker of BalloonTalker
	(properties
		x 0
		y 93
		talkWidth 150
	)
	
	(method (init)
		(if (>= (watchMan x?) 164)
			(= x 10)
			(= tailPosn 1)
		else
			(= x (+ (watchMan x?) 5))
			(= tailPosn 0)
		)
		(if (and (not local6) (watchMan mover?))
			(= local6 ((watchMan mover?) x?))
		)
		(watchMan setMotion: 0 setCycle: 0)
		(super init: &rest)
	)
	
	(method (say)
		(watchMan stopUpd:)
		(super say: &rest)
	)
)

(instance Canvas of TWInvItem
	(properties
		loop 1
		cel 2
		message 20
		signal $0002
		noun 2
		modNum 240
	)
)

(instance Hammer of TWInvItem
	(properties
		loop 1
		cel 3
		message 21
		signal $0002
		noun 18
		modNum 240
	)
)

(instance Nails of TWInvItem
	(properties
		loop 1
		cel 4
		message 154
		signal $0002
		noun 9
		modNum 240
	)
)

(instance Tools of TWInvItem
	(properties
		loop 9
		message 156
		signal $0002
		noun 25
		modNum 240
	)
)

(instance pepperActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(30
					(curRoom setScript: pawPepper)
					(return 1)
				)
				(86
					(curRoom setScript: (ScriptID 875 1) 0 @local17)
					(return 1)
				)
				(34
					(messager say: 10 34)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance ljActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(85
				(if (Btst 223)
					(messager say: 23 85 36)
				else
					(messager say: 23 85 35)
				)
			)
			(6 (messager say: 23 6))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance roomPoly of Polygon
	(properties)
)
