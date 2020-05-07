;;; Sierra Script 1.0 - (do not remove this comment)
(script# 341)
(include sci.sh)
(use Main)
(use rSacred)
(use Kq6Procs)
(use Conv)
(use PolyPath)
(use Polygon)
(use Motion)
(use Actor)
(use System)

(public
	proc341_0 0
	proc341_1 1
	proc341_2 2
	proc341_3 3
	genie 4
	poofAwayScript 5
)

(local
	local0
	local1
	local2
)
(procedure (proc341_0)
	(genie init:)
)

(procedure (proc341_1)
	(genie setScript: genieTrap)
)

(procedure (proc341_2)
	(genie dispose:)
)

(procedure (proc341_3)
	(genie addToPic:)
)

(instance myConv of Conversation
	(properties)
)

(instance geniePoly of Polygon
	(properties)
)

(instance eyeGlint of Prop
	(properties
		view 902
		cycleSpeed 15
	)
	
	(method (init)
		(self setPri: 15 setCycle: End self)
		(super init:)
	)
	
	(method (cue)
		(if (== cel 2)
			(self setPri: 15 setCycle: Beg self)
		else
			((genie script?) cue:)
			(self dispose:)
		)
	)
)

(instance genie of Actor
	(properties
		x 260
		y 116
		noun 8
		modNum 340
		yStep 8
		view 334
		signal $6000
		xStep 12
	)
	
	(method (init)
		(curRoom
			addObstacle:
				(geniePoly
					type: 2
					init: 279 120 239 120 234 116 239 113 279 113 283 116
					yourself:
				)
		)
		(rSacred geniePresent: 1)
		(super init:)
	)
	
	(method (dispose)
		(soundFx fade: 0 6 6)
		(eyeGlint dispose:)
		((curRoom obstacles?) delete: geniePoly)
		(geniePoly dispose:)
		(rSacred geniePresent: 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if local0
					(myConv add: 340 8 2 26 1 add: 340 8 2 26 2 init:)
				else
					(= local0 1)
					(myConv add: 340 8 2 25 1 add: 340 8 2 25 2 init:)
				)
			)
			(63
				(ego setScript: 0)
				(theGame handsOff:)
				(curRoom setScript: genieAsMintJunkie)
			)
			(43
				(messager say: noun theVerb 0 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poofAwayScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(genie setScript: 0 setCycle: 0 setMotion: 0)
				(eyeGlint dispose:)
				(= cycles 1)
			)
			(1
				(messager say: 1 0 7 1 self 340)
			)
			(2
				(soundFx2 number: 943 setLoop: 1 play:)
				(genie setLoop: 2 cel: 0 setCycle: End self)
			)
			(3
				(messager say: 1 0 7 2 self 340)
			)
			(4
				(rSacred geniePresent: 0)
				(self dispose:)
				(genie dispose:)
			)
		)
	)
)

(instance genieAsMintJunkie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (> (ego x?) (genie x?))
					(ego setMotion: PolyPath 249 118 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setHeading: 45)
				(= cycles 8)
			)
			(2
				(myConv add: 340 8 63 0 1 add: 340 8 63 0 2 init: self)
			)
			(3
				(genie
					setLoop: 3
					posn: (- (genie x?) 1) (- (genie y?) 25)
					setCycle: End self
				)
			)
			(4
				(genie
					setCycle: 0
					setMotion: MoveTo (+ (ego x?) 12) (- (ego y?) 27) self
				)
			)
			(5 (genie setCycle: Beg self))
			(6
				(genie
					setLoop: 5
					cel: 0
					posn: (+ (ego x?) 13) (- (ego y?) 3)
				)
				(ego
					view: 334
					normal: 0
					setLoop: 6
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(7
				(genie setCycle: End self)
				(ego cel: 3)
			)
			(8
				(myConv add: 340 8 63 0 3 add: 340 8 63 0 4 init: self)
			)
			(9
				(ego reset: 6 put: 23 340)
				(soundFx2 number: 943 setLoop: 1 play:)
				(genie
					setLoop: 2
					cel: 0
					posn: (+ (ego x?) 13) (- (ego y?) 2)
					setCycle: End self
				)
			)
			(10
				(= local1 1)
				(genie dispose:)
				(= cycles 4)
			)
			(11
				(messager say: 8 63 0 5 self 340)
			)
			(12
				(theGame handsOn:)
				(self dispose:)
				(DisposeScript 341)
			)
		)
	)
)

(instance genieTrap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 4)
				(= seconds 2)
				(ego setHeading: 30)
			)
			(1
				(eyeGlint posn: (genie x?) (- (genie y?) 44) init:)
			)
			(2 (= cycles 2))
			(3
				(messager say: 1 0 1 2 0 340)
				(= seconds 1)
			)
			(4
				(genie setLoop: 1 cel: 0 setCycle: End self)
			)
			(5 (genie setCycle: Beg self))
			(6 (genie setCycle: End self))
			(7 (genie setCycle: Beg self))
			(8 (= seconds 2))
			(9
				(messager say: 1 0 1 3 0 340)
				(= seconds 1)
			)
			(10 (= seconds 2))
			(11
				(genie setLoop: 1 cel: 0)
				(= cycles 6)
			)
			(12
				(genie setLoop: 5 cel: 0 setCycle: End self)
				(soundFx2 number: 348 setLoop: 1 play:)
			)
			(13 (genie setCycle: Beg self))
			(14
				(genie setLoop: 0 posn: 260 116)
				(= seconds 2)
			)
			(15
				(eyeGlint posn: (genie x?) (- (genie y?) 44) init:)
			)
			(16 (= cycles 2))
			(17
				(genie setLoop: 3 cel: 0 posn: 259 89 setCycle: End self)
			)
			(18
				(soundFx2 number: 347 setLoop: -1 play:)
				(genie setCycle: Beg self)
			)
			(19
				(genie
					setLoop: 4
					cel: 0
					setCycle: Fwd
					setMotion: MoveTo (- (genie x?) 30) (- (genie y?) 20) self
				)
			)
			(20
				(genie
					setMotion: MoveTo (+ (genie x?) 60) (- (genie y?) 20) self
				)
			)
			(21
				(genie
					setMotion: MoveTo (- (genie x?) 60) (- (genie y?) 20) self
				)
			)
			(22
				(genie
					setMotion: MoveTo (+ (genie x?) 60) (- (genie y?) 20) self
				)
			)
			(23
				(genie
					setMotion: MoveTo (- (genie x?) 60) (+ (genie y?) 20) self
				)
			)
			(24
				(genie
					setMotion: MoveTo (+ (genie x?) 60) (+ (genie y?) 20) self
				)
			)
			(25
				(genie
					setMotion: MoveTo (- (genie x?) 30) (+ (genie y?) 20) self
				)
			)
			(26
				(genie setLoop: 3 cel: 3 posn: 259 89 setCycle: Beg self)
			)
			(27
				(soundFx2 stop:)
				(genie setLoop: 0 posn: 260 116)
				(= seconds 2)
			)
			(28
				(eyeGlint posn: (genie x?) (- (genie y?) 44) init:)
			)
			(29 (= cycles 2))
			(30
				(theGame handsOn:)
				(messager say: 1 0 1 4 0 340)
				(= seconds 1)
			)
			(31
				(eyeGlint posn: (genie x?) (- (genie y?) 44) init:)
			)
			(32 (= seconds 10))
			(33
				(if
				(and (not (curRoom script?)) (not modelessDialog))
					(messager say: 1 0 5 1 0 340)
					(= seconds 1)
				else
					(self cue:)
				)
			)
			(34
				(eyeGlint posn: (genie x?) (- (genie y?) 44) init:)
			)
			(35 (= seconds 10))
			(36
				(if
				(and (not (curRoom script?)) (not modelessDialog))
					(messager say: 1 0 6 1 0 340)
					(= seconds 1)
				else
					(self cue:)
				)
			)
			(37
				(eyeGlint posn: (genie x?) (- (genie y?) 44) init:)
			)
			(38 (= seconds 10))
			(39
				(if
				(and (not (curRoom script?)) (not modelessDialog))
					(messager say: 1 0 7 1 0 340)
					(= seconds 1)
				else
					(self cue:)
				)
			)
			(40
				(theGame handsOff:)
				(if (not (curRoom script?)) (Face ego genie))
				(soundFx2 number: 943 setLoop: 1 play:)
				(genie setLoop: 2 cel: 0 setCycle: End self)
			)
			(41
				(messager say: 1 0 7 2 0 340)
				(= seconds 1)
			)
			(42
				(theGame handsOn:)
				(genie dispose:)
			)
		)
	)
)
