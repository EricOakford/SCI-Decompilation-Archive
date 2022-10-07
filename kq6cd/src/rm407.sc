;;; Sierra Script 1.0 - (do not remove this comment)
(script# 407)
(include sci.sh)
(use Main)
(use rLab)
(use n401)
(use Kq6Procs)
(use PolyPath)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm407 0
)

(instance rm407 of LabRoom
	(properties
		west 400
	)
	
	(method (init)
		(proc401_2)
		(super init: &rest)
		(hiwEastWall init:)
		(if
		(and (not (Btst 1)) (not (rLab seenSecretLatch?)))
			(curRoom setScript: holeInWallEntry)
		else
			((ScriptID 30 0) cue:)
			(curRoom setScript: (ScriptID 30 1))
		)
		((ScriptID 30 0) initCrypt: 1)
		((ScriptID 30 5) addToPic:)
		((ScriptID 30 9) addToPic:)
		((ScriptID 30 8) addToPic:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 2 1 44 1 0 400)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(theGame handsOff:)
		(curRoom setScript: emptyHandedDeath)
	)
	
	(method (notify)
		((ScriptID 30 5) addToPic:)
		((ScriptID 30 9) addToPic:)
		((ScriptID 30 8) addToPic:)
		((ScriptID 30 3) show:)
	)
)

(instance holeInset of View
	(properties
		x 159
		y 190
		z 85
		view 487
		priority 15
		signal $6010
	)
)

(instance holeRoom of View
	(properties
		x 159
		y 139
		view 414
		priority 12
		signal $6010
	)
)

(instance tapestry of View
	(properties
		x 158
		y 135
		view 414
		loop 1
		priority 13
		signal $6010
	)
)

(instance sDoor of Prop
	(properties
		x 204
		y 96
		view 414
		loop 4
		priority 13
		signal $6010
		cycleSpeed 3
	)
)

(instance minoInset of Prop
	(properties
		x 158
		y 135
		view 414
		loop 2
		priority 14
		signal $6010
		cycleSpeed 3
	)
)

(instance tapeMove of Prop
	(properties
		x 158
		y 135
		view 414
		loop 3
		priority 13
		signal $6010
		cycleSpeed 3
	)
)

(instance mino of Actor
	(properties
		x 5
		y 158
		yStep 3
		view 443
		signal $4000
		xStep 5
	)
)

(instance theHole of Actor
	(properties
		x 261
		y 164
		z 50
		noun 17
		modNum 400
		view 232
		loop 6
		signal $6810
		moveSpeed 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(holeTimer dispose:)
				(curRoom setScript: lookInHole)
			)
			(2
				(messager say: 17 2 0 1 0 400)
			)
			(5
				(curRoom setScript: getTheHole)
			)
			(else 
				(messager say: 17 0 0 1 0 400)
			)
		)
	)
)

(instance hiwEastWall of Feature
	(properties
		x 255
		y 155
		noun 16
		modNum 400
		approachX 242
		approachY 162
	)
	
	(method (init)
		(self approachVerbs: 5 setOnMeCheck: 1 1024 16384)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(25
				(curRoom setScript: putHoleOnWall 0 1)
			)
			(1
				(if (rLab seenSecretLatch?)
					(messager say: 16 1 46 1 0 400)
				else
					(messager say: 16 1 45 0 0 400)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance holeInWallEntry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 36 158 init: setMotion: PolyPath 73 158 self)
			)
			(1
				((ScriptID 30 0) cue:)
				(theIconBar enable: 6)
				(soundFx2 number: 401 setLoop: 1 play: self)
			)
			(2
				(cond 
					((== (rLab timesInHoleWallRoom?) 0) (messager say: 1 0 39 0 self 400))
					((ego has: 18) (messager say: 1 0 41 1 self 400))
					((not (rLab seenSecretLatch?)) (messager say: 1 0 39 1 self 400))
					(else (self cue:))
				)
			)
			(3
				(if
					(and
						(not ((ScriptID 30 0) holeIsUp?))
						(not (ego has: 18))
						(not (rLab seenSecretLatch?))
					)
					(curRoom setScript: emptyHandedDeath)
				else
					(self cue:)
				)
			)
			(4
				(if (== (rLab timesInHoleWallRoom?) 0)
					(rLab timesInHoleWallRoom: 1)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance emptyHandedDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego reset: setMotion: PolyPath 160 160 self)
			)
			(1
				(soundFx2 number: 401 setLoop: 1 play: self)
			)
			(2
				(messager say: 1 0 40 1 self 400)
			)
			(3 (= seconds 3))
			(4
				(if (!= (ego heading?) 180) (ego setHeading: 180))
				(= cycles 12)
			)
			(5
				(soundFx2 number: 401 setLoop: 1 play: self)
			)
			(6
				(messager say: 1 0 40 2 self 400)
			)
			(7 (= seconds 3))
			(8
				(ego setHeading: 270)
				(= cycles 12)
			)
			(9
				(soundFx2 number: 401 setLoop: 1 play: self)
			)
			(10
				(messager say: 1 0 40 3 self 400)
			)
			(11
				(messager say: 1 0 40 4 self 400)
			)
			(12
				(soundFx2 number: 401 setLoop: 1 play:)
				(theMusic number: 407 setLoop: 1 play:)
				(mino
					init:
					setCycle: Walk
					setMotion: PolyPath 84 158 self
				)
			)
			(13
				(messager say: 1 0 40 5 self 400)
			)
			(14
				(ego
					view: 413
					setStep: 2 1
					setLoop: 0
					normal: 0
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 160 144 self
				)
				(mino setMotion: PolyPath 160 160 self)
				(soundFx2 number: 401 setLoop: 1 play:)
			)
			(15 (ego setCycle: 0))
			(16
				(soundFx2 stop:)
				(mino setHeading: 0)
				(= cycles 6)
			)
			(17
				(messager say: 1 0 40 6 self 400)
			)
			(18
				(soundFx2 number: 401 setLoop: 1 play:)
				(mino setMotion: PolyPath 159 149 self)
			)
			(19
				(soundFx2 stop:)
				(ego
					setLoop: 2
					cel: 0
					posn: 159 149
					cycleSpeed: 3
					setCycle: CycleTo 2 1 self
				)
				(mino dispose:)
			)
			(20
				(ego setCycle: EndLoop self)
				(theMusic stop:)
				(soundFx2 number: 402 setLoop: 1 play:)
			)
			(21
				(soundFx2 number: 960 setLoop: 1 play:)
				(= cycles 6)
			)
			(22
				(if (cast contains: theHole)
					(EgoDead 28)
				else
					(EgoDead 27)
				)
			)
		)
	)
)

(instance putHoleOnWall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 18 curRoomNum)
				(theGame handsOff:)
				(curRoom style: 100)
				(ego setMotion: PolyPath 242 162 self)
			)
			(1
				(ego
					view: 232
					setLoop: 0
					normal: 0
					posn: 254 162
					setCycle: CycleTo 5 1 self
				)
			)
			(2
				(ego cel: 6)
				(if (not (Btst 114))
					(Bset 114)
					(theGame givePoints: 1)
				)
				(theHole init:)
				(messager say: 16 25 0 0 self 400)
			)
			(3
				(ego
					posn: 242 162
					reset: 0
					setLoop: (ego cel?)
					setMotion: MoveTo 225 162 self
				)
			)
			(4
				(ego setLoop: -1)
				(if (== register 1)
					(ego setScript: holeTimer)
					(theGame handsOn:)
					(self dispose:)
				else
					(client setScript: emptyHandedDeath)
				)
			)
		)
	)
)

(instance getTheHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setScript: 0 setMotion: PolyPath 242 162 self)
			)
			(1
				(ego setHeading: 45)
				(= cycles 6)
			)
			(2
				(ego view: 232 normal: 0 setLoop: 0 posn: 254 162 cel: 6)
				(= cycles 3)
			)
			(3
				(theHole dispose:)
				(ego cycleSpeed: 6 setCycle: BegLoop self)
			)
			(4
				(ego posn: 242 162 reset: 6)
				(= cycles 8)
			)
			(5
				(ego setLoop: (ego cel?) setMotion: MoveTo 225 162 self)
			)
			(6
				(theGame handsOn:)
				(ego setLoop: -1 get: 18)
				((ScriptID 30 0) holeCoords: 0)
				((ScriptID 30 0) holeWall: 0)
				(self dispose:)
			)
		)
	)
)

(instance lookInHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 234 162 self)
			)
			(1
				(ego
					view: 232
					setLoop: 3
					cel: 0
					normal: 0
					posn: 246 163
					setCycle: EndLoop self
				)
			)
			(2
				(messager say: 17 1 47 1 self 400)
			)
			(3
				(messager say: 17 1 47 2 self 400)
			)
			(4
				(theGame givePoints: 1)
				(self setScript: holie self)
			)
			(5 (ego setCycle: BegLoop self))
			(6
				(ego posn: 234 162 reset: 0)
				(= cycles 4)
			)
			(7
				(messager say: 17 1 47 3 self 400)
			)
			(8
				(messager say: 17 1 47 4 self 400)
			)
			(9
				(soundFx2 number: 483 setLoop: 1 play:)
				(theHole
					view: 232
					setLoop: 6
					setPri: 13
					setCycle: Forward
					setMotion: MoveTo 315 93 self
				)
			)
			(10
				(messager say: 17 1 47 5 self 400)
			)
			(11
				(messager say: 17 1 47 6 self 400)
			)
			(12
				(rLab seenSecretLatch: 1)
				(theHole dispose:)
				(theGame handsOn:)
				(curRoom style: 10)
				(self dispose:)
			)
		)
	)
)

(instance holie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 30 3) addToPic:)
				(= seconds 2)
			)
			(1
				(holeInset addToPic:)
				(holeRoom addToPic:)
				(tapestry addToPic:)
				(sDoor init: stopUpd:)
				(minoInset init:)
				(tapeMove init:)
				(= seconds 2)
			)
			(2
				(minoInset setCycle: CycleTo 3 1 self)
				(tapeMove setCycle: CycleTo 3 1)
			)
			(3
				(soundFx2 number: 408 setLoop: 1 play: self)
			)
			(4
				(soundFx2 number: 909 setLoop: 1 play:)
				(sDoor setCycle: EndLoop self)
			)
			(5
				(minoInset setCycle: EndLoop self)
				(tapeMove setCycle: EndLoop)
			)
			(6
				(holeInset dispose:)
				(holeRoom dispose:)
				(tapestry dispose:)
				(sDoor dispose:)
				(minoInset dispose:)
				(tapeMove dispose:)
				(curRoom drawPic: 400 (if global169 15 else 100))
				((ScriptID 30 5) addToPic:)
				((ScriptID 30 9) addToPic:)
				((ScriptID 30 8) addToPic:)
				((ScriptID 30 3) init: show:)
				((ScriptID 30 4) addToPic:)
				(= cycles 6)
			)
			(7 (self dispose:))
		)
	)
)

(instance holeTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 20))
			(1
				(theGame handsOff:)
				(messager say: 1 0 43 1 self 400)
			)
			(2
				(soundFx2 number: 483 setLoop: 1 play:)
				(theHole
					setPri: 13
					setCycle: Forward
					setMotion: MoveTo 315 93 self
				)
			)
			(3
				(messager say: 1 0 43 2 self 400)
			)
			(4
				(ego setMotion: PolyPath 160 160 self)
			)
			(5
				(theHole setCycle: 0 dispose:)
				(emptyHandedDeath start: 2)
				(curRoom setScript: emptyHandedDeath)
			)
		)
	)
)
